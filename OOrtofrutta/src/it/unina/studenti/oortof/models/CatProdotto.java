package it.unina.studenti.oortof.models;

public enum CatProdotto {
  
  Fruttaverdura {
    public String toString() {
      return "Frutta e Verdura";
    }
  }, 
  Prodottocaseareo {
    public String toString() {
      return "Prodotto Caseario";
    }
  }, 
  Farinaceo, 
  Uovo, 
  Carnepesce{
    public String toString() {
      return "Carne e Pesce";
    }
  }, 
  Bibita,
  Conserva, 
  Altro
}
