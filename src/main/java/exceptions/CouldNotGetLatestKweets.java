package exceptions;

public class CouldNotGetLatestKweets extends Throwable
{
    public CouldNotGetLatestKweets(String message) {
        super(message);
    }
}
