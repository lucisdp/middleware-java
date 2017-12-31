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


public class LinearAlgebraConfiguration {
    private static String libraryName;
    private static VectorOperation vectorOperation;
    private static VectorStorageFactory vectorStorageFactory;
    private static MatrixOperation matrixOperation;
    private static MatrixStorageFactory matrixStorageFactory;

    /**
     * Get current library name
     * @return library name
     */
    public static String getLibraryName() {
        return libraryName;
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
     * The library can be changed during the program's execution, but new Vectors/Matrices cannot communicate with previously
     * created objects.
     *
     * @param name: library to use
     * @throws LinearAlgebraLibraryNotFound if library backend is not available.
     */
    public static void setLibrary(String name){
        libraryName = name;

        if(libraryName.equalsIgnoreCase("apache")) {
            vectorOperation = new ApacheVectorOperation();
            vectorStorageFactory = new ApacheVectorStorageFactory();
            matrixOperation = new ApacheMatrixOperation();
            matrixStorageFactory = new ApacheMatrixStorageFactory();
        }
        else if (libraryName.equalsIgnoreCase("ojalgo")){
            vectorOperation = new OjalgoVectorOperation();
            vectorStorageFactory = new OjalgoVectorStorageFactory();
            matrixOperation = new OjalgoMatrixOperation();
            matrixStorageFactory = new OjalgoMatrixStorageFactory();
        }

        else if (libraryName.equalsIgnoreCase("simple")) {
            vectorOperation = new SimpleVectorOperation();
            vectorStorageFactory = new SimpleVectorStorageFactory();
            matrixOperation = new SimpleMatrixOperation();
            matrixStorageFactory = new SimpleMatrixStorageFactory();
        }
        else
            throw new LinearAlgebraLibraryNotFound(libraryName);
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
