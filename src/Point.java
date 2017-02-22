/**
 * Created by wally on 2/21/17.
 */
public class Point {
    private int x;
    private int y;
    private int clusterGroupNum;

    public Point(int x, int y, int clusterGroupNum) {
        this.x = x;
        this.y = y;
        this.clusterGroupNum = clusterGroupNum;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getClusterGroupNum() {
        return clusterGroupNum;
    }

    public double calcDistanceToCentroid(Point centroid) {
        int xDifference = centroid.getX() - x;
        int yDifference = centroid.getY() - y;
        return Math.sqrt((Math.pow(xDifference, 2) + Math.pow(yDifference, 2)));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("x: %-3s  y: %-3s cluster: %s\n", this.x, this.y, clusterGroupNum));
        return sb.toString();
    }}
