package it.unina.studenti.oortof.models;


public class Acquisto {
  private float quantita;
  private float prezzo;
  private Lotto lotto;
  private Scontrino scontrino;
  
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
    this.quantita = quantita;
  }

  public float getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(float prezzo) {
    this.prezzo = prezzo;
  }

  public Lotto getLotto() {
    return lotto;
  }

  public void setLotto(Lotto lotto) {
    this.lotto = lotto;
  }

  public Scontrino getScontrino() {
    return scontrino;
  }

  public void setScontrino(Scontrino scontrino) {
    this.scontrino = scontrino;
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
