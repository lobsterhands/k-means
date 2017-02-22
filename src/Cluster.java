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
        return this.pointList.size() == 0;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ClusterID: %s\n", this.clusterID));
        if (this.pointList.isEmpty()) {
            sb.append("This cluster is empty.");
        } else {
            for (Point p : pointList) {
                sb.append(String.format("x: %s y: %s\n",  p.getX(), p.getY()));
            }
            if (centroid != null) {
                sb.append(String.format("Centroid: x: %-3s y: %-3s", centroid.getX(), centroid.getY()));
            }
        }
        return sb.toString();
    }

}
