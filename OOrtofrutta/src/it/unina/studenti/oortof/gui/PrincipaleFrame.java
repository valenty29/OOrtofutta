package it.unina.studenti.oortof.gui;

import it.unina.studenti.oortof.models.Bibita;
import it.unina.studenti.oortof.models.DBContext;
import it.unina.studenti.oortof.models.SQLProductDAO;
import it.unina.studenti.oortof.models.TipoBibita;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

public class PrincipaleFrame extends JFrame {

  private JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          PrincipaleFrame frame = new PrincipaleFrame();
          frame.setVisible(true);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  private void test()
  {
    DBContext context = new DBContext("jdbc:postgresql://localhost/postgres", "postgres", "Inb4Ext!");
    SQLProductDAO dao = new SQLProductDAO(context);

    List<Bibita> bibites = dao.getBibita(null, null, 0.2f, 1.2f, true, null, null, null, TipoBibita.SoftDrink);
    for(Bibita bib: bibites)
    {
        System.out.println(bib.getProdotto().getNome());
    }
  }


  public PrincipaleFrame() {
    test();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new BorderLayout(0, 0));
    
    JPanel pulsantiFissiPanel = new JPanel();
    contentPane.add(pulsantiFissiPanel, BorderLayout.NORTH);
    
    JButton modificaButton = new JButton("Modifica");
    pulsantiFissiPanel.add(modificaButton);
    
    JButton aggiungiButton = new JButton("Aggiungi");
    pulsantiFissiPanel.add(aggiungiButton);
    
    JButton cancellaButton = new JButton("Cancella");
    pulsantiFissiPanel.add(cancellaButton);
    
    JButton cercaButton = new JButton("Cerca");
    pulsantiFissiPanel.add(cercaButton);
    
    JTabbedPane ilTabbedPanel = new JTabbedPane(JTabbedPane.TOP);
    contentPane.add(ilTabbedPanel, BorderLayout.CENTER);
    
    ProdottiPanel prodottiPanel = new ProdottiPanel();
    ilTabbedPanel.add(prodottiPanel);
    
    JPanel carrelloPanel = new JPanel();
    ilTabbedPanel.add(carrelloPanel);
    
    JPanel clientiPanel = new JPanel();
    ilTabbedPanel.add(clientiPanel);
    
    JPanel scontriniPanel = new JPanel();
    ilTabbedPanel.add(scontriniPanel);
  }

}
