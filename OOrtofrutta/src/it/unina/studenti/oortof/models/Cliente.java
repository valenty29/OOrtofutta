package it.unina.studenti.oortof.models;

import java.util.Date;
import java.util.List;

public class Cliente {
  private int id;
  private String cf;
  private String nome;
  private String cognome;
  private Date dataNascita;
  private String luogoNascita;
  private Genere genere;
  private String email;
  private int totalePunti;
  private List<Scontrino> scontrini;

  public Cliente(int id, String cf, String nome, String cognome, Date dataNascita, String luogoNascita, Genere genere, String email, int totalePunti, List<Scontrino> scontrini) {
    this.id = id;
    this.cf = cf;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.luogoNascita = luogoNascita;
    this.genere = genere;
    this.email = email;
    this.totalePunti = totalePunti;
    this.scontrini = scontrini;
  }

  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getCF() {
    return cf;
  }

  public void setCF(String CF) {
    this.cf = CF;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public Date getDataNascita() {
    return dataNascita;
  }

  public void setDataNascita(Date dataNascita) {
    this.dataNascita = dataNascita;
  }

  public String getLuogoNascita() {
    return luogoNascita;
  }

  public void setLuogoNascita(String luogoNascita) {
    this.luogoNascita = luogoNascita;
  }

  public Genere getSesso() {
    return genere;
  }

  public void setSesso(Genere genere) {
    this.genere = genere;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getTotalePunti() {
    return totalePunti;
  }

  public void setTotalePunti(int totalePunti) {
    this.totalePunti = totalePunti;
  }
  
  public List<Scontrino> getScontrini() {
    return scontrini;
  }

  public void setScontrini(List<Scontrino> scontrini) {
    this.scontrini = scontrini;
  }
  
  public void addScontrino(Scontrino scontrino) {
    scontrini.add(scontrino);
  }

  public void addScontrino(int index, Scontrino scontrino) {
    scontrini.add(index, scontrino);
  }
  
  public Scontrino getScontrinoAt(int index) {
    return scontrini.get(index);
  }
  
  public void removeScontrino(int index) {
    scontrini.remove(index);
  }
  
  public void removeScontrino(Scontrino scontrino) {
    scontrini.remove(scontrino);
  }
  
  public int getScontriniSize() {
    return scontrini.size();
  }
  
  public String toString() {
    return cognome + " " + nome;
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof Cliente)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (id > 0 && ((Cliente)other).id > 0) {
      return id == ((Cliente)other).id;
    }
    return cf.equals(((Cliente)other).cf);
  }
}
