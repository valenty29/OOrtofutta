package it.unina.studenti.oortof.models;

public class ConservaSpecifico extends ProdottoSpecifico {

  private TipoConservazione tipoConservazione;

  public ConservaSpecifico() {
  }

  public ConservaSpecifico(TipoConservazione tipoConservazione) {
    this.tipoConservazione = tipoConservazione;
  }
  
  public void setTipoConservazione(TipoConservazione tipoConservazione) {
    TipoConservazione oldTipoConservazione = this.tipoConservazione;
    this.tipoConservazione = tipoConservazione;
    firePropertyChanged("tipoConservazione", oldTipoConservazione, tipoConservazione);
  }

  public TipoConservazione getTipoConservazione() {
    return tipoConservazione;
  }

  public void copyTo(ProdottoSpecifico conservaSpecifico) {
    ((ConservaSpecifico)conservaSpecifico).setTipoConservazione(tipoConservazione);
  }
}
