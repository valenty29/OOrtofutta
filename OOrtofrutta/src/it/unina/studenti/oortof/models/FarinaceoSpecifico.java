package it.unina.studenti.oortof.models;

public class FarinaceoSpecifico extends ProdottoSpecifico {

  private boolean glutine;
  private String tipoFarina;
  private boolean fresco;
  private boolean surgelato;
  
  public FarinaceoSpecifico() {
  }

  public FarinaceoSpecifico(boolean glutine, String tipoFarina, boolean fresco, boolean surgelato) {
    this.glutine = glutine;
    this.tipoFarina = tipoFarina;
    this.fresco = fresco;
    this.surgelato = surgelato;
  }

  public boolean isGlutine() {
    return glutine;
  }

  public void setGlutine(boolean glutine) {
    boolean oldGlutine = this.glutine;
    this.glutine = glutine;
    firePropertyChanged("glutine", oldGlutine, glutine);
  }

  public String getTipoFarina() {
    return tipoFarina;
  }

  public void setTipoFarina(String tipoFarina) {
    String oldTipoFarina = this.tipoFarina;
    this.tipoFarina = tipoFarina;
    firePropertyChanged("tipoFarina", oldTipoFarina, tipoFarina);
  }

  public boolean isFresco() {
    return fresco;
  }

  public void setFresco(boolean fresco) {
    boolean oldFresco = this.fresco;
    this.fresco = fresco;
    firePropertyChanged("fresco", oldFresco, fresco);
  }

  public boolean isSurgelato() {
    return surgelato;
  }

  public void setSurgelato(boolean surgelato) {
    boolean oldSurgelato = this.surgelato;
    this.surgelato = surgelato;
    firePropertyChanged("surgelato", oldSurgelato, surgelato);
  }
  
  public void copyTo(ProdottoSpecifico farinaceoSpecifico) {
    ((FarinaceoSpecifico)farinaceoSpecifico).setGlutine(glutine);
    ((FarinaceoSpecifico)farinaceoSpecifico).setTipoFarina(tipoFarina);
    ((FarinaceoSpecifico)farinaceoSpecifico).setFresco(fresco);
    ((FarinaceoSpecifico)farinaceoSpecifico).setSurgelato(surgelato);
  }

}
