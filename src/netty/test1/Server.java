package netty.test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * 对于ChannelOption.SO_BACKLOG的解释
 * 服务器端TCP内核模块维护有2个队列，我们称之为AB吧
 * 客户端向服务器端connect的时候，会发送带有SYN标志的包(第一个握手)
 * 服务器接收到客户端发来的SYN时，向客户端发送SYN ACK确认(第二次握手)
 * 此时TCP内核模块把客户端连接加入到A队列中，然后服务器接收到客户端发来的ACK时(第三次握手)
 * TCP内核模块把客户端连接从A队列移到B队列，连接完成，应用程序的accept会返回
 * 也就是说accept从B队列中取出完成三次握手的连接
 * A队列和B队列的长度之和是backlog。当A,B队列的长度之和大于backlog时，新连接会被TCP内核拒绝
 * 所以，如果backlog过小，可能会出现accept速度跟不上A,B队列满了，导致新的客户端无法连接
 * 要注意的是:backlog对程序支持的连接数并无影响,backlog影响的知识还没有被accept取出的连接
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        //一个用于处理服务器端接收客户端连接的
        EventLoopGroup pGroup = new NioEventLoopGroup();
        //一个进行网络通讯的(网络读写的)
        EventLoopGroup cGroup = new NioEventLoopGroup();
        //创建一个辅助工具类，用于和服务器通道的一系列配置
        ServerBootstrap b = new ServerBootstrap();
        b.group(pGroup, cGroup)//绑定两个线程组
                .channel(NioServerSocketChannel.class)//制定NIO的模式
                .option(ChannelOption.SO_BACKLOG, 1024)//设置TCP缓冲区
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)//设置发送缓冲的大小
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)//设置接收缓冲的大小
                .option(ChannelOption.SO_KEEPALIVE, true)//保持连接
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new ReadTimeoutHandler(5));
                        //在这里配置具体数据的接收方法处理
                        channel.pipeline().addLast(new ServerHandle());
                    }
                });
        //进行绑定
        ChannelFuture channelFuture = b.bind(8765).sync();
        //等待关闭
        //Thread.sleep(Integer.MAX_VALUE)
        channelFuture.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();

    }
}
