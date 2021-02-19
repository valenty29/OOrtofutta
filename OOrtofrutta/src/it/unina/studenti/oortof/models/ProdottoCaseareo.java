package it.unina.studenti.oortof.models;

public class ProdottoCaseareo extends Prodotto {
  private String tipoLatte;
  private String stabilimento;
  private int stagionatura;

  public ProdottoCaseareo(int id, int codProdotto, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, String tipoLatte, String stabilimento, int stagionatura) {
    super(id, nome, prezzo, sfuso, catProdotto);
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

  public int getStagionatura() {
    return stagionatura;
  }

  public void setStagionatura(int stagionatura) {
    this.stagionatura = stagionatura;
  }
}
