import java.util.ArrayList;
import java.util.List;

/**
 * Created by wally on 2/21/17.
 */
public class Cluster {

    private static int clustersTotal = 0;
    private int clusterID;
    private Point centroid;
    private List<Point> pointList;

    private double centroidDistanceChange;

    public double getCentroidDistanceChange() {
        return centroidDistanceChange;
    }

    public Cluster() {
        this.clusterID = ++clustersTotal;
        this.pointList = new ArrayList<>();
    }

    public int getClusterID() {
        return clusterID;
    }

    public void addPointToList(Point p) {
        pointList.add(p);
    }

    public void clearCluster() {
        this.pointList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return pointList.size() == 0;
    }

    public int getSize() {
        return pointList.size();
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public void calculateInitialCentroid() {
        double sumX = 0;
        double sumY = 0;
        int nPoints = 0;
        for (Point p : pointList) {
            sumX += p.getX();
            sumY += p.getY();
            nPoints++;
        }
        double avgX = sumX / nPoints;
        double avgY = sumY / nPoints;
        this.centroid = new Point(avgX, avgY);
    }

    public void updateCentroid() {
        Point oldCentroid = centroid;
        double sumX = 0;
        double sumY = 0;
        int nPoints = 0;
        for (Point p : pointList) {
            sumX += p.getX();
            sumY += p.getY();
            nPoints++;
        }
        double avgX = sumX / nPoints;
        double avgY = sumY / nPoints;
        this.centroid = new Point(avgX, avgY);

        centroidDistanceChange = Math.sqrt((Math.pow((avgX - oldCentroid.getX()), 2) + Math.pow(avgY - oldCentroid.getY(), 2)));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ClusterID: %s (Size: %s)\n", clusterID, pointList.size()));
        if (centroid != null){
            sb.append(String.format("Centroid: x: %-3s y: %-3s\n", centroid.getX(), centroid.getY()));
        } else {
            sb.append("Centroid has not been placed\n");
        }
        if (this.pointList.isEmpty()) {
            sb.append("This cluster is empty.");
        } else {
            for (Point p : pointList) {
                sb.append(String.format("x: %s y: %s\n",  p.getX(), p.getY()));
            }
        }
        return sb.toString();
    }

}
