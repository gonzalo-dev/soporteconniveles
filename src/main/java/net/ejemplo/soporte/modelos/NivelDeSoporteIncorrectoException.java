package net.ejemplo.soporte.modelos;

/**
 *
 * @author gonzalo.trejo
 */
public class NivelDeSoporteIncorrectoException extends Exception {

    public NivelDeSoporteIncorrectoException() {
    }

    public NivelDeSoporteIncorrectoException(String message) {
        super(message);
    }

    public NivelDeSoporteIncorrectoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NivelDeSoporteIncorrectoException(Throwable cause) {
        super(cause);
    }

    public NivelDeSoporteIncorrectoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
