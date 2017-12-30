package utils;

import exceptions.LinearAlgebraLibraryNotFound;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinearAlgebraConfigurationTest {
    @Test(expected = LinearAlgebraLibraryNotFound.class)
    public void testSetLibraryToUnknownValue() throws Exception {
        LinearAlgebraConfiguration.setLibraryName("unknown");
    }

    @Test
    public void testSetFromConfigFile() throws Exception {
        LinearAlgebraConfiguration.setLibraryFromConfig();
        assertEquals(Configuration.getLinearAlgebraLibrary(), LinearAlgebraConfiguration.getLibraryName());
    }
}
