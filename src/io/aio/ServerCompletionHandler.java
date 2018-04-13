package io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,Server> {
    @Override
    public void completed(AsynchronousSocketChannel result, Server attachment) {
        //当有下一个客户端接入的时候 直接调用Server的accept方法，这样反复执行下去，保证多个客户端都可以阻塞
        attachment.asynchronousServerSocketChannel.accept(attachment,this);
        read(result);
    }

    @Override
    public void failed(Throwable exc, Server attachment) {
        exc.printStackTrace();
    }

    private void read(final AsynchronousSocketChannel asynchronousSocketChannel){
        //读取数据
        ByteBuffer buf=ByteBuffer.allocate(1024);
        asynchronousSocketChannel.read(buf, buf, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                //进行读取之后，重置标识位
                attachment.flip();
                //获得读取的字节数
                System.out.println("Server -> "+"收到客户端数据长度为："+result);
                //获得读取的数据
                String resultData=new String(attachment.array()).trim();
                System.out.println("Server -> "+"收到客户端数据信息为"+resultData);
                String response="服务器响应，收到客户端发来的数据："+resultData;
                write(asynchronousSocketChannel,response);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    private void write(AsynchronousSocketChannel asynchronousSocketChannel,String response){
        try {
            ByteBuffer buf=ByteBuffer.allocate(1024);
            buf.put(response.getBytes());
            buf.flip();
            asynchronousSocketChannel.write(buf).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
