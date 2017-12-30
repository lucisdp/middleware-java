package utils;

import exceptions.LinearAlgebraLibraryNotFound;
import linalg.LinearAlgebraConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinearAlgebraConfigurationTest {
    @Test(expected = LinearAlgebraLibraryNotFound.class)
    public void testSetLibraryToUnknownValue() throws Exception {
        LinearAlgebraConfiguration.setLibrary("unknown");
    }

    @Test
    public void testSetFromConfigFile() throws Exception {
        LinearAlgebraConfiguration.setLibraryFromConfig();
        assertEquals(Configurations.getLinearAlgebraLibrary(), LinearAlgebraConfiguration.getLibraryName());
    }
}
