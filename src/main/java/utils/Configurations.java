package utils;

import exceptions.LinearAlgebraLibraryNotFound;
import linalg.LinearAlgebraLibrary;

import java.io.IOException;
import java.io.InputStream;

/**
 * This module reads and stores all necessary system properties from the config.properties file in the resources folder.
 * In particular, it reads which Linear Algebra library and which Linear Programming solver to use.
 */
public class Configurations {
    private static java.util.Properties prop = readPropertiesFile();

    private static java.util.Properties readPropertiesFile() {
        java.util.Properties prop = new java.util.Properties();
        InputStream input = null;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            input = classloader.getResourceAsStream("config.properties");
            prop.load(input);
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    /**
     * Reads the LinearAlgebraLibrary property from the config file.
     * It does not check that the library in present in our backend.
     * @return library name
     */
    public static LinearAlgebraLibrary getLinearAlgebraLibrary() {
        String libName = prop.getProperty("LinearAlgebraLibrary");
        if(libName.equalsIgnoreCase("apache"))
            return LinearAlgebraLibrary.APACHE;
        else if(libName.equalsIgnoreCase("ojalgo"))
            return LinearAlgebraLibrary.OJALGO;
        else if(libName.equalsIgnoreCase("simple"))
            return LinearAlgebraLibrary.SIMPLE;
        else
            throw new LinearAlgebraLibraryNotFound(libName);
    }

    /**
     * Gets the LinearProgrammingLibrary property from the config file.
     * @return library name
     */
    public static String getLinearProgrammingLibrary() {
        return prop.getProperty("LinearProgrammingLibrary");
    }
}
