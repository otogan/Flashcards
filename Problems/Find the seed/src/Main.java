// Posted from EduTools plugin
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int minSeed = scanner.nextInt();
        int maxSeed = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int selectedSeed = maxSeed;
        int minMax = k;
        for (int i = minSeed; i <= maxSeed; i++) {
            Random random = new Random(i);
            int max = 0;
            for (int j = 0; j < n; j++) {
                int next = random.nextInt(k);
                max = Math.max(max, next);
            }
            if (max < minMax || max == minMax && i < selectedSeed) {
                selectedSeed = i;
                minMax = max;
            }
        }
        System.out.println(selectedSeed);
        System.out.println(minMax);
    }
}