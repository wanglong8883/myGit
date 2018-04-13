package interface8;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //static方法必须通过接口类调用
        JDK8Interface.staticMethod();

        //default方法必须通过实现类的对象调用
        new JDK8InterfaceImpl().defaultMethod();

    }
}
