package it.unina.studenti.oortof.models;

import java.util.Date;

public class Lotto {
  private int id;
  private Prodotto prodotto;
  private String codLotto;
  private Date scadenza;
  private float disponibilita;
  private Date dataProduzione;
  private String codPaeseOrigine;

  public Lotto(int id, Prodotto prodotto, String codLotto, Date scadenza, float disponibilita, Date dataProduzione, String codPaeseOrigine) {
    this.id = id;
    this.prodotto = prodotto;
    this.codLotto = codLotto;
    this.scadenza = scadenza;
    this.disponibilita = disponibilita;
    this.dataProduzione = dataProduzione;
    this.codPaeseOrigine = codPaeseOrigine;
  }

  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public Prodotto getProdotto() {
    return prodotto;
  }
  
  public void setProdotto(Prodotto prodotto) {
    this.prodotto = prodotto;
  }

  public String getCodLotto() {
    return codLotto;
  }

  public void setCodLotto(String codLotto) {
    this.codLotto = codLotto;
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
  
  public String toString() {
    return codLotto;
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof Lotto)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (id > 0 && ((Lotto)other).id > 0) {
      return id == ((Lotto)other).id;
    }
    return prodotto.equals(((Lotto)other).prodotto) && codLotto.equals(((Lotto)other).codLotto);
  }
}
