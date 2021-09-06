package it.unina.studenti.oortof.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.AbstractDocument;

import it.unina.studenti.oortof.gui.models.AcquistiTableModel;
import it.unina.studenti.oortof.gui.models.InputCheckRule;
import it.unina.studenti.oortof.gui.models.InputCheckingDocumentFilter;
import it.unina.studenti.oortof.gui.models.ScontriniTableModel;
import it.unina.studenti.oortof.models.application.ApplicationStatus;
import it.unina.studenti.oortof.models.entities.Cliente;
import it.unina.studenti.oortof.models.entities.Genere;
import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.RaccoltaPunti;
import it.unina.studenti.oortof.models.entities.Scontrino;
import it.unina.studenti.oortof.models.entities.prodotti.Acquisto;

public class ClientiPanel extends DesignClientiPanel implements DocumentListener, ActionListener {
  private Cliente cliente;

  private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  private ScontriniTableModel scontriniModel;
  private AcquistiTableModel acquistiModel;
  private boolean modelToViewRunning = false;
  private static final long serialVersionUID = 1L;

  @SuppressWarnings("serial")
  public ClientiPanel() {
    ApplicationStatus.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        applicationStatusChanged(evt);
      }
    });

    listenGuiField();

    scontriniTable.setModel(new ScontriniTableModel());
    acquistiTable.setModel(new AcquistiTableModel());

    scontriniModel = (ScontriniTableModel)scontriniTable.getModel();
    acquistiModel = (AcquistiTableModel)acquistiTable.getModel();

    acquistiTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        l.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());

        return l;

      }
    });

    ((AbstractDocument)puntiFruttaVerduraTextField.getDocument()).setDocumentFilter(new InputCheckingDocumentFilter(puntiFruttaVerduraTextField, InputCheckRule.numeriSpazio));
    ((AbstractDocument)puntiFarinaceoTextField.getDocument()).setDocumentFilter(new InputCheckingDocumentFilter(puntiFarinaceoTextField, InputCheckRule.numeriSpazio));
    ((AbstractDocument)puntiConservaTextField.getDocument()).setDocumentFilter(new InputCheckingDocumentFilter(puntiConservaTextField, InputCheckRule.numeriSpazio));
    ((AbstractDocument)puntiBibitaTextField.getDocument()).setDocumentFilter(new InputCheckingDocumentFilter(puntiBibitaTextField, InputCheckRule.numeriSpazio));
    ((AbstractDocument)puntiCarnePesceTextField.getDocument()).setDocumentFilter(new InputCheckingDocumentFilter(puntiCarnePesceTextField, InputCheckRule.numeriSpazio));
    ((AbstractDocument)puntiProdottoCasearioTextField.getDocument()).setDocumentFilter(new InputCheckingDocumentFilter(puntiProdottoCasearioTextField, InputCheckRule.numeriSpazio));
    ((AbstractDocument)puntiUovoTextField.getDocument()).setDocumentFilter(new InputCheckingDocumentFilter(puntiUovoTextField, InputCheckRule.numeriSpazio));
    ((AbstractDocument)puntiConservaTextField.getDocument()).setDocumentFilter(new InputCheckingDocumentFilter(puntiConservaTextField, InputCheckRule.numeriSpazio));
    ((AbstractDocument)puntiAltroTextField.getDocument()).setDocumentFilter(new InputCheckingDocumentFilter(puntiAltroTextField, InputCheckRule.numeriSpazio));
    ((AbstractDocument)puntiTotaliTextField.getDocument()).setDocumentFilter(new InputCheckingDocumentFilter(puntiTotaliTextField, InputCheckRule.numeriSpazio));
  }

  public void setModel(Cliente cliente) {
    Scontrino modelloScontrino = new Scontrino();
    ObservedList<Acquisto> modelloAcquisti = new ObservedList<Acquisto>("acquisti");
    this.cliente = cliente;
    Scontrino scontrino = new Scontrino();
    PropertyChangeListener dataModelListener = new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        dataModelChanged(evt);
      }
    };
    cliente.addPropertyChangeListener(dataModelListener);

    acquistiModel.setModel(cliente.getScontrini());
    scontriniModel.setModel(cliente.getScontrini());

    scontriniTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent event) {
        int index = scontriniTable.getSelectedRow();
        if (index != -1) {
          acquistiModel.setIndex(index);

        }
      }
    });

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    viewToModel();
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    viewToModel();
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    viewToModel();
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    viewToModel();
  }

  private void setEnabledColor(Container container, boolean enabled, Color color) {
    if (container instanceof JTextField || container instanceof AbstractButton) {
      container.setEnabled(enabled);
      container.setBackground(color);
    }
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

  private void listenGuiField() {
    listenGuiField(this);
  }

  private void listenGuiField(Container container) {
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

  private void navigation() {
    acquistiModel.isSearching(false);
    setEnabledColor(infoClientePanel, false, SystemColor.control);
    setEnabledColor(puntiPanel, false, SystemColor.control);
    modelToView();
  }

  private void insert() {
    setEnabledColor(infoClientePanel, true, Color.white);
    setEnabledColor(puntiPanel, false, SystemColor.control);
    setEnabledColor(cfTextField, false, SystemColor.control);
    // cliente.copyTo(oldCliente);
    // cliente.clear();
  }

  private void update() {
    setEnabledColor(infoClientePanel, true, Color.cyan);
    setEnabledColor(puntiPanel, false, SystemColor.control);
    setEnabledColor(cfTextField, false, SystemColor.control);
  }

  private void search() {
    acquistiModel.isSearching(true);
    setEnabledColor(infoClientePanel, true, Color.yellow);
    setEnabledColor(puntiPanel, true, Color.yellow);
    (new Cliente()).copyTo(cliente);
  }

  private void applicationStatusChanged(PropertyChangeEvent evt) {
    if (ApplicationStatus.getInstance().getActiveTab() != ApplicationStatus.TAB_CLIENTI) {
      return;
    }
    switch (ApplicationStatus.getInstance().getStatus()) {
    case ApplicationStatus.STATUS_NAVIGATION:
      navigation();
      break;
    case ApplicationStatus.STATUS_INSERT:
      insert();
      break;
    case ApplicationStatus.STATUS_UPDATE:
      update();
      break;
    case ApplicationStatus.STATUS_SEARCH:
      search();
      break;
    }

    if (evt.getPropertyName().equals("status")) {
      switch (ApplicationStatus.getInstance().getStatus()) {
      case ApplicationStatus.STATUS_NAVIGATION:
        navigation();
        break;
      case ApplicationStatus.STATUS_INSERT:
        insert();
        break;
      case ApplicationStatus.STATUS_UPDATE:
        update();
        break;
      case ApplicationStatus.STATUS_SEARCH:
        search();
        break;
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

  private void dataModelChanged(PropertyChangeEvent evt) {
    modelToView();
  }

  private void modelToView() {
    modelToViewRunning = true;
    nomeTextField.setText(cliente.getString(Cliente.NOME));
    cognomeTextField.setText(cliente.getString(Cliente.COGNOME));
    cfTextField.setText(cliente.getString(Cliente.CF));
    eMailTextField.setText(cliente.getString(Cliente.EMAIL));
    try {
      dataNascitaTextField.setText(cliente.getString(Cliente.DATA_NASCITA) != null ? formatter.format(cliente.getDataNascita()) : "");
    }
    catch (Exception e) {

    }

    luogoNascitaTextField.setText(cliente.getString(Cliente.LUOGO_NASCITA));

    if (cliente.getGenere() == Genere.M) {
      mRadioButton.setSelected(true);
      fRadioButton.setSelected(false);
    }
    else if (cliente.getGenere() == Genere.F) {
      fRadioButton.setSelected(true);
      mRadioButton.setSelected(false);
    }
    else {
      fRadioButton.setSelected(false);
      mRadioButton.setSelected(false);
    }

    puntiFruttaVerduraTextField.setText(cliente.getRaccoltaPunti().getString(RaccoltaPunti.FRUTTA_VERDURA));
    puntiCarnePesceTextField.setText(cliente.getRaccoltaPunti().getString(RaccoltaPunti.CARNE_PESCE));
    puntiBibitaTextField.setText(cliente.getRaccoltaPunti().getString(RaccoltaPunti.BIBITA));
    puntiFarinaceoTextField.setText(cliente.getRaccoltaPunti().getString(RaccoltaPunti.FARINACEO));
    puntiConservaTextField.setText(cliente.getRaccoltaPunti().getString(RaccoltaPunti.CONSERVA));
    puntiProdottoCasearioTextField.setText(cliente.getRaccoltaPunti().getString(RaccoltaPunti.PRODOTTO_CASEARIO));
    puntiUovoTextField.setText(cliente.getRaccoltaPunti().getString(RaccoltaPunti.UOVO));
    puntiAltroTextField.setText(cliente.getRaccoltaPunti().getString(RaccoltaPunti.ALTRO));
    modelToViewRunning = false;
  }

  private void viewToModel() {
    if (modelToViewRunning) {
      return;
    }
    cliente.setValue(Cliente.NOME, nomeTextField.getText().isBlank() || nomeTextField.getText().isEmpty() ? null : nomeTextField.getText());
    cliente.setValue(Cliente.COGNOME, cognomeTextField.getText().isBlank() || cognomeTextField.getText().isEmpty() ? null : cognomeTextField.getText());
    cliente.setValue(Cliente.CF, cfTextField.getText().isBlank() || cfTextField.getText().isEmpty() ? null : cfTextField.getText());
    cliente.setValue(Cliente.EMAIL, eMailTextField.getText().isBlank() || eMailTextField.getText().isEmpty() ? null : eMailTextField.getText());
    cliente.setValue(Cliente.DATA_NASCITA, dataNascitaTextField.getText().isBlank() || dataNascitaTextField.getText().isEmpty() ? null : dataNascitaTextField.getText());
    cliente.setValue(Cliente.LUOGO_NASCITA, luogoNascitaTextField.getText().isBlank() || luogoNascitaTextField.getText().isEmpty() ? null : luogoNascitaTextField.getText());

    if (mRadioButton.isSelected()) {
      cliente.setValue(Cliente.GENERE, Genere.M);
    }
    else if (fRadioButton.isSelected()) {
      cliente.setValue(Cliente.GENERE, Genere.F);
    }
    else {
      cliente.setValue(Cliente.GENERE, null);
    }

    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.BIBITA, puntiBibitaTextField.getText().isBlank() || puntiBibitaTextField.getText().isEmpty() ? null : puntiBibitaTextField.getText());
    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.ALTRO, puntiAltroTextField.getText().isBlank() || puntiAltroTextField.getText().isEmpty() ? null : puntiAltroTextField.getText());
    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.FARINACEO, puntiFarinaceoTextField.getText().isBlank() || puntiFarinaceoTextField.getText().isEmpty() ? null : puntiFarinaceoTextField.getText());
    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.FRUTTA_VERDURA, puntiFruttaVerduraTextField.getText().isBlank() || puntiFruttaVerduraTextField.getText().isEmpty() ? null : puntiFruttaVerduraTextField.getText());
    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.CARNE_PESCE, puntiCarnePesceTextField.getText().isBlank() || puntiCarnePesceTextField.getText().isEmpty() ? null : puntiCarnePesceTextField.getText());
    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.UOVO, puntiUovoTextField.getText().isBlank() || puntiUovoTextField.getText().isEmpty() ? null : puntiUovoTextField.getText());
    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.CONSERVA, puntiConservaTextField.getText().isBlank() || puntiConservaTextField.getText().isEmpty() ? null : puntiConservaTextField.getText());
    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.PRODOTTO_CASEARIO, puntiProdottoCasearioTextField.getText().isBlank() || puntiProdottoCasearioTextField.getText().isEmpty() ? null : puntiProdottoCasearioTextField.getText());
  }



}
