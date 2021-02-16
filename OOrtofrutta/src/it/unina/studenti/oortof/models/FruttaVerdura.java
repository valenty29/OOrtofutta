package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FruttaVerdura extends Prodotto {
    private TipoFruttaVerdura tipoFV;
    private boolean bio;
    private boolean surgelato;



    public FruttaVerdura(int CodProdotto, String Nome, float Prezzo, boolean Sfuso, CatProdotto Tipo, TipoFruttaVerdura tipoFV, boolean bio, boolean surgelato)
    {
        super(CodProdotto, Nome, Prezzo, Sfuso, Tipo);
        this.tipoFV = tipoFV;
        this.bio = bio;
        this.surgelato = surgelato;
    }

    public FruttaVerdura(ResultSet rSet)
    {
        super(rSet);
        try {
            tipoFV = TipoFruttaVerdura.valueOf(rSet.getString(5));
            bio = rSet.getBoolean(6);
            surgelato = rSet.getBoolean(7);
        } catch (SQLException e) {
        }
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
