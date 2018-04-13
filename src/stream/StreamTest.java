package stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        //第一种 通过Stream接口的of静态方法创建一个流
        Stream<String> stream = Stream.of("hello", "world", "helloworld");

        //第二种 通过Arrays类的Stream方法，实际上第一种of方法底层也是调用Arrays.stream(values)
        String[] array = new String[]{"hello", "world", "helloworld"};
        Stream<String> stream3 = Arrays.stream(array);

        //第三种 通过集合的stream方法，该方法是Collection接口的默认方法，所有集合都继承了该方法
        Stream<String> stream2=Arrays.asList("hello","world","helloworld").stream();

        List<String> list=Arrays.asList("hello","world","helloworld");
        List<String> collect=list.stream().map(s->s.toUpperCase()).collect(Collectors.toList());
        collect.forEach(System.out::println);

        Stream<List<Integer>> listStream=Stream.of(Arrays.asList(1),Arrays.asList(2,3),Arrays.asList(4,5,6));
        List<Integer> collect1=listStream.flatMap(theList->theList.stream()).map(integer -> integer*integer).collect(Collectors.toList());

        List<Integer> list1=Arrays.asList(1,3,5,7,9);
        IntSummaryStatistics statistics=list1.stream().filter(integer -> integer>2).mapToInt(i->i*2).skip(2).limit(2).summaryStatistics();
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getAverage());

    }
}
