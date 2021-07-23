package it.unina.studenti.oortof.models;

public class Uovo extends Prodotto {
  
  public Uovo() {
    super();
    replaceProdottoSpecifico(UOVO_INDEX, new UovoSpecifico());
    prodottoCommon.setValue(ProdottoCommon.UOVO, Boolean.TRUE);
  }

  public Uovo(int id, String nome, float prezzo, boolean sfuso, int tipoAllevamento, CatPeso catPeso) {
    super(id, nome, prezzo, sfuso);
    replaceProdottoSpecifico(UOVO_INDEX, new UovoSpecifico(tipoAllevamento, catPeso));
    prodottoCommon.setValue(ProdottoCommon.UOVO, Boolean.TRUE);
 }
}
