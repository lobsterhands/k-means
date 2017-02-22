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
}
