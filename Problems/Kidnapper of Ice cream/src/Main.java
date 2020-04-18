// Posted from EduTools plugin
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    private static Map<String, Integer> mapChars(String words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        var map1 = mapChars(line1);
        var map2 = mapChars(line2);
        boolean won = true;
        for (String s : map2.keySet()) {
            if (map1.getOrDefault(s, 0) < map2.get(s)) {
                won = false;
                break;
            }
        }
        System.out.println(won ? "You get money" : "You are busted");
    }
}