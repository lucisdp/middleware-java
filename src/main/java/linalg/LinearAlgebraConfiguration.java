package linalg;

import exceptions.LinearAlgebraLibraryNotFound;
import linalg.libraries.apache.ApacheMatrixOperation;
import linalg.libraries.apache.ApacheMatrixStorageFactory;
import linalg.libraries.apache.ApacheVectorOperation;
import linalg.libraries.apache.ApacheVectorStorageFactory;
import linalg.libraries.ojalgo.OjalgoMatrixOperation;
import linalg.libraries.ojalgo.OjalgoMatrixStorageFactory;
import linalg.libraries.ojalgo.OjalgoVectorOperation;
import linalg.libraries.ojalgo.OjalgoVectorStorageFactory;
import linalg.libraries.simple.SimpleMatrixOperation;
import linalg.libraries.simple.SimpleMatrixStorageFactory;
import linalg.libraries.simple.SimpleVectorOperation;
import linalg.libraries.simple.SimpleVectorStorageFactory;
import utils.Configurations;


/**
 * This class is responsible for storing all necessary information regarding the current Linear Algebra library being used
 * in our Vector and Matrix class. It provides a single entry point for getting and setting the library configuration,
 * guaranteeing that both Vector and Matrix classes are always using the same library.
 */
public class LinearAlgebraConfiguration {
    private static LinearAlgebraLibrary library;
    private static VectorOperation vectorOperation;
    private static VectorStorageFactory vectorStorageFactory;
    private static MatrixOperation matrixOperation;
    private static MatrixStorageFactory matrixStorageFactory;

    /**
     * Get current library name
     * @return library name
     */
    public static LinearAlgebraLibrary getLibraryName() {
        return library;
    }

    /**
     * Sets the Linear Algebra library backend to the one specified in the configuration file.
     *
     * @throws LinearAlgebraLibraryNotFound if library backend is not available.
     */
    public static void setLibraryFromConfig(){
        setLibrary(Configurations.getLinearAlgebraLibrary());
    }

    /**
     * Sets the Linear Algebra library backend. Current options are: simple, ojalgo and apache.
     * Using this function will result in changing which library is used to perform matrix / vector operations.
     * The library should be set before any Vector/Matrix code is run, or a NullPointerException may be thrown.
     * The library can be changed during the program's execution, but new Vector/Matrix objects cannot communicate with
     * previously created objects.
     *
     * @param lib: library to use
     * @throws LinearAlgebraLibraryNotFound if library backend is not available.
     */
    public static void setLibrary(LinearAlgebraLibrary lib){
        library = lib;

        if(lib == LinearAlgebraLibrary.APACHE) {
            vectorOperation = new ApacheVectorOperation();
            vectorStorageFactory = new ApacheVectorStorageFactory();
            matrixOperation = new ApacheMatrixOperation();
            matrixStorageFactory = new ApacheMatrixStorageFactory();
        }
        else if (lib == LinearAlgebraLibrary.OJALGO){
            vectorOperation = new OjalgoVectorOperation();
            vectorStorageFactory = new OjalgoVectorStorageFactory();
            matrixOperation = new OjalgoMatrixOperation();
            matrixStorageFactory = new OjalgoMatrixStorageFactory();
        }
        else if (lib == LinearAlgebraLibrary.SIMPLE) {
            vectorOperation = new SimpleVectorOperation();
            vectorStorageFactory = new SimpleVectorStorageFactory();
            matrixOperation = new SimpleMatrixOperation();
            matrixStorageFactory = new SimpleMatrixStorageFactory();
        }
        else if(lib == null)
            throw new NullPointerException("Library cannot be null.");
        else
            throw new RuntimeException("Unknown linear algebra library. Check LinearAlgebraLibrary class for new values.");
    }

    static VectorOperation getVectorOperation() {
        return vectorOperation;
    }

    static VectorStorageFactory getVectorStorageFactory() {
        return vectorStorageFactory;
    }

    static MatrixOperation getMatrixOperation() {
        return matrixOperation;
    }

    static MatrixStorageFactory getMatrixStorageFactory() {
        return matrixStorageFactory;
    }
}
