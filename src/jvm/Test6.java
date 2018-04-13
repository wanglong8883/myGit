package jvm;

import java.util.HashMap;
import java.util.Map;

public class Test6 {
    public static void main(String[] args) {
        //-Xmx30M -Xms30M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000
        //这种现象原因为:虚拟机对于体积不大的对象 会有先把数据分配到TLAB区域中,因此就失去了老年代分配的机会
        //-Xmx30M -Xms30M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000 -XX:-UseTLAB
        Map<Integer,byte[]> m=new HashMap<>();
        for (int i = 0; i < 5*1024; i++) {
            byte[] b=new byte[1024];
            m.put(i,b);
        }
    }
}
