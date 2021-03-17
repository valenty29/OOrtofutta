package it.unina.studenti.oortof.models;

public class ProdottoCasearioSpecifico extends ProdottoSpecifico {

  private String tipoLatte;
  private String stabilimento;
  private Integer stagionatura;
  
  public ProdottoCasearioSpecifico() {
  }

  public ProdottoCasearioSpecifico(String tipoLatte, String stabilimento, Integer stagionatura) {
    this.tipoLatte = tipoLatte;
    this.stabilimento = stabilimento;
    this.stagionatura = stagionatura;
  }

  public String getTipoLatte() {
    return tipoLatte;
  }

  public void setTipoLatte(String tipoLatte) {
    this.tipoLatte = tipoLatte;
  }

  public String getStabilimento() {
    return stabilimento;
  }

  public void setStabilimento(String stabilimento) {
    this.stabilimento = stabilimento;
  }

  public Integer getStagionatura() {
    return stagionatura;
  }

  public void setStagionatura(Integer stagionatura) {
    this.stagionatura = stagionatura;
  }

  public void copyTo(ProdottoSpecifico prodottoCasearioSpecifico) {
    ((ProdottoCasearioSpecifico)prodottoCasearioSpecifico).setTipoLatte(tipoLatte);
    ((ProdottoCasearioSpecifico)prodottoCasearioSpecifico).setStabilimento(stabilimento);
    ((ProdottoCasearioSpecifico)prodottoCasearioSpecifico).setStagionatura(stagionatura);
  }


}
