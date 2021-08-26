package it.unina.studenti.oortof.models.exception;

import java.util.List;

public class BatchDatabaseException extends Exception{
    private List<DatabaseException> exceptionList;

    public BatchDatabaseException(List<DatabaseException> exceptions) {
        this.exceptionList = exceptions;
    }

    public List<DatabaseException> getExceptionList() {
        return exceptionList;
    }

    @Override
    public String toString() {
        String str = "";
        for (DatabaseException exception : exceptionList) {
            str += exception.toString() + "\n";
        }
        return str;
    }
}
