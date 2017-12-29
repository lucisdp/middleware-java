package utils;

import exceptions.EmptyVectorException;
import exceptions.IncompatibleBoundsException;
import linalg.Vector;

import java.util.Random;

/**
 * This class is responsible for sampling from any necessary probability distributions for our software. It also allows
 * for setting a particular random seed, permitting reproducibility of results.
 *
 * @author lucianodp
 */
public class Sampler {
    private static Random random = new Random();

    /**
     * Sets the random seed.
     * @param seed: random seed
     */
    public static void setSeed(int seed) {random = new Random(seed); }

    /**
     * Computes an iid sample of standard gaussian r.v. of given size.
     * @param size: sample size
     * @return Vector containing the sample
     * @throws EmptyVectorException if size is non-positive
     */
    public static Vector sampleGaussian(int size){
        if (size <= 0)
            throw new EmptyVectorException();

        double[] direction = new double[size];
        for(int i=0; i < size; i++)
            direction[i] = random.nextGaussian();

        return new Vector(direction);
    }

    /**
     * Samples a single uniform r.v. in the interval [low, high]
     * @param low: lower bound of interval
     * @param high: upper bound of interval
     * @return random sample
     * @throws IncompatibleBoundsException if low is not smaller than high
     */
    public static double sampleUniform(double low, double high){
        if (high <= low)
            throw new IncompatibleBoundsException();
        return low + random.nextDouble() * (high - low);
    }
}
