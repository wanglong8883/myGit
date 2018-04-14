package netty.test2;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ByteBuf buf=Unpooled.copiedBuffer("$_".getBytes());
                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        ChannelFuture channelFuture=b.connect("127.0.0.1",8765).sync();
        channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("aa$_".getBytes()));
        channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("bbbb$_".getBytes()));
        channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("cccccccc$_".getBytes()));

        //等待客户端端口关闭
        channelFuture.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
