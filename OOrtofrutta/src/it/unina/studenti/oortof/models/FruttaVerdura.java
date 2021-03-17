package it.unina.studenti.oortof.models;


public class FruttaVerdura extends Prodotto {
  
  public FruttaVerdura() {
    super();
    setProdottoSpecifico(new FruttaVerduraSpecifico());
    prodottoCommon.setValue(ProdottoCommon.FRUTTA_VERDURA, Boolean.TRUE);
  }

  public FruttaVerdura(int id, String nome, float prezzo, boolean sfuso, TipoFruttaVerdura tipoFruttaVerdura, boolean bio, boolean surgelato) {
    super(id, nome, prezzo, sfuso);
    setProdottoSpecifico(new FruttaVerduraSpecifico(tipoFruttaVerdura, bio, surgelato));
    prodottoCommon.setValue(ProdottoCommon.FRUTTA_VERDURA, Boolean.TRUE);
}

  public void setFruttaVerduraSpecifico(FruttaVerduraSpecifico fruttaVerduraSpecifico) {
    FruttaVerduraSpecifico oldFruttaVerduraSpecifico = (FruttaVerduraSpecifico)getProdottoSpecifico();
    setProdottoSpecifico(fruttaVerduraSpecifico);
    firePropertyChanged("fruttaVerduraSpecifico", oldFruttaVerduraSpecifico, fruttaVerduraSpecifico);
  }

  public FruttaVerduraSpecifico getFruttaVerduraSpecifico() {
    return (FruttaVerduraSpecifico)getProdottoSpecifico();
  }
  
  public void copyTo(FruttaVerdura fruttaVerdura) {
    super.copyTo(fruttaVerdura);
    getProdottoSpecifico().copyTo(fruttaVerdura.getFruttaVerduraSpecifico());
  }

}
