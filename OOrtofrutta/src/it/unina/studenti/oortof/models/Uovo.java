package it.unina.studenti.oortof.models;

public class Uovo extends Prodotto {
  
  UovoSpecifico uovoSpecifico;

  
  public Uovo() {
    uovoSpecifico = new UovoSpecifico();
  }

  public Uovo(Integer id, String nome, Float prezzo, Boolean sfuso, CatProdotto catProdotto, Integer tipoAllevamento, String codAllevamento, CatPeso catPeso) {
    super(id, nome, prezzo, sfuso, catProdotto);
    uovoSpecifico = new UovoSpecifico(tipoAllevamento, codAllevamento, catPeso);
  }
  
  public void setUovoSpecifico(UovoSpecifico uovoSpecifico) {
    UovoSpecifico oldUovoSpecifico = this.uovoSpecifico;
    this.uovoSpecifico = uovoSpecifico;
    firePropertyChanged("uovoSpecifico", oldUovoSpecifico, uovoSpecifico);
  }
  
  public UovoSpecifico getUovoSpecifico() {
    return uovoSpecifico;
  }

  public void copyTo(Uovo uovo) {
    super.copyTo(uovo);
    uovoSpecifico.copyTo(uovo.getUovoSpecifico());
  }
}
