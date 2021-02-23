package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Conserva {
    private Prodotto prodotto;
    private TipoConservazione tipoC;
    public Conserva(Prodotto prodotto, TipoConservazione tipoC) {
        this.prodotto = prodotto;
        this.tipoC = tipoC;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public TipoConservazione getTipoC() {
        return tipoC;
    }

    public void setTipoC(TipoConservazione tipoC) {
        this.tipoC = tipoC;
    }
}
