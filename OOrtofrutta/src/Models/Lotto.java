package Models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Lotto {
    private String codLottoProdotto;
    private Date scadenza;
    private float disponibilita;
    private Date dataProduzione;
    private String codPaeseOrigine;

    public Lotto(String codLottoProdotto, Date scadenza, float disponibilita, Date dataProduzione, String codPaeseOrigine) {
        this.codLottoProdotto = codLottoProdotto;
        this.scadenza = scadenza;
        this.disponibilita = disponibilita;
        this.dataProduzione = dataProduzione;
        this.codPaeseOrigine = codPaeseOrigine;
    }

    public Lotto(ResultSet rSet)
    {
        try {
            codLottoProdotto = rSet.getString(0);
            scadenza = rSet.getDate(3);
            disponibilita = rSet.getFloat(4);
            dataProduzione = rSet.getDate(5);
            codPaeseOrigine = rSet.getString(6);
        } catch (SQLException e)
        {

        }
    }

    public String getCodLottoProdotto() {
        return codLottoProdotto;
    }

    public void setCodLottoProdotto(String codLottoProdotto) {
        this.codLottoProdotto = codLottoProdotto;
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
