package linalg.libraries.simple;

import linalg.LinearAlgebraLibrary;
import linalg.MatrixTest;

public class SimpleMatrixTest extends MatrixTest {
    @Override
    public LinearAlgebraLibrary getLibrary(){
       return LinearAlgebraLibrary.SIMPLE;
    }
}
