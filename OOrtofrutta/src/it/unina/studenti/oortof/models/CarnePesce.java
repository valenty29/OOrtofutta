package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarnePesce {
  private Prodotto prodotto;
  private TipoCarnePesce tipoCP;
  private boolean daAllevamento;
  private String animale;
  private boolean confezionato;



  public CarnePesce(Prodotto prodotto, TipoCarnePesce tipoCP, boolean daAllevamento, String animale, boolean confezionato) {
    this.prodotto = prodotto;
    this.tipoCP = tipoCP;
    this.daAllevamento = daAllevamento;
    this.animale = animale;
    this.confezionato = confezionato;
  }

  public Prodotto getProdotto() {
    return prodotto;
  }

  public void setProdotto(Prodotto prodotto) {
    this.prodotto = prodotto;
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
