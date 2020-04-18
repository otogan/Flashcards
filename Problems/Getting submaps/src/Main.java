// Posted from EduTools plugin
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int min = Integer.parseInt(input[0]);
        int max = Integer.parseInt(input[1]);
        int n = Integer.parseInt(scanner.nextLine());
        Map<Integer, String> map = new TreeMap<>();
        while (n-- > 0) {
            input = scanner.nextLine().split(" ");
            int key = Integer.parseInt(input[0]);
            if (min <= key && key <= max) {
                map.put(key, input[1]);
            }
        }
        map.forEach((k, v) -> System.out.println(k + " " + v));
    }
}