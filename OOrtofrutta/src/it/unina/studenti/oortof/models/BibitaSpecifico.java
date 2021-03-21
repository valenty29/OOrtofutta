package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;

public class BibitaSpecifico extends ProdottoSpecifico {
  
  public static final int GRADAZIONE_ALCOLICA = 0;   // Float
  public static final int FRIZZANTE = 1;             // Boolean
  public static final int TIPO_BIBITA = 2;           // TipoBibita

  public BibitaSpecifico() {
    attributes = new Object[3];
  }
  
  public BibitaSpecifico(Float gradazioneAlcolica, Boolean frizzante, TipoBibita tipoBibita) {
    this();
    setValue(GRADAZIONE_ALCOLICA, gradazioneAlcolica);
    setValue(FRIZZANTE, frizzante);
    setValue(TIPO_BIBITA, tipoBibita);
  }

  public Float getGradazioneAlcolica() {
    return getFloat(GRADAZIONE_ALCOLICA);
  }
  
  public void setGradazioneAlcolica(Float gradazioneAlcolica) {
    Float oldGradazioneAlcolica = getGradazioneAlcolica();
    if (equals(gradazioneAlcolica, oldGradazioneAlcolica)) {
      return;
    }
    setValue(GRADAZIONE_ALCOLICA, gradazioneAlcolica);
    firePropertyChanged("gradazioneAlcolica", oldGradazioneAlcolica, gradazioneAlcolica);
  }

  public boolean isFrizzante() {
    return isBoolean(FRIZZANTE);
  }
  
  public Boolean getFrizzante() {
    return getBoolean(FRIZZANTE);
  }

  public void setFrizzante(Boolean frizzante) {
    Boolean oldFrizzante = getFrizzante();
    if (equals(frizzante, oldFrizzante)) {
      return;
    }
    setValue(FRIZZANTE, frizzante);
    firePropertyChanged("frizzante", oldFrizzante, frizzante);
  }

  public TipoBibita getTipoBibita() {
    try {
      return TipoBibita.valueOf(getString(TIPO_BIBITA));
    }
    catch (Exception e) {
      return null;
    }
  }

  public void setTipoBibita(TipoBibita tipoBibita) {
    TipoBibita oldTipoBibita = getTipoBibita();
    if (equals(tipoBibita, oldTipoBibita)) {
      return;
    }
    setValue(TIPO_BIBITA, tipoBibita);
    firePropertyChanged("tipoBibita", oldTipoBibita, tipoBibita);
  }
  
  public void copyTo(ObservedModel bibitaSpecifico) {
    ((BibitaSpecifico)bibitaSpecifico).setGradazioneAlcolica(getGradazioneAlcolica());
    ((BibitaSpecifico)bibitaSpecifico).setFrizzante(getFrizzante());
    ((BibitaSpecifico)bibitaSpecifico).setTipoBibita(getTipoBibita());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);    
  }

  @Override
  public void clear() {
    setGradazioneAlcolica(null);
    setFrizzante(null);
    setTipoBibita(null);
  }
  
}
