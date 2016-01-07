package at.korti.katlonengine.client.model.parser.exception;

/**
 * Created by Korti on 07.01.2016.
 */
public class WaveFrontParseException extends RuntimeException {

    public WaveFrontParseException() {
    }

    public WaveFrontParseException(String message) {
        super(message);
    }

    public WaveFrontParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public WaveFrontParseException(Throwable cause) {
        super(cause);
    }

    public WaveFrontParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
