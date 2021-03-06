package it.unina.studenti.oortof.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;

public class ProdottiListPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private JTable table;
  public ProdottiListPanel() {
    setLayout(new BorderLayout(0, 0));
    
    JScrollPane scrollPane = new JScrollPane();
    add(scrollPane);
    
    table = new JTable();
    scrollPane.setViewportView(table);
  }

}
