// Posted from EduTools plugin
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        int n = scanner.nextInt();
        int i = n;
        while (i-- > 0) {
            arr.add(scanner.nextInt());
        }
        arr.sort(null);
        System.out.println(arr.get(n - 1) * arr.get(n - 2));
    }
}
