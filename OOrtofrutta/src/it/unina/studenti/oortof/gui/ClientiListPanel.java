package it.unina.studenti.oortof.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JTable;

import it.unina.studenti.oortof.models.Cliente;
import it.unina.studenti.oortof.models.ClientiListTableModel;
import it.unina.studenti.oortof.models.ObservableList;

public class ClientiListPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private JTable table;
  
  Cliente cliente;
  ObservableList<Cliente> listCliente;
 
  
  public ClientiListPanel() {
	  
	  
    setLayout(new BorderLayout(0, 0));
    
    JScrollPane scrollPane = new JScrollPane();
    add(scrollPane);
    
    table = new JTable(new ClientiListTableModel());
    scrollPane.setViewportView(table);
    
    
    
  }
  
  
  public void setModel(Cliente cliente, ObservableList<Cliente> listCliente) {
	    this.cliente = cliente;
	    this.listCliente = listCliente;
	    ClientiListTableModel model = (ClientiListTableModel)table.getModel();
	    model.setModel(listCliente);
	    
	  }
  
  private void dataModelChanged(PropertyChangeEvent evt) {
	 // modelToView();
  }
  
  boolean modelToViewRunning = false;
  
  private void modelToView() {
	  /*modelToViewRunning = true;
	  for(Cliente cliente: listCliente) {
		  table.add(new JLabel(cliente.getNome()));
	  }
	  modelToViewRunning = false;*/
  }

}
