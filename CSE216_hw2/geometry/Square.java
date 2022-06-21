package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The class impementing squares.
 * Note: you can add more methods if you want, but additional methods must be <code>private</code> or <code>protected</code>.
 *
 * @author {Aneesh Surasani}
 */
public class Square implements Shape {
    
    /**
     * The constructor accepts an array of <code>Point</code>s to form the vertices of the square. If more than four
     * points are provided, only the first four are considered by this constructor. If less than four points are
     * provided, or if the points do not form a valid square, the constructor throws <code>java.lang.IllegalArgumentException</code>.
     *
     * @param vertices the array of vertices (i.e., <code>Point</code> instances) provided to the constructor.
     */
    private List<Point> square_points;
    public Square(Point... vertices) {
        List<Point> squares = Arrays.asList(vertices);

        if(squares.size() < 4){
            throw new IllegalArgumentException();
        }

        square_points = new ArrayList<>();

        this.square_points.add(squares.get(0));
        this.square_points.add(squares.get(1));
        this.square_points.add(squares.get(2));
        this.square_points.add(squares.get(3));

        if(!(isMember(this.square_points))){throw new IllegalArgumentException();}

        if(!(isMember(square_points))){throw new IllegalArgumentException();}


        // TODO
    }
    
    /**
     * Checks if the series of <code>Point</code> instances form a valid square if the first four form the vertices of
     * the square. This method considers the points in a counterclockwise manner starting with the vertex with the least
     * x-value. If multiple vertices have the same x-value, the counterclockwise ordering starts at the vertex with the
     * least y-value amongst them.
     *
     * @param vertices the list of specified vertices.
     * @return <code>true</code> if the first four vertices in the argument form a valid square, and <code>false</code>
     * otherwise.
     */
    @Override
    public boolean isMember(List<Point> vertices) {
        Point middle = find_middle();
        CounterClockwise sort = new CounterClockwise(middle);
        Collections.sort(vertices,sort);

        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);
        Point p4 = vertices.get(3);

        double edge1 = Math.sqrt((p2.getY()-p1.getY())*(p2.getY()-p1.getY())+(p2.getX()-p1.getX())*(p2.getX()-p1.getX()));
        double edge2 = Math.sqrt((p3.getY()-p2.getY())*(p3.getY()-p2.getY())+(p3.getX()-p2.getX())*(p3.getX()-p2.getX()));
        double edge3 = Math.sqrt((p4.getY()-p3.getY())*(p4.getY()-p3.getY())+(p4.getX()-p3.getX())*(p4.getX()-p3.getX()));
        double edge4 = Math.sqrt((p1.getY()-p4.getY())*(p1.getY()-p4.getY())+(p1.getX()-p4.getX())*(p1.getX()-p4.getX()));
        if((Math.round(edge1) == Math.round(edge2)) && (Math.round(edge2)== Math.round(edge3)&& (Math.round(edge3)== Math.round(edge4)))){return true;}
        else{return false;}


    }
    
    @Override
    public int numberOfSides() {
        return 4;
    }
    
    @Override
    public List<Point> vertices() {
        return square_points; // TODO
    }
    
    @Override
    public Square rotateBy(int degrees) {
        double vertexX1 = square_points.get(0).getX();
        double vertexY1 = square_points.get(0).getY();

        double vertexX2 = square_points.get(1).getX();
        double vertexY2 = square_points.get(1).getY();

        double vertexX3 = square_points.get(2).getX();
        double vertexY3 = square_points.get(2).getY();

        double vertexX4 = square_points.get(3).getX();
        double vertexY4 = square_points.get(3).getY();

        double x_center = (vertexX1 + vertexX2 + vertexX3 + vertexX4)/4;
        double y_center = (vertexY1 + vertexY2 + vertexY3 + vertexY4)/4;

        vertexX1 = vertexX1 - x_center;
        vertexX2 = vertexX2 - x_center;
        vertexX3 = vertexX3 - x_center;
        vertexX4 = vertexX4 - x_center;

        vertexY1 = vertexY1 - y_center;
        vertexY2 = vertexY2 - y_center;
        vertexY3 = vertexY3 - y_center;
        vertexY4 = vertexY4 - y_center;

        vertexX1 = (vertexX1*Math.cos(Math.toRadians(degrees))) - (vertexY1*Math.sin(Math.toRadians(degrees)));
        vertexY1 = (vertexX1*Math.sin(Math.toRadians(degrees))) + (vertexY1*Math.cos(Math.toRadians(degrees)));

        vertexX2 = (vertexX2*Math.cos(Math.toRadians(degrees))) - (vertexY2*Math.sin(Math.toRadians(degrees)));
        vertexY2 = (vertexX2*Math.sin(Math.toRadians(degrees))) + (vertexY2*Math.cos(Math.toRadians(degrees)));

        vertexX3 = (vertexX3*Math.cos(Math.toRadians(degrees))) - (vertexY3*Math.sin(Math.toRadians(degrees)));
        vertexY3 = (vertexX3*Math.sin(Math.toRadians(degrees))) + (vertexY3*Math.cos(Math.toRadians(degrees)));

        vertexX4 = (vertexX4*Math.cos(Math.toRadians(degrees))) - (vertexY4*Math.sin(Math.toRadians(degrees)));
        vertexY4 = (vertexX4*Math.sin(Math.toRadians(degrees))) + (vertexY4*Math.cos(Math.toRadians(degrees)));

        vertexX1 = vertexX1 + x_center;
        vertexX2 = vertexX2 + x_center;
        vertexX3 = vertexX3 + x_center;
        vertexX4 = vertexY4 + x_center;

        vertexY1 = vertexY1 + y_center;
        vertexY2 = vertexY2 + y_center;
        vertexY3 = vertexY3 + y_center;
        vertexY4 = vertexY4 + x_center;

        Point point1 = new Point(vertexX1, vertexY1);
        Point point2 = new Point(vertexX2, vertexY2);
        Point point3 = new Point(vertexX3, vertexY3);
        Point point4 = new Point(vertexX4, vertexY4);

        Square newSquare = new Square(point1, point2, point3, point4);
        return newSquare;

    }
    
    @Override
    public String toString() {
        return null; // TODO
    }

    public Point find_middle(){
        double vertexX1 = square_points.get(0).getX();
        double vertexY1 = square_points.get(0).getY();

        double vertexX2 = square_points.get(1).getX();
        double vertexY2 = square_points.get(1).getY();

        double vertexX3 = square_points.get(2).getX();
        double vertexY3 = square_points.get(2).getY();

        double vertexX4 = square_points.get(3).getX();
        double vertexY4 = square_points.get(3).getY();

        double x_center = (vertexX1 + vertexX2 + vertexX3 + vertexX4)/4;
        double y_center = (vertexY1 + vertexY2 + vertexY3 + vertexY4)/4;

        Point center = new Point(x_center, y_center);
        return center;
    }
}
