package it.unina.studenti.oortof.models.entities.prodotti;

public class Farinaceo extends Prodotto {

  public Farinaceo() {
    super();
    replaceProdottoSpecifico(FARINACEO_INDEX, new FarinaceoSpecifico());
    prodottoCommon.setValue(ProdottoCommon.FARINACEO, Boolean.TRUE);
  }

  public Farinaceo(int id, String nome, float prezzo, boolean sfuso, boolean glutine, String tipoFarina, boolean fresco, boolean surgelato) {
    super(id, nome, prezzo, sfuso);
    replaceProdottoSpecifico(FARINACEO_INDEX, new FarinaceoSpecifico(glutine, tipoFarina, fresco, surgelato));
    prodottoCommon.setValue(ProdottoCommon.FARINACEO, Boolean.TRUE);
  }
}
