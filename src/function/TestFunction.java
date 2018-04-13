package function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TestFunction {
    public int compute(int i, Function<Integer, Integer> function) {
        Integer result = function.apply(i);
        return result;
    }

    @Test
    public void test1() {
        TestFunction testFunction = new TestFunction();
        int result2 = testFunction.compute(5, num -> num + 2);

        System.out.println(result2);
        int result3 = testFunction.compute(5, num -> num + 2);
        System.out.println(result3);
        int result4 = testFunction.compute(5, num -> num + 2);
        System.out.println(result4);
        int result5 = testFunction.compute(5, num -> num * num);
        System.out.println(result5);


        int result8 = testFunction.compute(5, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 2;
            }
        });

    }

    @Test
    public void test2() {
        Predicate<String> predicate = s -> s.length() > 5;
        System.out.println(predicate.test("hello"));
    }

    @Test
    public void test3() {
        Supplier<String> supplier = String::new;
        System.out.println(supplier.get());
    }

    public List<Integer> conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    @Test
    public void test4() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        TestFunction testFunction = new TestFunction();
        List<Integer> result = testFunction.conditionFilter(list, integer -> integer > 5);
        result.forEach(System.out::println);
    }

    @Test
    public void test5() {
        List<String> list = Arrays.asList("hello", "world", "helloworld");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() > 5) {
                System.out.println(list.get(i).toUpperCase());
            }
        }
    }

    @Test
    public void test6() {
        List<String> list = Arrays.asList("hello", "world", "helloworld");
        list.stream().filter(s -> s.length() > 5).map(s -> s.toUpperCase()).forEach(System.out::println);
    }

    @Test
    public void test7() {
        List<String> list = Arrays.asList("hello", "world", "helloworld");
        list.stream().filter(s -> s.length() > 5).map(String::toUpperCase).forEach(System.out::println);
    }

    public int compute1(int i, Function<Integer, Integer> after, Function<Integer, Integer> before) {
        return after.compose(before).apply(i);
    }

    public int compute2(int i, Function<Integer, Integer> before, Function<Integer, Integer> after) {
        return before.andThen(after).apply(i);
    }

    @Test
    public void test8() {
        TestFunction testFunction = new TestFunction();
        System.out.println(testFunction.compute1(5, i -> i * 2, i -> i * i));
        System.out.println(testFunction.compute2(5, i -> i * 2, i -> i * i));

//        System.out.println(testFunction.compute1(5, new Function<Integer, Integer>() {
//            @Override
//            public Integer apply(Integer integer) {
//                return integer * 2;
//            }
//        }, new Function<Integer, Integer>() {
//            @Override
//            public Integer apply(Integer integer) {
//                return integer*integer;
//            }
//        }));

    }

    @Test
    public void test9() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        TestFunction testFunction = new TestFunction();
        List<Integer> result = testFunction.conditionFilter(list, integer -> integer > 5);
        result.forEach(System.out::println);
        result.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

    }

    public List<Integer> conditionFilterNegate(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream().filter(predicate.negate()).collect(Collectors.toList());
    }

    public List<Integer> conditionFilterAnd(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate2) {
        return list.stream().filter(predicate.and(predicate2)).collect(Collectors.toList());
    }

    public List<Integer> conditionFilterOr(List<Integer> list,Predicate<Integer> predicate,Predicate<Integer> predicate2){
        return list.stream().filter(predicate.or(predicate2)).collect(Collectors.toList());
    }
    @Test
    public void test10(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        TestFunction testFunction = new TestFunction();
        List<Integer> result =testFunction.conditionFilterAnd(list,integer -> integer>5,integer1->integer1%2==0);
        result.forEach(System.out::println);
        result =testFunction.conditionFilterOr(list,integer -> integer>5,integer1-> integer1%2==0);
        result.forEach(System.out::println);
        result =testFunction.conditionFilterNegate(list,integer -> integer>5);
        result.forEach(System.out::println);
    }
    @Test
    public void test11(){
        String str=null;
        Optional<String> optional=Optional.ofNullable(str);
        optional.ifPresent(s-> System.out.println(s));
    }
}
