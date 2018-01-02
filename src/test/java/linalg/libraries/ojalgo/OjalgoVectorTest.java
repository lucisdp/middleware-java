package linalg.libraries.ojalgo;

import linalg.LinearAlgebraLibrary;
import linalg.VectorTest;

public class OjalgoVectorTest extends VectorTest {
    @Override
    public LinearAlgebraLibrary getLibrary() {
        return LinearAlgebraLibrary.OJALGO;
    }
}
