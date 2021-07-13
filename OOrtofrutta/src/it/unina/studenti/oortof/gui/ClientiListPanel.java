package it.unina.studenti.oortof.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.unina.studenti.oortof.gui.models.ClientiListTableModel;
import it.unina.studenti.oortof.models.ApplicationCounter;
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
      table.setOpaque(true);
      table.setBackground(SystemColor.control);
      table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

      table.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
              if (e.getClickCount() == 2) {
                  if (table.getSelectedRow() >= 0) {
                      JTabbedPane detailListTabbed = (JTabbedPane)SwingUtilities.getAncestorOfClass(JTabbedPane.class, table);
                      detailListTabbed.setSelectedIndex(0);
                  }
              }
          }
      });
  }
}
