package it.unina.studenti.oortof.gui.models;

import java.util.regex.Pattern;

public class InputCheckRules {
    static public InputCheckRule numeriSpazio = new InputCheckRule(".*[^0-9 ]", "Inserire numeri o spazio");
    static public InputCheckRule soloNumeri = new InputCheckRule(".*[^0-9]", "Inserire solo numeri");
    static public InputCheckRule soloLettere = new InputCheckRule(".*[^a-zA-Z]", "Inserire solo lettere");
    static public InputCheckRule lettereSpazio = new InputCheckRule(".*[^a-zA-Z ]", "Inserire lettere o spazio");
}
