package it.unina.studenti.oortof.models;

public class Uovo extends Prodotto {
  
  public Uovo() {
    super();
    setProdottoSpecifico(new UovoSpecifico());
    prodottoCommon.setValue(ProdottoCommon.UOVO, Boolean.TRUE);
  }

  public Uovo(int id, String nome, float prezzo, boolean sfuso, int tipoAllevamento, String codAllevamento, CatPeso catPeso) {
    super(id, nome, prezzo, sfuso);
    setProdottoSpecifico(new UovoSpecifico(tipoAllevamento, codAllevamento, catPeso));
    prodottoCommon.setValue(ProdottoCommon.UOVO, Boolean.TRUE);
 }
  
  public void setUovoSpecifico(UovoSpecifico uovoSpecifico) {
    UovoSpecifico oldUovoSpecifico = (UovoSpecifico)getProdottoSpecifico();
    setProdottoSpecifico(uovoSpecifico);
    firePropertyChanged("uovoSpecifico", oldUovoSpecifico, uovoSpecifico);
  }
  
  public UovoSpecifico getUovoSpecifico() {
    return (UovoSpecifico)getProdottoSpecifico();
  }

  public void copyTo(Uovo uovo) {
    super.copyTo(uovo);
    getProdottoSpecifico().copyTo(uovo.getUovoSpecifico());
  }
}
