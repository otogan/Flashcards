// Posted from EduTools plugin
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int k = scanner.nextInt() - 1;
        int n = scanner.nextInt();
        double m = scanner.nextDouble();
        boolean searching;
        do {
            Random random = new Random(++k);
            searching = false;
            for (int i = 0; i < n; i++) {
                if (random.nextGaussian() > m) {
                    searching = true;
                }
            }
        } while (searching);
        System.out.println(k);
    }
}