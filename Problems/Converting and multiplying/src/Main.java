// Posted from EduTools plugin
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = 1;
        do {
            String s = scanner.nextLine();
            try {
                n = Integer.parseInt(s);
                if (n != 0) {
                    System.out.println(n * 10);
                }
            } catch (Exception e) {
                System.out.println("Invalid user input: " + s);
            }
        } while (n != 0);
    }
}