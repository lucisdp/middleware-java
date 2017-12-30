package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This module reads and stores all necessary system properties from the config.properties file in the resources folder.
 * In particular, it reads which Linear Algebra library and which Linear Programming solver to use.
 */
public class Configuration {
    private static Properties prop = readPropertiesFile();

    private static Properties readPropertiesFile() {
        Properties prop = new Properties();
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
    public static String getLinearAlgebraLibrary() {
        return prop.getProperty("LinearAlgebraLibrary");
    }

    /**
     * Gets the LinearProgrammingLibrary property from the config file.
     * @return library name
     */
    public static String getLinearProgrammingLibrary() {
        return prop.getProperty("LinearProgrammingLibrary");
    }
}
