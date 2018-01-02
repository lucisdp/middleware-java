package convex.sampling;

import exceptions.IncompatibleDimensionsException;
import linalg.LinearAlgebraLibrary;
import linalg.Matrix;
import linalg.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LineTest {
    private Vector center;
    private Vector direction;
    private Line line;

    private void setLibrary(LinearAlgebraLibrary lib){
        Vector.FACTORY.setFactory(lib);
        Matrix.FACTORY.setFactory(lib);
    }

    @Before
    public void setUp(){
        setLibrary(LinearAlgebraLibrary.OJALGO);
        center = Vector.FACTORY.makeVector(new double[] {1,2,3});
        direction = Vector.FACTORY.makeVector(new double[] {-1,0,1});
        line = new Line(center, direction);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testWrongDimension() {
        new Line(center, Vector.FACTORY.makeVector(new double[] {1,2}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroDirection() {
        new Line(center, Vector.FACTORY.makeVector(new double[] {0,0,0}));
    }

    @Test
    public void testGetCenter() {
        assertArrayEquals(new double[] {1,2,3}, line.getCenter().asArray(),  1e-10);
    }

    @Test
    public void testGetDirection() {
        assertArrayEquals(new double[] {-1,0,1}, line.getDirection().asArray(), 1e-10);
    }

    @Test
    public void testGetDim() {
        assertEquals(3, line.getDim());
    }

    @Test
    public void testGetCenterPoint() throws Exception {
        assertArrayEquals(new double[] {1,2,3}, line.getPoint(0).asArray(), 1e-10);
    }

    @Test
    public void testGetPositivePoint() throws Exception {
        assertArrayEquals(new double[] {-3,2,7}, line.getPoint(4).asArray(), 1e-10);
    }

    @Test
    public void testGetNegativePoint() throws Exception {
        assertArrayEquals(new double[] {5,2,-1}, line.getPoint(-4).asArray(), 1e-10);
    }

    //TODO: test sampling method
}
