package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Farinaceo {
    private Prodotto prodotto;
    private boolean glutine;
    private String tipoFarina;
    private boolean fresco;
    private boolean surgelato;

    public Farinaceo(Prodotto prodotto, int id, String nome, float prezzo, boolean sfuso, CatProdotto tipo, boolean glutine, String tipoFarina, boolean fresco, boolean surgelato) {
        this.prodotto = prodotto;
        this.glutine = glutine;
        this.tipoFarina = tipoFarina;
        this.fresco = fresco;
        this.surgelato = surgelato;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public boolean isGlutine() {
        return glutine;
    }

    public void setGlutine(boolean glutine) {
        this.glutine = glutine;
    }

    public String getTipoFarina() {
        return tipoFarina;
    }

    public void setTipoFarina(String tipoFarina) {
        this.tipoFarina = tipoFarina;
    }

    public boolean isFresco() {
        return fresco;
    }

    public void setFresco(boolean fresco) {
        this.fresco = fresco;
    }

    public boolean isSurgelato() {
        return surgelato;
    }

    public void setSurgelato(boolean surgelato) {
        this.surgelato = surgelato;
    }
}
