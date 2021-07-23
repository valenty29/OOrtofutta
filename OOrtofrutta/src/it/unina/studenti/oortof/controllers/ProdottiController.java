package it.unina.studenti.oortof.controllers;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import it.unina.studenti.oortof.dao.SQLProductDAO;
import it.unina.studenti.oortof.models.*;
import it.unina.studenti.oortof.models.application.ApplicationCounter;
import it.unina.studenti.oortof.models.application.ApplicationInfo;
import it.unina.studenti.oortof.models.application.ApplicationStatus;
import it.unina.studenti.oortof.models.entities.Carrello;
import it.unina.studenti.oortof.models.entities.Lotto;
import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.prodotti.Prodotto;
import it.unina.studenti.oortof.models.exception.DatabaseException;
import it.unina.studenti.oortof.models.exception.ValidationException;

public class ProdottiController implements Controller<Prodotto> {

  private Carrello carrello;
  private Prodotto oldProdotto = new Prodotto();
  private Prodotto prodotto;
  private ObservedList<Prodotto> prodotti;
  
  private SQLProductDAO sqlProductDao;
  
  public ProdottiController() {
	  sqlProductDao = new SQLProductDAO();
  }
  
  @Override
  public void rollback() {
    oldProdotto.copyTo(prodotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }

  @Override
  public void insert() {
    prodotto.copyTo(oldProdotto);
    prodotto.clear();
    Lotto lotto = new Lotto();
    prodotto.getProdottoCommon().addLotto(lotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_INSERT);
  }

  @Override
  public void update() {
    prodotto.copyTo(oldProdotto);
    Lotto lotto = new Lotto();
    prodotto.getProdottoCommon().addLotto(lotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_UPDATE);
  }

  @Override
  public void search() {
    prodotto.copyTo(oldProdotto);
    prodotto.clear();
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_SEARCH);
  }

  void commitInsert() {
    try {
      sqlProductDao.createProduct(prodotto);
    } catch (ValidationException ve) {
      JOptionPane.showMessageDialog(null, ve.toString(), "Campi invalidi", JOptionPane.ERROR_MESSAGE);
      ApplicationInfo.getInstance().setMessage("Sono stati inseriti dei dati non validi", ApplicationInfo.LEVEL_ERROR);
    } catch (DatabaseException de) {
      ApplicationInfo.getInstance().setMessage("Si è verificato un errore nella base dati", ApplicationInfo.LEVEL_ERROR);
    }

    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }
  
  void commitUpdate() {
    sqlProductDao.updateProducts(oldProdotto, prodotto);
    prodotti.set(prodotti.indexOf(oldProdotto), prodotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }
  
  void commitSearch() {
    try {
      Prodotto prodottoDAO = new Prodotto();
      prodotto.copyTo(prodottoDAO);
      ObservedList<Prodotto> ritorno = sqlProductDao.getProduct(prodottoDAO);
      ritorno.copyTo(prodotti);
      ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
    }
    catch (ValidationException ve) {
      JOptionPane.showMessageDialog(null, ve.toString(), "Campi non validi", JOptionPane.ERROR_MESSAGE);
      ApplicationInfo.getInstance().setMessage("CAMPI NON VALIDI", ApplicationInfo.LEVEL_ERROR);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
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
  public void preDelete() {
  }

  

  @Override
  public void delete() {
    ArrayList<Prodotto> toDelete = new ArrayList<>();
    toDelete.add(prodotto);
    sqlProductDao.deleteProducts(toDelete);
    prodotti.remove(prodotto);
  }

  public void setModel(Prodotto prodotto, ObservedList<Prodotto> prodotti, Carrello carrello) {
    this.carrello = carrello;
    this.prodotto = prodotto;
    this.prodotti = prodotti;
  }

  @Override
  public void setModel(Prodotto prodotto) {

  }

  @Override
  public void setModel(Prodotto prodotto, ObservedList<Prodotto> prodotti) {

  }

  @Override
  public void listToDetail() {
    int index = ApplicationCounter.getInstance().getCounter();
    if (index > 0 && index < prodotti.size() + 1) {
      prodotti.get(index - 1).copyTo(prodotto);
      prodotto.getProdottoCommon().getLotti().forEach(lotto -> {
        carrello.getLotti().stream().filter(lotto1 -> {
          if (lotto1.getId().equals(lotto.getId())) {
            lotto.setDisponibilita(lotto.getDisponibilita() - lotto1.getDisponibilita());
            return true;
          }
          return false;
        }).findFirst();
      });
    }
    else {
      prodotto.clear();
    }
  }

}
