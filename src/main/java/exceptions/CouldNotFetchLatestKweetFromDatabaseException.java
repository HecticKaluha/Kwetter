package exceptions;

public class CouldNotFetchLatestKweetFromDatabaseException extends Exception
{
    public CouldNotFetchLatestKweetFromDatabaseException(String message) {
        super(message);
    }
}
