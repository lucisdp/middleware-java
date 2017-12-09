package linalg;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class OjalgoMatrixTest {
    private OjalgoMatrix mat;

    @Before
    public void setUp(){
        mat = new OjalgoMatrix(new double[][] {{1,2,3},{4,5,6}});
    }

    @Test
    public void testGetNumRows(){
        assertEquals(2, mat.getNumRows());
    }

    @Test
    public void testGetNumCols(){
        assertEquals(3, mat.getNumCols());
    }

    @Test
    public void testGet(){
        assertEquals(1, mat.get(0,0), 1e-10);
        assertEquals(2, mat.get(0,1), 1e-10);
        assertEquals(3, mat.get(0,2), 1e-10);
        assertEquals(4, mat.get(1,0), 1e-10);
        assertEquals(5, mat.get(1,1), 1e-10);
        assertEquals(6, mat.get(1,2), 1e-10);
    }

    @Test(expected = java.lang.ArrayIndexOutOfBoundsException.class)
    public void testGetNegativeRowIndex(){
        mat.get(-1,0);
    }

    @Test(expected = java.lang.ArrayIndexOutOfBoundsException.class)
    public void testGetLargerThanSizeRowIndex(){
        mat.get(2,0);
    }

    @Test(expected = java.lang.ArrayIndexOutOfBoundsException.class)
    public void testGetNegativeColIndex(){
        mat.get(0,-1);
    }

    @Test(expected = java.lang.ArrayIndexOutOfBoundsException.class)
    public void testGetLargerThanSizeColIndex(){
        mat.get(0,3);
    }

    @Test
    public void testEquals(){
        assertTrue(mat.equals(new OjalgoMatrix(new double[][] {{1,2,3}, {4,5,6}})));
    }

    @Test
    public void testEqualsWithSmallImprecision(){
        OjalgoMatrix mat2 = new OjalgoMatrix(new double[][] {{1+1e-8,2,3}, {4,5,6}});
        assertFalse(mat.equals(mat2));
    }

    @Test
    public void testEqualsWithVerySmallImprecision(){
        OjalgoMatrix mat2 = new OjalgoMatrix(new double[][] {{1+1e-10,2,3}, {4,5,6}});
        assertTrue(mat.equals(mat2));
    }

    @Test
    public void testAdd(){
        OjalgoMatrix mat2 = new OjalgoMatrix(new double[][] {{-1,0,1}, {-3,4,6}});
        OjalgoMatrix res = mat.add(mat2);
        assertEquals(0, res.get(0,0), 1e-10);
        assertEquals(2, res.get(0,1), 1e-10);
        assertEquals(4, res.get(0,2), 1e-10);
        assertEquals(1, res.get(1,0), 1e-10);
        assertEquals(9, res.get(1,1), 1e-10);
        assertEquals(12, res.get(1,2), 1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWrongDimension(){
        OjalgoMatrix mat2 = new OjalgoMatrix(new double[][] {{-1,1,2,3},{1,1,1,1}});
        mat.add(mat2);
    }

    @Test
    public void testSubtract(){
        OjalgoMatrix mat2 = new OjalgoMatrix(new double[][] {{-1,0,1}, {-3,4,6}});
        OjalgoMatrix res = mat.subtract(mat2);
        assertEquals(2, res.get(0,0), 1e-10);
        assertEquals(2, res.get(0,1), 1e-10);
        assertEquals(2, res.get(0,2), 1e-10);
        assertEquals(7, res.get(1,0), 1e-10);
        assertEquals(1, res.get(1,1), 1e-10);
        assertEquals(0, res.get(1,2), 1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractWrongDimension(){
        mat.subtract(new OjalgoMatrix(new double[][] {{-1,1,2,3},{1,1,1,1}}));
    }

    @Test
    public void testMultiplyDouble(){
        OjalgoMatrix res = mat.multiply(2);
        assertEquals(2, res.get(0,0), 1e-10);
        assertEquals(4, res.get(0,1), 1e-10);
        assertEquals(6, res.get(0,2), 1e-10);
        assertEquals(8, res.get(1,0), 1e-10);
        assertEquals(10, res.get(1,1), 1e-10);
        assertEquals(12, res.get(1,2), 1e-10);
    }

    @Test
    public void testMultiplyMatrix(){
        OjalgoMatrix mat2 = new OjalgoMatrix(new double[][] {{-1,0}, {-3,4}, {5,-2}});
        OjalgoMatrix res = mat.multiply(mat2);
        assertEquals(8, res.get(0,0), 1e-10);
        assertEquals(2, res.get(0,1), 1e-10);
        assertEquals(11, res.get(1,0), 1e-10);
        assertEquals(8, res.get(1,1), 1e-10);
    }

    @Test
    public void testMultiplyMatrixGetNumRows(){
        OjalgoMatrix mat2 = new OjalgoMatrix(new double[][] {{-1,0}, {-3,4}, {5,-2}});
        OjalgoMatrix res = mat.multiply(mat2);
        assertEquals(2, res.getNumRows());
    }

    @Test
    public void testMultiplyMatrixGetNumCols(){
        OjalgoMatrix mat2 = new OjalgoMatrix(new double[][] {{-1,0}, {-3,4}, {5,-2}});
        OjalgoMatrix res = mat.multiply(mat2);
        assertEquals(2, res.getNumCols());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyMatrixIncompatibleDimensions(){
        OjalgoMatrix mat2 = new OjalgoMatrix(new double[][] {{-1,0}, {-3,4}});
        mat.multiply(mat2);
    }

    @Test
    public void testMultiplyVector(){
        OjalgoVector vec = new OjalgoVector(new double[] {-3,4,5});
        OjalgoVector res = mat.multiply(vec);
        assertEquals(20, res.get(0), 1e-10);
        assertEquals(38, res.get(1), 1e-10);
    }

    @Test
    public void testMultiplyVectorGetDim(){
        OjalgoVector vec = new OjalgoVector(new double[] {-3,4,5});
        OjalgoVector res = mat.multiply(vec);
        assertEquals(2, res.getDim());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyVectorIncompatibleDimensions(){
        OjalgoVector vec = new OjalgoVector(new double[] {-3,4});
        mat.multiply(vec);
    }

    @Test
    public void testGetRow(){
        OjalgoVector vec = mat.getRow(0);
        assertEquals(1, vec.get(0), 1e-10);
        assertEquals(2, vec.get(1), 1e-10);
        assertEquals(3, vec.get(2), 1e-10);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetRowNegativeIndex(){
        mat.getRow(2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetRowLargerThanSizeIndex(){
        mat.getRow(-1);
    }
}

