package it.unina.studenti.oortof.controllers;

import it.unina.studenti.oortof.dao.ClienteDAO;
import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.models.application.ApplicationStatus;
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
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }

  @Override
  public void insert() {
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_INSERT);
    
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
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
    
  }

  @Override
  public void delete() {
    System.out.println("delete del controllerr");
    
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
    throw new RuntimeException("Carrello non ha un sistema lista/dettaglio");

  }

  @Override
  public void preDelete() {
    System.out.println("preDelete dal Controller");
    
  }

  
  
}
