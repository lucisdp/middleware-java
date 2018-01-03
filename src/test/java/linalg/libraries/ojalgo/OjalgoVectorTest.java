package linalg.libraries.ojalgo;

import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.VectorTest;
import org.junit.BeforeClass;

public class OjalgoVectorTest extends VectorTest {
    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.OJALGO);

    }
}
