package it.unina.studenti.oortof.models.entities.prodotti;

public class Altro extends Prodotto {


  public Altro(int id, String nome, float prezzo, boolean sfuso) {
    super(id, nome, prezzo, sfuso);
    replaceProdottoSpecifico(ALTRO_INDEX, new AltroSpecifico());
    prodottoCommon.setValue(ProdottoCommon.ALTRO, Boolean.TRUE);
  }

}
