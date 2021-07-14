package it.unina.studenti.oortof.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import it.unina.studenti.oortof.gui.models.AcquistiTableModel;
import it.unina.studenti.oortof.gui.models.ScontriniTableModel;
import it.unina.studenti.oortof.models.Acquisto;
import it.unina.studenti.oortof.models.ApplicationCounter;
import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.Cliente;
import it.unina.studenti.oortof.models.Genere;
import it.unina.studenti.oortof.models.ObservedList;
import it.unina.studenti.oortof.models.Prodotto;
import it.unina.studenti.oortof.models.ProdottoCommon;
import it.unina.studenti.oortof.models.RaccoltaPunti;
import it.unina.studenti.oortof.models.Scontrino;

public class ClientiPanel extends DesignClientiPanel implements DocumentListener, ActionListener{
	Cliente cliente;
	Cliente oldCliente = new Cliente();
	
	
	private ScontriniTableModel scontriniModel;
	private AcquistiTableModel acquistiModel;
	private static final long serialVersionUID = 1L;
	
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

	    scontriniTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	int index = scontriniTable.getSelectedRow();
	        	if (index != -1) {
	        		acquistiModel.setIndex(index);

	        	}
	        }
	    });

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
	
	void navigation() {
		acquistiModel.isSearching(false);
	    setEnabledColor(infoClientePanel, false, SystemColor.control);
	    setEnabledColor(puntiPanel, false, SystemColor.control);
	    modelToView();
	  }

	  void insert() {
	    setEnabledColor(infoClientePanel, true, Color.white);
	    setEnabledColor(puntiPanel, false, SystemColor.control);
	    //cliente.copyTo(oldCliente);
	    //cliente.clear();
	  }

	  void update() {
	    setEnabledColor(infoClientePanel, true, Color.cyan);
	    setEnabledColor(puntiPanel, false, SystemColor.control);
	  }

	  void search() {
		acquistiModel.isSearching(true);
	    setEnabledColor(infoClientePanel, true, Color.yellow);
	    setEnabledColor(puntiPanel, true, Color.yellow);
	    (new Cliente()).copyTo(cliente);
	  }
	    
	  void applicationStatusChanged(PropertyChangeEvent evt) {
		  if (ApplicationStatus.getInstance().getActiveTab() != ApplicationStatus.TAB_CLIENTI) {
			  return;
		  }
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
	  
	  void modelToView() {
	    modelToViewRunning = true;
	    nomeTextField.setText(cliente.getString(Cliente.NOME));
	    cognomeTextField.setText(cliente.getString(Cliente.COGNOME));
	    cfTextField.setText(cliente.getString(Cliente.CF));
	    eMailTextField.setText(cliente.getString(Cliente.EMAIL));
	    dataNascitaTextField.setText(cliente.getString(Cliente.DATA_NASCITA));
	    luogoNascitaTextField.setText(cliente.getString(Cliente.LUOGO_NASCITA));
	    
	    if(cliente.getGenere() == Genere.M) {
	    	mRadioButton.setSelected(true);
	    	fRadioButton.setSelected(false);
	    } else if (cliente.getGenere() == Genere.F){
	    	fRadioButton.setSelected(true);
	    	mRadioButton.setSelected(false);
	    } else {
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
	 
	public Cliente getViewModel() {
	    return null;
	 }
	
	void viewToModel() {
	    if (modelToViewRunning) {
	      return;
	    }
	    cliente.setValue(Cliente.NOME, nomeTextField.getText().isBlank() || nomeTextField.getText().isEmpty() ? null : nomeTextField.getText());
	    cliente.setValue(Cliente.COGNOME, cognomeTextField.getText().isBlank() || cognomeTextField.getText().isEmpty() ? null: cognomeTextField.getText());
	    cliente.setValue(Cliente.CF, cfTextField.getText().isBlank() || cfTextField.getText().isEmpty() ? null : cfTextField.getText());
	    cliente.setValue(Cliente.EMAIL, eMailTextField.getText().isBlank() || eMailTextField.getText().isEmpty() ? null : eMailTextField.getText());
	    cliente.setValue(Cliente.DATA_NASCITA, dataNascitaTextField.getText().isBlank() || dataNascitaTextField.getText().isEmpty() ? null : dataNascitaTextField.getText());
	    cliente.setValue(Cliente.LUOGO_NASCITA, luogoNascitaTextField.getText().isBlank() || luogoNascitaTextField.getText().isEmpty() ? null : luogoNascitaTextField.getText());
	    
	    if (mRadioButton.isSelected()) {
	    	cliente.setValue(Cliente.GENERE, Genere.M);
	    } else if (fRadioButton.isSelected()) {
	    	cliente.setValue(Cliente.GENERE, Genere.F);
	    } else {
	    	cliente.setValue(Cliente.GENERE, null);
	    }
	    //cliente.setValue(Cliente.GENERE, mRadioButton.isSelected() ? 'M' : (fRadioButton.isSelected() ? 'F' : null));
	    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.BIBITA, puntiBibitaTextField.getText().isBlank() || puntiBibitaTextField.getText().isEmpty() ? null : puntiBibitaTextField.getText());
	    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.ALTRO, puntiAltroTextField.getText().isBlank() || puntiAltroTextField.getText().isEmpty() ? null : puntiAltroTextField.getText());
	    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.FARINACEO, puntiFarinaceoTextField.getText().isBlank() || puntiFarinaceoTextField.getText().isEmpty() ? null : puntiFarinaceoTextField.getText());
	    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.FRUTTA_VERDURA, puntiFruttaVerduraTextField.getText().isBlank() || puntiFruttaVerduraTextField.getText().isEmpty() ? null : puntiFruttaVerduraTextField.getText());
	    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.CARNE_PESCE, puntiCarnePesceTextField.getText().isBlank() ||  puntiCarnePesceTextField.getText().isEmpty() ? null : puntiCarnePesceTextField.getText());
	    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.UOVO, puntiUovoTextField.getText().isBlank() || puntiUovoTextField.getText().isEmpty() ? null : puntiUovoTextField.getText());
	    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.CONSERVA, puntiConservaTextField.getText().isBlank() || puntiConservaTextField.getText().isEmpty() ? null : puntiConservaTextField.getText());
	    cliente.getRaccoltaPunti().setValue(RaccoltaPunti.PRODOTTO_CASEARIO, puntiProdottoCasearioTextField.getText().isBlank() || puntiProdottoCasearioTextField.getText().isEmpty() ? null: puntiProdottoCasearioTextField.getText());
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
	  

}
