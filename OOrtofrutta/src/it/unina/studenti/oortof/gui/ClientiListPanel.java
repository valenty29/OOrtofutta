package it.unina.studenti.oortof.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.unina.studenti.oortof.gui.models.ClientiListTableModel;
import it.unina.studenti.oortof.models.Cliente;

import it.unina.studenti.oortof.models.ObservedList;

public class ClientiListPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private JTable table;
  
  Cliente cliente;
  ObservedList listCliente;
  public ClientiListPanel() {
	  
	  
    setLayout(new BorderLayout(0, 0));
    
    JScrollPane scrollPane = new JScrollPane();
    add(scrollPane);
    
    table = new JTable(new ClientiListTableModel());
    scrollPane.setViewportView(table);
  }
  
  
  public void setModel(Cliente cliente, ObservedList listCliente) {
	    ClientiListTableModel model = (ClientiListTableModel)table.getModel();
	    model.setModel(cliente, listCliente);
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	        	cliente.getScontrini().clear();
	        	int index = table.getSelectedRow();
	        	if (index != -1) {
	        		listCliente.get(index).copyTo(cliente);
	        	}
	        	
	        }
	    });
	  }
}
