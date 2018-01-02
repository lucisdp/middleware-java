package exceptions;

public class IncompatibleLinearAlgebraBackendException extends RuntimeException {
    public IncompatibleLinearAlgebraBackendException() {
        super("Trying to perform operation with two objects of incompatible types. Check you are not mixing distinct Vector/Matrix classes.");
    }
}
