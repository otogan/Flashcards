// Posted from EduTools plugin
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String previous = scanner.next();
        String next;
        boolean sorted = true;
        while (scanner.hasNext()) {
            next = scanner.next();
            if (next.compareTo(previous) < 0) {
                sorted = false;
            }
            previous = next;
        }
        System.out.println(sorted);
    }
}
