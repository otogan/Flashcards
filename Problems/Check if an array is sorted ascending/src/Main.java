// Posted from EduTools plugin
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int pre = Integer.MIN_VALUE;
        int next = pre;
        boolean sorted = true;
        while (n-- > 0) {
            next = scanner.nextInt();
            if (next < pre) {
                sorted = false;
            }
            pre = next;
        }
        System.out.println(sorted);
    }
}
