package linalg.libraries.ojalgo;

import linalg.LinearAlgebraLibrary;
import linalg.MatrixTest;

public class OjalgoMatrixTest extends MatrixTest {

    @Override
    public LinearAlgebraLibrary getLibrary() {
        return LinearAlgebraLibrary.OJALGO;
    }
}
