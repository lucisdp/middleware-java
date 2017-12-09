package linalg;

import org.ojalgo.matrix.PrimitiveMatrix;

abstract class VectorFactory {
    abstract IVector makeVector(double[] vec);
    abstract IVector makeVector(IVector vec);
}

class OjalgoFactory extends VectorFactory {
    @Override
    IVector makeVector(double[] vec) {
        return new OjalgoVector(PrimitiveMatrix.FACTORY.columns(vec));
    }

    IVector makeVector(OjalgoVector vec){
        return vec.copy();
    }

    @Override
    IVector makeVector(IVector vec) {
        return new OjalgoVector(vec.toArray());
    }
}

/*
* Factory Class: responsible for creating new vectors
* Client: instantiate a factory based on the desired backend vector library (Ojalgo, simple vector, ...)
* Client should only use the factory to create new vectors
* Use a Singleton Vector Factory class which we can set the backend vector subclass
* */