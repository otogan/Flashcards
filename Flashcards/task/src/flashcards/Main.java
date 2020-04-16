package flashcards;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static String getInput(Set<String> checkList, boolean isCard, int i, Scanner scanner) {
        String input;
        boolean asking = true;
        if (isCard) {
            System.out.println("The card #" + (i + 1) + ":");
        } else {
            System.out.println("The definition of the card #" + (i + 1) + ":");
        }
        do {
            input = scanner.nextLine().toLowerCase();
            asking = checkList.contains(input);
            if (asking) {
                if (isCard) {
                    System.out.println("The card \"" + input + "\" already exists. Try again:");
                } else {
                    System.out.println("The definition \"" + input + "\" already exists. Try again:");
                }
            }
        } while (asking);
        return input;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        int n = scanner.nextInt();
        scanner.skip("\n");

        Map<String, String> termMap = new LinkedHashMap<>();
        Map<String, String> definitionMap = new LinkedHashMap<>();
        String[] terms = new String[n];
        String[] definitions = new String[n];

        for (int i = 0; i < n; i++) {
            String term = getInput(termMap.keySet(), true, i, scanner);
            String definition = getInput(definitionMap.keySet(), false, i, scanner);
            termMap.put(term, definition);
            definitionMap.put(definition, term);
        }
        for (String s : termMap.keySet()) {
            System.out.println("Print the definition of \"" + s + "\":");
            String answer = scanner.nextLine().toLowerCase();
            if (termMap.get(s).equals(answer)) {
                System.out.println("Correct answer.");
            } else {
                System.out.print("Wrong answer. The correct one is \"" + termMap.get(s));
                System.out.println(definitionMap.containsKey(answer) ?
                        "\", you've just written the definition of \"" + definitionMap.get(answer) + "\"."
                        : "\"."
                );
            }

        }
    }
}
