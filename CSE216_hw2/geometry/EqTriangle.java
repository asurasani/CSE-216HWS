package geometry;

import java.awt.geom.Point2D;
import java.util.*;


/**
 * The class implementing equilateral triangles, i.e., triangles in which all three sides have the same length.
 * Note: you can add more methods if you want, but additional methods must be <code>private</code> or <code>protected</code>.
 *
 * @author {Aneesh Surasani}
 */
public class EqTriangle implements Shape {

    /**
     * The constructor accepts an array of <code>Point</code>s to form the vertices of the equilateral triangle. If more
     * than three points are provided, only the first three are considered by this constructor. If less than three
     * points are provided, or if the points do not form a valid equilateral triangle, the constructor throws
     * <code>java.lang.IllegalArgumentException</code>.
     *
     */

    /**
     * The constructor accepts an array of <code>Point</code>s to form the vertices of the equilateral triangle. If more
     * than three points are provided, only the first three are considered by this constructor. If less than three
     * points are provided, or if the points do not form a valid equilateral triangle, the constructor throws
     * <code>java.lang.IllegalArgumentException</code>.
     *
     * @param vertices the array of vertices (i.e., <code>Point</code> instances) provided to the constructor.
     */
    private List<Point> vertices;
    public EqTriangle(Point... vertices) {
        List<Point> list = Arrays.asList(vertices);
        if(list.size() < 3){
            throw new IllegalArgumentException();
        }
        this.vertices = new ArrayList<Point>();
        this.vertices.add(list.get(0));
        this.vertices.add(list.get(1));
        this.vertices.add(list.get(2));

        boolean flig = isMember(this.vertices);
        if(!(flig)){
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Checks if the series of <code>Point</code> instances form a valid equilateral triangle if first three form the
     * vertices of the triangle.
     *
     * @param vertices the list of specified vertices.
     * @return <code>true</code> if the first three vertices in the argument form a valid equilateral triangle, and
     * <code>false</code> otherwise.
     */
    @Override
    public boolean isMember(List<Point> vertices) {
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);
        double edge1 = Math.sqrt((p2.getY()-p1.getY())*(p2.getY()-p1.getY())+(p2.getX()-p1.getX())*(p2.getX()-p1.getX()));
        double edge2 = Math.sqrt((p3.getY()-p2.getY())*(p3.getY()-p2.getY())+(p3.getX()-p2.getX())*(p3.getX()-p2.getX()));
        double edge3 = Math.sqrt((p3.getY()-p1.getY())*(p3.getY()-p1.getY())+(p3.getX()-p1.getX())*(p3.getX()-p1.getX()));
        if((Math.round(edge1) == Math.round(edge2)) && (Math.round(edge1)== Math.round(edge3))){return true;}
       else{return false;} // TODO
    }
    
    @Override
    public int numberOfSides() {
        return 3;
    }
    
    @Override
    public List<Point> vertices() {
        return vertices; // TODO
    }
    
    @Override
    public EqTriangle rotateBy(int degrees) {
        double vertexX1 = vertices.get(0).getX();
        double vertexY1 = vertices.get(0).getY();

        double vertexX2 = vertices.get(1).getX();
        double vertexY2 = vertices.get(1).getY();

        double vertexX3 = vertices.get(2).getX();
        double vertexY3 = vertices.get(2).getY();

        double x_center = ((vertexX1 + vertexX2 + vertexX3)/3.0);
        double y_center = (vertexY1 + vertexY2 + vertexY3)/3.0;

        vertexX1 = vertexX1 - x_center;
        vertexX2 = vertexX2 - x_center;
        vertexX3 = vertexX3 - x_center;

        vertexY1 = vertexY1 - y_center;
        vertexY2 = vertexY2 - y_center;
        vertexY3 = vertexY3 - y_center;

        vertexX1 = (vertexX1*Math.cos(Math.toRadians(degrees))) - (vertexY1*Math.sin(Math.toRadians(degrees)));
        vertexY1 = (vertexX1*Math.sin(Math.toRadians(degrees))) + (vertexY1*Math.cos(Math.toRadians(degrees)));

        vertexX2 = (vertexX2*Math.cos(Math.toRadians(degrees))) - (vertexY2*Math.sin(Math.toRadians(degrees)));
        vertexY2 = (vertexX2*Math.sin(Math.toRadians(degrees))) + (vertexY2*Math.cos(Math.toRadians(degrees)));

        vertexX3 = (vertexX3*Math.cos(Math.toRadians(degrees))) - (vertexY3*Math.sin(Math.toRadians(degrees)));
        vertexY3 = (vertexX3*Math.sin(Math.toRadians(degrees))) + (vertexY3*Math.cos(Math.toRadians(degrees)));

        vertexX1 = vertexX1 + x_center;
        vertexX2 = vertexX2 + x_center;
        vertexX3 = vertexX3 + x_center;

        vertexY1 = vertexY1 + y_center;
        vertexY2 = vertexY2 + y_center;
        vertexY3 = vertexY3 + y_center;

        Point point1 = new Point(vertexX1, vertexY1);
        Point point2 = new Point(vertexX2, vertexY2);
        Point point3 = new Point(vertexX3, vertexY3);
        EqTriangle newTriangle = new EqTriangle(point1,point2,point3);


        return newTriangle; // TODO
    }
    public String toString(){
        return "Vertex1: "+vertices.get(0).getX()+", "+vertices.get(0).getY() +" Vertex2: "+ vertices.get(1).getX()+", "+vertices.get(1).getY()+" Vertex3: "+vertices.get(2).getX()+", "+vertices.get(2).getY();
    }
}
