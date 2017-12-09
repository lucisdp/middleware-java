package linalg;

import org.ojalgo.matrix.PrimitiveMatrix;
import org.ojalgo.type.context.NumberContext;


public class OjalgoVector extends SimpleVector{
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

    public OjalgoVector add(double val){
        return new OjalgoVector(ojalgoVector.add(val));
    }
    public OjalgoVector add(IVector vec){ return new OjalgoVector(ojalgoVector.add(checkInput(vec).ojalgoVector)); }

    public OjalgoVector subtract(double val){
        return new OjalgoVector(ojalgoVector.add(-val));
    }
    public OjalgoVector subtract(IVector vec){ return new OjalgoVector(ojalgoVector.subtract(checkInput(vec).ojalgoVector)); }

    public OjalgoVector multiply(double val){
        return new OjalgoVector(ojalgoVector.multiply(val));
    }

    public OjalgoVector divide(double val){
        return new OjalgoVector(ojalgoVector.divide(val));
    }
    public OjalgoVector divide(IVector vec){ return new OjalgoVector(ojalgoVector.divideElements(checkInput(vec).ojalgoVector)); }

    public double dot(IVector vec){ return this.ojalgoVector.dot(checkInput(vec).ojalgoVector); }

    public double norm(){
        return ojalgoVector.norm();
    }

    public boolean equals(IVector vec){ return ojalgoVector.equals(checkInput(vec).ojalgoVector, new NumberContext(10,10)); }
}
