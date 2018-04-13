package io.nio;

import org.junit.Test;

import java.nio.IntBuffer;

public class TestBuffer {
    @Test
    public void test1() {
        //1 基本操作

        //创建制定长度的缓冲区
        IntBuffer buf=IntBuffer.allocate(10);
        buf.put(13);//position位置:0->1
        buf.put(21);//position位置:1->2
        buf.put(35);//position位置:2->3
        //把位置复位为0,也是position位置:3->0
        buf.flip();
        System.out.println("使用flip复位: "+buf);
        System.out.println("容量为: "+buf.capacity());
        System.out.println("限制为: "+buf.limit());

        System.out.println("获取下标为1的元素: "+buf.get(1));
        System.out.println("get(index)方法,position位置不改变: "+buf);
        buf.put(1,4);
        System.out.println("put(index,change)方法，position位置不改变: "+buf);
        for (int i = 0; i < buf.limit(); i++) {
            //调用get方法会使其缓冲区的位置(position)向后递增一位
            System.out.println(buf.get());
        }
        System.out.println("buf对象遍历之后为: "+buf);
    }

    @Test
    public void test2(){
        //2 wrap方法使用
        //wrap方法会包裹一个数组:一般这种方法不会先初始化缓存对象的长度，因为没有意义，最后还会被wrap所包裹的数组覆盖。
        //并且wrap方法修改缓冲区对象的时候，数组本身也会跟着发生变化
        int[] arr=new int[]{1,2,5};
        IntBuffer buf=IntBuffer.wrap(arr);
        System.out.println(buf);
        IntBuffer buf1=IntBuffer.wrap(arr,0,2);
        //这样使用表示容量为数组arr的长度，但是可操作的元素只有实际进入缓冲区的数据长度
        System.out.println(buf1);
    }

    @Test
    public void test3(){
        IntBuffer buf=IntBuffer.allocate(10);
        int[] arr=new int[]{1,2,5};
        buf.put(arr);
        System.out.println(buf);
        //一种复制方法
        IntBuffer buf2=buf.duplicate();
        System.out.println(buf2);

        //设置buf的位置属性
        //buf.position(0)
        buf.flip();
        System.out.println(buf);
        System.out.println("可读数据为:"+buf.remaining());
        int[] arr2=new int[buf.remaining()];
        //将缓冲区数据放入arr2数组中去
        buf.get(arr2);
        for (int i : arr2) {
            System.out.println(Integer.toString(i));
        }
    }
}
