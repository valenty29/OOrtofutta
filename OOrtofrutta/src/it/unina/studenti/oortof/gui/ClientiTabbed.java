package it.unina.studenti.oortof.gui;

import javax.swing.JTabbedPane;

import it.unina.studenti.oortof.models.application.ApplicationCounter;
import it.unina.studenti.oortof.models.application.ApplicationStatus;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class ClientiTabbed extends JTabbedPane {

  private static final long serialVersionUID = 1L;
  ClientiPanel clientiPanel = new ClientiPanel();
  ClientiListPanel clientiListPanel = new ClientiListPanel();

  public ClientiTabbed() {
    setTabPlacement(JTabbedPane.RIGHT);
    this.add(clientiPanel);
    this.add(clientiListPanel);
    setEnabledAt(1, false);
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
    if (!ApplicationStatus.getInstance().isNavigation() || ApplicationCounter.getInstance().getCounter() < 0) {
      setSelectedIndex(0);
      setEnabledAt(1, false);
    }
    else {
      setEnabledAt(1, true);
    }
    Component [] components = ((JPanel)this.getParent().getParent()).getComponents();
    JTabbedPane ilTabbed = null;
    for (Component component : components) {
     if (component instanceof JTabbedPane) {ilTabbed = (JTabbedPane)component;}
    }
    if (evt.getOldValue().equals(ApplicationStatus.STATUS_SEARCH) && evt.getNewValue().equals(ApplicationStatus.STATUS_NAVIGATION) && ApplicationCounter.getInstance().getLimit() > 0 && ilTabbed.getSelectedIndex() == 2) {
      setSelectedIndex(1);
    }
  }
}
