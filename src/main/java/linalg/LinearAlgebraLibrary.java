package linalg;

/**
 * All linear algebra libraries supported at this moment. Right now, we have three implementations:
 *
 * <ul>
 *     <li>APACHE: Apache Commons Math is an Apache project aiming to provide a very broad LinearAlgebra toolkit.</li>
 *     <li>OJALGO: a third-party library, containing an extensive support for Vector and Matrix operations. It was also
 *     <a href="https://github.com/lessthanoptimal/Java-Matrix-Benchmark">shown</a> to be one of the fastest libraries
 *     available across a large number of operations. </li>
 *     <li>SIMPLE: this is a small vector/matrix library implemented by ourselves, used mostly for performance benchmarking
 *     and testing. You should rather use one of the options above.</li>
 * </ul>
 *
 * @see <a href="http://commons.apache.org/proper/commons-math/">Apache Commons Math website</a>
 * @see <a href="http://ojalgo.org">OjAlgo website</a>
 * @author lucianodp
 */
public enum LinearAlgebraLibrary {
    APACHE, OJALGO, SIMPLE
}
