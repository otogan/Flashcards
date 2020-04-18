// Posted from EduTools plugin
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        int a = scanner.nextInt();
        while (a-- > 0) {
            arr.add(scanner.nextInt());
        }
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        boolean con = false;
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) == n && arr.get(i + 1) == m
            || arr.get(i) == m && arr.get(i + 1) == n) {
                con = true;
            }
        }
        System.out.println(con);
    }
}