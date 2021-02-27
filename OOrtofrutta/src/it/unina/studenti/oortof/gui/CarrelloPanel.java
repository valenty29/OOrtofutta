package it.unina.studenti.oortof.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class CarrelloPanel extends JPanel {
  private JTable carrelloTable;
  public CarrelloPanel() {
    setLayout(new BorderLayout(0, 0));
    
    carrelloTable = new JTable();
    carrelloTable.setBorder(new LineBorder(new Color(0, 0, 0)));
    add(carrelloTable, BorderLayout.CENTER);
  }
  

}
