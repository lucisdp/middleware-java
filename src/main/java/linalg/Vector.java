package linalg;

import org.ojalgo.matrix.PrimitiveMatrix;
import org.ojalgo.type.context.NumberContext;


public class Vector {
    PrimitiveMatrix ojalgoVector;

    public Vector(double[] values){
        ojalgoVector = PrimitiveMatrix.FACTORY.columns(values);
    }
    public Vector(long dim, double value) { ojalgoVector = PrimitiveMatrix.FACTORY.makeZero(dim, 1).add(value); }

    Vector(PrimitiveMatrix values){
        ojalgoVector = values;
    }

    public long getDim(){
        return ojalgoVector.countRows();
    }

    private void checkDim(Vector vec){
        if (getDim() != vec.getDim())
            throw new IllegalArgumentException("Wrong input dimension.");

    }

    public double get(long index){
        return ojalgoVector.get(index);
    }

    public Vector add(double val){
        return new Vector(ojalgoVector.add(val));
    }

    public Vector add(Vector vec){
        checkDim(vec);
        return new Vector(ojalgoVector.add(vec.ojalgoVector));
    }

    public Vector subtract(double val){
        return new Vector(ojalgoVector.add(-val));
    }

    public Vector subtract(Vector vec){
        checkDim(vec);
        return new Vector(ojalgoVector.subtract(vec.ojalgoVector));
    }

    public Vector multiply(double val){
        return new Vector(ojalgoVector.multiply(val));
    }

    public Vector divide(double val){
        if (Double.isNaN(val) || Double.isInfinite(val) || val == 0)
            throw new ArithmeticException("Bad value in divide! Check if it is Zero, NaN or Infinite.");

        return new Vector(ojalgoVector.divide(val));
    }

    public Vector divide(Vector vec){
        checkDim(vec);
        
        for (int i=0; i < getDim(); i++){
            double val = get(i);
            if (Double.isNaN(val) || Double.isInfinite(val) || val == 0)
                throw new ArithmeticException("Bad value in divide! Check if it is Zero, NaN or Infinite.");
        }

        return new Vector(ojalgoVector.divideElements(vec.ojalgoVector));
    }

    public double dot(Vector vec){
        checkDim(vec);
        return this.ojalgoVector.dot(vec.ojalgoVector);
    }

    public double norm(){
        return ojalgoVector.norm();
    }

    public boolean equals(Vector vec){
        // return true if Frobenius norm of difference is smaller than 1e-10
        return ojalgoVector.equals(vec.ojalgoVector, new NumberContext(10,10));
    }

    public boolean isSmallerThan(Vector vec){
        checkDim(vec);
        for (int i=0; i < getDim(); i++){
            if (this.get(i) >= vec.get(i))
                return false;
        }
        return true;
    }

    public boolean isLargerThan(double val){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) <= val)
                return false;
        }
        return true;
    }

    public boolean isSmallerOrEqualThan(Vector vec){
        checkDim(vec);
        for (int i=0; i < getDim(); i++){
            if (this.get(i) > vec.get(i))
                return false;
        }
        return true;
    }

    public boolean isSmallerOrEqualThan(double val){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) > val)
                return false;
        }
        return true;
    }

    public double[] toArray(){
        double[] res = new double[(int) getDim()];
        for (int i=0; i < getDim(); i++)
            res[i] = get(i);
        return res;
    }

    @Override
    public String toString() {
        return ojalgoVector.toString();
    }


}
