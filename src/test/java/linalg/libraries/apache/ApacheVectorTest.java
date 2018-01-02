package linalg.libraries.apache;

import linalg.LinearAlgebraLibrary;
import linalg.VectorTest;

public class ApacheVectorTest extends VectorTest {

    @Override
    public LinearAlgebraLibrary getLibrary() {
        return LinearAlgebraLibrary.APACHE;
    }
}
