package convex.sampling;

import convex.objects.ConvexBody;
import convex.objects.Polytope;
import exceptions.convex.PointOutsideConvexBodyException;
import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.Vector;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class HitAndRunTest {
    private HitAndRun sampler;
    private ConvexBody elp;

    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.OJALGO);
    }

    @Before
    public void setUp(){
        sampler = new HitAndRun(64, 8);
        elp = new Polytope(new double[][] {{-1,0}, {0,-1}, {1,0}, {0,1}}, new double[] {1,1,1,1}); //new Box(2); //new Ellipsoid(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeChainLength() throws Exception {
        sampler = new HitAndRun(-10, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroChainLength() throws Exception {
        sampler = new HitAndRun(0, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSampleSize() throws Exception {
        sampler = new HitAndRun(10, -8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroSampleSize() throws Exception {
        sampler = new HitAndRun(10, 0);
    }

    @Test(expected = PointOutsideConvexBodyException.class)
    public void testSampleChainWithInitialPointOutside() throws Exception {
        sampler.chain(elp, Vector.FACTORY.make(new double[] {2,0}));
    }

    @Test(expected = PointOutsideConvexBodyException.class)
    public void testSampleUniformWithInitialPointOutside() throws Exception {
        sampler.uniform(elp, Vector.FACTORY.make(new double[] {2,0}));
    }

    @Test
    public void testStep() throws Exception {
        Vector sample = Vector.FACTORY.make(new double[] {0,0});
        for(int i=0; i < 1000; i++) {
            sample = sampler.step(elp, sample);
            assertTrue(elp.isInside(sample));
        }
    }
}
