package it.unina.studenti.oortof.models.entities.prodotti;

import java.beans.PropertyChangeListener;

import it.unina.studenti.oortof.models.entities.ObservedModel;

public abstract class ProdottoSpecifico extends ObservedModel implements PropertyChangeListener {

//  abstract public void copyTo(ProdottoSpecifico prodottoSpecifico);
  abstract public void clear();
}
