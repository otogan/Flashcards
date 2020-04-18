import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[4];
        for (int i = 0; i < 4; i++) {
            list[i] = scanner.next();
        }
        Arrays.stream(list).forEach(System.out::println);
    }
}