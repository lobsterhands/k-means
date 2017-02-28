import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lyle on 2/21/2017.
 */
public class KMeans {
    private int kValue;
    private String fileName;
    private String filePath;
    private List<Point> pointList;
    private Cluster[] clusterArray;
    private String outFile = "output.txt";

    private KMeans(String kValue, String fileName) {
        if (Integer.parseInt(kValue) < 2) {
            System.err.println("K value must be at least 2");
            System.exit(1);
        }
        this.kValue = Integer.parseInt(kValue);
        this.pointList = new ArrayList<>();
        this.clusterArray = new Cluster[Integer.parseInt(kValue)];
        this.fileName = fileName;
    }

    private void init() {
        // Create k clusters
        for (int i = 0; i < kValue; i++) {
            Cluster c = new Cluster();
            clusterArray[i] = c;
        }

        // Get the file path of the file by searching for filename
        URL url = ClassLoader.getSystemClassLoader().getResource(fileName);
        if (url != null) {
            this.filePath = url.getFile();
        }

        // Read file passed in as arg[1]
        try {
            Scanner scan = new Scanner(new File(filePath));
            scan.useDelimiter("\n");

            // Parse the file and add points to the list of points with a randomly set cluster association
            while (scan.hasNext()) {
                // Split the line by white space between values; Create new points from values and add to points list
                String[] coordinatesXY = scan.next().split("\\s+");
                int x = Integer.parseInt(coordinatesXY[0]);
                int y = Integer.parseInt(coordinatesXY[1]);
                Point p = new Point(x, y);
                pointList.add(p);

                // Assign each point to a randomly selected cluster to calculate initial centroids from data points
                int randomCluster = (int) Math.floor(Math.random() * kValue);
                clusterArray[randomCluster].addPointToList(p);
            }
            scan.close();
        } catch (FileNotFoundException | NullPointerException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        }
    }

    private Cluster[] getClusterArray() {
        return clusterArray;
    }

    private List<Point> getPointList() {
        return pointList;
    }

    private void writeResultsToFile() {
        try {
            FileWriter fWriter = new FileWriter(outFile);

            for (Cluster c : clusterArray) {
                fWriter.write(c.printPointsInCluster());
            }

            fWriter.close();
            System.out.println("Results written to " + outFile);
        } catch (IOException e) {
            System.err.println("Error writing to file. " + e);
        }
    }

    public static void main(String[] args) {
        // Ensure correct number of arguments are passed
        if (args.length != 2) {
            System.err.println("Usage: java kmeans <kValue number> <filename.txt>");
            System.exit(1);
        }

        KMeans kmeans = new KMeans(args[0], args[1]);
        kmeans.init();

        // Initialize a centroid in each cluster then clear the cluster
        Cluster[] clusters = kmeans.getClusterArray();
        for (Cluster c : clusters) {
            c.calculateInitialCentroid();
        }

        boolean centroidMoved = true;
        while (centroidMoved) {
            for (Cluster c : clusters) {
                c.clearCluster();
            }

            // Assign each point to a cluster by distance to nearest centroid
            for (Point point : kmeans.getPointList()) {
                // Calculate point's distance from first cluster's centroid
                double minDistance = point.calcDistanceToCentroid(clusters[0].getCentroid());
                int clusterNum = clusters[0].getClusterID(); // Track which cluster is associated with minDistance

                // Find which cluster point belongs to (the one with the nearest centroid)
                for (int i = 1; i < clusters.length; i++) {
                    double distance = point.calcDistanceToCentroid(clusters[i].getCentroid());
                    if (distance < minDistance) {
                        minDistance = distance;
                        clusterNum = clusters[i].getClusterID();
                    }
                }
                // Add the point to the cluster having the nearest centroid
                point.setClusterGroupNum(clusterNum);
                clusters[clusterNum - 1].addPointToList(point);
            }

            for (Cluster c : clusters) {
                c.updateCentroid();
            }

            boolean atLeastOneChanged = false;
            for (Cluster c : clusters) {
                if (c.getCentroidDistanceChange() > 0) {
                    atLeastOneChanged = true;
                    break;
                }
            }
            centroidMoved = atLeastOneChanged;
        }

        kmeans.writeResultsToFile();
    }
}
