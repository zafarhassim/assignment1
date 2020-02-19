import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Artist {

    public String name;
    public Artist next;

    public Artist(String string) {
            name = string;
    }

    public String displayLink() {
            return name;
        }
 }

