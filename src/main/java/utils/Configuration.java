package utils;

import exceptions.LinearAlgebraLibraryNotFound;
import linalg.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Configuration {
    private static String configFilePath = "src/main/resources/config.properties";
    private static Properties prop = readPropertiesFile();

    private static Properties readPropertiesFile() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(configFilePath);
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

    public static String getLinearAlgebraLibrary() {
        return prop.getProperty("LinearAlgebraLibrary");
    }

    public static void setLinearAlgebraLibrary(String libraryName) {
        prop.setProperty("LinearAlgebraLibrary", libraryName);

        if (libraryName.equalsIgnoreCase("simple")) {
            Vector.setOpStrategy(new SimpleVectorOperationStrategy());
            Matrix.setOpStrategy(new SimpleMatrixOperationStrategy());
        }
        else if (libraryName.equalsIgnoreCase("ojalgo")) {
            Vector.setOpStrategy(new OjalgoVectorOperationStrategy());
            Matrix.setOpStrategy(new OjalgoMatrixOperationStrategy());
        }
        else
            throw new LinearAlgebraLibraryNotFound(libraryName);
    }
}
