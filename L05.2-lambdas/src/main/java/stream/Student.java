package stream;

/**
 *
 */
public class Student {
    String name;
    int age;
    int course;
    double avgMark;

    public Student(String name, int age, int course, double avgMark) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.avgMark = avgMark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", course=" + course +
                ", avgMark=" + avgMark +
                '}';
    }
}
