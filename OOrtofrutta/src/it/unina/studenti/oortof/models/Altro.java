package it.unina.studenti.oortof.models;


public class Altro extends Prodotto {
  
public Altro() {
  super();
  setProdottoSpecifico(new AltroSpecifico());
  prodottoCommon.setValue(ProdottoCommon.ALTRO, Boolean.TRUE);
  
}

  public Altro(int id, String nome, float prezzo, boolean sfuso) {
    super(id, nome, prezzo, sfuso);    
  }

}
