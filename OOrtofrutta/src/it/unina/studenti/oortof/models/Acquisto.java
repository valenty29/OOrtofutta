package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Acquisto extends ObservedModel implements PropertyChangeListener {
  
  public static final int QUANTITA = 0;  //Float
  public static final int PREZZO = 1;    //Float
  public static final int LOTTO = 2;     //Lotto
  
  public Acquisto() {
    attributes = new Object[3];
  }
  
  public Acquisto(Float quantita, Float prezzo, Lotto lotto) {
    this();
    setValue(QUANTITA, quantita);
    setValue(PREZZO, prezzo);
    setValue(LOTTO, lotto);
  }

  public Float getQuantita() {
    return getFloat(QUANTITA);
  }

  public void setQuantita(Float quantita) {
    float oldQuantita = getQuantita();
    setValue(QUANTITA, quantita);
    firePropertyChanged("quantita", oldQuantita, quantita);
  }

  public Float getPrezzo() {
    return getFloat(PREZZO);
  }

  public void setPrezzo(Float prezzo) {
    float oldPrezzo = getPrezzo();
    setValue(PREZZO, prezzo);
    firePropertyChanged("prezzo", oldPrezzo, prezzo);
  }

  public Lotto getLotto() {
    return (Lotto)attributes[LOTTO];
  }

  public void setLotto(Lotto lotto) {
    if (lotto == attributes[LOTTO]) {
      return;
    }
    lotto.addPropertyChangeListener(this);
    Lotto oldLotto = (Lotto)attributes[LOTTO];
    attributes[LOTTO] = lotto;
    firePropertyChanged("lotto", oldLotto, lotto);
  }

  public boolean equals(Object other) {
    if (!(other instanceof Acquisto)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    return getLotto().equals(((Acquisto)other).getLotto()) && getQuantita() == ((Acquisto)other).getQuantita();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
    
  }

}
