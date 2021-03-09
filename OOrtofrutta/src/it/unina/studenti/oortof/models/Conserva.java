package it.unina.studenti.oortof.models;

public class Conserva extends Prodotto {

  private ConservaSpecifico conservaSpecifico;
  
  public Conserva() {
    conservaSpecifico = new ConservaSpecifico();
  }
  
  public Conserva(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, TipoConservazione tipoConservazione) {
    super(id, nome, prezzo, sfuso, catProdotto);
    conservaSpecifico = new ConservaSpecifico(tipoConservazione);
  }
  
  public void setConservaSpecifico(ConservaSpecifico conservaSpecifico) {
    this.conservaSpecifico = conservaSpecifico;
  }
  
  public ConservaSpecifico getConservaSpecifico() {
    return conservaSpecifico;
  }

  public void copyTo(Conserva conserva) {
    super.copyTo(conserva);
    conservaSpecifico.copyTo(conserva.getConservaSpecifico());
  }
}
