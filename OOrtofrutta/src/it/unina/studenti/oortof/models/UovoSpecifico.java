package it.unina.studenti.oortof.models;

public class UovoSpecifico extends ProdottoSpecifico {

  private int tipoAllevamento;
  private String codAllevamento;
  private CatPeso catPeso;
  
  public UovoSpecifico() {
  }

  public UovoSpecifico(int tipoAllevamento, String codAllevamento, CatPeso catPeso) {
    this.tipoAllevamento = tipoAllevamento;
    this.codAllevamento = codAllevamento;
    this.catPeso = catPeso;
  }

  public int getTipoAllevamento() {
    return tipoAllevamento;
  }

  public void setTipoAllevamento(int tipoAllevamento) {
    int oldTipoAllevamento = this.tipoAllevamento;
    this.tipoAllevamento = tipoAllevamento;
    firePropertyChanged("tipoAllevamento", oldTipoAllevamento, tipoAllevamento);
  }

  public String getCodAllevamento() {
    return codAllevamento;
  }

  public void setCodAllevamento(String codAllevamento) {
    String oldCodAllevamento = this.codAllevamento;
    this.codAllevamento = codAllevamento;
    firePropertyChanged("codAllevamento", oldCodAllevamento, codAllevamento);
  }

  public CatPeso getCatPeso() {
    return catPeso;
  }

  public void setCatPeso(CatPeso catPeso) {
    CatPeso oldCatPeso = this.catPeso;
    this.catPeso = catPeso;
    firePropertyChanged("catPeso", oldCatPeso, catPeso);
  }

  public void copyTo(ProdottoSpecifico uovoSpecifico) {
    ((UovoSpecifico)uovoSpecifico).setTipoAllevamento(tipoAllevamento);
    ((UovoSpecifico)uovoSpecifico).setCodAllevamento(codAllevamento);
    ((UovoSpecifico)uovoSpecifico).setCatPeso(catPeso);
  }

}
