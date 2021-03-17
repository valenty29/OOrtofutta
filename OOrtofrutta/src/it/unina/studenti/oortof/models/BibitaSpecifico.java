package it.unina.studenti.oortof.models;

public class BibitaSpecifico extends ProdottoSpecifico {

  private Float gradazioneAlcolica;
  private Boolean frizzante;
  private TipoBibita tipoBibita;
  
  public BibitaSpecifico() {
  }
  
  public BibitaSpecifico(Float gradazioneAlcolica, Boolean frizzante, TipoBibita tipoBibita) {
    this.gradazioneAlcolica = gradazioneAlcolica;
    this.frizzante = frizzante;
    this.tipoBibita = tipoBibita;
  }

  public Float getGradazioneAlcolica() {
    return gradazioneAlcolica;
  }

  public void setGradazioneAlcolica(Float gradazioneAlcolica) {
    Float oldGradazioneAlcolica = this.gradazioneAlcolica;
    this.gradazioneAlcolica = gradazioneAlcolica;
    firePropertyChanged("gradazioneAlcolica", oldGradazioneAlcolica, gradazioneAlcolica);
  }

  public Boolean isFrizzante() {
    return frizzante;
  }

  public void setFrizzante(Boolean frizzante) {
    Boolean oldFrizzante = this.frizzante;
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
