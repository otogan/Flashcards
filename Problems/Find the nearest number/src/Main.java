import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> input = new ArrayList<>();
        for (String s : scanner.nextLine().split(" ")) {
            input.add(Integer.parseInt(s));
        }
        int n = scanner.nextInt();
        int minDiff = Integer.MAX_VALUE;
        List<Integer> output = new ArrayList<>();
        for (int x : input) {
            int diff = Math.abs(n - x);
            if (diff <= minDiff) {
                if (diff < minDiff) {
                    minDiff = diff;
                    output.clear();
                }
                output.add(x);
            }
        }
        output.sort(null);
        output.forEach(i -> System.out.print(i + " "));
    }
}