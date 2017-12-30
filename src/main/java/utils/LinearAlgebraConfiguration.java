package utils;

import exceptions.LinearAlgebraLibraryNotFound;
import linalg.Matrix;
import linalg.Vector;
import linalg.libraries.apache.ApacheMatrixOperationStrategy;
import linalg.libraries.apache.ApacheMatrixStorageFactory;
import linalg.libraries.apache.ApacheVectorOperationStrategy;
import linalg.libraries.apache.ApacheVectorStorageFactory;
import linalg.libraries.ojalgo.OjalgoMatrixOperationStrategy;
import linalg.libraries.ojalgo.OjalgoMatrixStorageFactory;
import linalg.libraries.ojalgo.OjalgoVectorOperationStrategy;
import linalg.libraries.ojalgo.OjalgoVectorStorageFactory;
import linalg.libraries.simple.SimpleMatrixOperationStrategy;
import linalg.libraries.simple.SimpleMatrixStorageFactory;
import linalg.libraries.simple.SimpleVectorOperationStrategy;
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
            Vector.setOpStrategy(new ApacheVectorOperationStrategy());
            Vector.setStorageFactory(new ApacheVectorStorageFactory());
            Matrix.setOpStrategy(new ApacheMatrixOperationStrategy());
            Matrix.setStorageFactory(new ApacheMatrixStorageFactory());
        }
        else if (libraryName.equalsIgnoreCase("ojalgo")){
            Vector.setOpStrategy(new OjalgoVectorOperationStrategy());
            Vector.setStorageFactory(new OjalgoVectorStorageFactory());
            Matrix.setOpStrategy(new OjalgoMatrixOperationStrategy());
            Matrix.setStorageFactory(new OjalgoMatrixStorageFactory());
        }

        else if (libraryName.equalsIgnoreCase("simple")) {
            Vector.setOpStrategy(new SimpleVectorOperationStrategy());
            Vector.setStorageFactory(new SimpleVectorStorageFactory());
            Matrix.setOpStrategy(new SimpleMatrixOperationStrategy());
            Matrix.setStorageFactory(new SimpleMatrixStorageFactory());
        }
        else
            throw new LinearAlgebraLibraryNotFound(libraryName);
    }
}
