package jvm;

import java.util.HashMap;
import java.util.Map;

public class Test5 {
    public static void main(String[] args) {
        //初始的对象在eden区
        //-Xms64m -Xmx64m -XX:+PrintGCDetails
//        for (int i = 0; i < 5; i++) {
//            byte[] b=new byte[1024*1024];
//        }



        //测试进入老年代的对象
        //-Xms1024m -Xmx1024m -XX:+UseSerialGC -XX:MaxTenuringThreshold=15 -XX:+PrintGCDetails
        //-XX:+PrintHeapAtGC
        Map<Integer,byte[]> m=new HashMap<>();
        for (int i = 0; i < 5; i++) {
            byte[] b=new byte[1024*1024];
            m.put(i,b);
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 300; j++) {
                byte[] b=new byte[1024*1024];
            }
        }
    }
}
