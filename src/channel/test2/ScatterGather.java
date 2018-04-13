package channel.test2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ScatterGather {
    public static void main(String[] args) throws IOException {
        ByteBuffer header = ByteBuffer.allocate(48);
        ByteBuffer body = ByteBuffer.allocate(1024);
        ByteBuffer[] bufferArray = {header, body};
        SocketChannel socketChannel = SocketChannel.open();
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
        while (true){
            int readyChannels =selector.select();
            if(readyChannels==0)
                continue;
            Set selectKeys=selector.selectedKeys();
            Iterator keyIterator=selectKeys.iterator();
            while (keyIterator.hasNext()){
//                SelectionKey key=keyIterator.next();
//                if(key.isAcceptable()){
//                    // a connection has accepted by a ServerSocketChannel.
//                }else if(key1.isConnectable()){
//                    // a connection was established with a remote server.
//                }else if(key1.isReadable()){
//                    // a channel is ready for reading
//                }else if(key1.isWritable()){
//                    // a channel is ready for writing
//                }
                keyIterator.remove();
            }
        }

//        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
//        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
//        while(true){
//            SocketChannel socketChannel2=serverSocketChannel.accept();
//        }
    }
}
