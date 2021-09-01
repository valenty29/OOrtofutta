package it.unina.studenti.oortof.models.entities.prodotti;

import java.beans.PropertyChangeEvent;

import it.unina.studenti.oortof.models.entities.ObservedModel;
import it.unina.studenti.oortof.models.entities.prodotti.enumeration.TipoCarnePesce;

public class CarnePesceSpecifico extends ProdottoSpecifico{
  
  public static final int TIPO_CARNE_PESCE = 0; //TipoCarnePesce
  public static final int DA_ALLEVAMENTO = 1;   //Boolean
  public static final int ANIMALE = 2;          //String
  public static final int CONFEZIONATO = 3;     //Boolean
  
  public CarnePesceSpecifico() {
    attributes = new Object[4];
  }
  
  public CarnePesceSpecifico(TipoCarnePesce tipoCarnePesce, boolean daAllevamento, String animale, boolean confezionato) {
    this();
    setValue(TIPO_CARNE_PESCE, tipoCarnePesce);
    setValue(DA_ALLEVAMENTO, daAllevamento);
    setValue(ANIMALE, animale);
    setValue(CONFEZIONATO, confezionato);
  }


  public TipoCarnePesce getTipoCarnePesce() {
    try {
      return TipoCarnePesce.valueOf(getString(TIPO_CARNE_PESCE));
    }
    catch (Exception e) {
      return null;
    }
  }

  public void setTipoCarnePesce(TipoCarnePesce tipoCarnePesce) {
    TipoCarnePesce oldTipoCarnePesce = getTipoCarnePesce();
    if (equals(tipoCarnePesce, oldTipoCarnePesce)) {
      return;
    }
    setValue(TIPO_CARNE_PESCE, tipoCarnePesce);
    firePropertyChange("tipoCarnePesce", oldTipoCarnePesce, tipoCarnePesce);
  }

  public boolean isDaAllevamento() {
    return isBoolean(DA_ALLEVAMENTO);
  }
  
  public Boolean getDaAllevamento() {
    return getBoolean(DA_ALLEVAMENTO);
  }
  
  public void setDaAllevamento(Boolean daAllevamento) {
    Boolean oldDaAllevamento = getDaAllevamento();
    if (equals(daAllevamento, oldDaAllevamento)) {
      return;
    }
    setValue(DA_ALLEVAMENTO, daAllevamento);
    firePropertyChange("daAllevamento", oldDaAllevamento, daAllevamento);
  }

  public String getAnimale() {
    return getString(ANIMALE);
  }

  public void setAnimale(String animale) {
    String oldAnimale = getAnimale();
    if (equals(animale, oldAnimale)) {
      return;
    }
    setValue(ANIMALE, animale);
    firePropertyChange("animale", oldAnimale, animale);
  }

  public boolean isConfezionato() {
    return isBoolean(CONFEZIONATO);
  }

  public Boolean getConfezionato() {
    return getBoolean(CONFEZIONATO);
  }
  
  public void setConfezionato(Boolean confezionato) {
    Boolean oldConfezionato = getConfezionato();
    if (equals(confezionato, oldConfezionato)) {
      return;
    }
    setValue(CONFEZIONATO, confezionato);
    firePropertyChange("confezionato", oldConfezionato, confezionato);
  }
  
  public void copyTo(ObservedModel carnePesceSpecifico) {
    ((CarnePesceSpecifico)carnePesceSpecifico).setTipoCarnePesce(getTipoCarnePesce());
    ((CarnePesceSpecifico)carnePesceSpecifico).setDaAllevamento(getDaAllevamento());
    ((CarnePesceSpecifico)carnePesceSpecifico).setAnimale(getAnimale());
    ((CarnePesceSpecifico)carnePesceSpecifico).setConfezionato(getConfezionato());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);    
  }

  @Override
  public void clear() {
    setAnimale(null);
    setConfezionato(null);
    setDaAllevamento(null);
    setTipoCarnePesce(null);
  }
}
