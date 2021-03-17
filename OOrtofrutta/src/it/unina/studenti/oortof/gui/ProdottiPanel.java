package it.unina.studenti.oortof.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import it.unina.studenti.oortof.models.ApplicationCounter;
import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.Prodotto;
import it.unina.studenti.oortof.models.ProdottoCommon;
import it.unina.studenti.oortof.models.ProdottoSpecifico;

public class ProdottiPanel extends DesignProdottiPanel {

  private static final long serialVersionUID = 1L;
  
  Prodotto prodotto;
  Prodotto oldProdotto = new Prodotto();
  
  ButtonGroup bg = new ButtonGroup();
  
  ActionListener checkBoxActionListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      boolean selection = ((JCheckBox)e.getSource()).isSelected();
      boolean oneSelected = false;
      oneSelected |= fruttaVerduraCheckbox.isSelected();  
      oneSelected |= conserveCheckbox.isSelected();       
      oneSelected |= prodottiCaseariCheckbox.isSelected();
      oneSelected |= farinaceiCheckbox.isSelected();      
      oneSelected |= uovaCheckbox.isSelected();           
      oneSelected |= carnePesceCheckbox.isSelected();     
      oneSelected |= bibiteCheckbox.isSelected();
      int lastIndex = -1;
      lastIndex = fruttaVerduraCheckbox.isSelected() ? 0 : lastIndex;  
      lastIndex = conserveCheckbox.isSelected() ? 1 : lastIndex;         
      lastIndex = prodottiCaseariCheckbox.isSelected() ? 2 : lastIndex;  
      lastIndex = farinaceiCheckbox.isSelected() ? 3 : lastIndex;        
      lastIndex = uovaCheckbox.isSelected() ? 4 : lastIndex;             
      lastIndex = carnePesceCheckbox.isSelected() ? 5 : lastIndex;       
      lastIndex = bibiteCheckbox.isSelected() ? 6 : lastIndex;           
      int selectedIndex = -1;
      if (selection) {
        if (e.getSource() == fruttaVerduraCheckbox) {
          selectedIndex = 0;
        }
        else if (e.getSource() == conserveCheckbox) {
          selectedIndex = 1;
        }
        else if (e.getSource() == prodottiCaseariCheckbox) {
          selectedIndex = 2;
        }
        else if (e.getSource() == farinaceiCheckbox) {
          selectedIndex = 3;
        }
        else if (e.getSource() == uovaCheckbox) {
          selectedIndex = 4;
        }
        else if (e.getSource() == carnePesceCheckbox) {
          selectedIndex = 5;
        }
        else if (e.getSource() == bibiteCheckbox) {
          selectedIndex = 6;
        }
      }
      caratteristicheSpecificheTabbed.setVisible(oneSelected);
      caratteristicheSpecificheTabbed.setSelectedIndex(selectedIndex >= 0 ? selectedIndex : lastIndex);
      
      caratteristicheSpecificheTabbed.setEnabledAt(0, fruttaVerduraCheckbox.isSelected());
      caratteristicheSpecificheTabbed.setEnabledAt(1, conserveCheckbox.isSelected());
      caratteristicheSpecificheTabbed.setEnabledAt(2, prodottiCaseariCheckbox.isSelected());
      caratteristicheSpecificheTabbed.setEnabledAt(3, farinaceiCheckbox.isSelected());
      caratteristicheSpecificheTabbed.setEnabledAt(4, uovaCheckbox.isSelected());
      caratteristicheSpecificheTabbed.setEnabledAt(5, carnePesceCheckbox.isSelected());
      caratteristicheSpecificheTabbed.setEnabledAt(6, bibiteCheckbox.isSelected());
    }
  };

  public ProdottiPanel() {
    ApplicationStatus.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        applicationStatusChanged(evt);
      }
    });
    fruttaVerduraCheckbox.addActionListener(checkBoxActionListener);
    carnePesceCheckbox.addActionListener(checkBoxActionListener);
    prodottiCaseariCheckbox.addActionListener(checkBoxActionListener);
    bibiteCheckbox.addActionListener(checkBoxActionListener);     
    farinaceiCheckbox.addActionListener(checkBoxActionListener);   
    conserveCheckbox.addActionListener(checkBoxActionListener);    
    uovaCheckbox.addActionListener(checkBoxActionListener);         
    altriTipoCheckbox.addActionListener(checkBoxActionListener);    
  }
  
  public void setModel(Prodotto prodotto) {
    this.prodotto = prodotto;
    PropertyChangeListener dataModelListener = new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        dataModelChanged(evt);
      }
    };
    prodotto.addPropertyChangeListener(dataModelListener);
  }
  
  public Prodotto getViewModel() {
    return null;
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
    if (ApplicationCounter.getInstance().getCounter() <= 0) {
      caratteristicheSpecificheTabbed.setVisible(false);
    }
    modelToView();
  }

  void insert() {
    setEnabledColor(true, Color.white);
    groupCheckBox(true);
    prodotto.copyTo(oldProdotto);
    prodotto.clear();
  }

  void update() {
    setEnabledColor(true, Color.cyan);
    groupCheckBox(true);
  }

  void search() {
    setEnabledColor(true, Color.yellow);
    groupCheckBox(false);
    caratteristicheSpecificheTabbed.setVisible(false);
  }
    
  void applicationStatusChanged(PropertyChangeEvent evt) {
    switch (ApplicationStatus.getInstance().getStatus()) {
      case ApplicationStatus.STATUS_NAVIGATION: navigation(); break;
      case ApplicationStatus.STATUS_INSERT: insert(); break;
      case ApplicationStatus.STATUS_UPDATE: update(); break;
      case ApplicationStatus.STATUS_SEARCH: search(); break;
    }
  }
  
  void dataModelChanged(PropertyChangeEvent evt) {
    modelToView();
  }
  
  
 void modelToView() {
    nomeTextField.setText(prodotto.getProdottoCommon().getNome());
    codiceProdottoTextField.setValue(prodotto.getProdottoCommon().getId());
    prezzoTextField.setValue(prodotto.getProdottoCommon().getPrezzo());
    sfusoCheckBox.setSelected(prodotto.getProdottoCommon().isSfuso());
  }
}
