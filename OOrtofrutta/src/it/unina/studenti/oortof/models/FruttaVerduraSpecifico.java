package it.unina.studenti.oortof.models;

public class FruttaVerduraSpecifico extends ProdottoSpecifico {

  public static final int TIPO_FRUTTA_VERDURA = 0; // TipoFruttaVerdura
  public static final int BIO = 1;                 // Boolean
  public static final int SURGELATO = 2;           // Boolean
  
  public FruttaVerduraSpecifico() {
    attributes = new Object[3];
  }

  public FruttaVerduraSpecifico(TipoFruttaVerdura tipoFruttaVerdura, boolean bio, boolean surgelato) {
    setValue(TIPO_FRUTTA_VERDURA, tipoFruttaVerdura);
    setValue(BIO, bio);
    setValue(SURGELATO, surgelato);
  }

  public TipoFruttaVerdura getTipoFruttaVerdura() {
    return TipoFruttaVerdura.valueOf(getString(TIPO_FRUTTA_VERDURA));
  }

  public void setTipoFruttaVerdura(TipoFruttaVerdura tipoFruttaVerdura) {
    TipoFruttaVerdura oldTipoFruttaVerdura = TipoFruttaVerdura.valueOf(getString(TIPO_FRUTTA_VERDURA));
    setValue(TIPO_FRUTTA_VERDURA, tipoFruttaVerdura);
    firePropertyChanged("tipoFruttaVerdura", oldTipoFruttaVerdura, tipoFruttaVerdura);    
  }

  public boolean isBio() {
    return isBoolean(BIO);
  }
  
  public Boolean getBio() {
    return getBoolean(BIO);
  }

  public void setBio(Boolean bio) {
    Boolean oldBio = getBio();
    setValue(BIO, bio);
    firePropertyChanged("bio", oldBio, bio);
  }

  public boolean isSurgelato() {
    return isBoolean(SURGELATO);
  }
  
  public Boolean getSurgelato() {
    return getBoolean(SURGELATO);
  }

  public void setSurgelato(boolean surgelato) {
    boolean oldSurgelato = getSurgelato();
    setValue(SURGELATO, surgelato);
    firePropertyChanged("surgelato", oldSurgelato, surgelato);
  }

  public void copyTo(ProdottoSpecifico fruttaVerduraSpecifico) {
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setTipoFruttaVerdura(getTipoFruttaVerdura());
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setBio(getBio());
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setSurgelato(getSurgelato());
  }
  

}
