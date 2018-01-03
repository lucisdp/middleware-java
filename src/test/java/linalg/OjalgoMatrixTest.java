package linalg;

import org.junit.BeforeClass;

public class OjalgoMatrixTest extends MatrixTest {
    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.OJALGO);
    }
}
