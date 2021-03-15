package it.unina.studenti.oortof.models;

public class Conserva extends Prodotto {

  public Conserva() {
    super();
    setProdottoSpecifico(new ConservaSpecifico());
    prodottoCommon.setValue(ProdottoCommon.CONSERVA, Boolean.TRUE);
  }
  
  public Conserva(int id, String nome, float prezzo, boolean sfuso, TipoConservazione tipoConservazione) {
    super(id, nome, prezzo, sfuso);
    setProdottoSpecifico(new ConservaSpecifico(tipoConservazione));
    prodottoCommon.setValue(ProdottoCommon.CONSERVA, Boolean.TRUE);
  }
  
  public void setConservaSpecifico(ConservaSpecifico conservaSpecifico) {
    ConservaSpecifico oldConservaSpecifico = (ConservaSpecifico)getProdottoSpecifico();
    setProdottoSpecifico(conservaSpecifico);
    firePropertyChanged("conservaSpecifico", oldConservaSpecifico, conservaSpecifico);
  }
  
  public ConservaSpecifico getConservaSpecifico() {
    return (ConservaSpecifico)getProdottoSpecifico();
  }

  public void copyTo(Conserva conserva) {
    super.copyTo(conserva);
    getProdottoSpecifico().copyTo(conserva.getConservaSpecifico());
  }
}
