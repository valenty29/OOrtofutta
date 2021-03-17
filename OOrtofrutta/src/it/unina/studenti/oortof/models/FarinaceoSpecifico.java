package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;

public class FarinaceoSpecifico extends ProdottoSpecifico {
  
  public static final int GLUTINE = 0;     //Boolean
  public static final int TIPO_FARINA = 1; //TipoFarina
  public static final int FRESCO = 2;      //Boolean
  public static final int SURGELATO = 3;   //Boolean

  public FarinaceoSpecifico() {
  }

  public FarinaceoSpecifico(boolean glutine, String tipoFarina, boolean fresco, boolean surgelato) {
    this();
    setValue(GLUTINE, glutine);
    setValue(TIPO_FARINA, tipoFarina);
    setValue(FRESCO, fresco);
    setValue(SURGELATO, surgelato);
  }

  public boolean isGlutine() {
    return isBoolean(GLUTINE);
  }
  
  public Boolean getGlutine() {
    return getBoolean(GLUTINE);
  }

  public void setGlutine(Boolean glutine) {
    Boolean oldGlutine = getGlutine();
    if (equals(glutine, oldGlutine)) {
      return;
    }
    setValue(GLUTINE, glutine);
    firePropertyChanged("glutine", oldGlutine, glutine);
  }

  public String getTipoFarina() {
    return getString(TIPO_FARINA);
  }

  public void setTipoFarina(String tipoFarina) {
    String oldTipoFarina = getTipoFarina();
    if (equals(tipoFarina, oldTipoFarina)) {
      return;
    }
    setValue(TIPO_FARINA, tipoFarina);
    firePropertyChanged("tipoFarina", oldTipoFarina, tipoFarina);
  }

  public boolean isFresco() {
    return isBoolean(FRESCO);
  }
  
  public Boolean getFresco() {
    return getBoolean(FRESCO);
  }

  public void setFresco(Boolean fresco) {
    Boolean oldFresco = getFresco();
    if (equals(fresco, oldFresco)) {
      return;
    }
    setValue(FRESCO, fresco);
    firePropertyChanged("fresco", oldFresco, fresco);
  }

  public boolean isSurgelato() {
    return isBoolean(SURGELATO);
  }

  public Boolean getSurgelato() {
    return getBoolean(SURGELATO);
  }
  
  public void setSurgelato(Boolean surgelato) {
    Boolean oldSurgelato = getSurgelato();
    if (equals(surgelato, oldSurgelato)) {
      return;
    }
    setValue(SURGELATO, surgelato);
    firePropertyChanged("surgelato", oldSurgelato, surgelato);
  }
  
  public void copyTo(ProdottoSpecifico farinaceoSpecifico) {
    ((FarinaceoSpecifico)farinaceoSpecifico).setGlutine(isGlutine());
    ((FarinaceoSpecifico)farinaceoSpecifico).setTipoFarina(getTipoFarina());
    ((FarinaceoSpecifico)farinaceoSpecifico).setFresco(isFresco());
    ((FarinaceoSpecifico)farinaceoSpecifico).setSurgelato(isSurgelato());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);   
  }

  @Override
  public void clear() {
    setGlutine(null);
    setTipoFarina(null);
    setFresco(null);
    setSurgelato(null);
  }

}
