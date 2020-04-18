// Posted from EduTools plugin
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int current = Integer.MIN_VALUE;
        int sequence = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int next = scanner.nextInt();
            if (next >= current) {
                sequence++;
            } else {
                sequence = 1;
            }
            if (max < sequence) {
                max = sequence;
            }
            current = next;
        }
        System.out.println(max);
    }
}
