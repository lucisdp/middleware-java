package convex.objects;

import linalg.IVector;
import linalg.OjalgoVector;

class Box extends ConvexBody {
    private IVector low, high;

    public Box(IVector low, IVector high){
        super(low.getDim());
        this.low = low;
        this.high = high;
        validateAttributes();
    }

    public Box(double[] low, double[] high){
        this(new OjalgoVector(low), new OjalgoVector(high));
    }

    public Box(IVector center, double length){
        super(center.getDim());

        if (length <= 0)
            throw new IllegalArgumentException("Length must be positive.");

        low = center.subtract(0.5*length);
        high = center.add(0.5*length);
    }

    public Box(double[] center, double length){
        this(new OjalgoVector(center), length);
    }

    public Box(int dim){
        super(dim);
        low = new OjalgoVector(dim, -1);
        high = new OjalgoVector(dim, 1);
    }

    private void validateAttributes(){
        checkDim(low);
        checkDim(high);

        if(!low.isSmallerThan(high))
            throw new IllegalArgumentException("Low must be strictly smaller than high!");
    }

    public IVector getLow() {
        return low;
    }

    public IVector getHigh() {
        return high;
    }

    @Override
    public boolean isInside(IVector point) {
        checkDim(point);
        return low.isSmallerThan(point) && point.isSmallerThan(high);
    }
}
