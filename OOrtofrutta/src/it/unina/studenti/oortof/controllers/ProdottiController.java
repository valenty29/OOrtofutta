package it.unina.studenti.oortof.controllers;

import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.ObservedModel;
import it.unina.studenti.oortof.models.Prodotto;

public class ProdottiController implements Controller {
  
  private Prodotto prodotto;
  private Prodotto oldProdotto = new Prodotto();

  @Override
  public void rollback() {
    oldProdotto.copyTo(prodotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }

  @Override
  public void insert() {
    prodotto.copyTo(oldProdotto);
    prodotto.clear();
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_INSERT);
  }

  @Override
  public void update() {
    prodotto.copyTo(oldProdotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_UPDATE);
  }

  @Override
  public void search() {
    prodotto.copyTo(oldProdotto);
    prodotto.clear();
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_SEARCH);
  }

  @Override
  public void commit() {
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }

  @Override
  public void delete() {
  }

  @Override
  public void setModel(ObservedModel observedModel) {
    prodotto = (Prodotto)observedModel;
  }

}
