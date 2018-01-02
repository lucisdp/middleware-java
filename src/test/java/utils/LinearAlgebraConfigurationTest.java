package utils;

import linalg.LinearAlgebraConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinearAlgebraConfigurationTest {
    @Test(expected = NullPointerException.class)
    public void testSetLibraryToNull() throws Exception {
        LinearAlgebraConfiguration.setLibrary(null);
    }

    @Test
    public void testSetFromConfigFile() throws Exception {
        LinearAlgebraConfiguration.setLibraryFromConfig();
        assertEquals(Configurations.getLinearAlgebraLibrary(), LinearAlgebraConfiguration.getLibraryName());
    }
}
