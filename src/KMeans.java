import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * Created by Lyle on 2/21/2017.
 */
public class KMeans {
    private int k;
    private URL fileURL;
    private List<Point> pointList;
    private List clusterList;

    private KMeans(String k, String fileName) {
        this.k = Integer.parseInt(k);
        this.fileURL = ClassLoader.getSystemClassLoader().getResource(fileName);
        this.pointList = new ArrayList<>();
        this.clusterList = new ArrayList();
    }

    private String getFileString() {
        return fileURL.getFile();
    }

    private int getK() {
        return k;
    }

    private List getClusterList() {
        return clusterList;
    }

    private void addPointToList(Point p) {
        pointList.add(p);
    }

    private List<Point> getPointList() {
        return pointList;
    }


    public static void main(String[] args) {

        // Ensure correct number of arguments are passed
        if (args.length != 2) {
            System.err.println("Usage: java kmeans <k number> <filename.txt>");
            System.exit(1);
        }

        KMeans kmeans = new KMeans(args[0], args[1]);

        // Read file passed in via arguments
        try {
            Scanner scan = new Scanner(new File(kmeans.getFileString()));
            scan.useDelimiter("\n");

            // Parse the file and add points to the list of points
            while (scan.hasNext()) {
                // Split the line by white space between values
                String[] coordinates = scan.next().split("\\s+");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                Point p = new Point(x, y);
                kmeans.addPointToList(p);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println("Exception thrown: " + e);
        }

        List<Point> kPointList = kmeans.getPointList();
        kPointList.forEach((point)-> {
            System.out.println("x: " + point.getX() + " y: " + point.getY());
        });

    }
}
