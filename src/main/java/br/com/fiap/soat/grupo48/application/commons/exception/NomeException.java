package br.com.fiap.soat.grupo48.application.commons.exception;

public class NomeException extends ApplicationException {

    private static final long serialVersionUID = -5702077678463666943L;

    public NomeException(String message) {
        super(message);
    }

    public NomeException(String message, Throwable cause) {
        super(message, cause);
    }
}
