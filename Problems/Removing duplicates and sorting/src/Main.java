// Posted from EduTools plugin
import java.util.Scanner;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        TreeSet<String> set = new TreeSet<>();
        while (n-- > 0) {
            set.add(scanner.nextLine());
        }
        set.forEach(System.out::println);
    }
}