import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public boolean areSiblings(File f1, File f2) {
        // implement me
        return f1.getParent().equals(f2.getParent());
    }
}