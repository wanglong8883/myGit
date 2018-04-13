package interface8;

public interface JDK8Interface {
    //static 修饰符定义静态方法
    static void staticMethod(){
        System.out.println("接口中的静态方法");
    }

    //default 修饰符定义默认方法
    default void defaultMethod(){
        System.out.println("接口中的默认方法");
    }
}
