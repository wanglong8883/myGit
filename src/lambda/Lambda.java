package lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

public class Lambda {
    @Test
    public void test1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();
    }

    @Test
    public void test2() {
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
        new Thread(() -> System.out.println()).start();
    }

    @Test
    public void test3() {
        List features = Arrays.asList("Lambdas", "Default Method", "StreamTest API", "Date and Time API");
        for (Object feature : features) {
            System.out.println(feature);
        }
    }

    @Test
    public void test4() {
        // Java 8之后：
        List features = Arrays.asList("Lambdas", "Default Method", "StreamTest API", "Date and Time API");
        long start=System.currentTimeMillis();
        features.forEach(n -> System.out.println(n));
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        start=System.currentTimeMillis();
        features.forEach(System.out::println);
        end=System.currentTimeMillis();
        System.out.println(end-start);
        start=System.currentTimeMillis();
        for (Object feature : features) {
            System.out.println(feature);
        }
        end=System.currentTimeMillis();
        System.out.println(end-start);
    }


}
