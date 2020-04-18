// Posted from EduTools plugin
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int d = Integer.valueOf(scanner.nextLine());
        HashSet<String> dictionary = new HashSet<>();
        while (d-- > 0) {
            dictionary.add(scanner.nextLine().toLowerCase());
        }
        HashSet<String> erroneous = new HashSet<>();
        d = Integer.valueOf(scanner.nextLine());
        while (d-- > 0) {
            Set<String> line = new HashSet<>(Arrays.asList(scanner.nextLine().toLowerCase().split(" ")));
            line.removeAll(dictionary);
            erroneous.addAll(line);
        }
        erroneous.forEach(System.out::println);
    }
}