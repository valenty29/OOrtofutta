package it.unina.studenti.oortof.models;

import java.util.List;

public class ValidationException extends Exception {
    private List<FieldException> exceptionList;

    public ValidationException(List<FieldException> exceptions) {
        this.exceptionList = exceptions;
    }

    public List<FieldException> getExceptionList() {
        return exceptionList;
    }

    @Override
    public String toString() {
        String str = "";
        for (FieldException exception : exceptionList) {
            str += exception.toString() + "\n";
        }
        return str;
    }
}
