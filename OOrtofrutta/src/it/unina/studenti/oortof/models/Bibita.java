package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bibita {
  private Prodotto prodotto;
  private float gradazioneAlcolica;
  private boolean frizzante;
  private TipoBibita tipoBibita;

  public Bibita(Prodotto prodotto, float gradazioneAlcolica, boolean frizzante, TipoBibita tipoBibita) {
    this.prodotto = prodotto;
    this.gradazioneAlcolica = gradazioneAlcolica;
    this.frizzante = frizzante;
    this.tipoBibita = tipoBibita;
  }

  public Prodotto getProdotto() {
    return prodotto;
  }

  public void setProdotto(Prodotto prodotto) {
    this.prodotto = prodotto;
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
