package it.unina.studenti.oortof.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Lotto {
    private int id;
    private String codLotto;
    private Date scadenza;
    private float disponibilita;
    private Date dataProduzione;
    private String codPaeseOrigine;

    public Lotto(int id, String codLotto, Date scadenza, float disponibilita, Date dataProduzione, String codPaeseOrigine) {
        this.id = id;
        this.codLotto = codLotto;
        this.scadenza = scadenza;
        this.disponibilita = disponibilita;
        this.dataProduzione = dataProduzione;
        this.codPaeseOrigine = codPaeseOrigine;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodLotto() {
        return codLotto;
    }

    public void setCodLotto(String codLotto) {
        this.codLotto = codLotto;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public float getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(float disponibilita) {
        this.disponibilita = disponibilita;
    }

    public Date getDataProduzione() {
        return dataProduzione;
    }

    public void setDataProduzione(Date dataProduzione) {
        this.dataProduzione = dataProduzione;
    }

    public String getCodPaeseOrigine() {
        return codPaeseOrigine;
    }

    public void setCodPaeseOrigine(String codPaeseOrigine) {
        this.codPaeseOrigine = codPaeseOrigine;
    }
}
