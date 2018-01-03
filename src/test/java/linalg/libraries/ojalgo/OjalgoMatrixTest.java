package linalg.libraries.ojalgo;

import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.MatrixTest;
import org.junit.BeforeClass;

public class OjalgoMatrixTest extends MatrixTest {
    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.OJALGO);
    }
}
