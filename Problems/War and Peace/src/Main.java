// Posted from EduTools plugin
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().toLowerCase().split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (String s : map.keySet()) {
            System.out.println(s + " " + map.get(s));
        }
    }
}