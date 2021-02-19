package it.unina.studenti.oortof.models;


public class FruttaVerdura extends Prodotto {
  private TipoFruttaVerdura tipoFruttaVerdura;
  private boolean bio;
  private boolean surgelato;

  public FruttaVerdura(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, TipoFruttaVerdura tipoFruttaVerdura, boolean bio, boolean surgelato) {
    super(id, nome, prezzo, sfuso, catProdotto);
    this.tipoFruttaVerdura = tipoFruttaVerdura;
    this.bio = bio;
    this.surgelato = surgelato;
  }

  public TipoFruttaVerdura getTipoFV() {
    return tipoFruttaVerdura;
  }

  public void setTipoFV(TipoFruttaVerdura tipoFV) {
    this.tipoFruttaVerdura = tipoFV;
  }

  public boolean isBio() {
    return bio;
  }

  public void setBio(boolean bio) {
    this.bio = bio;
  }

  public boolean isSurgelato() {
    return surgelato;
  }

  public void setSurgelato(boolean surgelato) {
    this.surgelato = surgelato;
  }

}
