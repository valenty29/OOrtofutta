package it.unina.studenti.oortof.controllers;

import it.unina.studenti.oortof.dao.ClienteDAO;
import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.models.entities.Carrello;
import it.unina.studenti.oortof.models.entities.Cliente;
import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.ObservedModel;

public class CarrelloController implements Controller {
  private Carrello carrello;
  private Cliente cliente;
  private ClienteDAO clienteDAO;
  public CarrelloController() {
    clienteDAO = new SQLClienteDAO();
  }
  @Override
  public void rollback() {
    throw new RuntimeException("Rollback non permesso per il Carrello");
  }

  @Override
  public void insert() {
    throw new RuntimeException("Insert non permesso per il Carrello");
    
  }

  @Override
  public void update() {
    throw new RuntimeException("Update non permesso per il Carrello");
    
  }

  @Override
  public void search() {
    throw new RuntimeException("Search non permesso per il Carrello");
    
  }

  @Override
  public void commit() {
    System.out.println("COMMITTT");
    
  }

  @Override
  public void delete() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setModel(ObservedModel observedModel, ObservedList observedList) {
    // TODO Auto-generated method stub

    
  }
  public void setModel(ObservedModel observedModel) {
    // TODO Auto-generated method stub


  }

  public void setModel(Carrello carrello, Cliente cliente) {

    this.carrello = carrello;
    this.cliente = cliente;
  }

  @Override
  public void listToDetail() {
    // TODO Auto-generated method stub

  }

  @Override
  public void preDelete() {
    // TODO Auto-generated method stub
    
  }

  
  
}
