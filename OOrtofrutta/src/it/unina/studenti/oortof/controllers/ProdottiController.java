package it.unina.studenti.oortof.controllers;

import it.unina.studenti.oortof.dao.SQLProductDAO;
import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.ObservedList;
import it.unina.studenti.oortof.models.ObservedModel;
import it.unina.studenti.oortof.models.Prodotto;

public class ProdottiController implements Controller {
  
  private Prodotto prodotto;
  private Prodotto oldProdotto = new Prodotto();
  private ObservedList prodotti = new ObservedList("prodotti");
  
  SQLProductDAO sqlProductDao = new SQLProductDAO();
  
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

  void commitInsert() {
  }
  
  void commitUpdate() {
  }
  
  void commitSearch() {
  }
  
  @Override
  public void commit() {
    switch (ApplicationStatus.getInstance().getStatus()) {
      case ApplicationStatus.STATUS_INSERT: commitInsert(); break;
      case ApplicationStatus.STATUS_UPDATE: commitUpdate(); break;
      case ApplicationStatus.STATUS_SEARCH: commitSearch(); break;
    }
  }

  @Override
  public void delete() {
  }

  @Override
  public void setModel(ObservedModel prodotto, ObservedList prodotti) {
    this.prodotto = (Prodotto)prodotto;
    this.prodotti = prodotti;
  }

}
