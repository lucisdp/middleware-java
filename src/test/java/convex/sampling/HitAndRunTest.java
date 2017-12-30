package convex.sampling;

import convex.objects.ConvexBody;
import convex.objects.Polytope;
import exceptions.PointOutsideConvexBodyException;
import linalg.Vector;
import org.junit.Before;
import org.junit.Test;
import utils.LinearAlgebraConfiguration;

import static org.junit.Assert.assertTrue;


public class HitAndRunTest {
    private HitAndRun sampler;
    private ConvexBody elp;

    @Before
    public void setUp(){
        LinearAlgebraConfiguration.setLibraryFromConfig();
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
        sampler.chain(elp, new Vector(new double[] {2,0}));
    }

    @Test(expected = PointOutsideConvexBodyException.class)
    public void testSampleUniformWithInitialPointOutside() throws Exception {
        sampler.uniform(elp, new Vector(new double[] {2,0}));
    }

    @Test
    public void testStep() throws Exception {
        Vector sample = new Vector(new double[] {0,0});
        for(int i=0; i < 1000; i++) {
            sample = sampler.step(elp, sample);
            assertTrue(elp.isInside(sample));
        }
    }
}
