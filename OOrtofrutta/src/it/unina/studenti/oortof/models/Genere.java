package it.unina.studenti.oortof.models;


public enum Genere {
    M {
      public String toString() {
        return "Maschio";
      }
    },
    F {
      public String toString() {
        return "Femmina";
      }
    }
}
