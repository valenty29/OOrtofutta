package it.unina.studenti.oortof.models;

public class FarinaceoSpecifico extends ProdottoSpecifico {

  private Boolean glutine;
  private String tipoFarina;
  private Boolean fresco;
  private Boolean surgelato;
  
  public FarinaceoSpecifico() {
  }

  public FarinaceoSpecifico(Boolean glutine, String tipoFarina, Boolean fresco, Boolean surgelato) {
    this.glutine = glutine;
    this.tipoFarina = tipoFarina;
    this.fresco = fresco;
    this.surgelato = surgelato;
  }

  public Boolean isGlutine() {
    return glutine;
  }

  public void setGlutine(Boolean glutine) {
    Boolean oldGlutine = this.glutine;
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

  public Boolean isFresco() {
    return fresco;
  }

  public void setFresco(Boolean fresco) {
    Boolean oldFresco = this.fresco;
    this.fresco = fresco;
    firePropertyChanged("fresco", oldFresco, fresco);
  }

  public Boolean isSurgelato() {
    return surgelato;
  }

  public void setSurgelato(Boolean surgelato) {
    Boolean oldSurgelato = this.surgelato;
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
