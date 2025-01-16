
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("students.txt");
        if (!f.exists()){
            System.out.println("students.txt not found");
            return;
        }
        Scanner studentScanner = new Scanner(f);

        while (studentScanner.hasNext()) {
            String[] studentArgs = studentScanner.nextLine().split(",");
            String lastName = studentArgs[0];
            String firstName = studentArgs[1];
            int age = Integer.parseInt(studentArgs[2]);
            int classroom = Integer.parseInt(studentArgs[3]);
            int bus = Integer.parseInt(studentArgs[4]);
            float gpa = Float.parseFloat(studentArgs[5]);
            String teacherLast = studentArgs[6];
            String teacherFirst = studentArgs[7];
            students.add(new Student(firstName, lastName, age, classroom, bus, gpa, teacherFirst, teacherLast));
        }
        students.sort((u1, u2) -> {
            if (u1.getLastName().equals(u2.getLastName())) {
                return u1.getFirstName().compareTo(u2.getFirstName());
            }
            return u1.getLastName().compareTo(u2.getLastName());
        });

        studentScanner.close();

        printCommands();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] words = input.split(" ");
            switch (words[0]) {
                case "S":
                case "Student":
                    if (words.length < 2) {
                        printCommands();
                        continue;
                    }

                    Student student = getStudent(words[1]);
                    if (student == null) {
                        continue;
                    }

                    if (words.length < 3) {
                        System.out.printf("Student: %s %s - Grade %d - Classroom %d - Teacher %s %s\n",
                                student.getFirstName(),
                                student.getLastName(),
                                student.getGrade(),
                                student.getClassroom(),
                                student.getTeacher().getFirstName(),
                                student.getTeacher().getLastName());
                        continue;
                    }
                    if (words.length == 3 && (words[2].equals("B") || words[2].equals("Bus"))) {
                        System.out.printf("Student: %s %s - Bus Route: %d\n", student.getFirstName(),
                                student.getLastName(), student.getBus());
                        continue;
                    }
                    continue;
                case "T":
                case "Teacher":
                    if (words.length < 2) {
                        printCommands();
                        continue;
                    }
                    students.stream().filter(s -> s.getTeacher().getLastName().equalsIgnoreCase(words[1]))
                            .forEach(s -> System.out.printf("%s %s\n", s.getFirstName(), s.getLastName()));
                    continue;
                case "B":
                case "Bus":
                    if (words.length < 2) {
                        printCommands();
                        continue;
                    }
                    try {
                        int bus = Integer.parseInt(words[1]);
                        students.stream()
                                .filter(s -> s.getBus() == bus)
                                .forEach(s -> System.out.printf("%s %s - Grade: %d - Classroom: %s\n",
                                        s.getFirstName(), s.getLastName(), s.getGrade(), s.getClassroom()));
                    } catch (NumberFormatException e) {
                        printCommands();
                    }
                    continue;
                case "G":
                case "Grade":
                    if (words.length < 2) {
                        printCommands();
                        continue;
                    }
                    try {
                        int grade = Integer.parseInt(words[1]);
                        if (words.length == 2) {
                            students.stream().filter(s -> s.getGrade() == grade)
                                    .forEach(s -> System.out.printf("%s %s\n", s.getFirstName(), s.getLastName()));

                            continue;
                        }
                        if (words.length == 3) {
                            Student queriedStudent = null;
                            if (words[2].equals("H") || words[2].equals("High")) {
                                queriedStudent = students.stream().filter(s -> s.getGrade() == grade)
                                        .max(Comparator.comparingDouble(Student::getGPA))
                                        .orElse(null);
                            }
                            if (words[2].equals("L") || words[2].equals("Low")) {
                                queriedStudent = students.stream().filter(s -> s.getGrade() == grade)
                                        .min(Comparator.comparingDouble(Student::getGPA))
                                        .orElse(null);
                            }
                            if (queriedStudent != null) {
                                System.out.printf("%s %s - GPA: %.2f - Bus Route: %d - Teacher: %s %s\n",
                                        queriedStudent.getFirstName(), queriedStudent.getLastName(),
                                        queriedStudent.getGPA(), queriedStudent.getBus(),
                                        queriedStudent.getTeacher().getFirstName(), queriedStudent.getTeacher().getLastName());
                            }
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        printCommands();
                    }
                    continue;
                case "A":
                case "Average":
                    if (words.length < 2) {
                        printCommands();
                        continue;
                    }

                    try {
                        int grade = Integer.parseInt(words[1]);
                        double average = getAverage(grade);
                        if (Double.isNaN(average)) {
                            continue;
                        }
                        System.out.printf("Average for grade %d is %.2f\n", grade, average);
                    } catch (NumberFormatException e) {
                        printCommands();
                    }
                    continue;
                case "I":
                case "Info":
                    students.stream().mapToInt(Student::getGrade).distinct().sorted().forEach(grade -> {
                        long studentsInGrade = students.stream().filter(s -> s.getGrade() == grade).count();
                        System.out.printf("%d: %d\n", grade, studentsInGrade);
                    });
                    continue;
                case "Q":
                case "Quit":
                    return;
                default:
                    printCommands();
            }
        }
    }

    public static void printCommands() {
        System.out.println("Enter a command:");
        System.out.println("S[tudent] <lastname> [B[us]]");
        System.out.println("B[us] <number>");
        System.out.println("G[rade] <number> [H[igh]|L[ow]]");
        System.out.println("A[verage]: <number>");
        System.out.println("I[nfo]");
        System.out.println("Q[uit]");
    }
    public static double getAverage(int grade) {
        return students.stream().filter(student -> student.getGrade() == grade)
                .mapToDouble(Student::getGPA).average().orElse(Double.NaN);
    }

    public static Student getStudent(String lastName) {
        return students.stream().filter(student -> student.getLastName().equalsIgnoreCase(lastName))
                .findFirst().orElse(null);
    }


}
