package it.unina.studenti.oortof.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.unina.studenti.oortof.gui.models.ClientiListTableModel;
import it.unina.studenti.oortof.models.application.ApplicationCounter;
import it.unina.studenti.oortof.models.application.ApplicationStatus;
import it.unina.studenti.oortof.models.entities.Cliente;
import it.unina.studenti.oortof.models.entities.ObservedList;

public class ClientiListPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private JTable table;
  private Cliente cliente;
  private ObservedList listCliente;

  public ClientiListPanel() {

    setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();
    add(scrollPane);

    table = new JTable(new ClientiListTableModel());
    table.setOpaque(true);
    table.setBackground(SystemColor.control);
    table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    scrollPane.setViewportView(table);

    ApplicationCounter.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if (ApplicationStatus.getInstance().getActiveTab() == ApplicationStatus.TAB_CLIENTI) {
          int index = ApplicationCounter.getInstance().getCounter() - 1;
          if (index >= 0 && index < table.getRowCount()) {
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
        if (as.getActiveTab() == ApplicationStatus.TAB_CLIENTI && evt.getOldValue().equals(ApplicationStatus.STATUS_SEARCH) && evt.getNewValue().equals(ApplicationStatus.STATUS_NAVIGATION) && as.getAction() == ApplicationStatus.ACTION_COMMIT) {
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
      public void valueChanged(ListSelectionEvent event) {
        if (ApplicationCounter.getInstance().getCounter() != (table.getSelectedRow() + 1)) {
          if (event.getFirstIndex() == ApplicationCounter.getInstance().getCounter() - 1 && table.getSelectedRow() == -1) {
            ApplicationCounter.getInstance().setCounter(ApplicationCounter.getInstance().getCounter());
          }
          else {
            ApplicationCounter.getInstance().setCounter(table.getSelectedRow() + 1);
          }

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

  public void setModel(Cliente cliente, ObservedList listCliente) {
    ClientiListTableModel model = (ClientiListTableModel)table.getModel();
    model.setModel(cliente, listCliente);
    listCliente.addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        ApplicationCounter.getInstance().setLimit(listCliente.size());
      }
    });

  }
}
