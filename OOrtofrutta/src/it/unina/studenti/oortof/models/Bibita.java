package it.unina.studenti.oortof.models;


public class Bibita extends Prodotto{
  private float gradazioneAlcolica;
  private boolean frizzante;
  private TipoBibita tipoBibita;

  

  public Bibita(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, float gradazioneAlcolica, boolean frizzante, TipoBibita tipoBibita) {
    super(id, nome, prezzo, sfuso, catProdotto);
    this.gradazioneAlcolica = gradazioneAlcolica;
    this.frizzante = frizzante;
    this.tipoBibita = tipoBibita;
    
  }

  public float getGradazioneAlcolica() {
    return gradazioneAlcolica;
  }

  public void setGradazioneAlcolica(float gradazioneAlcolica) {
    this.gradazioneAlcolica = gradazioneAlcolica;
  }

  public boolean isFrizzante() {
    return frizzante;
  }

  public void setFrizzante(boolean frizzante) {
    this.frizzante = frizzante;
  }

  public TipoBibita getTipoB() {
    return tipoBibita;
  }

  public void setTipoB(TipoBibita tipoB) {
    this.tipoBibita = tipoB;
  }
}
