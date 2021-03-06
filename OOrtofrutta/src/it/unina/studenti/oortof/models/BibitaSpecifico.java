package it.unina.studenti.oortof.models;

public class BibitaSpecifico extends ProdottoSpecifico {

  private float gradazioneAlcolica;
  private boolean frizzante;
  private TipoBibita tipoBibita;
  
  public BibitaSpecifico() {
  }
  
  public BibitaSpecifico(float gradazioneAlcolica, boolean frizzante, TipoBibita tipoBibita) {
    this.gradazioneAlcolica = gradazioneAlcolica;
    this.frizzante = frizzante;
    this.tipoBibita = tipoBibita;
  }

  public float getGradazioneAlcolica() {
    return gradazioneAlcolica;
  }

  public void setGradazioneAlcolica(float gradazioneAlcolica) {
    float oldGradazioneAlcolica = this.gradazioneAlcolica;
    this.gradazioneAlcolica = gradazioneAlcolica;
    firePropertyChanged("gradazioneAlcolica", oldGradazioneAlcolica, gradazioneAlcolica);
  }

  public boolean isFrizzante() {
    return frizzante;
  }

  public void setFrizzante(boolean frizzante) {
    boolean oldFrizzante = this.frizzante;
    this.frizzante = frizzante;
    firePropertyChanged("frizzante", oldFrizzante, frizzante);
  }

  public TipoBibita getTipoBibita() {
    return tipoBibita;
  }

  public void setTipoBibita(TipoBibita tipoBibita) {
    TipoBibita oldTipoBibita = this.tipoBibita;
    this.tipoBibita = tipoBibita;
    firePropertyChanged("tipoBibita", oldTipoBibita, tipoBibita);
  }
  
  public void copyTo(ProdottoSpecifico bibitaSpecifico) {
    ((BibitaSpecifico)bibitaSpecifico).setGradazioneAlcolica(gradazioneAlcolica);
    ((BibitaSpecifico)bibitaSpecifico).setFrizzante(frizzante);
    ((BibitaSpecifico)bibitaSpecifico).setTipoBibita(tipoBibita);
  }

}
