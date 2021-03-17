package it.unina.studenti.oortof.controllers;

import it.unina.studenti.oortof.models.ApplicationStatus;

public class ProdottiController implements Controller {

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
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_UPDATE);
  }

  @Override
  public void search() {
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_SEARCH);
  }

  @Override
  public void commit() {
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }

  @Override
  public void delete() {
  }

}
