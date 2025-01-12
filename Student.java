public class Student extends Person {
    private final int grade;
    private final int classroom;
    private final int bus;
    private final float GPA;
    private final Person teacher;

    public Student(String firstName, String lastName, int grade, int classroom, int bus,
                   float GPA, String teacherFirstName, String teacherLastName) {
        super(firstName, lastName);
        this.grade = grade;
        this.classroom = classroom;
        this.bus = bus;
        this.GPA = GPA;
        this.teacher = new Person(teacherFirstName, teacherLastName);
    }

    public int getGrade() {
        return grade;
    }

    public int getClassroom() {
        return classroom;
    }

    public int getBus() {
        return bus;
    }

    public float getGPA() {
        return GPA;
    }

    public Person getTeacher() {
        return teacher;
    }

    @Override
    public String toString() {
        return "Student{" + "firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", grade=" + grade +
                ", classroom=" + classroom +
                ", bus=" + bus +
                ", GPA=" + GPA +
                ", teacher=" + teacher +
                '}';
    }
}
