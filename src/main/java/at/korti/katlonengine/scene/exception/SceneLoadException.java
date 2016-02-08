package at.korti.katlonengine.scene.exception;

/**
 * Created by Korti on 08.02.2016.
 */
public class SceneLoadException extends RuntimeException {

    public SceneLoadException() {
    }

    public SceneLoadException(String message) {
        super(message);
    }

    public SceneLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public SceneLoadException(Throwable cause) {
        super(cause);
    }

    public SceneLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
