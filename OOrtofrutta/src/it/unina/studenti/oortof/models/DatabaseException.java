package it.unina.studenti.oortof.models;

public class DatabaseException extends Exception {
    private String errorMessage;

    public DatabaseException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
