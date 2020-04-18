// Posted from EduTools plugin
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        char[] original = scanner.nextLine().toCharArray();
        char[] cipher = scanner.nextLine().toCharArray();
        Map<Character, Character> encoder = new LinkedHashMap<>();
        Map<Character, Character> decoder = new LinkedHashMap<>();
        for (int i = 0; i < original.length; i++) {
            encoder.put(original[i], cipher[i]);
            decoder.put(cipher[i], original[i]);
        }
        char[] line1 = scanner.nextLine().toCharArray();
        StringBuilder result = new StringBuilder();
        for (char c : line1) {
            result.append(encoder.get(c));
        }
        System.out.println(result);
        char[] line2 = scanner.nextLine().toCharArray();
        result = new StringBuilder();
        for (char c : line2) {
            result.append(decoder.get(c));
        }
        System.out.println(result);
    }
}