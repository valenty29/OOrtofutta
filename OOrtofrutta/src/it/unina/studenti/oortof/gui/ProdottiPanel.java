package it.unina.studenti.oortof.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.SystemColor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.Lotto;
import it.unina.studenti.oortof.models.ProdottoCommon;
import it.unina.studenti.oortof.models.ProdottoSpecifico;

public class ProdottiPanel extends DesignProdottiPanel {

  private static final long serialVersionUID = 1L;
  
  ProdottoCommon prodottoCommon;
  ProdottoSpecifico[] prodottiSpecifici;
  List<Lotto> lotti;
  
  ButtonGroup bg = new ButtonGroup();

  public ProdottiPanel() {
    ApplicationStatus.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        applicationStatusChanged(evt);
      }
    });
  }
  
  public void setModel(ProdottoCommon prodottoCommon, ProdottoSpecifico[] prodottiSpecifici) {
    this.prodottoCommon = prodottoCommon;
    this.prodottiSpecifici = prodottiSpecifici;
    PropertyChangeListener dataModelListener = new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        dataModelChanged(evt);
      }
    };
    prodottoCommon.addPropertyChangeListener(dataModelListener);
    for (ProdottoSpecifico prodottoSpecifico : prodottiSpecifici) {
      prodottoSpecifico.addPropertyChangeListener(dataModelListener);
    }
  }
  
  void setEnabledColor(boolean enabled, Color color) {
    setEnabledColor(this, enabled, color);
  }
  
  void setEnabledColor(Container container, boolean enabled, Color color) {
    for (int i = 0; i < container.getComponentCount(); i++) {
      Component c = container.getComponent(i);
      if (c instanceof JTextField || c instanceof AbstractButton) {
        c.setEnabled(enabled);
        c.setBackground(color);
      }
      else if (c instanceof Container) {
        setEnabledColor((Container)c, enabled, color);
      }
    }
  }
  
  void groupCheckBox(boolean group) {
    if (group) {
      bg.add(fruttaVerduraCheckbox);
      bg.add(carnePesceCheckbox);
      bg.add(prodottiCaseariCheckbox);
      bg.add(bibiteCheckbox);
      bg.add(farinaceiCheckbox);
      bg.add(conserveCheckbox);
      bg.add(uovaCheckbox);
      bg.add(altriTipoCheckbox);
    }
    else {
      bg.remove(fruttaVerduraCheckbox);
      bg.remove(carnePesceCheckbox);
      bg.remove(prodottiCaseariCheckbox);
      bg.remove(bibiteCheckbox);
      bg.remove(farinaceiCheckbox);
      bg.remove(conserveCheckbox);
      bg.remove(uovaCheckbox);
      bg.remove(altriTipoCheckbox);
    }
  }
  
  void navigation() {
    setEnabledColor(false, SystemColor.control);
  }

  void insert() {
    setEnabledColor(true, Color.white);
    groupCheckBox(true);
  }

  void update() {
    setEnabledColor(true, Color.cyan);
    groupCheckBox(true);
  }

  void search() {
    setEnabledColor(true, Color.yellow);
    groupCheckBox(false);
  }

  void applicationStatusChanged(PropertyChangeEvent evt) {
    switch (ApplicationStatus.getInstance().getStatus()) {
      case ApplicationStatus.NAVIGATION: navigation(); break;
      case ApplicationStatus.INSERT: insert(); break;
      case ApplicationStatus.UPDATE: update(); break;
      case ApplicationStatus.SEARCH: search(); break;
    }
  }
  
  void dataModelChanged(PropertyChangeEvent evt) {
    nomeTextField.setText(prodottoCommon.getNome());
    codiceProdottoTextField.setValue(prodottoCommon.getId());
    prezzoTextField.setValue(prodottoCommon.getPrezzo());
    sfusoCheckBox.setSelected(prodottoCommon.isSfuso());
  }
  
}
