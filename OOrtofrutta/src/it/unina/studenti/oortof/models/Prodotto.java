package it.unina.studenti.oortof.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Prodotto {
  private int id;
  private String nome;
  private float prezzo;
  private boolean sfuso;
  private CatProdotto tipo;
  private List<Lotto> lotti = new ArrayList<Lotto>();

  public Prodotto(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto) {
    this.id = id;
    this.nome = nome;
    this.prezzo = prezzo;
    this.sfuso = sfuso;
    this.tipo = catProdotto;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public float getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(float prezzo) {
    this.prezzo = prezzo;
  }

  public boolean isSfuso() {
    return sfuso;
  }

  public void setSfuso(boolean sfuso) {
    this.sfuso = sfuso;
  }

  public CatProdotto getTipo() {
    return tipo;
  }

  public void setTipo(CatProdotto tipo) {
    this.tipo = tipo;
  }

  public List<Lotto> getLotti() {
    return lotti;
  }

  public void setLotti(List<Lotto> lotti) {
    this.lotti = lotti;
  }
  
  public void addLotto(Lotto lotto) {
    lotti.add(lotto);
  }

  public void addLotto(int index, Lotto lotto) {
    lotti.add(index, lotto);
  }
  
  public Lotto getLottoAt(int index) {
    return lotti.get(index);
  }
  
  public void removeLotto(int index) {
    lotti.remove(index);
  }
  
  public void removeLotto(Lotto lotto) {
    lotti.remove(lotto);
  }
  
  public int getLottiSize() {
    return lotti.size();
  }
  

  public String toString() {
    return nome;
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof Prodotto)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    return id == ((Prodotto)other).id;
  }
}
