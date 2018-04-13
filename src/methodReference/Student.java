package methodReference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class Student {
    private String name;
    private int score;
    public Student(){

    }
    public Student(String name,int score){
        this.name=name;
        this.score=score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static int compareStudentByScore(Student student1,Student student2){
        return student1.getScore()-student2.getScore();
    }

    public int compareByScore(Student student){
        return this.getScore()-student.getScore();
    }

    public static void main(String[] args) {
        Student s1=new Student("zhangsan",60);
        Student s2=new Student("lisi",70);
        Student s3=new Student("wangwu",80);
        Student s4=new Student("zhaoliu",90);
        List<Student> students= Arrays.asList(s1,s2,s3,s4);
//        students.sort((o1,o2)->o1.getScore()-o2.getScore());
//        students.sort(new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return o1.getScore()-o2.getScore();
//            }
//        });
        //1.类名::静态方法
//        students.sort(Student::compareStudentByScore);
//        students.forEach(student -> System.out.println(student.getScore()));

        //2.对象:实例方法
        StudentComparator studentComparator=new StudentComparator();
        students.sort(studentComparator::compareStudentByScore);
        students.forEach(student -> System.out.println(student.getScore()));

        //3.类名::实例方法
        students.sort(Student::compareByScore);
        students.forEach(student -> System.out.println(student.getScore()));

        //4.类名::new
        Supplier<Student> supplier=Student::new;

    }
}
