package linalg.libraries.apache;

import linalg.LinearAlgebraLibrary;
import linalg.MatrixTest;

public class ApacheMatrixTest extends MatrixTest {
    @Override
    public LinearAlgebraLibrary getLibrary() {
        return LinearAlgebraLibrary.APACHE;
    }
}
