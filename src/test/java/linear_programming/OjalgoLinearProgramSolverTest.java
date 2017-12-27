package linear_programming;

import exceptions.NegativeDimensionException;
import org.junit.Before;
import org.junit.Test;

public class OjalgoLinearProgramSolverTest extends LinearProgramSolverTest {
    @Before
    public void setUp() throws Exception {
        solver = LinearProgramSolver.getLinearProgramSolver("ojalgo", dim);
    }

    @Test(expected = NegativeDimensionException.class)
    public void testNegativeDimension() throws Exception {
        LinearProgramSolver.getLinearProgramSolver("apache", -1);
    }
}
