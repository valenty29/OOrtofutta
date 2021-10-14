package it.unina.studenti.oortof.dao;

import it.unina.studenti.oortof.models.exception.FieldException;

public class DAOHelpers {
    public static String getFloatQueryField(String query) throws FieldException {

        String[] queryDestr = query.split(" ");
        String finalQuery = "";
        try {
            float f1 = Float.parseFloat(queryDestr[0]);

            try {
                float f2 = Float.parseFloat(queryDestr[1]);

                finalQuery += String.format("BETWEEN %f AND %f", f1, f2);
                finalQuery = finalQuery.replace(",", ".");
            }

            catch (ArrayIndexOutOfBoundsException oob) {
                finalQuery += String.format("= %f", f1);
                finalQuery = finalQuery.replace(",", ".");
            }

            catch (NumberFormatException nfef){
                String operator = queryDestr[1];

                finalQuery += String.format("%s %f", operator, f1);
                finalQuery = finalQuery.replace(",", ".");
            }

        } catch (Exception nfe){
            throw new FieldException(query, "Sintassi ricerca errata", "Sintassi corretta: [numero] < | [numero] > | [numero] [numero]");
        }
        return finalQuery;
    }

    public static String getIntQueryField(String query){

        String[] queryDestr = query.split(" ");
        String finalQuery = "";
        try {
            int i1 = Integer.parseInt(queryDestr[0]);

            try {
                int i2 = Integer.parseInt(queryDestr[1]);

                finalQuery += String.format("BETWEEN %d AND %d", i1, i2);
                finalQuery = finalQuery.replace(",", ".");
            }

            catch (ArrayIndexOutOfBoundsException oob) {
                finalQuery += String.format("= %d", i1);
                finalQuery = finalQuery.replace(",", ".");
            }

            catch (NumberFormatException nfef){
                String operator = queryDestr[1];

                finalQuery += String.format("%s %d", operator, i1);
                finalQuery = finalQuery.replace(",", ".");
            }

        } catch (Exception nfe){
            System.out.println("PASSATO VALORE INVALIDO A QUERY");
        }

        return finalQuery;
    }
}
