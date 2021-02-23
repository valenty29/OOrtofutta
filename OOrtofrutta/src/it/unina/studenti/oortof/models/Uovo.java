package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Uovo {
    private Prodotto prodotto;
    private int tipoAllevamento;
    private String codAllevamento;
    private CatPeso catPeso;

    public Uovo(Prodotto prodotto, int tipoAllevamento, String codAllevamento, it.unina.studenti.oortof.models.CatPeso catPeso) {
        //super(codProdotto, nome, prezzo, sfuso, tipo);
        this.prodotto = prodotto;
        this.tipoAllevamento = tipoAllevamento;
        this.codAllevamento = codAllevamento;
        this.catPeso = catPeso;
    }


    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getTipoAllevamento() {
        return tipoAllevamento;
    }

    public void setTipoAllevamento(int tipoAllevamento) {
        this.tipoAllevamento = tipoAllevamento;
    }

    public String getCodAllevamento() {
        return codAllevamento;
    }

    public void setCodAllevamento(String codAllevamento) {
        this.codAllevamento = codAllevamento;
    }


    public it.unina.studenti.oortof.models.CatPeso getCatPeso() {
        return catPeso;
    }

    public void setCatPeso(it.unina.studenti.oortof.models.CatPeso catPeso) {
        this.catPeso = catPeso;
    }
}
