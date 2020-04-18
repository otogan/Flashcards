// Posted from EduTools plugin
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // start coding here
            StringBuilder sb = new StringBuilder();

            int in;
            while ((in = reader.read()) != -1) {
                sb.append((char) in);
            }
            System.out.println(sb.reverse());
        }
    }
}