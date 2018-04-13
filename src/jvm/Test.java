package jvm;

public class Test {
    public static void main(String[] args) {

        //查看GC信息
        System.out.println("查看GC信息");
        System.out.println("max memory:"+Runtime.getRuntime().maxMemory());
        System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
        System.out.println("total memory:"+Runtime.getRuntime().totalMemory());

        byte[] b1=new byte[1*1024*1024];
        System.out.println("分配了1M");
        System.out.println("max memory:"+Runtime.getRuntime().maxMemory());
        System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
        System.out.println("total memory:"+Runtime.getRuntime().totalMemory());

        byte[] b2=new byte[4*1024*1024];
        System.out.println("分配了4M");
        System.out.println("max memory:"+Runtime.getRuntime().maxMemory());
        System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
        System.out.println("total memory:"+Runtime.getRuntime().totalMemory());

        int a=0x00000000fa0a0000;
        int b=0x00000000fa801000;
        System.out.println("结果为"+(b-a)/1024);

    }
}
