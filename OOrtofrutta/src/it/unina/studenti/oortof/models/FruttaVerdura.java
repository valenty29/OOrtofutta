package it.unina.studenti.oortof.models;


public class FruttaVerdura extends Prodotto {
  
  FruttaVerduraSpecifico fruttaVerduraSpecifico;
  
  public FruttaVerdura() {
    fruttaVerduraSpecifico = new FruttaVerduraSpecifico();
  }

  public FruttaVerdura(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, TipoFruttaVerdura tipoFruttaVerdura, boolean bio, boolean surgelato) {
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
