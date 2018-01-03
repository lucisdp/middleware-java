package linalg.libraries.simple;

import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.MatrixTest;
import org.junit.BeforeClass;

public class SimpleMatrixTest extends MatrixTest {
    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.SIMPLE);
    }
}
