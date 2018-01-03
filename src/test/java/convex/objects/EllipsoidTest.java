package convex.objects;

import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.EmptyIntersectionException;
import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;
import exceptions.NegativeLengthException;
import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.Vector;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class EllipsoidTest {
    private Vector center, axisLengths;
    private Ellipsoid elp;

    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.OJALGO);
    }

    @Before
    public void setUp(){
        center = Vector.FACTORY.make(new double[] {1,2,3});
        axisLengths = Vector.FACTORY.make(new double[] {4,5,6});
        elp = new Ellipsoid(center, axisLengths);
    }

    @Test(expected= IncompatibleDimensionsException.class)
    public void testWrongDimensions() throws Exception {
        new Ellipsoid(center, Vector.FACTORY.make(new double[] {1,2}));
    }

    @Test(expected= NegativeLengthException.class)
    public void testNegativeAxisLength() throws Exception {
        new Ellipsoid(center, Vector.FACTORY.make(new double[] {-1,2,3}));
    }

    @Test(expected=NegativeLengthException.class)
    public void testZeroAxisLength() throws Exception {
        new Ellipsoid(center, Vector.FACTORY.make(new double[] {0,2,3}));
    }

    @Test(expected=NegativeLengthException.class)
    public void testNegativeRadius() throws Exception {
        new Ellipsoid(center, -1);
    }

    @Test(expected= NegativeDimensionException.class)
    public void testNegativeDim() throws Exception {
        new Ellipsoid(-1);
    }

    @Test
    public void testGetCenter() throws Exception {
        assertArrayEquals(center.asArray(), elp.getCenter().asArray(), 1e-10);
    }

    @Test
    public void testGetCenterWithRadiusConstructor() throws Exception {
        elp = new Ellipsoid(center, 4);
        assertArrayEquals(center.asArray(), elp.getCenter().asArray(), 1e-10);
    }

    @Test
    public void testGetCenterWithDimConstructor() throws Exception {
        elp = new Ellipsoid(3);
        assertArrayEquals(new double[] {0,0,0}, elp.getCenter().asArray(), 1e-10);
    }

    @Test
    public void testGetAxisLength() throws Exception {
        assertArrayEquals(axisLengths.asArray(), elp.getHalfAxisLengths().asArray(), 1e-10);
    }

    @Test
    public void testGetAxisLengthWithRadiusConstructor() throws Exception {
        elp = new Ellipsoid(center, 4);
        assertArrayEquals(new double[] {4,4,4}, elp.getHalfAxisLengths().asArray(), 1e-10);
    }

    @Test
    public void testGetAxisLengthWithDimConstructor() throws Exception {
        elp = new Ellipsoid(3);
        assertArrayEquals(new double[] {1,1,1}, elp.getHalfAxisLengths().asArray(), 1e-10);
    }

    @Test
    public void testGetDim() throws Exception {
        double[] center = {1,2,3};
        double[] axisLengths = {4,5,6};
        Ellipsoid elp = new Ellipsoid(center, axisLengths);
        assertEquals(3, elp.getDim());
    }

    @Test
    public void testGetDimWithRadiusConstructor() throws Exception {
        elp = new Ellipsoid(center, 4);
        assertEquals(3, elp.getDim());
    }

    @Test
    public void testGetDimWithDimConstructor() throws Exception {
        elp = new Ellipsoid(3);
        assertEquals(3, elp.getDim());
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testIsInsideWrongDimension() throws Exception{
        elp.isInside(new double[] {0,0});
    }

    @Test
    public void testIsInsideOnInterior() throws Exception{
        assertTrue(elp.isInside(new double[] {1,2,3}));
    }

    @Test
    public void testIsInsideOnBoundary() throws Exception{
        assertFalse(elp.isInside(new double[] {5,2,3}));
    }

    @Test
    public void testIsInsideOnExterior() throws Exception{
        assertFalse(elp.isInside(new double[] {7,2,3}));
    }

    @Test
    public void testIsInsideOnInteriorCloseToBoundary() throws Exception{
        assertTrue(elp.isInside(new double[] {4.99999,2,3}));
    }

    @Test
    public void testIsInsideOnExteriorCloseToBoundary() throws Exception{
        assertFalse(elp.isInside(new double[] {5.00000001,2,3}));
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testWrongLineDimension(){
        Line line = new Line(Vector.FACTORY.make(new double[] {5,2}), Vector.FACTORY.make(new double[] {0,1}));
        elp.intersect(line);
    }

    @Test
    public void testIntersectionWithCenterOnInterior(){
        Line line = new Line(center, Vector.FACTORY.make(new double[] {1,0,0}));
        LineSegment segment = elp.intersect(line);
        assertEquals(-4, segment.getLower(), 1e-10);
        assertEquals(4, segment.getUpper(), 1e-10);
    }

    @Test
    public void testIntersectionWithCenterOnBoundary(){
        Line line = new Line(Vector.FACTORY.make(new double[] {-3,2,3}), Vector.FACTORY.make(new double[] {1,0,0}));
        LineSegment segment = elp.intersect(line);
        assertEquals(0, segment.getLower(), 1e-10);
        assertEquals(8, segment.getUpper(), 1e-10);
    }

    @Test
    public void testIntersectionWithCenterOnExterior(){
        Line line = new Line(Vector.FACTORY.make(new double[] {-5,2,3}), Vector.FACTORY.make(new double[] {1,0,0}));
        LineSegment segment = elp.intersect(line);
        assertEquals(2, segment.getLower(), 1e-10);
        assertEquals(10, segment.getUpper(), 1e-10);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testTangentIntersectionWithCenterOnBoundary(){
        Line line = new Line(Vector.FACTORY.make(new double[] {5,2,3}), Vector.FACTORY.make(new double[] {0,0,1}));
        elp.intersect(line);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testTangentIntersectionWithCenterNotOnBoundary(){
        Line line = new Line(Vector.FACTORY.make(new double[] {5,2,0}), Vector.FACTORY.make(new double[] {0,0,1}));
        elp.intersect(line);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testNoIntersection(){
        Line line = new Line(Vector.FACTORY.make(new double[] {6,2,0}), Vector.FACTORY.make(new double[] {0,0,1}));
        elp.intersect(line);
    }
}