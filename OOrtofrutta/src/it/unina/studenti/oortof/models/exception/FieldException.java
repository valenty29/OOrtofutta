package it.unina.studenti.oortof.models.exception;

import it.unina.studenti.oortof.models.entities.ObservedModel;

import java.util.Optional;

public class FieldException extends Exception {
    private String value;
    private String errorMessage;
    private String expectedFormat;

    public FieldException(String value, String errorMessage, String expectedFormat) {
        this.value = value;
        this.errorMessage = errorMessage;
        this.expectedFormat = expectedFormat;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExpectedFormat() {
        return expectedFormat;
    }

    public void setExpectedFormat(String expectedFormat) {
        this.expectedFormat = expectedFormat;
    }

    @Override
    public String toString() {
        return "Errore validazione: " +
                "Valore=" + value +
                ", messaggio=" + errorMessage + (expectedFormat != null ? ('\'' +
                ", expectedFormat=" + expectedFormat) : "");
    }
}
