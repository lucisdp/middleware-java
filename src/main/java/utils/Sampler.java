package utils;

import exceptions.EmptyVectorException;
import exceptions.IncompatibleBoundsException;
import linalg.Vector;

import java.util.Random;


public class Sampler {
    private static Random random = new Random();

    public static Vector sampleGaussian(int size){
        if (size <= 0)
            throw new EmptyVectorException();

        double[] direction = new double[size];
        for(int i=0; i < size; i++)
            direction[i] = random.nextGaussian();

        return new Vector(direction);
    }

    public static double sampleUniform(double low, double high){
        if (high <= low)
            throw new IncompatibleBoundsException();
        return low + random.nextDouble() * (high - low);
    }
}
