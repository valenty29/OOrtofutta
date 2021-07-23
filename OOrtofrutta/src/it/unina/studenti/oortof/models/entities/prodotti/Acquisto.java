package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Acquisto extends ObservedModel implements PropertyChangeListener {
  
  public static final int QUANTITA = 0;  //Float
  public static final int PREZZO = 1;    //Float
  public static final int LOTTO = 2;     //Lotto
  public static final int TIPO_PRODOTTO = 3; //Lotto
  public static final int NOME_PRODOTTO = 4;
  
  public Acquisto() {
    attributes = new Object[5];
    setLotto(new Lotto());
  }
  
  public Acquisto(Float quantita, Float prezzo, Lotto lotto, String tipoProdotto, String nomeProdotto) {
    this();
    setValue(QUANTITA, quantita);
    setValue(PREZZO, prezzo);
    setValue(LOTTO, lotto);
    setValue(TIPO_PRODOTTO, tipoProdotto);
    setValue(NOME_PRODOTTO, nomeProdotto);
  }

  public Float getQuantita() {
    return getFloat(QUANTITA);
  }

  public void setQuantita(Float quantita) {
    Float oldQuantita = getQuantita();
    if (equals(oldQuantita, quantita)) {
      return;
    }
    setValue(QUANTITA, quantita);
    firePropertyChange("quantita", oldQuantita, quantita);
  }

  public Float getPrezzo() {
    return getFloat(PREZZO);
  }

  public void setPrezzo(Float prezzo) {
    Float oldPrezzo = getPrezzo();
    if (equals(oldPrezzo, prezzo)) {
      return;
    }
    setValue(PREZZO, prezzo);
    firePropertyChange("prezzo", oldPrezzo, prezzo);
  }

  public String getNomeProdotto() {
    return getString(NOME_PRODOTTO);
  }

  public void setNomeProdotto(String nomeProdotto) {
    String oldNomeProdotto = getTipoProdotto();
    if (equals(oldNomeProdotto, nomeProdotto)) {
      return;
    }
    setValue(NOME_PRODOTTO, nomeProdotto);
    firePropertyChange("nomeProdotto", oldNomeProdotto, nomeProdotto);
  }


  public String getTipoProdotto() {
    return getString(TIPO_PRODOTTO);
  }

  public void setTipoProdotto(String tipoProdotto) {
    String oldTipoProdotto = getTipoProdotto();
    if (equals(oldTipoProdotto, tipoProdotto)) {
      return;
    }
    setValue(TIPO_PRODOTTO, tipoProdotto);
    firePropertyChange("tipoProdotto", oldTipoProdotto, tipoProdotto);
  }

  public Lotto getLotto() {
    return (Lotto)attributes[LOTTO];
  }

  public void setLotto(Lotto lotto) {
    Lotto oldLotto = getLotto();
    if (equals(oldLotto, lotto)) {
      return;
    }
    if (oldLotto != null) {
    	oldLotto.removePropertyChangeListener(this);
    }

    setValue(LOTTO, lotto);
    lotto.addPropertyChangeListener(this);
    firePropertyChange("lotto", oldLotto, lotto);
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

  public void copyTo(ObservedModel acquisto) {
    ((Acquisto)acquisto).setQuantita(getQuantita());
    ((Acquisto)acquisto).setPrezzo(getPrezzo());
    ((Acquisto)acquisto).setLotto(getLotto());
    ((Acquisto)acquisto).setNomeProdotto(getNomeProdotto());
    ((Acquisto)acquisto).setTipoProdotto(getTipoProdotto());
  }
}
