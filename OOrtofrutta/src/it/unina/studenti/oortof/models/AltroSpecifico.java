package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;

public class AltroSpecifico extends ProdottoSpecifico {

  public AltroSpecifico() {   
  }
  
//  public void copyTo(ProdottoSpecifico altroSpecifico) {
//  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);    
  }

  @Override
  public void clear() {
  }
}
