package linear_programming;

import exceptions.IncompatibleDimensionsException;
import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.Vector;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public abstract class LinearProgramSolverTest {
    final int dim=2;
    LinearProgramSolver solver = null;

    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.OJALGO);
    }

    public void testSolver(double[] objective, double[][] constrainsMatrix, double[] constrainsVector, double[] answer){
        solver.setObjectiveFunction(Vector.FACTORY.makeVector(objective));
        for(int i=0; i < constrainsMatrix.length; i++)
            solver.addLinearConstrain(Vector.FACTORY.makeVector(constrainsMatrix[i]), constrainsVector[i]);
        Vector solution = solver.findMinimizer();
        assertArrayEquals(answer, solution.asArray(), 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testSetObjectiveFunctionWithWrongDimension() throws Exception {
        Vector vec = Vector.FACTORY.makeVector(new double[] {1,0,0});
        solver.setObjectiveFunction(vec);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testAddConstrainWithWrongDimension() throws Exception {
        Vector vec = Vector.FACTORY.makeVector(new double[] {1,0,0});
        solver.addLinearConstrain(vec, 0);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testSetLowerWithWrongDimension() throws Exception {
        Vector vec = Vector.FACTORY.makeVector(new double[] {1,0,0});
        solver.setLower(vec);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testSetUpperWithWrongDimension() throws Exception {
        Vector vec = Vector.FACTORY.makeVector(new double[] {1,0,0});
        solver.setUpper(vec);
    }

    @Test
    public void testSolverOnProblem1() throws Exception {
        testSolver(new double[] {1,1}, new double[][] {{-1,0}, {0,-1}, {1,1}}, new double[] {0,0,1}, new double[] {0,0});
    }

    @Test
    public void testSolverOnProblem2() throws Exception {
        testSolver(new double[] {-1,1}, new double[][] {{-1,0}, {0,-1}, {1,1}}, new double[] {0,0,1}, new double[] {1,0});
    }

    @Test
    public void testSolverOnProblem3() throws Exception {
        testSolver(new double[] {0,-1}, new double[][] {{-1,0}, {0,-1}, {1,1}}, new double[] {0,0,1}, new double[] {0,1});
    }

    // TODO: check bahavior on unfeasible problems
}
