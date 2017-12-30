package utils;

import exceptions.LinearAlgebraLibraryNotFound;
import linalg.Matrix;
import linalg.Vector;
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


public class LinearAlgebraConfiguration {
    private static String libraryName;

    public static String getLibraryName() {
        return libraryName;
    }

    /**
     * Sets the Linear Algebra library backend. Current options are: simple, ojalgo and apache.
     * Using this function will result in changing which library is used to perform matrix / vector operations.
     *
     * @throws LinearAlgebraLibraryNotFound if library backend is not available.
     * @param libraryName: library to use
     */
    public static void setLibraryName(String libraryName) {
        LinearAlgebraConfiguration.libraryName = libraryName;
        setLibrary();
    }

    /**
     * Sets the Linear Algebra library backend to the one specified in the configuration file.
     *
     * @throws LinearAlgebraLibraryNotFound if library backend is not available.
     */
    public static void setLibraryFromConfig(){
        libraryName = Configuration.getLinearAlgebraLibrary();
        setLibrary();
    }

    private static void setLibrary(){
        if(libraryName.equalsIgnoreCase("apache")) {
            Vector.setVectorOperation(new ApacheVectorOperation());
            Vector.setStorageFactory(new ApacheVectorStorageFactory());
            Matrix.setMatrixOperation(new ApacheMatrixOperation());
            Matrix.setStorageFactory(new ApacheMatrixStorageFactory());
        }
        else if (libraryName.equalsIgnoreCase("ojalgo")){
            Vector.setVectorOperation(new OjalgoVectorOperation());
            Vector.setStorageFactory(new OjalgoVectorStorageFactory());
            Matrix.setMatrixOperation(new OjalgoMatrixOperation());
            Matrix.setStorageFactory(new OjalgoMatrixStorageFactory());
        }

        else if (libraryName.equalsIgnoreCase("simple")) {
            Vector.setVectorOperation(new SimpleVectorOperation());
            Vector.setStorageFactory(new SimpleVectorStorageFactory());
            Matrix.setMatrixOperation(new SimpleMatrixOperation());
            Matrix.setStorageFactory(new SimpleMatrixStorageFactory());
        }
        else
            throw new LinearAlgebraLibraryNotFound(libraryName);
    }
}
