package it.unina.studenti.oortof.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class CarrelloPanel extends JPanel {
  private JTable carrelloTable;
  public CarrelloPanel() {
    setLayout(new BorderLayout(0, 0));
    
    carrelloTable = new JTable();
    add(carrelloTable, BorderLayout.CENTER);
  }
  

}
