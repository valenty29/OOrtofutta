package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;

public class Cliente extends ObservedModel implements PropertyChangeListener{
  private int id;
  private String cf;
  private String nome;
  private String cognome;
  private Date dataNascita;
  private String luogoNascita;
  private Genere genere;
  private String email;
  private int totalePunti;
  private RaccoltaPunti raccoltaPunti;
  private List<Scontrino> scontrini;

  public Cliente(int id, String cf, String nome, String cognome, Date dataNascita, String luogoNascita, Genere genere, String email, int totalePunti, RaccoltaPunti raccoltaPunti) {
    this.id = id;
    this.cf = cf;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.luogoNascita = luogoNascita;
    this.genere = genere;
    this.email = email;
    this.raccoltaPunti = raccoltaPunti;
    this.totalePunti = totalePunti;
  }

  public RaccoltaPunti getRaccoltaPunti() {
    return raccoltaPunti;
  }

  public void setRaccoltaPunti(RaccoltaPunti raccoltaPunti) {
    this.raccoltaPunti = raccoltaPunti;
  }

  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    int oldId = this.id;
    this.id = id;
    firePropertyChanged("id", oldId, id);
  }
  
  public String getCF() {
    return cf;
  }

  public void setCF(String cf) {
    String oldCF = this.cf;
    this.cf = cf;
    firePropertyChanged("cf", oldCF, cf);    
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    String oldNome = this.nome;
    this.nome = nome;
    firePropertyChanged("nome", oldNome, nome);
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    String oldCognome = this.cognome;
    this.cognome = cognome;
    firePropertyChanged("cognome", oldCognome, cognome);
  }

  public Date getDataNascita() {
    return dataNascita;
  }

  public void setDataNascita(Date dataNascita) {
    Date oldDataNascita = this.dataNascita;
    this.dataNascita = dataNascita;
    firePropertyChanged("dataNascita", oldDataNascita, dataNascita);
  }

  public String getLuogoNascita() {
    return luogoNascita;
  }

  public void setLuogoNascita(String luogoNascita) {
    String oldLuogoNascita = this.luogoNascita;
    this.luogoNascita = luogoNascita;
    firePropertyChanged("luogoNascita", oldLuogoNascita, luogoNascita);
  }

  public Genere getSesso() {
    return genere;
  }

  public void setSesso(Genere genere) {
    Genere oldGenere = this.genere;
    this.genere = genere;
    firePropertyChanged("genere", oldGenere, genere);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    String oldEmail = this.email;
    this.email = email;
    firePropertyChanged("email", oldEmail, email);
  }

  public int getTotalePunti() {
    return totalePunti;
  }

  public void setTotalePunti(int totalePunti) {
    int oldTotalePunti = this.totalePunti;
    this.totalePunti = totalePunti;
    firePropertyChanged("totalePunti", oldTotalePunti, totalePunti);
  }
  
  public List<Scontrino> getScontrini() {
    return scontrini;
  }

  public void setScontrini(List<Scontrino> scontrini) {
    for (Scontrino scontrino : scontrini) {
      scontrino.addPropertyChangeListener(this);       
    }
    List<Scontrino> oldScontrini = this.scontrini;
    this.scontrini = scontrini;
    firePropertyChanged("scontrini", oldScontrini, scontrini);
  }
  
  public void addScontrino(Scontrino scontrino) {
    scontrini.add(scontrino);
    firePropertyChanged("scontrino", null, scontrino);
    scontrino.addPropertyChangeListener(this);
  }

  public void addScontrino(int index, Scontrino scontrino) {
    scontrini.add(index, scontrino);
    firePropertyChanged("scontrino", index, scontrino);
    scontrino.addPropertyChangeListener(this);
  }
  
  public Scontrino getScontrinoAt(int index) {
    return scontrini.get(index);
  }
  
  public void removeScontrino(int index) {
    scontrini.remove(index);
    Scontrino removedScontrino = scontrini.remove(index);
    removedScontrino.removePropertyChangeListener(this);
    firePropertyChanged("scontrino", removedScontrino, index);
  }
  
  public void removeScontrino(Scontrino scontrino) {
    int toRemoveIndex = scontrini.indexOf(scontrino);
    if (toRemoveIndex >= 0) {
      removeScontrino(toRemoveIndex);
    }
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

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }
}

