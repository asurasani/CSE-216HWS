package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TriangleSymmetries implements Symmetries<EqTriangle> {
    @Override
    public boolean areSymmetric(EqTriangle s1, EqTriangle s2) {
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

    @Override
    public Collection<EqTriangle> symmetriesOf(EqTriangle eqTriangle) {
        //6 symmetries of a triangle (rotation)(reflection on bisect)
        //for reflection hardcode switch of points
        EqTriangle t1 = eqTriangle.rotateBy(0);
        EqTriangle t2 = eqTriangle.rotateBy(120);
        EqTriangle t3 =eqTriangle.rotateBy(240);

        Collection<EqTriangle> triangles = new ArrayList<EqTriangle>();
        triangles.add(t1);
        triangles.add(t2);
        triangles.add(t3);

        return triangles;
    }
}
