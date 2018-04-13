/**
 * 面试题，写一个固定容量的同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者和10个消费者线程的阻塞调用
 * <p>
 * 使用wait和notify/notifyAll来实现
 */
package thread.test1;

import java.util.LinkedList;

public class MyContainer<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while (lists.size() == MAX) {//想想为什么用while而不是if
            try {
                this.wait();//effective java
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        this.notifyAll();//通知消费者线程进行消费
    }

    public synchronized T get(){
        T t=null;
        while(lists.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t=lists.removeFirst();
        count--;
        this.notifyAll();//通知生产者线程
        return t;
    }
}
