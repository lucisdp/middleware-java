package linalg.libraries.simple;

import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.VectorTest;
import org.junit.BeforeClass;

public class SimpleVectorTest extends VectorTest {
    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.SIMPLE);
    }
}
