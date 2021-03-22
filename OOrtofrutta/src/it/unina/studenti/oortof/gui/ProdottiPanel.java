package it.unina.studenti.oortof.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;

import it.unina.studenti.oortof.gui.models.LottiTableModel;
import it.unina.studenti.oortof.models.ApplicationCounter;
import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.Bibita;
import it.unina.studenti.oortof.models.BibitaSpecifico;
import it.unina.studenti.oortof.models.CarnePesceSpecifico;
import it.unina.studenti.oortof.models.CatPeso;
import it.unina.studenti.oortof.models.ConservaSpecifico;
import it.unina.studenti.oortof.models.FarinaceoSpecifico;
import it.unina.studenti.oortof.models.FruttaVerduraSpecifico;
import it.unina.studenti.oortof.models.ObservedModel;
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
  
  ButtonGroup tipoProdottoBG = new ButtonGroup();
  
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
    
    setTriState();
    
    lottiTable.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE && ApplicationStatus.getInstance().isUpdate()) {
          boolean selectionNoLastRow = lottiTable.getSelectedRow() >= 0 && lottiTable.getSelectedRow() != (lottiTable.getModel().getRowCount() - 1);
          if (selectionNoLastRow) {
            prodotto.getProdottoCommon().removeLotto(lottiTable.getSelectedRow());
          }
        }
      }
    });
    
    lottiTable.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        boolean lastRow = table.getModel().getRowCount() == (row + 1);
        isSelected = ApplicationStatus.getInstance().isNavigation() || !lastRow ? isSelected : false;
        JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        l.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        if (lastRow) {
          if (ApplicationStatus.getInstance().isInsert()) {
            l.setBackground(Color.white);
          }
          else if (ApplicationStatus.getInstance().isUpdate()) {
            l.setBackground(Color.cyan);
          }
        }
        return l;
      }
    });
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
    fruttaVerduraCheckbox.putClientProperty("model", prodotto.getProdottoCommon());
    fruttaVerduraCheckbox.putClientProperty("attributeIndex", ProdottoCommon.FRUTTA_VERDURA);
    carnePesceCheckbox.putClientProperty("model", prodotto.getProdottoCommon());
    carnePesceCheckbox.putClientProperty("attributeIndex", ProdottoCommon.CARNE_PESCE);
    prodottiCaseariCheckbox.putClientProperty("model", prodotto.getProdottoCommon());
    prodottiCaseariCheckbox.putClientProperty("attributeIndex", ProdottoCommon.PRODOTTO_CASEARIO);
    bibiteCheckbox.putClientProperty("model", prodotto.getProdottoCommon());             
    bibiteCheckbox.putClientProperty("attributeIndex", ProdottoCommon.BIBITA);
    farinaceiCheckbox.putClientProperty("model", prodotto.getProdottoCommon());
    farinaceiCheckbox.putClientProperty("attributeIndex", ProdottoCommon.FARINACEO);
    conserveCheckbox.putClientProperty("model", prodotto.getProdottoCommon());
    conserveCheckbox.putClientProperty("attributeIndex", ProdottoCommon.CONSERVA);
    uovaCheckbox.putClientProperty("model", prodotto.getProdottoCommon());
    uovaCheckbox.putClientProperty("attributeIndex", ProdottoCommon.UOVO);
    altriTipoCheckbox.putClientProperty("model", prodotto.getProdottoCommon());
    altriTipoCheckbox.putClientProperty("attributeIndex", ProdottoCommon.ALTRO);
    sfusoCheckBox.putClientProperty("model", prodotto.getProdottoCommon());
    sfusoCheckBox.putClientProperty("attributeIndex", ProdottoCommon.SFUSO);
    surgelatoFruttaVerduraCheckbox.putClientProperty("model", prodotto.getFruttaVerduraSpecifico());
    surgelatoFruttaVerduraCheckbox.putClientProperty("attributeIndex", FruttaVerduraSpecifico.SURGELATO);
    biologicoCheckbox.putClientProperty("model", prodotto.getFruttaVerduraSpecifico());
    biologicoCheckbox.putClientProperty("attributeIndex", FruttaVerduraSpecifico.BIO);
    senzaGlutineCheckbox.putClientProperty("model", prodotto.getFarinaceoSpecifico());
    senzaGlutineCheckbox.putClientProperty("attributeIndex", FarinaceoSpecifico.GLUTINE);
    frescoCheckbox.putClientProperty("model", prodotto.getFarinaceoSpecifico());
    frescoCheckbox.putClientProperty("attributeIndex", FarinaceoSpecifico.FRESCO);
    surgelatoFarinaceiCheckbox.putClientProperty("model", prodotto.getFarinaceoSpecifico());
    surgelatoFarinaceiCheckbox.putClientProperty("attributeIndex", FarinaceoSpecifico.SURGELATO);
    daAllevamentoCheckBox.putClientProperty("model", prodotto.getCarnePesceSpecifico());
    daAllevamentoCheckBox.putClientProperty("attributeIndex", CarnePesceSpecifico.DA_ALLEVAMENTO);
    confezionatoCheckBox.putClientProperty("model", prodotto.getCarnePesceSpecifico());
    confezionatoCheckBox.putClientProperty("attributeIndex", CarnePesceSpecifico.CONFEZIONATO);
    frizzanteCheckBox.putClientProperty("model", prodotto.getBibitaSpecifico());
    frizzanteCheckBox.putClientProperty("attributeIndex", BibitaSpecifico.FRIZZANTE);
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
  
  MouseAdapter tristateMouseAdapter = new MouseAdapter() {
    public void mouseReleased(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON3 && ApplicationStatus.getInstance().isSearch()) {
        if (e.getSource() instanceof JCheckBox) {
          ObservedModel model = (ObservedModel)((JCheckBox)e.getSource()).getClientProperty("model");
          int attributeIndex = (int)((JCheckBox)e.getSource()).getClientProperty("attributeIndex");
          Boolean oldValue = model.getBoolean(attributeIndex);
          Boolean newValue = oldValue == null ? false : null;
          model.setValue(attributeIndex, newValue);
          if (newValue == null) {
            ((JCheckBox)e.getSource()).setSelected(false);
          }
          ActionEvent ae = new ActionEvent(e.getSource(), -1, "");
          checkBoxActionListener.actionPerformed(ae);
          model.firePropertyChange("", oldValue, newValue);
        }
        else if (e.getSource() instanceof JRadioButton) {
          ((JRadioButton)e.getSource()).getModel().getGroup().clearSelection();
        }
      }
    }
  };
  
  void setTriState() {
    setTriState(this);
  }
  
  void setTriState(Container container) {
    for (int i = 0; i < container.getComponentCount(); i++) {
      Component c = container.getComponent(i);
      if (c instanceof AbstractButton) {
        c.addMouseListener(tristateMouseAdapter);
      }
      else if (c instanceof Container) {
        setTriState((Container)c);
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
  
  void manageSpecificTab() {
    int index = -1;
    if (prodotto.getProdottoCommon().isFruttaVerdura()) {
      index = 0;
    }
    else if (prodotto.getProdottoCommon().isConserva()) {
      index = 1;
    }
    else if (prodotto.getProdottoCommon().isProdottoCaseario()) {
      index = 2;
    }
    else if (prodotto.getProdottoCommon().isFarinaceo()) {
      index = 3;
    }
    else if (prodotto.getProdottoCommon().isUovo()) {
      index = 4;
    }
    else if (prodotto.getProdottoCommon().isCarnePesce()) {
      index = 5;
    }
    else if (prodotto.getProdottoCommon().isBibita()) {
      index = 6;
    }
    caratteristicheSpecificheTabbed.setVisible(index >= 0);
    caratteristicheSpecificheTabbed.setSelectedIndex(index >= 0 ? index : 0);
    
    caratteristicheSpecificheTabbed.setEnabledAt(0, index == 0);
    caratteristicheSpecificheTabbed.setEnabledAt(1, index == 1);
    caratteristicheSpecificheTabbed.setEnabledAt(2, index == 2);
    caratteristicheSpecificheTabbed.setEnabledAt(3, index == 3);
    caratteristicheSpecificheTabbed.setEnabledAt(4, index == 4);
    caratteristicheSpecificheTabbed.setEnabledAt(5, index == 5);
    caratteristicheSpecificheTabbed.setEnabledAt(6, index == 6);
  }
  
  void groupCheckBox(boolean group) {
    if (group) {
      tipoProdottoBG.add(fruttaVerduraCheckbox);
      tipoProdottoBG.add(carnePesceCheckbox);
      tipoProdottoBG.add(prodottiCaseariCheckbox);
      tipoProdottoBG.add(bibiteCheckbox);
      tipoProdottoBG.add(farinaceiCheckbox);
      tipoProdottoBG.add(conserveCheckbox);
      tipoProdottoBG.add(uovaCheckbox);
      tipoProdottoBG.add(altriTipoCheckbox);
    }
    else {
      tipoProdottoBG.remove(fruttaVerduraCheckbox);
      tipoProdottoBG.remove(carnePesceCheckbox);
      tipoProdottoBG.remove(prodottiCaseariCheckbox);
      tipoProdottoBG.remove(bibiteCheckbox);
      tipoProdottoBG.remove(farinaceiCheckbox);
      tipoProdottoBG.remove(conserveCheckbox);
      tipoProdottoBG.remove(uovaCheckbox);
      tipoProdottoBG.remove(altriTipoCheckbox);
    }
  }
  
  void navigation() {
    setEnabledColor(false, SystemColor.control);
    if (ApplicationCounter.getInstance().getCounter() <= 0) {
      caratteristicheSpecificheTabbed.setVisible(false);
    }
    lottiTable.editingCanceled(null);
    modelToView();
  }

  void insert() {
    setEnabledColor(true, Color.white);
    groupCheckBox(true);
    caratteristicheSpecificheTabbed.setVisible(false);
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
    if (evt.getPropertyName().equals("status")) {
      switch (ApplicationStatus.getInstance().getStatus()) {
        case ApplicationStatus.STATUS_NAVIGATION: navigation(); break;
        case ApplicationStatus.STATUS_INSERT: insert(); break;
        case ApplicationStatus.STATUS_UPDATE: update(); break;
        case ApplicationStatus.STATUS_SEARCH: search(); break;
      }
    }
    else if (evt.getPropertyName().equals("action")) {
      if (evt.getNewValue().equals(ApplicationStatus.ACTION_PRE_DELETE)) {
        int response = JOptionPane.showConfirmDialog(this, "Si conferma la cancellazione ?", "Conferma Cancellazione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.OK_OPTION) {
          ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_DELETE);
        }
      }
    }
  }
  
  void dataModelChanged(PropertyChangeEvent evt) {
    modelToView();
  }
  
  static final Integer ZERO = 0;
  static final Integer UNO = 1;
  static final Integer DUE = 2;
  static final Integer TRE = 3;
  
  void modelToView() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        modelToViewCore();
      }
    });
  }

  void modelToViewCore() {
    nomeTextField.setText(prodotto.getProdottoCommon().getString(ProdottoCommon.NOME));
    codiceProdottoTextField.setText(prodotto.getProdottoCommon().getString(ProdottoCommon.ID));
    prezzoTextField.setText(prodotto.getProdottoCommon().getString(ProdottoCommon.PREZZO));
    sfusoCheckBox.setSelected(prodotto.getProdottoCommon().isSfuso());
    sfusoCheckBox.setForeground(prodotto.getProdottoCommon().getSfuso() != null ? Color.black : Color.gray);
    boolean group = tipoProdottoBG.getButtonCount() > 0;
    groupCheckBox(false);
    fruttaVerduraCheckbox.setSelected(prodotto.getProdottoCommon().isFruttaVerdura());
    fruttaVerduraCheckbox.setForeground(prodotto.getProdottoCommon().getFruttaVerdura() != null ? Color.black : Color.gray);
    prodottiCaseariCheckbox.setSelected(prodotto.getProdottoCommon().isProdottoCaseario());
    prodottiCaseariCheckbox.setForeground(prodotto.getProdottoCommon().getProdottoCaseario() != null ? Color.black : Color.gray);
    uovaCheckbox.setSelected(prodotto.getProdottoCommon().isUovo());
    uovaCheckbox.setForeground(prodotto.getProdottoCommon().getUovo() != null ? Color.black : Color.gray);
    bibiteCheckbox.setSelected(prodotto.getProdottoCommon().isBibita());
    bibiteCheckbox.setForeground(prodotto.getProdottoCommon().getBibita() != null ? Color.black : Color.gray);
    conserveCheckbox.setSelected(prodotto.getProdottoCommon().isConserva());
    conserveCheckbox.setForeground(prodotto.getProdottoCommon().getConserva() != null ? Color.black : Color.gray);
    farinaceiCheckbox.setSelected(prodotto.getProdottoCommon().isFarinaceo());
    farinaceiCheckbox.setForeground(prodotto.getProdottoCommon().getFarinaceo() != null ? Color.black : Color.gray);
    carnePesceCheckbox.setSelected(prodotto.getProdottoCommon().isCarnePesce());
    carnePesceCheckbox.setForeground(prodotto.getProdottoCommon().getCarnePesce() != null ? Color.black : Color.gray);
    altriTipoCheckbox.setSelected(prodotto.getProdottoCommon().isAltro());
    altriTipoCheckbox.setForeground(prodotto.getProdottoCommon().getAltro() != null ? Color.black : Color.gray);
    groupCheckBox(group);
    FruttaVerduraSpecifico fvs = (FruttaVerduraSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.FRUTTA_VERDURA_INDEX);
    fruttaRadioButton.setSelected(fvs != null && TipoFruttaVerdura.Frutta.equals(fvs.getTipoFruttaVerdura()));
    verduraRadioButton.setSelected(fvs != null && TipoFruttaVerdura.Verdura.equals(fvs.getTipoFruttaVerdura()));
    if (fvs.getTipoFruttaVerdura() == null) {
      fruttaVerduraBG.clearSelection();
    }
    surgelatoFruttaVerduraCheckbox.setSelected(fvs != null && fvs.isSurgelato());    
    surgelatoFruttaVerduraCheckbox.setForeground(fvs != null && fvs.getSurgelato() != null ? Color.black : Color.gray);
    biologicoCheckbox.setSelected(fvs != null && fvs.isBio());
    biologicoCheckbox.setForeground(fvs != null && fvs.getBio() != null ? Color.black : Color.gray);
    ConservaSpecifico cs = (ConservaSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.CONSERVA_INDEX);
    sottovuotoRadioButton.setSelected(cs != null && TipoConservazione.Sottovuoto.equals(cs.getTipoConservazione()));
    sottacetoRadioButton.setSelected(cs != null && TipoConservazione.Sottaceto.equals(cs.getTipoConservazione()));
    sottolioRadioButton.setSelected(cs != null && TipoConservazione.Sottolio.equals(cs.getTipoConservazione()));
    sottosaleRadioButton.setSelected(cs != null && TipoConservazione.Sottosale.equals(cs.getTipoConservazione()));
    sottoSpiritoRadioButton.setSelected(cs != null && TipoConservazione.Sottospirito.equals(cs.getTipoConservazione()));
    inZuccheriRadioButton.setSelected(cs != null && TipoConservazione.Zucchero.equals(cs.getTipoConservazione()));
    conserveAltroTipoRadioButton.setSelected(cs != null && TipoConservazione.Altro.equals(cs.getTipoConservazione()));
    if (cs.getTipoConservazione() == null) {
      conserveBG.clearSelection();
    }
    ProdottoCasearioSpecifico pcs = (ProdottoCasearioSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.PRODOTTO_CASEARIO_INDEX);
    stagionatureTextField.setText(pcs != null ? pcs.getString(ProdottoCasearioSpecifico.STAGIONATURA) : null);
    stabilimentoTextField.setText(pcs != null ? pcs.getString(ProdottoCasearioSpecifico.STABILIMENTO) : null);
    tipoLatteTextField.setText(pcs != null ? pcs.getString(ProdottoCasearioSpecifico.TIPO_LATTE) : null);
    FarinaceoSpecifico fs = (FarinaceoSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.FARINACEO_INDEX);
    tipoFarinaTextField.setText(fs != null ? fs.getString(FarinaceoSpecifico.TIPO_FARINA) : null);
    senzaGlutineCheckbox.setSelected(fs != null && fs.isGlutine());
    senzaGlutineCheckbox.setForeground(fs != null && fs.getGlutine() != null ? Color.black : Color.gray);
    frescoCheckbox.setSelected(fs != null && fs.isFresco());
    frescoCheckbox.setForeground(fs != null && fs.getFresco() != null ? Color.black : Color.gray);
    surgelatoFarinaceiCheckbox.setSelected(fs != null && fs.isSurgelato());
    surgelatoFarinaceiCheckbox.setForeground(fs != null && fs.getSurgelato() != null ? Color.black : Color.gray);
    UovoSpecifico us = (UovoSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.UOVO_INDEX);
    tipo0RadioButton.setSelected(us != null && ZERO.equals(us.getTipoAllevamento()));
    tipo1RadioButton.setSelected(us != null && UNO.equals(us.getTipoAllevamento()));
    tipo2RadioButton.setSelected(us != null && DUE.equals(us.getTipoAllevamento()));
    tipo3RadioButton.setSelected(us != null && TRE.equals(us.getTipoAllevamento()));
    if (us.getTipoAllevamento() == null) {
      tipoAllevamentoBG.clearSelection();
    }
    codiceAllevamentoTextField.setText(us != null ? us.getString(UovoSpecifico.COD_ALLEVAMENTO) : null);
    categoriaSRadioButton.setSelected(us != null && CatPeso.S.equals(us.getCatPeso()));
    categoriaMRadioButton.setSelected(us != null && CatPeso.M.equals(us.getCatPeso()));
    categoriaLRadioButton.setSelected(us != null && CatPeso.L.equals(us.getCatPeso()));
    categoriaXLRadioButton.setSelected(us != null && CatPeso.XL.equals(us.getCatPeso()));
    if (us.getCatPeso() == null) {
      categoriaPesoBG.clearSelection();
    }
    CarnePesceSpecifico cps = (CarnePesceSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.CARNE_PESCE_INDEX);
    carneRadioButton.setSelected(cps != null && TipoCarnePesce.Carne.equals(cps.getTipoCarnePesce()));
    pesceRadioButton.setSelected(cps != null && TipoCarnePesce.Pesce.equals(cps.getTipoCarnePesce()));
    if (cps.getTipoCarnePesce() == null) {
      carnePesceBG.clearSelection();
    }
    animaleTextField.setText(cps != null ? cps.getString(CarnePesceSpecifico.ANIMALE) : null);
    daAllevamentoCheckBox.setSelected(cps != null && cps.isDaAllevamento());
    daAllevamentoCheckBox.setForeground(cps != null && cps.getDaAllevamento() != null ? Color.black : Color.gray);
    confezionatoCheckBox.setSelected(cps != null && cps.isConfezionato());
    confezionatoCheckBox.setForeground(cps != null && cps.getConfezionato() != null ? Color.black : Color.gray);
    BibitaSpecifico bs = (BibitaSpecifico)prodotto.getProdottoSpecificoAt(Prodotto.BIBITA_INDEX);
    acquaRadioButton.setSelected(bs != null && TipoBibita.Acqua.equals(bs.getTipoBibita()));
    succhiDiFruttaRadioButton.setSelected(bs != null && TipoBibita.Succo.equals(bs.getTipoBibita()));
    softDrinkRadioButton.setSelected(bs != null && TipoBibita.SoftDrink.equals(bs.getTipoBibita()));
    fermentatiRadioButton.setSelected(bs != null && TipoBibita.Fermentato.equals(bs.getTipoBibita()));
    liquoriRadioButton.setSelected(bs != null && TipoBibita.Liquore.equals(bs.getTipoBibita()));
    bibitaAltroRadioButton.setSelected(bs != null && TipoBibita.Altro.equals(bs.getTipoBibita()));
    if (bs.getTipoBibita() == null) {
      bibitaBG.clearSelection();
    }
    gradazioneAlcolicaTextField.setText(bs != null ? bs.getString(BibitaSpecifico.GRADAZIONE_ALCOLICA) : null);
    frizzanteCheckBox.setSelected(bs != null && bs.isFrizzante());
    frizzanteCheckBox.setForeground(bs != null && bs.getFrizzante() != null ? Color.black : Color.gray);
    
    ((LottiTableModel)lottiTable.getModel()).fireTableDataChanged();
    if (ApplicationStatus.getInstance().isNavigation()) {
      manageSpecificTab();
    }
  }
  
  void viewToModelBibitaSpecifico() {
    prodotto.getBibitaSpecifico().setFrizzante(frizzanteCheckBox.isSelected() ? Boolean.TRUE : frizzanteCheckBox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    prodotto.getBibitaSpecifico().setValue(BibitaSpecifico.GRADAZIONE_ALCOLICA, gradazioneAlcolicaTextField.getText());
    TipoBibita tb = null;
    if (acquaRadioButton.isSelected()) {
      tb = TipoBibita.Acqua;
    }
    else if (succhiDiFruttaRadioButton.isSelected()) {
      tb = TipoBibita.Succo;
    }
    else if (softDrinkRadioButton.isSelected()) {
      tb = TipoBibita.SoftDrink;
    }
    else if (fermentatiRadioButton.isSelected()) {
      tb = TipoBibita.Fermentato;
    }
    else if (liquoriRadioButton.isSelected()) {
      tb = TipoBibita.Liquore;
    }
    else if (bibitaAltroRadioButton.isSelected()) {
      tb = TipoBibita.Altro;
    }
    prodotto.getBibitaSpecifico().setValue(BibitaSpecifico.TIPO_BIBITA, tb);
  }
  
  void viewToModelCarnePesceSpecifico() {
    TipoCarnePesce tcp = null;
    if (carneRadioButton.isSelected()) {
      tcp = TipoCarnePesce.Carne;
    }
    else if (pesceRadioButton.isSelected()) {
      tcp = TipoCarnePesce.Pesce;
    }
    prodotto.getCarnePesceSpecifico().setValue(CarnePesceSpecifico.TIPO_CARNE_PESCE, tcp);
    prodotto.getCarnePesceSpecifico().setAnimale(animaleTextField.getText());
    prodotto.getCarnePesceSpecifico().setDaAllevamento(daAllevamentoCheckBox.isSelected() ? Boolean.TRUE : daAllevamentoCheckBox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    prodotto.getCarnePesceSpecifico().setConfezionato(confezionatoCheckBox.isSelected() ? Boolean.TRUE : confezionatoCheckBox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
  }
  
  void viewToModelConservaSpecifico() {
    TipoConservazione tc = null;
    if (sottacetoRadioButton.isSelected()) {
      tc = TipoConservazione.Sottaceto;
    }
    else if (sottovuotoRadioButton.isSelected()) {
      tc = TipoConservazione.Sottovuoto;
    }
    else if (sottolioRadioButton.isSelected()) {
      tc = TipoConservazione.Sottolio;
    }
    else if (sottosaleRadioButton.isSelected()) {
      tc = TipoConservazione.Sottosale;
    }
    else if (sottoSpiritoRadioButton.isSelected()) {
      tc = TipoConservazione.Sottospirito;
    }
    else if (inZuccheriRadioButton.isSelected()) {
      tc = TipoConservazione.Zucchero;
    }
    else if (conserveAltroTipoRadioButton.isSelected()) {
      tc = TipoConservazione.Altro;
    }
    prodotto.getConservaSpecifico().setValue(ConservaSpecifico.TIPO_CONSERVAZIONE, tc);
  }
  
  void viewToModelFarinaceoSpecifico() {
    prodotto.getFarinaceoSpecifico().setTipoFarina(tipoFarinaTextField.getText());
    prodotto.getFarinaceoSpecifico().setFresco(frescoCheckbox.isSelected() ? Boolean.TRUE : frescoCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    prodotto.getFarinaceoSpecifico().setGlutine(senzaGlutineCheckbox.isSelected() ? Boolean.TRUE : senzaGlutineCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    prodotto.getFarinaceoSpecifico().setSurgelato(surgelatoFarinaceiCheckbox.isSelected() ? Boolean.TRUE : surgelatoFarinaceiCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
  }
  
  void viewToModelFruttaVerduraSpecifico() {
    prodotto.getFruttaVerduraSpecifico().setSurgelato(surgelatoFruttaVerduraCheckbox.isSelected() ? Boolean.TRUE : surgelatoFruttaVerduraCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    prodotto.getFruttaVerduraSpecifico().setBio(biologicoCheckbox.isSelected() ? Boolean.TRUE : biologicoCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    TipoFruttaVerdura tfv = null;
    if (fruttaRadioButton.isSelected()) {
      tfv = TipoFruttaVerdura.Frutta;
    }
    else if (verduraRadioButton.isSelected()) {
      tfv = TipoFruttaVerdura.Verdura;
    }
    prodotto.getFruttaVerduraSpecifico().setValue(FruttaVerduraSpecifico.TIPO_FRUTTA_VERDURA, tfv);
  }

  void viewToModelProdottoCasearioSpecifico() {
    prodotto.getProdottoCasearioSpecifico().setValue(ProdottoCasearioSpecifico.STAGIONATURA, stagionatureTextField.getText());
    prodotto.getProdottoCasearioSpecifico().setStabilimento(stabilimentoTextField.getText());
    prodotto.getProdottoCasearioSpecifico().setTipoLatte(tipoLatteTextField.getText());
  }
  
  void viewToModelUovoSpecifico() {
    Integer ta = null;
    if (tipo0RadioButton.isSelected()) {
      ta = 0;
    }
    else if (tipo1RadioButton.isSelected()) {
      ta = 1;
    }
    else if (tipo2RadioButton.isSelected()) {
      ta = 2;
    }
    else if (tipo3RadioButton.isSelected()) {
      ta = 3;
    }
    prodotto.getUovoSpecifico().setValue(UovoSpecifico.TIPO_ALLEVAMENTO, ta);
    CatPeso cp = null;
    if (categoriaSRadioButton.isSelected()) {
      cp = CatPeso.S;
    }
    else if (categoriaMRadioButton.isSelected()) {
      cp = CatPeso.M;
    }
    else if (categoriaLRadioButton.isSelected()) {
      cp = CatPeso.L;
    }
    else if (categoriaXLRadioButton.isSelected()) {
      cp = CatPeso.XL;
    }
    prodotto.getUovoSpecifico().setValue(UovoSpecifico.CAT_PESO, cp);
    prodotto.getUovoSpecifico().setCodAllevamento(codiceAllevamentoTextField.getText());
  }
  
  void viewToModel() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        viewToModelCore();
      }
    });
  }
  
  void viewToModelCore() {
    ProdottoCommon pc = prodotto.getProdottoCommon();
    pc.setValue(ProdottoCommon.NOME, nomeTextField.getText());
    pc.setValue(ProdottoCommon.ID, codiceProdottoTextField.getText());
    pc.setAltro(altriTipoCheckbox.isSelected() ? Boolean.TRUE : altriTipoCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    pc.setBibita(bibiteCheckbox.isSelected() ? Boolean.TRUE : bibiteCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    pc.setCarnePesce(carnePesceCheckbox.isSelected() ? Boolean.TRUE : carnePesceCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    pc.setConserva(conserveCheckbox.isSelected() ? Boolean.TRUE : conserveCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    pc.setFarinaceo(farinaceiCheckbox.isSelected() ? Boolean.TRUE : farinaceiCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    pc.setFruttaVerdura(fruttaVerduraCheckbox.isSelected() ? Boolean.TRUE : fruttaVerduraCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    pc.setProdottoCaseario(prodottiCaseariCheckbox.isSelected() ? Boolean.TRUE : prodottiCaseariCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    pc.setUovo(uovaCheckbox.isSelected() ? Boolean.TRUE : uovaCheckbox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    pc.setValue(ProdottoCommon.PREZZO, prezzoTextField.getText());
    pc.setSfuso(sfusoCheckBox.isSelected() ? Boolean.TRUE : sfusoCheckBox.getForeground().equals(Color.black) ? Boolean.FALSE : null);
    if (pc.isBibita()) {
      viewToModelBibitaSpecifico();
    }
    if (pc.isCarnePesce()) {
      viewToModelCarnePesceSpecifico();
    }
    if (pc.isConserva()) {
      viewToModelConservaSpecifico();
    }
    if (pc.isFarinaceo()) {
      viewToModelFarinaceoSpecifico();
    }
    if (pc.isFruttaVerdura()) {
      viewToModelFruttaVerduraSpecifico();
    }
    if (pc.isProdottoCaseario()) {
      viewToModelProdottoCasearioSpecifico();
    }
    if (pc.isUovo()) {
      viewToModelUovoSpecifico();
    }
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
