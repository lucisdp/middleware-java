package utils;

import exceptions.LinearAlgebraLibraryNotFound;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    // TODO: can we setup a dummy config file for testing ?

    @Test
    public void testLinearAlgebraLibrary(){
        assertEquals(Configuration.getLinearAlgebraLibrary(), "simple");
    }

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
