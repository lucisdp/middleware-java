package convex.objects;

import linalg.Vector;

class Box extends ConvexBody {
    private Vector low, high;

    public Box(Vector low, Vector high){
        super(low.getDim());
        this.low = low;
        this.high = high;
        validateAttributes();
    }

    public Box(double[] low, double[] high){
        this(new Vector(low), new Vector(high));
    }

    public Box(Vector center, double length){
        super(center.getDim());

        if (length <= 0)
            throw new IllegalArgumentException("Length must be positive.");

        low = center.subtract(0.5*length);
        high = center.add(0.5*length);
    }

    public Box(double[] center, double length){
        this(new Vector(center), length);
    }

    public Box(int dim){
        super(dim);
        low = new Vector(dim, -1);
        high = new Vector(dim, 1);
    }

    private void validateAttributes(){
        checkDim(low);
        checkDim(high);

        if(!low.isSmallerThan(high))
            throw new IllegalArgumentException("Low must be strictly smaller than high!");
    }

    public Vector getLow() {
        return low;
    }

    public Vector getHigh() {
        return high;
    }

    @Override
    public boolean isInside(Vector point) {
        return low.isSmallerThan(point) && point.isSmallerThan(high);
    }
}
