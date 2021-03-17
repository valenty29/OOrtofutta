package it.unina.studenti.oortof.models;

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
    return getBoolean(GLUTINE);
  }

  public void setGlutine(boolean glutine) {
    boolean oldGlutine = isGlutine();
    setValue(GLUTINE, glutine);
    firePropertyChanged("glutine", oldGlutine, glutine);
  }

  public String getTipoFarina() {
    return getString(TIPO_FARINA);
  }

  public void setTipoFarina(String tipoFarina) {
    String oldTipoFarina = getTipoFarina();
    setValue(TIPO_FARINA, tipoFarina);
    firePropertyChanged("tipoFarina", oldTipoFarina, tipoFarina);
  }

  public boolean isFresco() {
    return getBoolean(FRESCO);
  }

  public void setFresco(boolean fresco) {
    boolean oldFresco = isFresco();
    setValue(FRESCO, fresco);
    firePropertyChanged("fresco", oldFresco, fresco);
  }

  public boolean isSurgelato() {
    return getBoolean(SURGELATO);
  }

  public void setSurgelato(boolean surgelato) {
    boolean oldSurgelato = isSurgelato();
    setValue(SURGELATO, surgelato);
    firePropertyChanged("surgelato", oldSurgelato, surgelato);
  }
  
  public void copyTo(ProdottoSpecifico farinaceoSpecifico) {
    ((FarinaceoSpecifico)farinaceoSpecifico).setGlutine(isGlutine());
    ((FarinaceoSpecifico)farinaceoSpecifico).setTipoFarina(getTipoFarina());
    ((FarinaceoSpecifico)farinaceoSpecifico).setFresco(isFresco());
    ((FarinaceoSpecifico)farinaceoSpecifico).setSurgelato(isSurgelato());
  }

}
