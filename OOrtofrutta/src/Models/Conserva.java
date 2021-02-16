package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Conserva extends Prodotto {
    private TipoConservazione tipoC;
    public Conserva(int CodProdotto, String Nome, float Prezzo, boolean Sfuso, CatProdotto Tipo, TipoConservazione tipoC) {
        super(CodProdotto, Nome, Prezzo, Sfuso, Tipo);
        this.tipoC = tipoC;
    }

    public Conserva(ResultSet rSet) {
        super(rSet);
        try {
            tipoC = TipoConservazione.valueOf(rSet.getString(5));
        } catch (SQLException e)
        {

        }
    }

    public TipoConservazione getTipoC() {
        return tipoC;
    }

    public void setTipoC(TipoConservazione tipoC) {
        this.tipoC = tipoC;
    }
}
