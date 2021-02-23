package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdottoCaseareo {
    private Prodotto prodotto;
    private String tipoLatte;
    private String stabilimento;
    private int stagionatura;

    public ProdottoCaseareo(Prodotto prodotto, String tipoLatte, String stabilimento, int stagionatura) {
        this.prodotto = prodotto;
        this.tipoLatte = tipoLatte;
        this.stabilimento = stabilimento;
        this.stagionatura = stagionatura;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public String getTipoLatte() {
        return tipoLatte;
    }

    public void setTipoLatte(String tipoLatte) {
        this.tipoLatte = tipoLatte;
    }

    public String getStabilimento() {
        return stabilimento;
    }

    public void setStabilimento(String stabilimento) {
        this.stabilimento = stabilimento;
    }

    public int getStagionatura() {
        return stagionatura;
    }

    public void setStagionatura(int stagionatura) {
        this.stagionatura = stagionatura;
    }
}
