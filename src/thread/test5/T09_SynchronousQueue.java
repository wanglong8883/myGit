package thread.test5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T09_SynchronousQueue {//容量为0
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs=new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        strs.put("aaa");
//        strs.add("aaa");
        System.out.println(strs.size());
    }
}
