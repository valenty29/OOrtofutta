package it.unina.studenti.oortof.gui.models;

import java.util.regex.Pattern;

public class InputCheckRule {
    Pattern pattern;
    String errorMessage;

    static public InputCheckRule numeriSpazio = new InputCheckRule(".*[^0-9 <>.]", "Inserire numeri, punto, spazio o < >");
    static public InputCheckRule soloNumeri = new InputCheckRule(".*[^0-9]", "Inserire solo numeri");
    static public InputCheckRule soloLettere = new InputCheckRule(".*[^a-zA-Z]", "Inserire solo lettere");
    static public InputCheckRule lettereSpazio = new InputCheckRule(".*[^a-zA-Z ]", "Inserire lettere o spazio");

    public InputCheckRule(String pattern, String errorMessage){
        this.pattern = Pattern.compile(pattern);
        this.errorMessage = errorMessage;
    }

    public Pattern getPattern(){
        return pattern;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
