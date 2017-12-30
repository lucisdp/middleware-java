package utils;

import exceptions.IncompatibleBoundsException;
import exceptions.NegativeDimensionException;
import linalg.Vector;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class SamplerTest {

    @Test(expected = NegativeDimensionException.class)
    public void testNegativeSizeInGaussianSampler() throws Exception {
        Sampler.sampleGaussian(-1);
    }

    @Test(expected = NegativeDimensionException.class)
    public void testZeroSizeInGaussianSampler() throws Exception {
        Sampler.sampleGaussian(0);
    }

    @Test(expected = IncompatibleBoundsException.class)
    public void testEqualBoundsInUniformSampler() throws Exception {
        Sampler.sampleUniform(-4, -4);
    }

    @Test(expected = IncompatibleBoundsException.class)
    public void testIncompatibleBoundsInUniformSampler() throws Exception {
        Sampler.sampleUniform(0, -4);
    }

    @Test
    public void testNormalDistribution() throws Exception {
        Vector normal = Sampler.sampleGaussian(10000);
        KolmogorovSmirnovTest test = new KolmogorovSmirnovTest();
        double pValue = test.kolmogorovSmirnovTest(new NormalDistribution(0,1), normal.asArray());
        assertTrue(pValue > 0.01);
    }

    @Test
    public void testUniformDistribution() throws Exception {
        double[] uniform = new double[10000];
        for(int i=0; i < 10000; i++)
            uniform[i] = Sampler.sampleUniform(-2,3);
        KolmogorovSmirnovTest test = new KolmogorovSmirnovTest();
        double pValue = test.kolmogorovSmirnovTest(new UniformRealDistribution(-2,3), uniform);
        assertTrue(pValue > 0.01);
    }


}
