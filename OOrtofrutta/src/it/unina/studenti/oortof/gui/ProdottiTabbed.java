package it.unina.studenti.oortof.gui;

import javax.swing.JTabbedPane;

import it.unina.studenti.oortof.models.ApplicationCounter;
import it.unina.studenti.oortof.models.ApplicationStatus;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;

public class ProdottiTabbed extends JTabbedPane {

  private static final long serialVersionUID = 1L;
  ProdottiPanel prodottiPanel = new ProdottiPanel();
  ProdottiListPanel prodottiListPanel = new ProdottiListPanel();

  public ProdottiTabbed() {
    setTabPlacement(JTabbedPane.RIGHT);
    this.add(prodottiPanel);
    this.add(prodottiListPanel);
    setIconAt(0, new ImageIcon(ProdottiTabbed.class.getResource("/it/unina/studenti/oortof/gui/resources/images/detail.gif")));
    setIconAt(1, new ImageIcon(ProdottiTabbed.class.getResource("/it/unina/studenti/oortof/gui/resources/images/list.gif")));
    ApplicationStatus.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        applicationStatusChanged(evt);
      }
    });
  }

  void applicationStatusChanged(PropertyChangeEvent evt) {
    ApplicationStatus as = ApplicationStatus.getInstance();
    if (as.getActiveTab() != 0) {
      return;
    }
    ApplicationCounter ac = ApplicationCounter.getInstance();
    if (evt.getPropertyName().equals("status")) {
      if (as.isSearch()) {
        setSelectedIndex(0);
        setEnabledAt(1, false);
      }
      if (as.isNavigation()) {
        setEnabledAt(1, ac.getLimit() > 1);
        if (ac.getLimit() <= 1) {
          setSelectedIndex(0);
        }
      }
      if (evt.getOldValue().equals(ApplicationStatus.STATUS_SEARCH) && evt.getNewValue().equals(ApplicationStatus.STATUS_NAVIGATION) && as.getAction() == ApplicationStatus.ACTION_COMMIT) {
        setSelectedIndex(ac.getLimit() <= 1 ? 0 : 1);
      }
    }
  }
}
