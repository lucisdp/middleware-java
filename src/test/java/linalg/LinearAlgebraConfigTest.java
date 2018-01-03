package linalg;

import linalg.libraries.simple.SimpleMatrixFactory;
import linalg.libraries.simple.SimpleVectorFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinearAlgebraConfigTest {

    @Test
    public void testGetLibraryAfterSetting() throws Exception {
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.SIMPLE);
        assertEquals(LinearAlgebraLibrary.SIMPLE, LinearAlgebraConfig.getLibrary());
    }

    @Test
    public void testGetVectorFactoryAfterSetting() throws Exception {
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.SIMPLE);
        assertTrue(LinearAlgebraConfig.getVectorFactory() instanceof SimpleVectorFactory);
    }

    @Test
    public void testGetMatrixFactoryAfterSetting() throws Exception {
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.SIMPLE);
        assertTrue(LinearAlgebraConfig.getMatrixFactory() instanceof SimpleMatrixFactory);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullLibraryThrowsException() throws Exception {
        LinearAlgebraConfig.setLibrary(null);
    }
}
