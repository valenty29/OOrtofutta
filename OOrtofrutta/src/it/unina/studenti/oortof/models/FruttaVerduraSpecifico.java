package it.unina.studenti.oortof.models;

public class FruttaVerduraSpecifico extends ProdottoSpecifico {

  private TipoFruttaVerdura tipoFruttaVerdura;
  private boolean bio;
  private boolean surgelato;
  
  public FruttaVerduraSpecifico() {
  }

  public FruttaVerduraSpecifico(TipoFruttaVerdura tipoFruttaVerdura, boolean bio, boolean surgelato) {
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

  public boolean isBio() {
    return bio;
  }

  public void setBio(boolean bio) {
    boolean oldBio = this.bio;
    this.bio = bio;
    firePropertyChanged("bio", oldBio, bio);
  }

  public boolean isSurgelato() {
    return surgelato;
  }

  public void setSurgelato(boolean surgelato) {
    boolean oldSurgelato = this.surgelato;
    this.surgelato = surgelato;
    firePropertyChanged("surgelato", oldSurgelato, surgelato);
  }

  public void copyTo(ProdottoSpecifico fruttaVerduraSpecifico) {
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setTipoFruttaVerdura(tipoFruttaVerdura);
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setBio(bio);
    ((FruttaVerduraSpecifico)fruttaVerduraSpecifico).setSurgelato(surgelato);
  }
  

}
