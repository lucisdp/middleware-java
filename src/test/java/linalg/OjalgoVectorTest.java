package linalg;

public class OjalgoVectorTest extends VectorTest {
    @Override
    protected void setFactory() {
        Vector.FACTORY.setFactory(LinearAlgebraLibrary.OJALGO);
    }
}
