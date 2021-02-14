package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarnePesce extends Prodotto {
    private TipoCarnePesce tipoCP;
    private boolean daAllevamento;
    private String animale;
    private boolean confezionato;

    public CarnePesce(int CodProdotto, String Nome, float Prezzo, boolean Sfuso, CatProdotto tipo, TipoCarnePesce tipoCP, boolean daAllevamento, String Animale, boolean Confezionato)
    {
        super(CodProdotto, Nome, Prezzo, Sfuso, tipo);
        this.tipoCP = tipoCP;
        this.daAllevamento = daAllevamento;
        this.animale = animale;
        this.confezionato = confezionato;
    }

    public CarnePesce(ResultSet rSet)
    {
        super(rSet);
        try {
            tipoCP = TipoCarnePesce.valueOf(rSet.getString(5));
            daAllevamento = rSet.getBoolean(6);
            animale = rSet.getString(7);
            confezionato = rSet.getBoolean(8);
        } catch (SQLException e)
        {

        }
    }

    public TipoCarnePesce getTipoCP() {
        return tipoCP;
    }

    public void setTipoCP(TipoCarnePesce tipoCP) {
        this.tipoCP = tipoCP;
    }

    public boolean isDaAllevamento() {
        return daAllevamento;
    }

    public void setDaAllevamento(boolean daAllevamento) {
        this.daAllevamento = daAllevamento;
    }

    public String getAnimale() {
        return animale;
    }

    public void setAnimale(String animale) {
        this.animale = animale;
    }

    public boolean isConfezionato() {
        return confezionato;
    }

    public void setConfezionato(boolean confezionato) {
        this.confezionato = confezionato;
    }
}
