import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void readFile() {
        String pathToFile = "C:\\Users\\User\\Downloads\\dataset_91065.txt";
        File file = new File(pathToFile);
        try (Scanner scanner = new Scanner(file)) {
            int count = 0;
            while (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                if (n == 0) break;
                if (n % 2 == 0) count++;
            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + pathToFile);
        }
    }

    public static void main(String[] args) {
        readFile();
    }
}