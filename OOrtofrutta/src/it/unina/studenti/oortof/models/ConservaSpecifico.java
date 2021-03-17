package it.unina.studenti.oortof.models;

public class ConservaSpecifico extends ProdottoSpecifico {

  public static final int TIPO_CONSERVAZIONE = 0;  //TipoConservazione

  public ConservaSpecifico() {
    attributes = new Object [1];
  }

  public ConservaSpecifico(TipoConservazione tipoConservazione) {
    this();
    setValue(TIPO_CONSERVAZIONE, tipoConservazione);
  }
  
  public void setTipoConservazione(TipoConservazione tipoConservazione) {
    TipoConservazione oldTipoConservazione = getTipoConservazione();
    setValue(TIPO_CONSERVAZIONE, tipoConservazione);
    firePropertyChanged("tipoConservazione", oldTipoConservazione, tipoConservazione);
  }

  public TipoConservazione getTipoConservazione() {
    return TipoConservazione.valueOf(getString(TIPO_CONSERVAZIONE));
  }

  public void copyTo(ProdottoSpecifico conservaSpecifico) {
    ((ConservaSpecifico)conservaSpecifico).setTipoConservazione(getTipoConservazione());
  }
}
