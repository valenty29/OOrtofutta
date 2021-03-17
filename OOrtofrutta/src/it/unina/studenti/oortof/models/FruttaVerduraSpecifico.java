package it.unina.studenti.oortof.models;

public class FruttaVerduraSpecifico extends ProdottoSpecifico {

  private TipoFruttaVerdura tipoFruttaVerdura;
  private Boolean bio;
  private Boolean surgelato;
  
  public FruttaVerduraSpecifico() {
  }

  public FruttaVerduraSpecifico(TipoFruttaVerdura tipoFruttaVerdura, Boolean bio, Boolean surgelato) {
    this.tipoFruttaVerdura = tipoFruttaVerdura;
    this.bio = bio;
    this.surgelato = surgelato;
  }

  public TipoFruttaVerdura getTipoFruttaVerdura() {
    return tipoFruttaVerdura;
  }

  public void setTipoFruttaVerdura(TipoFruttaVerdura tipoFruttaVerdura) {
    TipoFruttaVerdura oldTipoFruttaVerdura = this.tipoFruttaVerdura;
    this.tipoFruttaVerdura = tipoFruttaVerdura;
    firePropertyChanged("tipoFruttaVerdura", oldTipoFruttaVerdura, tipoFruttaVerdura);    
  }

  public Boolean isBio() {
    return bio;
  }

  public void setBio(Boolean bio) {
    Boolean oldBio = this.bio;
    this.bio = bio;
    firePropertyChanged("bio", oldBio, bio);
  }

  public Boolean isSurgelato() {
    return surgelato;
  }

  public void setSurgelato(Boolean surgelato) {
    Boolean oldSurgelato = this.surgelato;
    this.surgelato = surgelato;
    firePropertyChanged("surgelato", oldSurgelato, surgelato);
  }

  public void copyTo(ProdottoSpecifico fruttaVerduraSpecifico) {
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setTipoFruttaVerdura(tipoFruttaVerdura);
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setBio(bio);
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setSurgelato(surgelato);
  }
  

}
