package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;

public class FruttaVerduraSpecifico extends ProdottoSpecifico {

  public static final int TIPO_FRUTTA_VERDURA = 0; // TipoFruttaVerdura
  public static final int BIO = 1;                 // Boolean
  public static final int SURGELATO = 2;           // Boolean
  
  public FruttaVerduraSpecifico() {
    attributes = new Object[3];
  }

  public FruttaVerduraSpecifico(TipoFruttaVerdura tipoFruttaVerdura, boolean bio, boolean surgelato) {
    this();
    setValue(TIPO_FRUTTA_VERDURA, tipoFruttaVerdura);
    setValue(BIO, bio);
    setValue(SURGELATO, surgelato);
  }

  public TipoFruttaVerdura getTipoFruttaVerdura() {
    try {
      return TipoFruttaVerdura.valueOf(getString(TIPO_FRUTTA_VERDURA));
    }
    catch (Exception e) {
      return null;
    }
  }

  public void setTipoFruttaVerdura(TipoFruttaVerdura tipoFruttaVerdura) {
    TipoFruttaVerdura oldTipoFruttaVerdura = getTipoFruttaVerdura();
    if (equals(tipoFruttaVerdura, oldTipoFruttaVerdura)) {
      return;
    }
    setValue(TIPO_FRUTTA_VERDURA, tipoFruttaVerdura);
    firePropertyChange("tipoFruttaVerdura", oldTipoFruttaVerdura, tipoFruttaVerdura);    
  }

  public boolean isBio() {
    return isBoolean(BIO);
  }
  
  public Boolean getBio() {
    return getBoolean(BIO);
  }

  public void setBio(Boolean bio) {
    Boolean oldBio = getBio();
    if (equals(bio, oldBio)) {
      return;
    }
    setValue(BIO, bio);
    firePropertyChange("bio", oldBio, bio);
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
    firePropertyChange("surgelato", oldSurgelato, surgelato);
  }

  public void copyTo(ObservedModel fruttaVerduraSpecifico) {
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setTipoFruttaVerdura(getTipoFruttaVerdura());
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setBio(getBio());
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setSurgelato(getSurgelato());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);    
  }

  @Override
  public void clear() {
    setTipoFruttaVerdura(null);
    setBio(null);
    setSurgelato(null);
  }
  

}
