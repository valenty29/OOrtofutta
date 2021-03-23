package it.unina.studenti.oortof.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.unina.studenti.oortof.gui.models.ProdottiListTableModel;
import it.unina.studenti.oortof.models.ApplicationCounter;
import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.ObservedList;
import it.unina.studenti.oortof.models.Prodotto;

public class ProdottiListPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private JTable table;
  public ProdottiListPanel() {
    setLayout(new BorderLayout(0, 0));
    
    JScrollPane scrollPane = new JScrollPane();
    add(scrollPane);
    
    table = new JTable();
    table.setOpaque(true);
    table.setBackground(SystemColor.control);

    table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    scrollPane.setViewportView(table);
    ApplicationCounter.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if (ApplicationStatus.getInstance().getActiveTab() == ApplicationStatus.TAB_PRODOTTI) {
          int index = ApplicationCounter.getInstance().getCounter() - 1;
          if (index >= 0) {
            table.setRowSelectionInterval(index, index);
          }
          else {
            table.clearSelection();
          }
        }
      }
    });
    ApplicationStatus.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        ApplicationStatus as = ApplicationStatus.getInstance();
        if (as.getActiveTab() == 0 && evt.getOldValue().equals(ApplicationStatus.STATUS_SEARCH) && evt.getNewValue().equals(ApplicationStatus.STATUS_NAVIGATION) && as.getAction() == ApplicationStatus.ACTION_COMMIT) {
          if (table.getModel().getRowCount() == 0) {
            table.clearSelection();
          }
          else {
            table.setRowSelectionInterval(0, 0);
          }
        }
      }
    });
    
    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (ApplicationCounter.getInstance().getCounter() != (table.getSelectedRow() + 1)) {
          ApplicationCounter.getInstance().setCounter(table.getSelectedRow() + 1);
          ApplicationCounter.getInstance().setLimit(table.getRowCount());
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
  
  public void setModel(ObservedList<Prodotto> prodotti) {
	  table.setModel(new ProdottiListTableModel(prodotti));
	  prodotti.addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        ApplicationCounter.getInstance().setLimit(prodotti.size());
      }
    });
  }

}
