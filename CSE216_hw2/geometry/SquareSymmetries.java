package geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SquareSymmetries implements Symmetries<Square>{
    public boolean areSymmetric(Square s1, Square s2) {
        List<Point> eq1_vertices= s1.vertices();
        List<Point> eq2_vertices= s2.vertices();
        boolean flag = false;

        for (int i =0; i<eq1_vertices.size(); i++){
            flag = false;
            for(int j = 0; j<eq2_vertices.size(); j++){
                if(pointEquals(eq1_vertices.get(i), eq2_vertices.get(j))){
                    flag = true;
                }
            }
            if(!flag){return false;}
        }
        return true;
    }

    public boolean pointEquals(Point point1, Point point2) {
        if(point1.getX() == point2.getY() && point1.getX() == point2.getY()){
            return true;
        }
        return false;
    }

    public List<Square> symmetriesOf(Square s1) {
        Square t1 = s1.rotateBy(0);
        Square t2 = s1.rotateBy(120);
        Square t3 = s1.rotateBy(240);

        Collection<Square> squares = new ArrayList<Square>();
        squares.add(t1);
        squares.add(t2);
        squares.add(t3);

        return (List<Square>) squares;
    }
}
