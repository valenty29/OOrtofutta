package it.unina.studenti.oortof.models;


public class FruttaVerdura extends Prodotto {
  
  FruttaVerduraSpecifico fruttaVerduraSpecifico;
  
  public FruttaVerdura() {
    fruttaVerduraSpecifico = new FruttaVerduraSpecifico();
  }

  public FruttaVerdura(Integer id, String nome, Float prezzo, Boolean sfuso, CatProdotto catProdotto, TipoFruttaVerdura tipoFruttaVerdura, Boolean bio, Boolean surgelato) {
    super(id, nome, prezzo, sfuso, catProdotto);
    fruttaVerduraSpecifico = new FruttaVerduraSpecifico(tipoFruttaVerdura, bio, surgelato);
  }

  public void setFruttaVerduraSpecifico(FruttaVerduraSpecifico fruttaVerduraSpecifico) {
    FruttaVerduraSpecifico oldFruttaVerduraSpecifico = this.fruttaVerduraSpecifico;
    this.fruttaVerduraSpecifico = fruttaVerduraSpecifico;
    firePropertyChanged("fruttaVerduraSpecifico", oldFruttaVerduraSpecifico, fruttaVerduraSpecifico);
  }

  public FruttaVerduraSpecifico getFruttaVerduraSpecifico() {
    return fruttaVerduraSpecifico;
  }
  
  public void copyTo(FruttaVerdura fruttaVerdura) {
    super.copyTo(fruttaVerdura);
    fruttaVerduraSpecifico.copyTo(fruttaVerdura.getFruttaVerduraSpecifico());
  }

}
