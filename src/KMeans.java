import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * Created by Lyle on 2/21/2017.
 */
public class KMeans {
    private int k;
    private String filePath;
    private List<Point> pointList;
    private int max_x = 0; // max values used to give max range for centroid placement
    private int max_y = 0;
    private Cluster[] clusterArray;

    private KMeans(String k, String fileName) {
        this.k = Integer.parseInt(k);
        this.pointList = new ArrayList<>();
        this.clusterArray = new Cluster[this.k];
        // Create k clusters
        for (int i = 0; i < this.k; i++) {
            Cluster c = new Cluster();
            clusterArray[i] = c;
        }

        // Get the file path of the file by searching for filename
        URL url = ClassLoader.getSystemClassLoader().getResource(fileName);
        if (url != null) {
            this.filePath = url.getFile();
        } else {
            System.err.println("The file <" + fileName + "> could not be found.");
            System.exit(1);
        }

        // Read file passed in as arg[1]
        try {
            Scanner scan = new Scanner(new File(url.getFile()));
            scan.useDelimiter("\n");

            // Parse the file and add points to the list of points with a randomly set cluster association
            // TODO: keep randomly set cluster association? keep attrib on kmeans?
            while (scan.hasNext()) {
                // Split the line by white space between values
                String[] coordinatesXY = scan.next().split("\\s+");

                int x = Integer.parseInt(coordinatesXY[0]);
                if (x > max_x) {
                    max_x = x;
                }
                int y = Integer.parseInt(coordinatesXY[1]);
                if (y > max_y) {
                    max_y = y;
                }

                Point p = new Point(x, y);
                p.setClusterGroupNum((int) Math.floor(Math.random() * this.k + 1));
                addPointToList(p);
                clusterArray[p.getClusterGroupNum() - 1].addPointToList(p);
            }
            scan.close();
        } catch (FileNotFoundException | NullPointerException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        }
    }

    private String getFilePath() {
        return filePath;
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

    public int getMax_x() {
        return max_x;
    }

    public int getMax_y() {
        return max_y;
    }

    public static void main(String[] args) {
        // Ensure correct number of arguments are passed
        if (args.length != 2) {
            System.err.println("Usage: java kmeans <k number> <filename.txt>");
            System.exit(1);
        }

        KMeans kmeans = new KMeans(args[0], args[1]);
        Cluster[] clusters = kmeans.getClusterArray();
        for (Cluster c : clusters) {
            System.out.println(c);
            c.calculateInitialCentroid();
            System.out.println(c.getCentroid());
        }

//        List<Point> kPointList = kmeans.getPointList();
//
        // Randomly place an initial centroid for each cluster
//        for (int i = 0; i < clusters.length; i++) {
//            Point newCentroid;
//            boolean isUnique;
//            // Ensure random centroids are not placed at identical coordinates
//            do {
//                isUnique = true;
//                int randomX = (int) Math.floor(Math.random() * kmeans.getMax_x());
//                int randomY = (int) Math.floor(Math.random() * kmeans.getMax_y());
//                newCentroid = new Point(randomX, randomY);
//                if (i > 0) {
//                   for (int j = 0; j < i; j++) {
//                        if (newCentroid.getX() == clusters[j].getCentroid().getX() &&
//                                newCentroid.getY() == clusters[j].getCentroid().getY()) {
//                            isUnique = false;
//                            break;
//                        }
//                    }
//                }
//            } while (!isUnique);
//            clusters[i].setCentroid(newCentroid); // new centroid is unique, so assign it to a cluster
//        }
//
//
//        // Assign each point to a cluster by distance to nearest centroid
//        for (Point point : kPointList) {
//            System.out.println(point);
//            // Set minDistance to distance from first cluster's centroid
//            double minDistance = point.calcDistanceToCentroid(clusters[0].getCentroid());
//            int clusterNum = clusters[0].getClusterID(); // Track which cluster the point belongs to
//
//
//            System.out.println("clusternum: " + clusterNum);
//            point.setClusterGroupNum(clusterNum);
//
//            for (int i = 1; i < clusters.length; i++) {
//                double distance = point.calcDistanceToCentroid(clusters[i].getCentroid());
//                System.out.println("min dist: " + minDistance);
//                System.out.println("comp dist: " + distance);
//                if (distance < minDistance) {
//                    System.out.println("Minimum updated.");
//                    minDistance = distance;
//                    clusterNum = clusters[i].getClusterID();
//                    System.out.println("clusternum: " + clusterNum);
//                } else {
//                    System.out.println("Existing minimum stays.");
//                }
//            }
//            point.setClusterGroupNum(clusterNum);
//            clusters[clusterNum - 1].addPointToList(point);
//            System.out.println(point);
//            System.out.println();
//        }
//
//        for (Cluster c : clusters) {
//            System.out.println(c);
//            System.out.println();
//        }
//
//
//        for (Cluster c : clusters) {
//            System.out.println(c.getCentroid());
//        }
    }
}
