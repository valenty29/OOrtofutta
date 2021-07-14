package it.unina.studenti.oortof.gui.models;

import java.util.regex.Pattern;

public class InputCheckRule {
    Pattern pattern;
    String errorMessage;

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
