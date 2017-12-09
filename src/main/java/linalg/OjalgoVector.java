package linalg;

import org.ojalgo.matrix.PrimitiveMatrix;
import org.ojalgo.type.context.NumberContext;


public class OjalgoVector implements IVector {
    PrimitiveMatrix ojalgoVector;

    public OjalgoVector(double[] values){
        ojalgoVector = PrimitiveMatrix.FACTORY.columns(values);
    }
    public OjalgoVector(int dim, double value) { ojalgoVector = PrimitiveMatrix.FACTORY.makeZero(dim, 1).add(value); }
    OjalgoVector(PrimitiveMatrix values){ ojalgoVector = values; }

    public int getDim(){
        return (int) ojalgoVector.countRows();
    }

    OjalgoVector checkInput(IVector ivec){
        checkDim(ivec);
        if (!(ivec instanceof OjalgoVector))
            throw new IllegalArgumentException("Expected OjalgoVector class.");
        return (OjalgoVector) ivec;
    }

    public double get(int index){
        return ojalgoVector.get(index);
    }

    public IVector add(double val){
        return new OjalgoVector(ojalgoVector.add(val));
    }
    public IVector add(IVector vec){ return new OjalgoVector(ojalgoVector.add(checkInput(vec).ojalgoVector)); }

    public IVector subtract(double val){
        return new OjalgoVector(ojalgoVector.add(-val));
    }
    public IVector subtract(IVector vec){ return new OjalgoVector(ojalgoVector.subtract(checkInput(vec).ojalgoVector)); }

    public IVector multiply(double val){
        return new OjalgoVector(ojalgoVector.multiply(val));
    }

    public IVector divide(double val){
        return new OjalgoVector(ojalgoVector.divide(val));
    }
    public IVector divide(IVector vec){ return new OjalgoVector(ojalgoVector.divideElements(checkInput(vec).ojalgoVector)); }

    public double dot(IVector vec){ return this.ojalgoVector.dot(checkInput(vec).ojalgoVector); }

    public double norm(){
        return ojalgoVector.norm();
    }

    public boolean equals(IVector vec){ return ojalgoVector.equals(checkInput(vec).ojalgoVector, new NumberContext(10,10)); }

    public IVector copy(){
        return new OjalgoVector(this.ojalgoVector.toRawCopy1D());
    }

    @Override
    public IVector makeVector(double[] values) {
        return new OjalgoVector(values);
    }

    public IVector makeVector(OjalgoVector vec) {
        return vec.copy();
    }

    public double[] toArray(){
        double[] res = new double[getDim()];
        for (int i=0; i < getDim(); i++)
            res[i] = this.get(i);
        return res;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        for (int i=0; i < getDim(); i++) {
            builder.append(this.get(i));
            builder.append(',');
            builder.append(' ');
        }
        builder.deleteCharAt(builder.length()-1);
        builder.deleteCharAt(builder.length()-1);
        builder.append('}');
        return builder.toString();
    }
}
