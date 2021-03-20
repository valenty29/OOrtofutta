package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends ObservedModel implements PropertyChangeListener{


  public static final int ID = 0;   // Float
  public static final int CF = 1;             // Boolean
  public static final int NOME = 2;           // TipoBibita
  public static final int COGNOME = 3;
  public static final int DATA_NASCITA = 4;
  public static final int LUOGO_NASCITA = 5;
  public static final int GENERE = 6;
  public static final int EMAIL = 7;
  public static final int RACCOLTA_PUNTI = 9;
  public static final int SCONTRINI = 10;

  public Cliente(){
    attributes = new Object[11];
    setRaccoltaPunti(new RaccoltaPunti());
    attributes[SCONTRINI] = new ArrayList<Scontrino>();
  }

  public Cliente(Integer id, String cf, String nome, String cognome, LocalDate dataNascita, String luogoNascita, Genere genere, String email, RaccoltaPunti raccoltaPunti) {
    this();
    setValue(ID, id);
    setValue(CF, cf);
    setValue(NOME, nome);
    setValue(COGNOME, cognome);
    setValue(DATA_NASCITA, dataNascita);
    setValue(LUOGO_NASCITA, luogoNascita);
    setValue(GENERE, genere);
    setValue(EMAIL, email);
    setValue(RACCOLTA_PUNTI, raccoltaPunti);
  }

  public RaccoltaPunti getRaccoltaPunti() {
    return (RaccoltaPunti)attributes[RACCOLTA_PUNTI];
  }

  public void setRaccoltaPunti(RaccoltaPunti raccoltaPunti) {

    if (equals(raccoltaPunti, attributes[RACCOLTA_PUNTI])) {
      return;
    }
    RaccoltaPunti oldRaccoltaPunti = (RaccoltaPunti)attributes[RACCOLTA_PUNTI];
    attributes[RACCOLTA_PUNTI] = raccoltaPunti;
    firePropertyChanged("raccoltaPunti", oldRaccoltaPunti, raccoltaPunti);
  }

  public Integer getId() {
    return getInteger(ID);
  }
  
  public void setId(Integer id) {
    Integer oldId = getId();
    setValue(ID, id);
    firePropertyChanged("id", oldId, id);
  }
  
  public String getCF() {
    return getString(CF);
  }

  public void setCF(String cf) {
    String oldCF = getCF();
    setValue(CF, cf);
    firePropertyChanged("cf", oldCF, cf);    
  }

  public String getNome() {
    return getString(NOME);
  }

  public void setNome(String nome) {
    String oldNome = getNome();
    setValue(NOME, nome);
    firePropertyChanged("nome", oldNome, nome);
  }

  public String getCognome() {
    return (String)attributes[COGNOME];
  }

  public void setCognome(String cognome) {
    if(equals(cognome, attributes[COGNOME])){
      return;
    }
    String oldCognome = (String)attributes[COGNOME];
    setValue(COGNOME, cognome);
    firePropertyChanged("cognome", oldCognome, cognome);
  }

  public LocalDate getDataNascita() {
	  try {
		  return LocalDate.parse((String)attributes[DATA_NASCITA]);
	  } catch (Exception e) {
		  return null;
	  }
  }

  public void setDataNascita(LocalDate dataNascita) {
    if(equals(dataNascita, attributes[DATA_NASCITA])){
      return;
    }
    LocalDate oldData = (LocalDate)attributes[DATA_NASCITA];
    setValue(DATA_NASCITA, dataNascita);
    firePropertyChanged("dataNascita", oldData, dataNascita);
  }

  public String getLuogoNascita() {
    return (String)attributes[LUOGO_NASCITA];
  }

  public void setLuogoNascita(String luogoNascita) {
    if(equals(luogoNascita, attributes[LUOGO_NASCITA])){
      return;
    }
    String oldLuogoNascita = (String)attributes[LUOGO_NASCITA];
    attributes[LUOGO_NASCITA] = luogoNascita;
    firePropertyChanged("luogoNascita", oldLuogoNascita, luogoNascita);
  }

  public Genere getGenere() {
	try {
		return Genere.valueOf(getString(Cliente.GENERE));
	} catch (Exception e) {
		return null;
	}
    
  }

  public void setGenere(Genere genere) {
    Genere oldGenere = getGenere();
    setValue(GENERE, genere);
    firePropertyChanged("genere", oldGenere, genere);
  }

  public String getEmail() {
    return getString(EMAIL);
  }

  public void setEmail(String email) {
    String oldEmail = getEmail();
    setValue(EMAIL, email);
    firePropertyChanged("email", oldEmail, email);
  }
  
  public List<Scontrino> getScontrini() {
    return (List<Scontrino>)attributes[SCONTRINI];
  }

  public void setScontrini(List<Scontrino> scontrini) {
    if (scontrini == attributes[SCONTRINI]) {
      return;
    }
    for (Scontrino scontrino : scontrini) {
      scontrino.addPropertyChangeListener(this);
    }
    List<Scontrino> oldScontrini = (List<Scontrino>)attributes[SCONTRINI];
    attributes[SCONTRINI] = scontrini;
    firePropertyChanged("scontrini", oldScontrini, scontrini);
  }
  
  public void addLotto(Scontrino scontrino) {
    ((List<Scontrino>)attributes[SCONTRINI]).add(scontrino);
    firePropertyChanged("scontrini", null, scontrino);
    scontrino.addPropertyChangeListener(this);
  }

  public void addLotto(int index, Scontrino scontrino) {
    ((List<Scontrino>)attributes[SCONTRINI]).add(index, scontrino);
    firePropertyChanged("scontrini", index, scontrino);
    scontrino.addPropertyChangeListener(this);
  }
  
  public Scontrino getScontrinoAt(int index) {
    return ((List<Scontrino>)attributes[SCONTRINI]).get(index);
  }
  
  public void removeScontrino(int index) {
    Scontrino removedScontrino = ((List<Scontrino>)attributes[SCONTRINI]).remove(index);
    removedScontrino.removePropertyChangeListener(this);
    firePropertyChanged("scontrini", removedScontrino, index);
  }
  
  public void removeScontrino(Scontrino scontrino) {
    int toRemoveIndex = ((List<Scontrino>)attributes[SCONTRINI]).indexOf(scontrino);
    if (toRemoveIndex >= 0) {
      removeScontrino(toRemoveIndex);
    }
  }
  
  public void clearScontrini() {
    if (((List<Scontrino>)attributes[SCONTRINI]).size() == 0) {
      return;
    }
    List<Scontrino> oldScontrini = new ArrayList<Scontrino>(((List<Scontrino>)attributes[SCONTRINI]));
    ((List<Scontrino>)attributes[SCONTRINI]).clear();
    firePropertyChanged("scontrini", oldScontrini, ((List<Scontrino>)attributes[SCONTRINI]));
  }
  
  public int getScontriniSize() {
    return ((List<Scontrino>)attributes[SCONTRINI]).size();
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder("");
    sb.append(getCognome());
    sb.append(" ");
    sb.append(getNome());
    return sb.toString();
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof Cliente)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (getId() > 0 && ((Cliente)other).getId() > 0) {
      return getId() == ((Cliente)other).getId();
    }
    return getCF().equals(((Cliente)other).getCF());
  }

  public void copyTo(Cliente cliente) {
    cliente.setId(getId());
    cliente.setCF(getCF());
    cliente.setNome(getNome());
    cliente.setCognome(getCognome());
    cliente.setDataNascita(getDataNascita());
    cliente.setLuogoNascita(getLuogoNascita());
    cliente.setGenere(getGenere());
    cliente.setEmail(getEmail());
    cliente.setRaccoltaPunti(getRaccoltaPunti());
    cliente.getScontrini().clear();
    for (Scontrino scontrino: ((List<Scontrino>)attributes[SCONTRINI])) {
      Scontrino newScontrino = new Scontrino();
      scontrino.copyTo(newScontrino);
      ((List<Scontrino>)attributes[SCONTRINI]).add(newScontrino);
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }
  
  public void clear() {
	  getRaccoltaPunti().clear();
	  clearScontrini();
	  setCF(null);
	  setCognome(null);
	  setDataNascita(null);
	  setEmail(null);
	  setGenere(null);
	  setNome(null);
	  setId(null);
	  setLuogoNascita(null);
  }
}

