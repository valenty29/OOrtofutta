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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JCheckBox;

import it.unina.studenti.oortof.gui.models.LottiTableModel;
import it.unina.studenti.oortof.models.ApplicationCounter;
import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.BibitaSpecifico;
import it.unina.studenti.oortof.models.CarnePesceSpecifico;
import it.unina.studenti.oortof.models.CatPeso;
import it.unina.studenti.oortof.models.ConservaSpecifico;
import it.unina.studenti.oortof.models.FarinaceoSpecifico;
import it.unina.studenti.oortof.models.FruttaVerduraSpecifico;
import it.unina.studenti.oortof.models.Prodotto;
import it.unina.studenti.oortof.models.ProdottoCasearioSpecifico;
import it.unina.studenti.oortof.models.ProdottoCommon;
import it.unina.studenti.oortof.models.TipoBibita;
import it.unina.studenti.oortof.models.TipoCarnePesce;
import it.unina.studenti.oortof.models.TipoConservazione;
import it.unina.studenti.oortof.models.TipoFruttaVerdura;
import it.unina.studenti.oortof.models.UovoSpecifico;

public class ProdottiPanel extends DesignProdottiPanel implements DocumentListener, ActionListener {

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
    listenGuiField();
    
    lottiTable.setModel(new LottiTableModel());
  }
  
  public void setModel(Prodotto prodotto) {
    this.prodotto = prodotto;
    PropertyChangeListener dataModelListener = new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        dataModelChanged(evt);
      }
    };
    ((LottiTableModel)lottiTable.getModel()).setList(prodotto.getProdottoCommon().getLotti());
    prodotto.addPropertyChangeListener(dataModelListener);
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
  
  void listenGuiField() {
    listenGuiField(this);
  }
  
  void listenGuiField(Container container) {
    for (int i = 0; i < container.getComponentCount(); i++) {
      Component c = container.getComponent(i);
      if (c instanceof JTextField) {
        ((JTextField)c).getDocument().addDocumentListener(this);
      }
      else if (c instanceof AbstractButton) {
        ((AbstractButton)c).addActionListener(this);
      }
      else if (c instanceof Container) {
        listenGuiField((Container)c);
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
  
  boolean modelToViewRunning = false;
  
  static final Integer ZERO = 0;
  static final Integer UNO = 1;
  static final Integer DUE = 2;
  static final Integer TRE = 3;
  
  void modelToView() {
    modelToViewRunning = true;
    nomeTextField.setText(prodotto.getProdottoCommon().getString(ProdottoCommon.NOME));
    codiceProdottoTextField.setText(prodotto.getProdottoCommon().getString(ProdottoCommon.ID));
    prezzoTextField.setText(prodotto.getProdottoCommon().getString(ProdottoCommon.PREZZO));
    sfusoCheckBox.setSelected(prodotto.getProdottoCommon().isSfuso());
    fruttaVerduraCheckbox.setSelected(prodotto.getProdottoCommon().isFruttaVerdura());
    prodottiCaseariCheckbox.setSelected(prodotto.getProdottoCommon().isProdottoCaseario());
    uovaCheckbox.setSelected(prodotto.getProdottoCommon().isUovo());
    bibiteCheckbox.setSelected(prodotto.getProdottoCommon().isBibita());
    conserveCheckbox.setSelected(prodotto.getProdottoCommon().isConserva());
    farinaceiCheckbox.setSelected(prodotto.getProdottoCommon().isFarinaceo());
    carnePesceCheckbox.setSelected(prodotto.getProdottoCommon().isCarnePesce());
    altriTipoCheckbox.setSelected(prodotto.getProdottoCommon().isAltro());
    FruttaVerduraSpecifico fvs = (FruttaVerduraSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.FRUTTA_VERDURA_INDEX);
    fruttaRadioButton.setSelected(fvs != null && TipoFruttaVerdura.Frutta.equals(fvs.getTipoFruttaVerdura()));
    verduraRadioButton.setSelected(fvs != null && TipoFruttaVerdura.Verdura.equals(fvs.getTipoFruttaVerdura()));
    surgelatoFruttaVerduraCheckbox.setSelected(fvs != null && fvs.isSurgelato());    
    biologicoCheckbox.setSelected(fvs != null && fvs.isBio());
    ConservaSpecifico cs = (ConservaSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.CONSERVA_INDEX);
    sottovuotoRadioButton.setSelected(cs != null && TipoConservazione.Sottovuoto.equals(cs.getTipoConservazione()));
    sottacetoRadioButton.setSelected(cs != null && TipoConservazione.Sottaceto.equals(cs.getTipoConservazione()));
    sottolioRadioButton.setSelected(cs != null && TipoConservazione.Sottolio.equals(cs.getTipoConservazione()));
    sottosaleRadioButton.setSelected(cs != null && TipoConservazione.Sottosale.equals(cs.getTipoConservazione()));
    sottoSpiritoRadioButton.setSelected(cs != null && TipoConservazione.Sottospirito.equals(cs.getTipoConservazione()));
    inZuccheriRadioButton.setSelected(cs != null && TipoConservazione.Zucchero.equals(cs.getTipoConservazione()));
    conserveAltroTipoRadioButton.setSelected(cs != null && TipoConservazione.Altro.equals(cs.getTipoConservazione()));
    ProdottoCasearioSpecifico pcs = (ProdottoCasearioSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.PRODOTTO_CASEARIO_INDEX);
    stagionatureTextField.setText(pcs != null ? pcs.getString(ProdottoCasearioSpecifico.STAGIONATURA) : null);
    stabilimentoTextField.setText(pcs != null ? pcs.getString(ProdottoCasearioSpecifico.STABILIMENTO) : null);
    tipoLatteTextField.setText(pcs != null ? pcs.getString(ProdottoCasearioSpecifico.TIPO_LATTE) : null);
    FarinaceoSpecifico fs = (FarinaceoSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.FARINACEO_INDEX);
    tipoFarinaTextField.setText(fs != null ? fs.getString(FarinaceoSpecifico.TIPO_FARINA) : null);
    senzaGlutineCheckbox.setSelected(fs != null && fs.isGlutine());
    frescoCheckbox.setSelected(fs != null && fs.isFresco());
    surgelatoFarinaceiCheckbox.setSelected(fs != null && fs.isSurgelato());
    UovoSpecifico us = (UovoSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.UOVO_INDEX);
    tipo0RadioButton.setSelected(us != null && ZERO.equals(us.getTipoAllevamento()));
    tipo1RadioButton.setSelected(us != null && UNO.equals(us.getTipoAllevamento()));
    tipo2RadioButton.setSelected(us != null && DUE.equals(us.getTipoAllevamento()));
    tipo3RadioButton.setSelected(us != null && TRE.equals(us.getTipoAllevamento()));
    codiceAllevamentoTextField.setText(us != null ? us.getString(UovoSpecifico.COD_ALLEVAMENTO) : null);
    categoriaSRadioButton.setSelected(us != null && CatPeso.S.equals(us.getCatPeso()));
    categoriaMRadioButton.setSelected(us != null && CatPeso.M.equals(us.getCatPeso()));
    categoriaLRadioButton.setSelected(us != null && CatPeso.L.equals(us.getCatPeso()));
    categoriaXLRadioButton.setSelected(us != null && CatPeso.XL.equals(us.getCatPeso()));
    CarnePesceSpecifico cps = (CarnePesceSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.CARNE_PESCE_INDEX);
    carneRadioButton.setSelected(cps != null && TipoCarnePesce.Carne.equals(cps.getTipoCarnePesce()));
    pesceRadioButton.setSelected(cps != null && TipoCarnePesce.Pesce.equals(cps.getTipoCarnePesce()));
    animaleTextField.setText(cps != null ? cps.getString(CarnePesceSpecifico.ANIMALE) : null);
    daAllevamentoCheckBox.setSelected(cps != null && cps.isDaAllevamento());
    confezionatoCheckBox.setSelected(cps != null && cps.isConfezionato());
    BibitaSpecifico bs = (BibitaSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.BIBITA_INDEX);
    acquaRadioButton.setSelected(bs != null && TipoBibita.Acqua.equals(bs.getTipoBibita()));
    succhiDiFruttaRadioButton.setSelected(bs != null && TipoBibita.Succo.equals(bs.getTipoBibita()));
    softDrinkRadioButton.setSelected(bs != null && TipoBibita.SoftDrink.equals(bs.getTipoBibita()));
    fermentatiRadioButton.setSelected(bs != null && TipoBibita.Fermentato.equals(bs.getTipoBibita()));
    liquoriRadioButton.setSelected(bs != null && TipoBibita.Liquore.equals(bs.getTipoBibita()));
    bibitaAltroRadioButton.setSelected(bs != null && TipoBibita.Altro.equals(bs.getTipoBibita()));
    gradazioneAlcolicaTextField.setText(bs != null ? bs.getString(BibitaSpecifico.GRADAZIONE_ALCOLICA) : null);
    frizzanteCheckBox.setSelected(bs != null && bs.isFrizzante());
    ((LottiTableModel)lottiTable.getModel()).fireTableDataChanged();
    
    modelToViewRunning = false;
  } 
  void viewToModel() {
    if (modelToViewRunning) {
      return;
    }
    prodotto.getProdottoCommon().setValue(ProdottoCommon.NOME, nomeTextField.getText());
    prodotto.getProdottoCommon().setValue(ProdottoCommon.ID, codiceProdottoTextField.getText());
    prodotto.getProdottoCommon().setValue(ProdottoCommon.PREZZO, prezzoTextField.getText());
    prodotto.getProdottoCommon().setValue(ProdottoCommon.SFUSO, sfusoCheckBox.isSelected());
  }
 
 

  @Override
  public void actionPerformed(ActionEvent e) {
    viewToModel();
  }
  
  @Override
  public void insertUpdate(DocumentEvent e) {
    viewToModel();
  }
  
  @Override
  public void removeUpdate(DocumentEvent e) {
    viewToModel();
  }
  
  @Override
  public void changedUpdate(DocumentEvent e) {
    viewToModel();
  }

}
