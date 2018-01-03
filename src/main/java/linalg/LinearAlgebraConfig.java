package linalg;

import exceptions.LinearAlgebraLibraryNotFound;
import linalg.libraries.apache.ApacheMatrixFactory;
import linalg.libraries.apache.ApacheVectorFactory;
import linalg.libraries.ojalgo.OjalgoMatrixFactory;
import linalg.libraries.ojalgo.OjalgoVectorFactory;
import linalg.libraries.simple.SimpleMatrixFactory;
import linalg.libraries.simple.SimpleVectorFactory;

/**
 * The LinearAlgebraConfig is a single entry point for all operations related to getting or setting of which Linear Algebra
 * library to use as the backend of classes Vector and Matrix. Remember to use setLibrary() to one library of your choice
 * before running any Vector/Matrix code, or you will obtain a NullPointerException.
 *
 * Through this class, you can also get the Vector and Matrix factories for instantiating your objects. This way, we can
 * ensure that compatible libraries will always be used for both Vector and Matrix objects. However, note that if the
 * LinearAlgebraLibrary is changed more than once, any object created BEFORE the change cannot communicate with objects created
 * AFTER the change, since they use different libraries under the hood.
 *
 * @author lucianodp
 */
public class LinearAlgebraConfig {
    private static LinearAlgebraLibrary library = null;
    private static VectorFactory vectorFactory = null;
    private static MatrixFactory matrixFactory = null;

    /**
     * Get the current library
     * @return LinearAlgebraLibrary enum
     */
    public static LinearAlgebraLibrary getLibrary(){
        return library;
    }

    /**
     * Set a new library of choice.
     * @param library: LinearAlgebraLibrary enum of choice
     * @throws NullPointerException if library is null
     * @throws LinearAlgebraLibraryNotFound if library was unrecognized (should never happen).
     */
    public static void setLibrary(LinearAlgebraLibrary library){
        if(library == null)
            throw new NullPointerException("Library cannot be null.");

        switch (library){
            case APACHE:
                vectorFactory = new ApacheVectorFactory();
                matrixFactory = new ApacheMatrixFactory();
                break;
            case OJALGO:
                vectorFactory = new OjalgoVectorFactory();
                matrixFactory = new OjalgoMatrixFactory();
                break;
            case SIMPLE:
                vectorFactory = new SimpleVectorFactory();
                matrixFactory = new SimpleMatrixFactory();
                break;
            default:
                throw new LinearAlgebraLibraryNotFound(library.name());
        }

        LinearAlgebraConfig.library = library;
    }

    /**
     * Get VectorFactory for creating new Vector objects. Returns null is library was not set beforehand.
     * @return VectorFactory
     */
    public static VectorFactory getVectorFactory(){
        return vectorFactory;
    }

    /**
     * Get MatrixFactory for creating new Matrix objects. Returns null is library was not set beforehand.
     * @return MatrixFactory
     */
    public static MatrixFactory getMatrixFactory(){
        return matrixFactory;
    }
}
