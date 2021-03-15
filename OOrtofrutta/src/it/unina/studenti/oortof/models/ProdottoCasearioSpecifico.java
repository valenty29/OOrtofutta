package it.unina.studenti.oortof.models;

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
    setValue(TIPO_LATTE, tipoLatte);
  }

  public String getStabilimento() {
    return getString(STABILIMENTO);
  }

  public void setStabilimento(String stabilimento) {
    setValue(STABILIMENTO, stabilimento);
  }

  public Integer getStagionatura() {
    return getInteger(STAGIONATURA);
  }

  public void setStagionatura(Integer stagionatura) {
    setValue(STAGIONATURA, stagionatura);
  }

  public void copyTo(ProdottoSpecifico prodottoCasearioSpecifico) {
    ((ProdottoCasearioSpecifico)prodottoCasearioSpecifico).setTipoLatte(getString(TIPO_LATTE));
    ((ProdottoCasearioSpecifico)prodottoCasearioSpecifico).setStabilimento(getString(STABILIMENTO));
    ((ProdottoCasearioSpecifico)prodottoCasearioSpecifico).setStagionatura(getInteger(STAGIONATURA));
  }


}
