package exceptions;

public class ParametersWereEmptyException extends Throwable
{
    public ParametersWereEmptyException(String message) {
        super(message);
    }
}
