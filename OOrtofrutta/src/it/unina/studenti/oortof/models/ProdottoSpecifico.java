package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeListener;

public abstract class ProdottoSpecifico extends ObservedModel implements PropertyChangeListener {

  abstract public void copyTo(ProdottoSpecifico prodottoSpecifico);
  abstract public void clear();
}
