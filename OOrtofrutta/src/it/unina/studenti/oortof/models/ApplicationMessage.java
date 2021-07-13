package it.unina.studenti.oortof.models;

public class ApplicationMessage {
    private String message;
    private int level;

    public ApplicationMessage(String message, int level) {
        this.message = message;
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }



}
