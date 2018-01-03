package linalg.libraries.apache;

import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.VectorTest;
import org.junit.BeforeClass;

public class ApacheVectorTest extends VectorTest {
    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.APACHE);
    }
}
