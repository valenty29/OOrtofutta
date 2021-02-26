package it.unina.studenti.oortof.models;

public class Uovo extends Prodotto {

  
  private int tipoAllevamento;
  private String codAllevamento;
  private CatPeso catPeso;

  public Uovo(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, int tipoAllevamento, String codAllevamento, CatPeso catPeso) {
    super(id, nome, prezzo, sfuso, catProdotto);
    this.tipoAllevamento = tipoAllevamento;
    this.codAllevamento = codAllevamento;
    this.catPeso = catPeso;
  }

  public int getTipoAllevamento() {
    return tipoAllevamento;
  }

  public void setTipoAllevamento(int tipoAllevamento) {
    this.tipoAllevamento = tipoAllevamento;
  }

  public String getCodAllevamento() {
    return codAllevamento;
  }

  public void setCodAllevamento(String codAllevamento) {
    this.codAllevamento = codAllevamento;
  }

  public CatPeso getCatPeso() {
    return catPeso;
  }

  public void setCatPeso(CatPeso catPeso) {
    this.catPeso = catPeso;
  }
}
