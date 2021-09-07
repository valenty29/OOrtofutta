package it.unina.studenti.oortof.models.exception;

@SuppressWarnings("serial")
public class DatabaseException extends Exception {
    private String errorMessage;

    public DatabaseException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
