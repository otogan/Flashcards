package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String term  = scanner.nextLine();
        String definition = scanner.nextLine();
        String answer = scanner.nextLine();
        System.out.println(definition.equalsIgnoreCase(answer) ?
                "Your answer is right!"
                : "Your answer is wrong...");
    }
}
