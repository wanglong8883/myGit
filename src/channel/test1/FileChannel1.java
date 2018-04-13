package channel.test1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel1 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile=new RandomAccessFile("data/noi-data.txt","rw");
        FileChannel inChannel=aFile.getChannel();
        //create buffer with capacity of 48 bytes;
        ByteBuffer buf=ByteBuffer.allocate(48);
        //read into buffer
        int bytesRead=inChannel.read(buf);
        while (bytesRead!=-1){
            System.out.println("Read"+bytesRead);
            //make buffer ready for read
            buf.flip();//首先读取数据到Buffer，然后反转Buffer，接着再从Buffer中读取数据
            while (buf.hasRemaining()){
                System.out.println((char)buf.get());
            }
            //make buffer ready for writing
            buf.clear();
            bytesRead=inChannel.read(buf);
        }
        aFile.close();


        RandomAccessFile fromFile=new RandomAccessFile("fromFile.txt","rw");
        FileChannel fromChannel=fromFile.getChannel();

        RandomAccessFile toFile=new RandomAccessFile("toFile","rw");
        FileChannel toChannel=toFile.getChannel();

        long position=0;
        long count=fromChannel.size();
//        toChannel.transferFrom(position, count, fromChannel);

    }
}
