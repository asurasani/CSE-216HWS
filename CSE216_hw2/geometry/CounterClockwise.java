package geometry;

import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class CounterClockwise implements Comparator<Point> {
    private List<Point> points;
    private Point middle;
    public CounterClockwise(Point middle){}

    @Override
    public int compare(Point o1, Point o2) {
        double a1 = (Math.toDegrees(Math.atan2(o1.getX() - middle.getX(), o1.getY() - middle.getY())) + 360) % 360;
        double a2 = (Math.toDegrees(Math.atan2(o2.getX() - middle.getX(), o2.getY() - middle.getY())) + 360) % 360;
        return (int) (a2 - a1);
    }
}

