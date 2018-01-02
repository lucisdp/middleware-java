package linalg;

public class OjalgoMatrixTest extends MatrixTest {
    @Override
    protected void setFactory() {
        Vector.FACTORY.setFactory(LinearAlgebraLibrary.OJALGO);
        Matrix.FACTORY.setFactory(LinearAlgebraLibrary.OJALGO);
    }
}
