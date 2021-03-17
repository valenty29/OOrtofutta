package it.unina.studenti.oortof.models;

public class CarnePesceSpecifico extends ProdottoSpecifico{
  private TipoCarnePesce tipoCarnePesce;
  private Boolean daAllevamento;
  private String animale;
  private Boolean confezionato;
  
  public CarnePesceSpecifico() {
  }
  
  public CarnePesceSpecifico(TipoCarnePesce tipoCarnePesce, Boolean daAllevamento, String animale, Boolean confezionato) {
    this.tipoCarnePesce = tipoCarnePesce;
    this.daAllevamento = daAllevamento;
    this.animale = animale;
    this.confezionato = confezionato;
  }


  public TipoCarnePesce getTipoCarnePesce() {
    return tipoCarnePesce;
  }

  public void setTipoCarnePesce(TipoCarnePesce tipoCarnePesce) {
    TipoCarnePesce oldTipoCarnePesce = this.tipoCarnePesce;
    this.tipoCarnePesce = tipoCarnePesce;
    firePropertyChanged("tipoCarnePesce", oldTipoCarnePesce, tipoCarnePesce);
  }

  public Boolean isDaAllevamento() {
    return daAllevamento;
  }

  public void setDaAllevamento(Boolean daAllevamento) {
    Boolean oldDaAllevamento = this.daAllevamento;
    this.daAllevamento = daAllevamento;
    firePropertyChanged("daAllevamento", oldDaAllevamento, daAllevamento);
  }

  public String getAnimale() {
    return animale;
  }

  public void setAnimale(String animale) {
    String oldAnimale = this.animale;
    this.animale = animale;
    firePropertyChanged("animale", oldAnimale, animale);
  }

  public Boolean isConfezionato() {
    return confezionato;
  }

  public void setConfezionato(Boolean confezionato) {
    Boolean oldConfezionato = this.confezionato;
    this.confezionato = confezionato;
    firePropertyChanged("confezionato", oldConfezionato, confezionato);
  }
  
  public void copyTo(ProdottoSpecifico carnePesceSpecifico) {
    ((CarnePesceSpecifico)carnePesceSpecifico).setTipoCarnePesce(tipoCarnePesce);
    ((CarnePesceSpecifico)carnePesceSpecifico).setDaAllevamento(daAllevamento);
    ((CarnePesceSpecifico)carnePesceSpecifico).setAnimale(animale);
    ((CarnePesceSpecifico)carnePesceSpecifico).setConfezionato(confezionato);
  }
}
