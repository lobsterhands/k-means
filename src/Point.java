public class Point {
    private double x;
    private double y;

    private int clusterGroupNum;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.clusterGroupNum = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getClusterGroupNum() {
        return clusterGroupNum;
    }

    public void setClusterGroupNum(int clusterGroupNum) {
        this.clusterGroupNum = clusterGroupNum;
    }

    public double calcDistanceToCentroid(Point centroid) {
        double xDifference = centroid.getX() - x;
        double yDifference = centroid.getY() - y;
        return Math.sqrt((Math.pow(xDifference, 2) + Math.pow(yDifference, 2)));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("x: %-3s  y: %-3s  cluster: %s", x, y, clusterGroupNum));
        return sb.toString();
    }}