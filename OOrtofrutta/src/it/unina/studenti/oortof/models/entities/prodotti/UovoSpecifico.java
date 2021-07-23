package it.unina.studenti.oortof.models.entities.prodotti;

import java.beans.PropertyChangeEvent;

import it.unina.studenti.oortof.models.entities.ObservedModel;
import it.unina.studenti.oortof.models.entities.prodotti.enumeration.CatPeso;

public class UovoSpecifico extends ProdottoSpecifico {

  public static final int TIPO_ALLEVAMENTO = 0; //Integer
  public static final int CAT_PESO = 1;         //CatPeso
  
  public UovoSpecifico() {
    attributes = new Object[3];
  }

  public UovoSpecifico(int tipoAllevamento, CatPeso catPeso) {
    this();
    setValue(TIPO_ALLEVAMENTO, tipoAllevamento);
    setValue(CAT_PESO, catPeso);
  }

  public Integer getTipoAllevamento() {
    return getInteger(TIPO_ALLEVAMENTO);
  }

  public void setTipoAllevamento(Integer tipoAllevamento) {
    Integer oldTipoAllevamento = getTipoAllevamento();
    if (equals(tipoAllevamento, oldTipoAllevamento)) {
      return;
    }
    setValue(TIPO_ALLEVAMENTO, tipoAllevamento);
    firePropertyChange("tipoAllevamento", oldTipoAllevamento, tipoAllevamento);
  }

  public CatPeso getCatPeso() {
    try {
      return CatPeso.valueOf(getString(CAT_PESO));
    }
    catch (Exception e) {
      return null;
    }
  }

  public void setCatPeso(CatPeso catPeso) {
    CatPeso oldCatPeso = getCatPeso();
    if (equals(catPeso, oldCatPeso)) {
      return;
    }
    setValue(CAT_PESO, catPeso);
    firePropertyChange("catPeso", oldCatPeso, catPeso);
  }

  public void copyTo(ObservedModel uovoSpecifico) {
    ((UovoSpecifico)uovoSpecifico).setTipoAllevamento(getTipoAllevamento());
    ((UovoSpecifico)uovoSpecifico).setCatPeso(getCatPeso());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);    
  }

  @Override
  public void clear() {
    setTipoAllevamento(null);
    setCatPeso(null);
  }

}
