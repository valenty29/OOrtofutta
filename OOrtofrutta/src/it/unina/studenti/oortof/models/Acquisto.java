package it.unina.studenti.oortof.models;


import java.util.List;

public class Acquisto extends ObservedModel {
  private float quantita;
  private float prezzo;
  private Lotto lotto;
  private Scontrino scontrino;

  public Acquisto() {

  }

  public Acquisto(float quantita, float prezzo, Lotto lotto, Scontrino scontrino) {
    this.quantita = quantita;
    this.prezzo = prezzo;
    this.lotto = lotto;
    this.scontrino = scontrino;
  }

  public float getQuantita() {
    return quantita;
  }

  public void setQuantita(float quantita) {
    float oldQuantita = this.quantita;
    this.quantita = quantita;
    firePropertyChanged("quantita", oldQuantita, quantita);
  }

  public float getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(float prezzo) {
    float oldPrezzo = prezzo;
    this.prezzo = prezzo;
    firePropertyChanged("prezzo", oldPrezzo, prezzo);
  }

  public Lotto getLotto() {
    return lotto;
  }

  public void setLotto(Lotto lotto) {
    Lotto oldLotto = this.lotto;
    this.lotto = lotto;
    firePropertyChanged("lotto", oldLotto, lotto);
  }

  public Scontrino getScontrino() {
    return scontrino;
  }

  public void setScontrino(Scontrino scontrino) {
    Scontrino oldScontrino = this.scontrino;
    this.scontrino = scontrino;
    firePropertyChanged("scontrino", oldScontrino, scontrino);
  }

  public void copyTo(Acquisto acquisto) {
    acquisto.setQuantita(getQuantita());
    acquisto.setPrezzo(getPrezzo());
    acquisto.setLotto(getLotto());
    acquisto.setScontrino(getScontrino());
  }

  
  
  public boolean equals(Object other) {
    if (!(other instanceof Acquisto)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    return lotto.equals(((Acquisto)other).lotto) && quantita == ((Acquisto)other).quantita;
  }
  
}
