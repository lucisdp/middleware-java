package linalg;

/**
 * Enumeration of all available Linear Algebra libraries. Currently we support:
 * <ul>
 *     <li>OjAlgo, a third-party library</li>
 *     <li>Apache Commons Math, an Apache Project</li>
 *     <li>Simple, which is our own implementation using arrays.</li>
 * </ul>
 */
public enum LinearAlgebraLibrary {
    OJALGO, APACHE, SIMPLE
}
