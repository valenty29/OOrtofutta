package it.unina.studenti.oortof.models;



public class Conserva extends Prodotto {
  private TipoConservazione tipoConservazione;

  public Conserva(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, TipoConservazione tipoConservazione) {
    super(id, nome, prezzo, sfuso, catProdotto);
    this.tipoConservazione = tipoConservazione;
  }

  public TipoConservazione getTipoC() {
    return tipoConservazione;
  }

  public void setTipoC(TipoConservazione tipoConservazione) {
    this.tipoConservazione = tipoConservazione;
  }
}
