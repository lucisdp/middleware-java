package convex.sampling;

import exceptions.IncompatibleDimensionsException;
import linalg.Vector;
import org.junit.Before;
import org.junit.Test;
import utils.LinearAlgebraConfiguration;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LineTest {
    public Vector center;
    public Vector direction;
    public Line line;

    @Before
    public void setUp(){
        LinearAlgebraConfiguration.setLibraryFromConfig();
        center = new Vector(new double[] {1,2,3});
        direction = new Vector(new double[] {-1,0,1});
        line = new Line(center, direction);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testWrongDimension() {
        new Line(center, new Vector(new double[] {1,2}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroDirection() {
        new Line(center, new Vector(new double[] {0,0,0}));
    }

    @Test
    public void testGetCenter() {
        assertArrayEquals(new double[] {1,2,3}, line.getCenter().getValues(),  1e-10);
    }

    @Test
    public void testGetDirection() {
        assertArrayEquals(new double[] {-1,0,1}, line.getDirection().getValues(), 1e-10);
    }

    @Test
    public void testGetDim() {
        assertEquals(3, line.getDim());
    }

    @Test
    public void testGetCenterPoint() throws Exception {
        assertArrayEquals(new double[] {1,2,3}, line.getPoint(0).getValues(), 1e-10);
    }

    @Test
    public void testGetPositivePoint() throws Exception {
        assertArrayEquals(new double[] {-3,2,7}, line.getPoint(4).getValues(), 1e-10);
    }

    @Test
    public void testGetNegativePoint() throws Exception {
        assertArrayEquals(new double[] {5,2,-1}, line.getPoint(-4).getValues(), 1e-10);
    }

    //TODO: test sampling method
}
