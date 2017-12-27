package linear_programming;

import exceptions.NegativeDimensionException;
import org.junit.Before;
import org.junit.Test;

public class ApacheLinearProgramSolverTest extends LinearProgramSolverTest {
    @Before
    public void setUp() throws Exception {
        solver = new ApacheLinearProgramSolver(dim);
    }

    @Test(expected = NegativeDimensionException.class)
    public void testNegativeDimension() throws Exception {
        new OjalgoLinearProgramSolver(-1);
    }
}
