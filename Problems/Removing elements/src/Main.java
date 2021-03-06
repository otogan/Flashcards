// Posted from EduTools plugin
import java.util.*;

class SetUtils {

    public static Set<Integer> getSetFromString(String str) {
        // write your code here
        TreeSet<Integer> set = new TreeSet<>();
        for (String s : str.split(" ")) {
            set.add(Integer.valueOf(s));
        }
        return set.headSet(11);
    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        // write your code here

    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.nextLine();
        Set<Integer> set = SetUtils.getSetFromString(numbers);
        SetUtils.removeAllNumbersGreaterThan10(set);
        set.forEach(e -> System.out.print(e + " "));
    }
}