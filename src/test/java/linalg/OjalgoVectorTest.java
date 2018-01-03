package linalg;

import org.junit.BeforeClass;

public class OjalgoVectorTest extends VectorTest {
    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.OJALGO);
    }
}
