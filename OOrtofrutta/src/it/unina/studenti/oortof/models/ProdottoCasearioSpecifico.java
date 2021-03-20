package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;

public class ProdottoCasearioSpecifico extends ProdottoSpecifico {
  
  public static final int TIPO_LATTE = 0;   //String
  public static final int STABILIMENTO = 1; //String
  public static final int STAGIONATURA = 2; //Integer

  public ProdottoCasearioSpecifico() {
    attributes = new Object[3];
  }

  public ProdottoCasearioSpecifico(String tipoLatte, String stabilimento, int stagionatura) {
    this();
    setValue(TIPO_LATTE, tipoLatte);
    setValue(STABILIMENTO, stabilimento);
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
    firePropertyChanged("tipoLatte", oldTipoLatte, tipoLatte);
  }

  public String getStabilimento() {
    return getString(STABILIMENTO);
  }

  public void setStabilimento(String stabilimento) {
    String oldStabilimento = getStabilimento();
    if (equals(stabilimento, oldStabilimento)) {
      return;
    }
    setValue(STABILIMENTO, stabilimento);
    firePropertyChanged("stabilimento", oldStabilimento, stabilimento);
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
    firePropertyChanged("stagionatura", oldStagionatura, stagionatura);
  }

  public void copyTo(ObservedModel prodottoCasearioSpecifico) {
    ((ProdottoCasearioSpecifico)prodottoCasearioSpecifico).setTipoLatte(getString(TIPO_LATTE));
    ((ProdottoCasearioSpecifico)prodottoCasearioSpecifico).setStabilimento(getString(STABILIMENTO));
    ((ProdottoCasearioSpecifico)prodottoCasearioSpecifico).setStagionatura(getInteger(STAGIONATURA));
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);    
  }

  @Override
  public void clear() {
    setTipoLatte(null);
    setStabilimento(null);
    setStagionatura(null);
  }


}
