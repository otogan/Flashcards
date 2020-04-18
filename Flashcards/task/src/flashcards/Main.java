package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Flashcards flashcards = new Flashcards();
        String action;
        do {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            action = scanner.nextLine().toLowerCase();

            processAction(action, scanner, flashcards);

        } while (!action.equals("exit"));
        System.out.println("Bye bye!");
    }

    private static void processAction(String action, Scanner scanner, Flashcards flashcards) {
        switch (action) {
            case "add":
                System.out.println("The card:");
                String newTerm = scanner.nextLine();
                if (flashcards.containsCard(newTerm)) {
                    System.out.println("The card \"" + newTerm + "\" already exists.");
                    break;
                }
                System.out.println("The definition of the card:");
                String newDefinition = scanner.nextLine();
                if (flashcards.addCard(newTerm, newDefinition)) {
                    System.out.println("The pair (\"" + newTerm + "\":\"" + newDefinition + "\") has been added.");
                } else {
                    System.out.println("The definition \"" + newDefinition + "\" already exists.");
                }
                break;
            case "remove":
                System.out.println("The card:");
                String termRemove = scanner.nextLine();
                String cardRemoved = flashcards.removeCard(termRemove);
                if (cardRemoved == null) {
                    System.out.println("Can't remove \"" + termRemove + "\": there is no such card.");
                } else {
                    System.out.println("The card has been removed.");
                }
                break;
            case "import":
                System.out.println("File name:");
                String importPath = scanner.nextLine();
                int nImported = flashcards.importCards(importPath);
                if (nImported == -1) {
                    System.out.println("File not found.");
                } else {
                    System.out.println(nImported + " cards have been loaded.");
                }
                break;
            case "export":
                System.out.println("File name:");
                String exportPath = scanner.nextLine();
                int nExported = flashcards.exportCards(exportPath);
                System.out.println(nExported + " cards have been saved.");
                break;
            case "ask":
                System.out.println("How many times to ask?");
                int nAsk = Integer.parseInt(scanner.nextLine());
                while (nAsk-- > 0) {
                    var card = flashcards.getRandomCard();
                    System.out.println("Print the definition of \"" + card.getKey() + "\":");
                    String answer = scanner.nextLine();
                    if (answer.equals(card.getValue())) {
                        System.out.println("Correct answer");
                    } else {
                        System.out.print("Wrong answer. The correct one is \"" + card.getValue() + "\"");
                        if (flashcards.containsDefinition(answer)) {
                            System.out.println(", you've just written the definition of \"" + flashcards.getTerm(answer) + "\".");
                        } else {
                            System.out.println(".");
                        }
                    }
                }
        }
    }
}

class Flashcards {
    private LinkedHashMap<String, String> cards;
    private Random random;

    public Flashcards() {
        cards = new LinkedHashMap<>();
        random = new Random();
    }

    public boolean addCard(String term, String definition) {
        boolean exists = containsCard(term) || containsDefinition(definition);
        if (!exists) {
            updateCard(term, definition);
        }
        return !exists;
    }

    public void updateCard(String term, String definition) {
        cards.put(term, definition);
    }

    public boolean containsCard(String term) {
        return cards.containsKey(term);
    }

    public boolean containsDefinition(String definition) {
        return cards.containsValue(definition);
    }

    public String removeCard(String term) {
        return cards.remove(term);
    }

    public Map.Entry<String, String> getRandomCard() {
        List<Map.Entry<String, String>> entries = new ArrayList(cards.entrySet());
        return entries.get(random.nextInt(entries.size()));
    }

    public String getTerm(String definition) {
        for (var entry : cards.entrySet()) {
            if (definition.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public int importCards(String pathToFile) {
        try (Scanner scanner = new Scanner(new File(pathToFile))) {
            int count = 0;
            while (scanner.hasNextLine()) {
                String term = scanner.nextLine();
                if (scanner.hasNextLine()) {
                    String definition = scanner.nextLine();
                    updateCard(term, definition);
                    count++;
                }
            }
            return count;
        } catch (FileNotFoundException e) {
            return -1;
        }
    }

    public int exportCards(String pathToFile) {
        try (PrintWriter printWriter = new PrintWriter(new File(pathToFile))) {
            int count = 0;
            for (var entry : cards.entrySet()) {
                printWriter.println(entry.getKey());
                printWriter.println(entry.getValue());
                count++;
            }
            printWriter.flush();
            return count;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
}