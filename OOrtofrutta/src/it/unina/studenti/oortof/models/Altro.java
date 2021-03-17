package it.unina.studenti.oortof.models;

public class Altro extends Prodotto {
  
  public Altro() {
  }

  public Altro(Integer id, String nome, Float prezzo, Boolean sfuso, CatProdotto catProdotto) {
    super(id, nome, prezzo, sfuso, catProdotto);
  }

}
