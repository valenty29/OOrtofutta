package it.unina.studenti.oortof.controllers;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import it.unina.studenti.oortof.dao.ProdottoDAO;
import it.unina.studenti.oortof.dao.SQLProdottoDAO;
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
  
  private ProdottoDAO prodottoDao;
  
  public ProdottiController() {
	  prodottoDao = new SQLProdottoDAO();
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
      prodottoDao.createProdotto(prodotto);
    } catch (ValidationException ve) {
      JOptionPane.showMessageDialog(null, ve.toString(), "Campi invalidi", JOptionPane.ERROR_MESSAGE);
      ApplicationInfo.getInstance().setMessage("Sono stati inseriti dei dati non validi", ApplicationInfo.LEVEL_ERROR);
      ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_NONE);
      return;
    } catch (DatabaseException de) {
      ApplicationInfo.getInstance().setMessage(de.getErrorMessage(), ApplicationInfo.LEVEL_ERROR);
      ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_NONE);
      return;
    }

    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }
  
  void commitUpdate() {
    try {
      prodottoDao.updateProdotto(oldProdotto, prodotto);
      prodotti.set(prodotti.indexOf(oldProdotto), prodotto);
    }
    catch (DatabaseException e) {
      ApplicationInfo.getInstance().setMessage(e.getErrorMessage(), ApplicationInfo.LEVEL_ERROR);
      ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_NONE);
      return;
    }

    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }
  
  void commitSearch() {
    try {
      Prodotto prodottoDAO = new Prodotto();
      prodotto.copyTo(prodottoDAO);
      ObservedList<Prodotto> ritorno = prodottoDao.getProdotti(prodottoDAO);
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
    try {
      prodottoDao.deleteProdotti(toDelete);
      prodotti.remove(prodotto);
    } catch (DatabaseException de) {
      ApplicationInfo.getInstance().setMessage(de.getErrorMessage(), ApplicationInfo.LEVEL_ERROR);
      ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_NONE);
      return;
    }

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
