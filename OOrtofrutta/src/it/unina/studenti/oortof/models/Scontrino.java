package it.unina.studenti.oortof.models;

import java.util.Date;
import java.util.List;

public class Scontrino {

  private int id;
  private Cliente cliente;
  private Date dataOrario;
  private float prezzoTotale;
  private List<Acquisto> acquisti;

  public Scontrino(int id, Cliente cliente, Date dataOrario, float prezzoTotale, List<Acquisto> acquisti) {
    this.id = id;
    this.cliente = cliente;
    this.dataOrario = dataOrario;
    this.prezzoTotale = prezzoTotale;
    this.acquisti = acquisti;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Date getDataOrario() {
    return dataOrario;
  }

  public void setDataOrario(Date dataOrario) {
    this.dataOrario = dataOrario;
  }

  public float getPrezzoTotale() {
    return prezzoTotale;
  }

  public void setPrezzoTotale(float prezzoTotale) {
    this.prezzoTotale = prezzoTotale;
  }
  
  public List<Acquisto> getAcquisti() {
    return acquisti;
  }

  public void setAcquisti(List<Acquisto> acquisti) {
    this.acquisti = acquisti;
  }
  
  public void addAcquisto(Acquisto acquisto) {
    acquisti.add(acquisto);
  }

  public void addLotto(int index, Acquisto acquisto) {
    acquisti.add(index, acquisto);
  }
  
  public Acquisto getAcquistoAt(int index) {
    return acquisti.get(index);
  }
  
  public void removeAcquisto(int index) {
    acquisti.remove(index);
  }
  
  public void removeAcquisto(Acquisto acquisto) {
    acquisti.remove(acquisto);
  }
  
  public int getAcquistiSize() {
    return acquisti.size();
  }
  
  
}
