package linalg.libraries.simple;

import linalg.LinearAlgebraLibrary;
import linalg.VectorTest;

public class SimpleVectorTest extends VectorTest {
    @Override
    public LinearAlgebraLibrary getLibrary() {
        return LinearAlgebraLibrary.SIMPLE;
    }
}
