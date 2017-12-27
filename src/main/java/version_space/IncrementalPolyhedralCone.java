package version_space;

import convex.objects.ConvexBody;
import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.EmptyIntersectionException;
import linalg.Matrix;
import linalg.Vector;

import java.util.ArrayList;
import java.util.List;


public class IncrementalPolyhedralCone implements ConvexBody {
    private List<Vector> constrainList;

    public IncrementalPolyhedralCone() {
        constrainList = new ArrayList<>();
    }

    public IncrementalPolyhedralCone(Matrix A) {
        constrainList = new ArrayList<>();
        for(int i = 0; i < A.getNumRows(); i++)
            constrainList.add(A.getRow(i));
    }

    public IncrementalPolyhedralCone(double[][] A){
        this(new Matrix(A));
    }

    @Override
    public int getDim() {
        if(isEmpty())
            return 0;
        return constrainList.get(0).getDim();
    }

    public List<Vector> getConstrainList() {
        return constrainList;
    }

    public int getNumConstrains(){
        return constrainList.size();
    }

    @Override
    public boolean isInside(Vector point) {
        if(!isEmpty())
            checkDim(point);

        for(Vector vector: constrainList){
            if(vector.dot(point) >= 0)
                return false;
        }
        return true;
    }

    @Override
    public LineSegment intersect(Line line) {
        if(!isEmpty())
            checkDim(line);

        double lowerBound = Double.NEGATIVE_INFINITY;
        double upperBound = Double.POSITIVE_INFINITY;

        for(Vector vector : constrainList){
            double numerator = -vector.dot(line.getCenter());
            double denominator = vector.dot(line.getDirection());

            if(denominator > 0)
                upperBound = Math.min(upperBound, numerator/denominator);

            else if(denominator < 0)
                lowerBound = Math.max(lowerBound, numerator/denominator);

            else if(numerator <= 0)
                throw new EmptyIntersectionException();
        }

        if(lowerBound >= upperBound)
            throw new EmptyIntersectionException();

        return new LineSegment(line, lowerBound, upperBound);
    }

    public void addConstrain(Vector vector){
        if(!isEmpty())
            checkDim(vector);
        constrainList.add(vector);
    }

    public boolean isEmpty(){
        return constrainList.isEmpty();
    }
}
