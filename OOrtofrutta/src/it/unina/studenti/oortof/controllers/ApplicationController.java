package it.unina.studenti.oortof.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unina.studenti.oortof.models.ApplicationCounter;
import it.unina.studenti.oortof.models.ApplicationStatus;

public class ApplicationController implements PropertyChangeListener {

  private static final ApplicationController instance = new ApplicationController();
  
  private ApplicationController() {
    ApplicationStatus.getInstance().addPropertyChangeListener(this);
    ApplicationCounter.getInstance().addPropertyChangeListener(this);
  }
  
  private void navigation() {
  }

  private void insert() {
  }

  private void update() {
  }

  private void search() {
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("status")) {
      if (ApplicationStatus.getInstance().isNavigation()) {
        navigation();
      }
      else if (ApplicationStatus.getInstance().isInsert()) {
        insert();
      }
      else if (ApplicationStatus.getInstance().isUpdate()) {
        update();
      }
      else if (ApplicationStatus.getInstance().isSearch()) {
        search();
      }
    }
  }

}
