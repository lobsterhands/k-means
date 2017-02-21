import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Lyle on 2/21/2017.
 */
public class KMeans {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Usage: java kmeans <k number> <filename.txt>");
            System.exit(1);
        }

        // Initialize k and URL with arguments passed
        int k = Integer.parseInt(args[0]);
        URL url = ClassLoader.getSystemClassLoader().getResource(args[1]);

        try {
            Scanner scan = new Scanner(new File(url.getFile()));
            scan.useDelimiter("\n");

            while (scan.hasNext()) {
                System.out.println(scan.next());
            }
            scan.close();

        } catch (FileNotFoundException e) {
            System.err.println("Exception thrown: " + e);
        }
    }
}
