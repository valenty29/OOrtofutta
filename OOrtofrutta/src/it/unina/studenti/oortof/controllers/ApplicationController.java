package it.unina.studenti.oortof.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import it.unina.studenti.oortof.models.application.ApplicationCounter;
import it.unina.studenti.oortof.models.application.ApplicationInfo;
import it.unina.studenti.oortof.models.application.ApplicationStatus;
import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.ObservedModel;

public class ApplicationController implements Controller, PropertyChangeListener {

  private static final ApplicationController instance = new ApplicationController();
  
  Controller[] controllers = new Controller[3];
  
  private void initControllers() {
	  controllers[0] = new ProdottiController();
	  controllers[1] = new CarrelloController();
	  controllers[2] = new ClientiController();
  }
  
  private ApplicationController() {
	initControllers();
    ApplicationStatus.getInstance().addPropertyChangeListener(this);
    ApplicationCounter.getInstance().addPropertyChangeListener(this);
  }
  
  public static ApplicationController getInstance() {
    return instance;
  }
  
  public Controller getSubController(int index) {
    return controllers[index];
  }
  
  public void insert() {
    ApplicationInfo.getInstance().setMessage("", ApplicationInfo.LEVEL_LOG);
    controllers[ApplicationStatus.getInstance().getActiveTab()].insert();
  }

  public void update() {
    ApplicationInfo.getInstance().setMessage("", ApplicationInfo.LEVEL_LOG);
    controllers[ApplicationStatus.getInstance().getActiveTab()].update();
  }

  public void search() {
    ApplicationInfo.getInstance().setMessage("", ApplicationInfo.LEVEL_LOG);
    controllers[ApplicationStatus.getInstance().getActiveTab()].search();
  }
  
  public void rollback() {
    ApplicationInfo.getInstance().setMessage("", ApplicationInfo.LEVEL_LOG);
    controllers[ApplicationStatus.getInstance().getActiveTab()].rollback();
  }
  
  public void commit() {
    ApplicationInfo.getInstance().setMessage("", ApplicationInfo.LEVEL_LOG);
    controllers[ApplicationStatus.getInstance().getActiveTab()].commit();
  }
  
  public void delete() {
    ApplicationInfo.getInstance().setMessage("", ApplicationInfo.LEVEL_LOG);
    controllers[ApplicationStatus.getInstance().getActiveTab()].delete();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("counter")) {
      controllers[ApplicationStatus.getInstance().getActiveTab()].listToDetail();
    }
    else if (evt.getPropertyName().equals("action")) {
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

  @Override
  public void setModel(ObservedModel observedModel, ObservedList observedList) {
    throw new RuntimeException("Application controller lavora senza un modello");
  }

  public void setModel(ObservedModel observedModel) {
    throw new RuntimeException("Application controller lavora senza un modello");
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
