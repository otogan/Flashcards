// Posted from EduTools plugin
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

class Main {
    public static void main(String[] args) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // start coding here
            int count = 0;
            int n;
            boolean space = true;
            while ((n = reader.read()) != -1) {
                if (' ' == (char) n) {
                    space = true;
                } else {
                    if (space) {
                        space = false;
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

}