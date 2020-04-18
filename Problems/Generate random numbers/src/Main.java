// Posted from EduTools plugin
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        Random random = new Random(a + b);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += random.nextInt(Math.abs(b - a) + 1) + Math.min(a, b);
        }
        System.out.println(sum);
    }
}