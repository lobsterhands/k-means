import java.util.ArrayList;
import java.util.List;

/**
 * Created by wally on 2/21/17.
 */
public class Cluster {

    private static int clustersTotal = 0;

    private int clusterID;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ClusterID: %s\n", this.clusterID));
        this.pointList.forEach((point) -> {
            sb.append(String.format("x: %s y: %s\n",  point.getX(), point.getY()));
        });
        return sb.toString();
    }

}
