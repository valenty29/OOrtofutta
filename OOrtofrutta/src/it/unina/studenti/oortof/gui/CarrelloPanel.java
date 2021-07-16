package it.unina.studenti.oortof.gui;

import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.gui.models.CarrelloTableModel;
import it.unina.studenti.oortof.gui.models.LottiTableModel;
import it.unina.studenti.oortof.models.Carrello;
import it.unina.studenti.oortof.models.Cliente;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class CarrelloPanel extends JPanel {
  private JTable carrelloTable;
  private JScrollPane scrollPane;
  private JButton confermaButton;
  private JButton cancellaButton;
  private Carrello carrello;
  private Cliente cliente;
  private JPanel southCarrelloPanel;
  private SQLClienteDAO sqlClienteDAO;
  private JPanel northCarrelloPanel;
  private JLabel nomeCognomeLabel;

  public CarrelloPanel() {
    sqlClienteDAO = new SQLClienteDAO();
    scrollPane = new JScrollPane();
    southCarrelloPanel = new JPanel();
    southCarrelloPanel.setLayout(new BoxLayout(southCarrelloPanel, BoxLayout.X_AXIS));
    setLayout(new BorderLayout(0, 0));
    add(scrollPane, BorderLayout.CENTER);

    northCarrelloPanel = new JPanel();
    add(northCarrelloPanel, BorderLayout.NORTH);

    nomeCognomeLabel = new JLabel("Seleziona un Cliente");
    northCarrelloPanel.add(nomeCognomeLabel);
    add(southCarrelloPanel, BorderLayout.SOUTH);

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
    southCarrelloPanel.add(confermaButton);
    southCarrelloPanel.add(cancellaButton);

    carrelloTable.setModel(new CarrelloTableModel());
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
    ((CarrelloTableModel)carrelloTable.getModel()).setList(carrello.getLotti());
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
    nomeCognomeLabel.setText(cliente.getNome() + " " + cliente.getCognome());
  }

}
