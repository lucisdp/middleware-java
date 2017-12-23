package convex.sampling;

import exceptions.IncompatibleDimensionsException;
import linalg.Vector;
import utils.Sampler;


public class Line {
    private Vector center, direction;

    public Line(Vector center, Vector direction) {
        if (center.getDim() != direction.getDim())
            throw new IncompatibleDimensionsException(center.getDim(), direction.getDim());
        if (direction.equals(0.0))
            throw new IllegalArgumentException("Direction cannot be zero vector.");
        this.center = center;
        this.direction = direction;
    }

    public Vector getCenter() {
        return center;
    }

    public Vector getDirection() {
        return direction;
    }

    public int getDim(){
        return center.getDim();
    }

    public Vector getPoint(double position){
        return center.add(direction.multiply(position));
    }

    public static Line sample(Vector point){
        Vector randomDirection = Sampler.sampleGaussian(point.getDim());
        return new Line(point, randomDirection);
    }
}
