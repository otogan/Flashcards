package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String importPath = checkArgs(args, "import");
        String exportPath = checkArgs(args, "export");
        Logger.startScanner();
        Flashcards flashcards = new Flashcards();
        if (importPath != null) {
            actionImport(flashcards, importPath);
        }
        String action;
        do {
            Logger.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            action = Logger.scanNextLine().toLowerCase();

            processAction(action, flashcards);

        } while (!action.equals("exit"));
        Logger.println("Bye bye!");
        Logger.closeScanner();
        if (exportPath != null) {
            actionExport(flashcards, exportPath);
        }
    }

    private static String checkArgs(String[] args, String arg) {
        var iterator = Arrays.stream(args).iterator();
        arg = "-" + arg;
        String value = null;
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (Objects.equals(arg, next) && iterator.hasNext()) {
                value = iterator.next();
                break;
            }
        }
        return value;
    }

    private static void processAction(String action, Flashcards flashcards) {
        switch (action) {
            case "add":
                actionAdd(flashcards);
                break;
            case "remove":
                actionRemove(flashcards);
                break;
            case "import":
                actionImport(flashcards);
                break;
            case "export":
                actionExport(flashcards);
                break;
            case "ask":
                actionAsk(flashcards);
                break;
            case "log":
                actionLog();
                break;
            case "hardest card":
                actionHardestCard(flashcards);
                break;
            case "reset stats":
                actionResetStats(flashcards);
                break;
        }
    }

    private static void actionAdd(Flashcards flashcards) {
        Logger.println("The card:");
        String newTerm = Logger.scanNextLine();
        if (flashcards.containsCard(newTerm)) {
            Logger.println("The card \"" + newTerm + "\" already exists.");
            return;
        }
        Logger.println("The definition of the card:");
        String newDefinition = Logger.scanNextLine();
        if (flashcards.addCard(newTerm, newDefinition)) {
            Logger.println("The pair (\"" + newTerm + "\":\"" + newDefinition + "\") has been added.");
        } else {
            Logger.println("The definition \"" + newDefinition + "\" already exists.");
        }
    }

    private static void actionRemove(Flashcards flashcards) {
        Logger.println("The card:");
        String termRemove = Logger.scanNextLine();
        String cardRemoved = flashcards.removeCard(termRemove);
        if (cardRemoved == null) {
            Logger.println("Can't remove \"" + termRemove + "\": there is no such card.");
        } else {
            Logger.println("The card has been removed.");
        }
    }

    private static void actionImport(Flashcards flashcards) {
        Logger.println("File name:");
        String importPath = Logger.scanNextLine();
        if (!actionImport(flashcards, importPath)) {
            Logger.println("File not found.");
        }
    }

    private static boolean actionImport(Flashcards flashcards, String importPath) {
        int nImported = flashcards.importCards(importPath);
        if (nImported > -1) {
            Logger.println(nImported + " cards have been loaded.");
            return true;
        }
        return false;
    }

    private static void actionExport(Flashcards flashcards) {
        Logger.println("File name:");
        String exportPath = Logger.scanNextLine();
        if (!actionExport(flashcards, exportPath)) {
            Logger.println("Failed exporting cards.");
        }
    }

    private static boolean actionExport(Flashcards flashcards, String exportPath) {
        int nExported = flashcards.exportCards(exportPath);
        if (nExported > -1) {
            Logger.println(nExported + " cards have been saved.");
            return true;
        }
        return false;
    }

    private static void actionAsk(Flashcards flashcards) {
        Logger.println("How many times to ask?");
        int nAsk = Integer.parseInt(Logger.scanNextLine());
        while (nAsk-- > 0) {
            var card = flashcards.getRandomCard();
            Logger.println("Print the definition of \"" + card.getTerm() + "\":");
            String answer = Logger.scanNextLine();
            if (card.checkAnswer(answer)) {
                Logger.println("Correct answer");
            } else {
                Logger.print("Wrong answer. (The correct one is \"" + card.getDefinition() + "\"");
                if (flashcards.containsDefinition(answer)) {
                    Logger.print(", you've just written the definition of \"" + flashcards.getTerm(answer) + "\"");
                }
                Logger.println(".)");
            }
        }
    }

    private static void actionLog() {
        Logger.println("File name:");
        String logPath = Logger.scanNextLine();
        Logger.export(logPath);
        Logger.println("The log has been saved.");
    }

    private static void actionHardestCard(Flashcards flashcards) {
        var hardestCards = flashcards.getHardestCards();
        var iterator = hardestCards.iterator();
        if (iterator.hasNext()) {
            var card = iterator.next();
            Logger.print("The hardest card");
            if (iterator.hasNext()) {
                Logger.print("s are \"" + card.getTerm() + "\"");
                while (iterator.hasNext()) {
                    card = iterator.next();
                    Logger.print(", \"" + card.getTerm() + "\"");
                }
                Logger.println(". You have " + card.getMistakes() + " errors answering them.");
            } else {
                Logger.println(" is \"" + card.getTerm() + "\". You have " + card.getMistakes() + " errors answering it.");
            }
        } else {
            Logger.println("There are no cards with errors.");
        }
    }

    private static void actionResetStats(Flashcards flashcards) {
        flashcards.resetStats();
        Logger.println("Card statistics has been reset.");
    }
}

class Flashcards {
    private LinkedHashMap<String, String> cards;
    private LinkedHashMap<String, Integer> mistakes;
    private Random random;

    public Flashcards() {
        cards = new LinkedHashMap<>();
        mistakes = new LinkedHashMap<>();
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
        mistakes.remove(term);
        return cards.remove(term);
    }

    public Card getCard(String term) {
        Card card = null;
        if (containsCard(term)) {
            card = new Card(this, term);
        }
        return card;
    }

    public Card getRandomCard() {
        var terms = new ArrayList<String>(cards.keySet());
        var term = terms.get(random.nextInt(terms.size()));
        return getCard(term);
    }

    public String getTerm(String definition) {
        for (var entry : cards.entrySet()) {
            if (definition.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String getDefinition(String term) {
        return cards.get(term);
    }

    public boolean checkAnswer(String term, String answer) {
        if (!containsCard(term)) { return false; }
        boolean check = getDefinition(term).equals(answer);
        if (!check) {
            int mistake = mistakes.getOrDefault(term, 0) + 1;
            mistakes.put(term,  mistake);
        }
        return check;
    }

    public boolean checkAnswer(Card card, String answer) {
        return checkAnswer(card.getTerm(), answer);
    }

    public int getMistakes(String term) {
        return mistakes.getOrDefault(term, 0);
    }

    public Set<Card> getHardestCards() {
        int highestMistake = 0;
        for (int n : mistakes.values()) {
            highestMistake = Math.max(n, highestMistake);
        }
        Set<Card> cards = new LinkedHashSet<>();
        for (var entry : mistakes.entrySet()) {
            if (highestMistake == entry.getValue()) {
                cards.add(new Card(this, entry.getKey()));
            }
        }
        return cards;
    }

    public void resetStats() {
        mistakes.clear();
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
                if (scanner.hasNextLine()) {
                    int mistake = Integer.parseInt(scanner.nextLine());
                    if (mistake > 0) {
                        mistakes.put(term,mistake);
                    } else {
                        mistakes.remove(term);
                    }
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
                printWriter.println(mistakes.getOrDefault(entry.getKey(), 0));
                count++;
            }
            printWriter.flush();
            return count;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static class Card {
        Flashcards flashcards;
        String term;

        private Card(Flashcards flashcards, String term) {
            this.flashcards = flashcards;
            this.term = term;
        }

        public String getTerm() {
            return term;
        }

        public String getDefinition() {
            return flashcards.getDefinition(term);
        }

        public int getMistakes() {
            return flashcards.getMistakes(term);
        }

        public boolean checkAnswer(String answer) {
            return flashcards.checkAnswer(term, answer);
        }
    }
}

class Logger {
    private static LinkedList<String> logs = new LinkedList<>();
    private static boolean newLine = true;
    private static Scanner scanner;

    private Logger() { }

    private static void append(String message) {
        logs.set(logs.size() - 1, logs.getLast() + message);
    }

    private static void add(String message) {
        logs.add(message);
    }

    public static String log(String message) {
        if (newLine) {
            add(message);
        } else {
            append(message);
            newLine = true;
        }
        return message;
    }

    public static void print(String message) {
        if (newLine) {
            add(message);
            newLine = false;
        } else {
            append(message);
        }
        System.out.print(message);
    }

    public static void println(String message) {
        log(message);
        System.out.println(message);
    }

    public static void startScanner() {
        scanner = new Scanner(System.in);
    }

    public static void closeScanner() {
        if (scanner != null) scanner.close();
        scanner = null;
    }

    public static String scanNextLine() {
        if (scanner == null) return null; //throw new IllegalAccessException("Scanner not started!");
        return log(scanner.nextLine());
    }

    public static void export(String pathToFile) {
        try (PrintWriter printWriter = new PrintWriter(new File(pathToFile))) {
            for (var message : logs) {
                printWriter.println(message);
            }
            printWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
