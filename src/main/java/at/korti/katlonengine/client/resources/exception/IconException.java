package at.korti.katlonengine.client.resources.exception;

/**
 * Created by Korti on 04.02.2016.
 */
public class IconException extends RuntimeException {

    public IconException(String message) {
        super(message);
    }

    public IconException(String message, Throwable cause) {
        super(message, cause);
    }

    public IconException(Throwable cause) {
        super(cause);
    }

    public IconException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
