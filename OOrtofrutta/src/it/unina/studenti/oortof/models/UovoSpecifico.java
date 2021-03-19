package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;

public class UovoSpecifico extends ProdottoSpecifico {

  public static final int TIPO_ALLEVAMENTO = 0; //Integer
  public static final int COD_ALLEVAMENTO = 1;  //String
  public static final int CAT_PESO = 2;         //CatPeso
  
  public UovoSpecifico() {
    attributes = new Object[3];
  }

  public UovoSpecifico(int tipoAllevamento, String codAllevamento, CatPeso catPeso) {
    this();
    setValue(TIPO_ALLEVAMENTO, tipoAllevamento);
    setValue(COD_ALLEVAMENTO, codAllevamento);
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
    firePropertyChanged("tipoAllevamento", oldTipoAllevamento, tipoAllevamento);
  }

  public String getCodAllevamento() {
    return getString(COD_ALLEVAMENTO);
  }

  public void setCodAllevamento(String codAllevamento) {
    String oldCodAllevamento = getCodAllevamento();
    if (equals(codAllevamento, oldCodAllevamento)) {
      return;
    }
    setValue(COD_ALLEVAMENTO, codAllevamento);
    firePropertyChanged("codAllevamento", oldCodAllevamento, codAllevamento);
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
    firePropertyChanged("catPeso", oldCatPeso, catPeso);
  }

//  public void copyTo(ProdottoSpecifico uovoSpecifico) {
//    ((UovoSpecifico)uovoSpecifico).setTipoAllevamento(getInteger(TIPO_ALLEVAMENTO));
//    ((UovoSpecifico)uovoSpecifico).setCodAllevamento(getString(COD_ALLEVAMENTO));
//    ((UovoSpecifico)uovoSpecifico).setCatPeso(CatPeso.valueOf(getString(CAT_PESO)));
//  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);    
  }

  @Override
  public void clear() {
    setTipoAllevamento(null);
    setCodAllevamento(null);
    setCatPeso(null);
  }

}
