// Posted from EduTools plugin
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    private static Map<Character, Integer> mapChars(String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : word.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String word1 = scanner.nextLine().toLowerCase();
        String word2 = scanner.nextLine().toLowerCase();
        var map1 = mapChars(word1);
        var map2 = mapChars(word2);
        System.out.println(map1.equals(map2) ? "yes" : "no");
    }
}
