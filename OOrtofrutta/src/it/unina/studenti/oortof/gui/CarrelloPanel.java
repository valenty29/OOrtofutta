package it.unina.studenti.oortof.gui;

import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.gui.models.LottiTableModel;
import it.unina.studenti.oortof.models.Carrello;
import it.unina.studenti.oortof.models.Cliente;
import it.unina.studenti.oortof.models.Prodotto;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@SuppressWarnings("serial")
public class CarrelloPanel extends JPanel {
  private JTable carrelloTable;
  private JScrollPane scrollPane;
  private JButton confermaButton;
  private JButton cancellaButton;
  private Carrello carrello;
  private Cliente cliente;
  private JPanel buttonPanel;
  private JPanel clientePanel;
  private SQLClienteDAO sqlClienteDAO;
  private JLabel firstNameLabel;
  private JLabel lastNameLabel;
  public CarrelloPanel() {
    sqlClienteDAO = new SQLClienteDAO();
    scrollPane = new JScrollPane();
    clientePanel = new JPanel();
    clientePanel.setLayout(new BoxLayout(clientePanel, BoxLayout.X_AXIS));
    firstNameLabel = new JLabel("TESTONE");
    lastNameLabel = new JLabel("TESTISSIMA");
    clientePanel.add(firstNameLabel);
    clientePanel.add(lastNameLabel);
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    add(clientePanel);
    add(scrollPane);
    add(buttonPanel);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
    carrelloTable = new JTable();
    carrelloTable.setBorder(new LineBorder(new Color(0, 0, 0)));

    scrollPane.setViewportView(carrelloTable);

    confermaButton = new JButton();
    confermaButton.setText("Conferma acquisti");

    confermaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        sqlClienteDAO.createScontrino(cliente, carrello.getLotti());
        carrello.clear();
      }
    });
    cancellaButton = new JButton();
    cancellaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        carrello.clear();
      }
    });
    cancellaButton.setText("Cancella acquisti");
    buttonPanel.add(confermaButton);
    buttonPanel.add(cancellaButton);



    carrelloTable.setModel(new LottiTableModel());
  }

  public void setModel(Carrello carrello, Cliente cliente) {
    this.carrello = carrello;
    this.cliente = cliente;
    PropertyChangeListener dataModelListener = new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        dataModelChanged(evt);
      }
    };
    ((LottiTableModel)carrelloTable.getModel()).setList(carrello.getLotti());
    carrello.addPropertyChangeListener(dataModelListener);
    cliente.addPropertyChangeListener(dataModelListener);
  }

  void dataModelChanged(PropertyChangeEvent evt) {
    modelToView();
  }

  void modelToView() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        modelToViewCore();
      }
    });
  }

  void modelToViewCore() {
    firstNameLabel.setText(cliente.getNome());
    lastNameLabel.setText(cliente.getCognome());
  }
  
}
