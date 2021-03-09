package it.unina.studenti.oortof.models;


public class Bibita extends Prodotto {
  
  private BibitaSpecifico bibitaSpecifico;

  public Bibita() {
    bibitaSpecifico = new BibitaSpecifico(); 
  }

  public Bibita(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, float gradazioneAlcolica, boolean frizzante, TipoBibita tipoBibita) {
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
