package linalg.libraries.apache;

import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.MatrixTest;
import org.junit.BeforeClass;

public class ApacheMatrixTest extends MatrixTest {
    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.APACHE);
    }
}
