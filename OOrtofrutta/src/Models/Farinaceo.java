package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Farinaceo extends Prodotto {
    private boolean Glutine;
    private String TipoFarina;
    private boolean Fresco;
    private boolean Surgelato;

    public Farinaceo(int codProdotto, String nome, float prezzo, boolean sfuso, CatProdotto tipo, boolean glutine, String tipoFarina, boolean fresco, boolean surgelato) {
        super(codProdotto, nome, prezzo, sfuso, tipo);
        Glutine = glutine;
        TipoFarina = tipoFarina;
        Fresco = fresco;
        Surgelato = surgelato;
    }

    public Farinaceo(ResultSet rSet)
    {
        super(rSet);
        try {
            Glutine = rSet.getBoolean(5);
            TipoFarina = rSet.getString(6);
            Fresco = rSet.getBoolean(7);
            Surgelato = rSet.getBoolean(8);
        } catch (SQLException e)
        {

        }
    }

    public boolean isGlutine() {
        return Glutine;
    }

    public void setGlutine(boolean glutine) {
        Glutine = glutine;
    }

    public String getTipoFarina() {
        return TipoFarina;
    }

    public void setTipoFarina(String tipoFarina) {
        TipoFarina = tipoFarina;
    }

    public boolean isFresco() {
        return Fresco;
    }

    public void setFresco(boolean fresco) {
        Fresco = fresco;
    }

    public boolean isSurgelato() {
        return Surgelato;
    }

    public void setSurgelato(boolean surgelato) {
        Surgelato = surgelato;
    }
}
