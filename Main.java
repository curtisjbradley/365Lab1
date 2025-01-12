import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            String input = sc.next();
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
                    System.out.println("Average");
                    continue;
                case "I":
                case "Info":
                    System.out.println("Info");
                    continue;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }
}
