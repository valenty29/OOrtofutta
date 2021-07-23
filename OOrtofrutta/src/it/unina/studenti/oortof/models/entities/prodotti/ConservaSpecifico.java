package it.unina.studenti.oortof.models.entities.prodotti;

import java.beans.PropertyChangeEvent;

import it.unina.studenti.oortof.models.entities.ObservedModel;
import it.unina.studenti.oortof.models.entities.prodotti.enumeration.TipoConservazione;

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
    if (equals(tipoConservazione, oldTipoConservazione)) {
      return;
    }
    setValue(TIPO_CONSERVAZIONE, tipoConservazione);
    firePropertyChange("tipoConservazione", oldTipoConservazione, tipoConservazione);
  }

  public TipoConservazione getTipoConservazione() {
    try {
      return TipoConservazione.valueOf(getString(TIPO_CONSERVAZIONE));
    }
    catch (Exception e) {
      return null;
    }
  }

  public void copyTo(ObservedModel conservaSpecifico) {
    ((ConservaSpecifico)conservaSpecifico).setTipoConservazione(getTipoConservazione());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);    
  }

  @Override
  public void clear() {
    setTipoConservazione(null);
  }
}
