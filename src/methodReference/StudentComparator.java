package methodReference;

import java.util.Arrays;
import java.util.List;

public class StudentComparator {
    public int compareStudentByScore(Student student1,Student student2){
        return student1.getScore()-student2.getScore();
    }

}
