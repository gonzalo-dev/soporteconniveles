package net.ejemplo.soporte.modelos;

/**
 *
 * @author gonzalo.trejo
 */
public class NoHayCapacidadException extends Exception{

    public NoHayCapacidadException() {
    }

    public NoHayCapacidadException(String message) {
        super(message);
    }

    public NoHayCapacidadException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoHayCapacidadException(Throwable cause) {
        super(cause);
    }

    public NoHayCapacidadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    
}
