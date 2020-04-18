// Posted from EduTools plugin
import java.util.*;

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
        int count = 0;
        Set<Character> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        for (char c : keys) {
            int v1 = map1.getOrDefault(c, 0);
            int v2 = map2.getOrDefault(c, 0);
            if (v1 != v2) {
                count += Math.abs(v1 - v2);
            }
        }
        System.out.println(count);
    }
}