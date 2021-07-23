package it.unina.studenti.oortof.models.entities.prodotti;

import java.beans.PropertyChangeEvent;

import it.unina.studenti.oortof.models.entities.ObservedModel;

public class ProdottoCasearioSpecifico extends ProdottoSpecifico {
  
  public static final int TIPO_LATTE = 0;   //String
  public static final int STAGIONATURA = 1; //Integer

  public ProdottoCasearioSpecifico() {
    attributes = new Object[3];
  }

  public ProdottoCasearioSpecifico(String tipoLatte, int stagionatura) {
    this();
    setValue(TIPO_LATTE, tipoLatte);
    setValue(STAGIONATURA, stagionatura);
  }

  public String getTipoLatte() {
    return getString(TIPO_LATTE);
  }

  public void setTipoLatte(String tipoLatte) {
    String oldTipoLatte = getTipoLatte();
    if (equals(tipoLatte, oldTipoLatte)) {
      return;
    }
    setValue(TIPO_LATTE, tipoLatte);
    firePropertyChange("tipoLatte", oldTipoLatte, tipoLatte);
  }

  public Integer getStagionatura() {
    return getInteger(STAGIONATURA);
  }

  public void setStagionatura(Integer stagionatura) {
    Integer oldStagionatura = getStagionatura();
    if (equals(stagionatura, oldStagionatura)) {
      return;
    }
    setValue(STAGIONATURA, stagionatura);
    firePropertyChange("stagionatura", oldStagionatura, stagionatura);
  }

  public void copyTo(ObservedModel prodottoCasearioSpecifico) {
    ((ProdottoCasearioSpecifico)prodottoCasearioSpecifico).setTipoLatte(getString(TIPO_LATTE));
    ((ProdottoCasearioSpecifico)prodottoCasearioSpecifico).setStagionatura(getInteger(STAGIONATURA));
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);    
  }

  @Override
  public void clear() {
    setTipoLatte(null);
    setStagionatura(null);
  }


}
