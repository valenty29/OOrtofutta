package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FruttaVerdura  {
    private Prodotto prodotto;
    private TipoFruttaVerdura tipoFV;
    private boolean bio;
    private boolean surgelato;



    public FruttaVerdura(Prodotto prodotto, TipoFruttaVerdura tipoFV, boolean bio, boolean surgelato)
    {
        this.prodotto = prodotto;
        this.tipoFV = tipoFV;
        this.bio = bio;
        this.surgelato = surgelato;
    }


    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public TipoFruttaVerdura getTipoFV() {
        return tipoFV;
    }

    public void setTipoFV(TipoFruttaVerdura tipoFV) {
        this.tipoFV = tipoFV;
    }

    public boolean isBio() {
        return bio;
    }

    public void setBio(boolean bio) {
        this.bio = bio;
    }

    public boolean isSurgelato() {
        return surgelato;
    }

    public void setSurgelato(boolean surgelato) {
        this.surgelato = surgelato;
    }
}
