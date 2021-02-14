package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bibita extends Prodotto {
    private float gradazioneAlcolica;
    private boolean frizzante;
    private TipoBibita tipoB;

    public Bibita(int CodProdotto, String Nome, float Prezzo, boolean Sfuso, CatProdotto Tipo, float gradazioneAlcolica, boolean frizzante, TipoBibita tipoB) {
        super(CodProdotto, Nome, Prezzo, Sfuso, Tipo);
        this.gradazioneAlcolica = gradazioneAlcolica;
        this.frizzante = frizzante;
        this.tipoB = tipoB;
    }

    public Bibita(ResultSet rSet) {
        super(rSet);
        try {
            gradazioneAlcolica = rSet.getFloat(5);
            frizzante = rSet.getBoolean(6);
            tipoB = TipoBibita.valueOf(rSet.getString(7));
        } catch (SQLException e)
        {

        }
    }

    public float getGradazioneAlcolica() {
        return gradazioneAlcolica;
    }

    public void setGradazioneAlcolica(float gradazioneAlcolica) {
        this.gradazioneAlcolica = gradazioneAlcolica;
    }

    public boolean isFrizzante() {
        return frizzante;
    }

    public void setFrizzante(boolean frizzante) {
        this.frizzante = frizzante;
    }

    public TipoBibita getTipoB() {
        return tipoB;
    }

    public void setTipoB(TipoBibita tipoB) {
        this.tipoB = tipoB;
    }
}
