import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final ArrayList<Student> students = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {
        Scanner studentScanner = new Scanner(new File("students.txt"));

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

        Scanner sc = new Scanner(System.in);
        while (true){
            String input = sc.nextLine();
            String[] words = input.split(" ");
            switch (words[0]){
                case "S":
                case "Student":
                    System.out.println("Student");
                    if (words.length < 2){
                        System.out.println("Invalid format S[tudent] <lastname> [B[us]]");
                        continue;
                    }
                    if (words.length < 3){
                        //Student [args]
                        continue;
                    }
                    if (words.length == 3 && (words[2].equals("B") || words[2].equals("Bus"))){
                       //Student  [args]
                        continue;
                    }
                    continue;
                case "T":
                case "Teacher":
                    System.out.println("Teacher");
                    continue;
                case "B":
                case "Bus":
                    System.out.println("Bus");
                    continue;
                case "G":
                case "Grade":
                    System.out.println("Grade");
                    continue;
                case "A":
                case "Average":
                    if (words.length < 2){
                        System.out.println("Invalid format: Average <grade>");
                        continue;
                    }

                    try {
                        int grade = Integer.parseInt(words[1]);
                        double average = getAverage(grade);
                        if (Double.isNaN(average)){
                            System.out.println("No students in grade");
                            continue;
                        }
                        System.out.printf("Average for grade %d is %.2f\n", grade, average);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid format: Average <grade>");
                    }
                    continue;
                case "I":
                case "Info":
                    students.stream().mapToInt(Student::getGrade).distinct().sorted().forEach(grade -> {
                        long studentsInGrade = students.stream().filter(student -> student.getGrade() == grade).count();
                        System.out.printf("%d: %d\n", grade, studentsInGrade);
                    });
                    continue;
                case "Q":
                case "Quit":
                    return;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }

    public static double getAverage(int grade){
        return students.stream().filter(student -> student.getGrade() == grade)
                        .mapToDouble(Student::getGPA).average().orElse(Double.NaN);
    }
}
