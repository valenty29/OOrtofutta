package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bibita extends Prodotto {
  private float gradazioneAlcolica;
  private boolean frizzante;
  private TipoBibita tipoBibita;

  public Bibita(int codProdotto, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, float gradazioneAlcolica, boolean frizzante, TipoBibita tipoBibita) {
    super(codProdotto, nome, prezzo, sfuso, catProdotto);
    this.gradazioneAlcolica = gradazioneAlcolica;
    this.frizzante = frizzante;
    this.tipoBibita = tipoBibita;
  }

  public Bibita(ResultSet rSet) {
    super(rSet);
    try {
      gradazioneAlcolica = rSet.getFloat(5);
      frizzante = rSet.getBoolean(6);
      tipoBibita = TipoBibita.valueOf(rSet.getString(7));
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
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
    return tipoBibita;
  }

  public void setTipoB(TipoBibita tipoB) {
    this.tipoBibita = tipoB;
  }
}
