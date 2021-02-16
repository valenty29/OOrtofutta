package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdottoCaseareo extends Prodotto {
    private String tipoLatte;
    private String stabilimento;
    private int stagionatura;

    public ProdottoCaseareo(int CodProdotto, String Nome, float Prezzo, boolean Sfuso, CatProdotto Tipo, String tipoLatte, String stabilimento, int stagionatura) {
        super(CodProdotto, Nome, Prezzo, Sfuso, Tipo);
        this.tipoLatte = tipoLatte;
        this.stabilimento = stabilimento;
        this.stagionatura = stagionatura;
    }

    public ProdottoCaseareo(ResultSet rSet) {
        super(rSet);
        try {
            tipoLatte = rSet.getString(5);
            stabilimento = rSet.getString(6);
            stagionatura = rSet.getInt(7);
        } catch (SQLException e)
        {

        }
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
