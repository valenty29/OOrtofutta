package it.unina.studenti.oortof.models;

public class Farinaceo extends Prodotto {
  private boolean glutine;
  private String tipoFarina;
  private boolean fresco;
  private boolean surgelato;

  public Farinaceo(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, boolean glutine, String tipoFarina, boolean fresco, boolean surgelato) {
    super(id, nome, prezzo, sfuso, catProdotto);
    this.glutine = glutine;
    this.tipoFarina = tipoFarina;
    this.fresco = fresco;
    this.surgelato = surgelato;
  }

  public boolean isGlutine() {
    return glutine;
  }

  public void setGlutine(boolean glutine) {
    this.glutine = glutine;
  }

  public String getTipoFarina() {
    return tipoFarina;
  }

  public void setTipoFarina(String tipoFarina) {
    this.tipoFarina = tipoFarina;
  }

  public boolean isFresco() {
    return fresco;
  }

  public void setFresco(boolean fresco) {
    this.fresco = fresco;
  }

  public boolean isSurgelato() {
    return surgelato;
  }

  public void setSurgelato(boolean surgelato) {
    this.surgelato = surgelato;
  }
}
