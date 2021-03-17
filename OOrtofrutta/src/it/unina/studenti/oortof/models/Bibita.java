package it.unina.studenti.oortof.models;


public class Bibita extends Prodotto {
  
  private BibitaSpecifico bibitaSpecifico;

  public Bibita() {
    bibitaSpecifico = new BibitaSpecifico(); 
  }

  public Bibita(Integer id, String nome, Float prezzo, Boolean sfuso, CatProdotto catProdotto, Float gradazioneAlcolica, Boolean frizzante, TipoBibita tipoBibita) {
    super(id, nome, prezzo, sfuso, catProdotto);
    bibitaSpecifico = new BibitaSpecifico(gradazioneAlcolica, frizzante, tipoBibita);
  }
  
  public void setBibitaSpecifico(BibitaSpecifico bibitaSpecifico) {
    BibitaSpecifico oldBibitaSpecifico = this.bibitaSpecifico;
    this.bibitaSpecifico = bibitaSpecifico;
    firePropertyChanged("bibitaSpecifico", oldBibitaSpecifico, bibitaSpecifico);
  }

  public BibitaSpecifico getBibitaSpecifico() {
    return bibitaSpecifico;
  }
  
  public void copyTo(Bibita bibita) {
    super.copyTo(bibita);
    bibitaSpecifico.copyTo(bibita.getBibitaSpecifico());
  }
}
