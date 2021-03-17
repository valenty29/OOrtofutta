package it.unina.studenti.oortof.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unina.studenti.oortof.models.ApplicationCounter;
import it.unina.studenti.oortof.models.ApplicationStatus;

public class ApplicationController implements Controller, PropertyChangeListener {

  private static final ApplicationController instance = new ApplicationController();
  
  Controller[] controllers = {new ProdottiController(), new CarrelloController(), new ClientiController()};
  
  private ApplicationController() {
    ApplicationStatus.getInstance().addPropertyChangeListener(this);
    ApplicationCounter.getInstance().addPropertyChangeListener(this);
  }
  
  public void insert() {
    controllers[ApplicationStatus.getInstance().getActiveTab()].insert();
  }

  public void update() {
    controllers[ApplicationStatus.getInstance().getActiveTab()].update();
  }

  public void search() {
    controllers[ApplicationStatus.getInstance().getActiveTab()].search();
  }
  
  public void rollback() {
    controllers[ApplicationStatus.getInstance().getActiveTab()].rollback();
  }
  
  public void commit() {
    controllers[ApplicationStatus.getInstance().getActiveTab()].commit();
  }
  
  public void delete() {
    controllers[ApplicationStatus.getInstance().getActiveTab()].delete();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("action")) {
      if (ApplicationStatus.getInstance().getAction() == ApplicationStatus.ACTION_ROLLBACK) {
        rollback();
      }
      else if (ApplicationStatus.getInstance().getAction() == ApplicationStatus.ACTION_INSERT) {
        insert();
      }
      else if (ApplicationStatus.getInstance().getAction() == ApplicationStatus.ACTION_UPDATE) {
        update();
      }
      else if (ApplicationStatus.getInstance().getAction() == ApplicationStatus.ACTION_SEARCH) {
        search();
      }
      else if (ApplicationStatus.getInstance().getAction() == ApplicationStatus.ACTION_COMMIT) {
        commit();
      }
      else if (ApplicationStatus.getInstance().getAction() == ApplicationStatus.ACTION_DELETE) {
        delete();
      }    
    }
  }
  
}
