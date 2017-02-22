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
    private Cluster[] clusterArray;

    private KMeans(String k, String fileName) {
        this.k = Integer.parseInt(k);
        URL url = ClassLoader.getSystemClassLoader().getResource(fileName);
        this.pointList = new ArrayList<>();
        this.clusterArray = new Cluster[this.k + 1];

        // Create k clusters
        for (int i = 0; i < this.k; i++) {
            Cluster c = new Cluster();
            clusterArray[i] = c;
        }

        if (url != null) {
            // Read file passed in
            try {
                Scanner scan = new Scanner(new File(url.getFile()));
                scan.useDelimiter("\n");

                // Parse the file and add points to the list of points with a randomly set cluster association
                while (scan.hasNext()) {
                    // Split the line by white space between values
                    String[] coordinates = scan.next().split("\\s+");
                    int x = Integer.parseInt(coordinates[0]);
                    int y = Integer.parseInt(coordinates[1]);
                    int randomVal = (int)Math.floor(Math.random() * this.k);
                    Point p = new Point(x, y, randomVal + 1);
                    this.addPointToList(p);
                    clusterArray[randomVal].addPointToList(p);
                }
                scan.close();
            } catch (FileNotFoundException | NullPointerException e) {
                System.err.println("File not found.");
                e.printStackTrace();
            }
        } else {
            System.err.println("Filename could not generate path url");
        }
    }
    private String getFileString() {
        return fileURL.getFile();
    }

    private int getK() {
        return k;
    }

    private Cluster[] getClusterArray() {
        return clusterArray;
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

        List<Point> kPointList = kmeans.getPointList();
        kPointList.forEach((point)-> {
            System.out.println("x: " + point.getX() + " y: " + point.getY() + " cluster: " + point.getClusterGroupNum());
        });

        Cluster[] clusters = kmeans.getClusterArray();
        System.out.println(clusters.length);
        for (Cluster c : clusters) {
            System.out.println("" + c);
        }

    }
}
