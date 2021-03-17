package it.unina.studenti.oortof.models;

public class Bibita extends Prodotto {
  
  public Bibita() {
    super();
    setProdottoSpecifico(new BibitaSpecifico());
    prodottoCommon.setValue(ProdottoCommon.BIBITA, Boolean.TRUE);
  }

  public Bibita(int id, String nome, float prezzo, boolean sfuso, float gradazioneAlcolica, boolean frizzante, TipoBibita tipoBibita) {
    super(id, nome, prezzo, sfuso);
    setProdottoSpecifico(new BibitaSpecifico(gradazioneAlcolica, frizzante, tipoBibita));
    prodottoCommon.setValue(ProdottoCommon.BIBITA, Boolean.TRUE);
  }
  
  public void setBibitaSpecifico(BibitaSpecifico bibitaSpecifico) {
    BibitaSpecifico oldBibitaSpecifico = (BibitaSpecifico)getProdottoSpecifico();
    setProdottoSpecifico(bibitaSpecifico);
    firePropertyChanged("bibitaSpecifico", oldBibitaSpecifico, bibitaSpecifico);
  }

  public BibitaSpecifico getBibitaSpecifico() {
    return (BibitaSpecifico)getProdottoSpecifico();
  }
  
  public void copyTo(Bibita bibita) {
    super.copyTo(bibita);
    getProdottoSpecifico().copyTo(bibita.getBibitaSpecifico());
  }
}
