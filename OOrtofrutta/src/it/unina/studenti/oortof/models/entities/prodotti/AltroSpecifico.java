package it.unina.studenti.oortof.models.entities.prodotti;

import java.beans.PropertyChangeEvent;

import it.unina.studenti.oortof.models.entities.ObservedModel;

public class AltroSpecifico extends ProdottoSpecifico {

  public AltroSpecifico() {   
  }
  
  public void copyTo(ObservedModel altroSpecifico) {
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);    
  }

  @Override
  public void clear() {
  }
}
