// Posted from EduTools plugin
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n;
        int max = Integer.MIN_VALUE;
        do {
            n = scanner.nextInt();
            if (n > max) {
                max = n;
            }
        } while (n != 0);
        System.out.println(max);
    }
}