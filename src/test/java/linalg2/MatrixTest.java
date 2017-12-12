package linalg2;

import exceptions.IncompatibleDimensionsException;
import exceptions.LinearAlgebraClassNotFound;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MatrixTest {
    Matrix mat, mat2;

    @Before
    public void setUp(){
        Matrix.setMatrixOperationStrategy("ojalgo");
        mat = new Matrix(new double[][] {{1,2,3},{4,5,6}});
        mat2 = new Matrix(new double[][] {{-1,0,1},{2,5,-8}});
    }

    @Test(expected = LinearAlgebraClassNotFound.class)
    public void testSetUnknownStrategy(){
        Matrix.setMatrixOperationStrategy("unknown");
    }

    @Test
    public void testSetSimpleStrategy() throws ClassNotFoundException{
        Matrix.setMatrixOperationStrategy("simple");
    }

    @Test
    public void testSetOjalgoStrategy() throws ClassNotFoundException{
        Matrix.setMatrixOperationStrategy("ojalgo");
    }

    @Test
    public void testGetRows(){
        assertEquals(2, mat.getRows());
    }

    @Test
    public void testGetCols(){
        assertEquals(3, mat.getCols());
    }

    @Test
    public void testGetValues(){
        assertArrayEquals(new double[] {1,2,3}, mat.getValues()[0], 1e-10);
        assertArrayEquals(new double[] {4,5,6}, mat.getValues()[1], 1e-10);
    }

    @Test
    public void testFillConstructor(){
        mat = new Matrix(2, 3, 1);
        assertArrayEquals(new double[] {1,1,1}, mat.getValues()[0], 1e-10);
        assertArrayEquals(new double[] {1,1,1}, mat.getValues()[1], 1e-10);
    }

    @Test
    public void testDimConstructor(){
        mat = new Matrix(2, 3);
        assertArrayEquals(new double[] {0,0,0}, mat.getValues()[0], 1e-10);
        assertArrayEquals(new double[] {0,0,0}, mat.getValues()[1], 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testAddMatrixOfWrongDimension(){
        mat.add(new Matrix(new double[][] {{-1,1,2,3}, {1,2,3,4}}));
    }

    @Test
    public void testAddValue(){
        Matrix res = mat.add(5);
        assertArrayEquals(new double[] {6,7,8}, res.getValues()[0], 1e-10);
        assertArrayEquals(new double[] {9,10,11}, res.getValues()[1], 1e-10);
    }

    @Test
    public void testAddMatrix(){
        Matrix res = mat2.add(mat);
        assertArrayEquals(new double[] {0,2,4}, res.getValues()[0], 1e-10);
        assertArrayEquals(new double[] {6,10,-2}, res.getValues()[1], 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testSubtractMatrixOfWrongDimension(){ mat.subtract(new Matrix(new double[][] {{-1,1,2,3}, {1,2,3,4}})); }

    @Test
    public void testSubtractValue(){
        Matrix res = mat.subtract(5);
        assertArrayEquals(new double[] {-4,-3,-2}, res.getValues()[0], 1e-10);
        assertArrayEquals(new double[] {-1,0,1}, res.getValues()[1], 1e-10);
    }

    @Test
    public void testSubtractMatrix(){
        Matrix res = mat.subtract(mat2);
        assertArrayEquals(new double[] {2,2,2}, res.getValues()[0], 1e-10);
        assertArrayEquals(new double[] {2,0,14}, res.getValues()[1], 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testMultiplyByMatrixOfWrongDimension(){ mat.multiply(new Matrix(new double[][] {{-1,1,2,3}, {1,2,3,4}})); }

    @Test
    public void testMultiplyByValue(){
        Matrix res = mat.multiply(2);
        assertArrayEquals(new double[] {2,4,6}, res.getValues()[0], 1e-10);
        assertArrayEquals(new double[] {8,10,12}, res.getValues()[1], 1e-10);
    }

    @Test
    public void testMultiplyByMatrix(){
        Matrix res = mat.multiply(mat2);
        assertArrayEquals(new double[] {-1,0,3}, res.getValues()[0], 1e-10);
        assertArrayEquals(new double[] {8,25,-48}, res.getValues()[1], 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testDivideByMatrixOfWrongDimension(){ mat.divide(new Matrix(new double[][] {{-1,1,2,3}, {1,2,3,4}})); }


    @Test
    public void testDivideByValue(){
        Matrix res = mat.divide(2);
        assertArrayEquals(new double[] {0.5,1,1.5}, res.getValues()[0], 1e-10);
        assertArrayEquals(new double[] {2,2.5,3}, res.getValues()[1], 1e-10);
    }

    @Test
    public void testDivideByMatrix(){
        mat2 = new Matrix(new double[][] {{-1,2,4},{4,6,10}});
        Matrix res = mat.divide(mat2);
        assertArrayEquals(new double[] {-1,1,0.75}, res.getValues()[0], 1e-10);
        assertArrayEquals(new double[] {1,0.833333333333,0.6}, res.getValues()[1], 1e-10);
    }

    @Test
    public void testToString(){ assertEquals("{1.0, 2.0, 3.0}\n{4.0, 5.0, 6.0}", mat.toString());}
}
