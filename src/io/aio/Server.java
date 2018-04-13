package io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    //线程池
    private ExecutorService executorService;
    //线程组
    private AsynchronousChannelGroup threadGroup;
    //服务器通道
    public AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public Server(int port) {
        try {
            //创建一个线程池
            executorService= Executors.newCachedThreadPool();
            //创建一个线程组
            threadGroup =AsynchronousChannelGroup.withCachedThreadPool(executorService,1);
            //创建服务器通道
            asynchronousServerSocketChannel=AsynchronousServerSocketChannel.open(threadGroup);
            //进行绑定
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));

            System.out.println("Server start , port :"+port);
            //进行阻塞
            asynchronousServerSocketChannel.accept(this,new ServerCompletionHandler());
            //一直阻塞 不让服务器停止
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server=new Server(8765);
    }
}
