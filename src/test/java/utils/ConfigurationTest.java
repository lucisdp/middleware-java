package utils;

import exceptions.LinearAlgebraLibraryNotFound;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    @Test
    public void testSetVectorLinearAlgebraLibrary(){
        Configuration.setLinearAlgebraLibrary("ojalgo");
        assertEquals(Configuration.getLinearAlgebraLibrary(), "ojalgo");
    }

    @Test(expected = LinearAlgebraLibraryNotFound.class)
    public void testSetUnknownLinearAlgebraLibrary(){
        Configuration.setLinearAlgebraLibrary("unknown");
    }
}
