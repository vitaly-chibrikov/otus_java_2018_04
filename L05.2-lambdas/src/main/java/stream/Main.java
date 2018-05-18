package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class Main {


    public static void main(String[] args) {

        Student s1 = new Student("Alex", 22, 5, 4.5);
        Student s2 = new Student("Maria", 22, 5, 3.5);
        Student s3 = new Student("John", 12, 4, 4.7);
        Student s4 = new Student("Bob", 22, 5, 4.8);
        List<Student> students = Arrays.asList(s1, s2, s3, s4);

        /*
         * OLD
         */
        List<Student> result = new ArrayList<>();
        // Напечатать имена топ-студентов 5го курса с оценкой больше 4, по убыванию
        for (Student student : students) {
            if (student.getAvgMark() > 4 && student.getCourse() == 5) {
                result.add(student);
            }
        }
        result.sort((o1, o2) -> Double.compare(o2.getAvgMark(), o1.getAvgMark()));

        for (Student student : result) {
            System.out.println(student.getName());
        }


        /*
         * STREAM
         */
        students.stream()
                .filter(student -> student.getAvgMark() > 4 && student.getCourse() == 5)
                .sorted(Comparator.comparingDouble(Student::getAvgMark).reversed())
                .forEach(student -> System.out.println(student.getName()));

    }
}
