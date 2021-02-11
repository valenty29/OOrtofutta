import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSeparator;

public class principaleFrame extends JFrame {

	private JPanel principalePanel;
	private JTextField nomeTextField;
	private JTextField codiceProdottoextFIeld;
	private JTextField prezzo1textField;
	private JTextField prezzo2textFeield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principaleFrame frame = new principaleFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public principaleFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 572);
		principalePanel = new JPanel();
		principalePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		principalePanel.setLayout(new BorderLayout(0, 0));
		setContentPane(principalePanel);
		
		JPanel pulsantiFissiInAltoPanel = new JPanel();
		FlowLayout fl_pulsantiFissiInAltoPanel = (FlowLayout) pulsantiFissiInAltoPanel.getLayout();
		fl_pulsantiFissiInAltoPanel.setAlignment(FlowLayout.LEFT);
		principalePanel.add(pulsantiFissiInAltoPanel, BorderLayout.NORTH);
		
		JButton modificaButton = new JButton("Modifica");
		pulsantiFissiInAltoPanel.add(modificaButton);
		
		JButton aggiungiButton = new JButton("Aggiungi");
		pulsantiFissiInAltoPanel.add(aggiungiButton);
		
		JButton cancellaButton = new JButton("Cancella");
		pulsantiFissiInAltoPanel.add(cancellaButton);
		
		JButton cercaButton = new JButton("Cerca");
		pulsantiFissiInAltoPanel.add(cercaButton);
		
		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanel.setSelectedIndex(-1);
		principalePanel.add(tabbedPanel, BorderLayout.CENTER);
		
		JPanel prodottiPanel = new JPanel();
		tabbedPanel.addTab("Prodotti", null, prodottiPanel, null);
		prodottiPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel RicercaGenericaPanel = new JPanel();
		RicercaGenericaPanel.setPreferredSize(new Dimension(10, 110));
		prodottiPanel.add(RicercaGenericaPanel, BorderLayout.NORTH);
		GridBagLayout gbl_RicercaGenericaPanel = new GridBagLayout();
		gbl_RicercaGenericaPanel.columnWidths = new int[]{79, 157, 162, 0, 129, 137, 0};
		gbl_RicercaGenericaPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_RicercaGenericaPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_RicercaGenericaPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		RicercaGenericaPanel.setLayout(gbl_RicercaGenericaPanel);
		
		JLabel nomeLabel = new JLabel("Nome");
		GridBagConstraints gbc_nomeLabel = new GridBagConstraints();
		gbc_nomeLabel.anchor = GridBagConstraints.WEST;
		gbc_nomeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nomeLabel.gridx = 0;
		gbc_nomeLabel.gridy = 0;
		RicercaGenericaPanel.add(nomeLabel, gbc_nomeLabel);
		
		nomeTextField = new JTextField();
		GridBagConstraints gbc_nomeTextField = new GridBagConstraints();
		gbc_nomeTextField.gridwidth = 2;
		gbc_nomeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nomeTextField.gridx = 1;
		gbc_nomeTextField.gridy = 0;
		RicercaGenericaPanel.add(nomeTextField, gbc_nomeTextField);
		nomeTextField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 3;
		gbc_separator.gridy = 0;
		RicercaGenericaPanel.add(separator, gbc_separator);
		
		JCheckBox fruttaVerduraCheckBox = new JCheckBox("Frutta e Verdura");
		GridBagConstraints gbc_fruttaVerduraCheckBox = new GridBagConstraints();
		gbc_fruttaVerduraCheckBox.anchor = GridBagConstraints.WEST;
		gbc_fruttaVerduraCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_fruttaVerduraCheckBox.gridx = 4;
		gbc_fruttaVerduraCheckBox.gridy = 0;
		RicercaGenericaPanel.add(fruttaVerduraCheckBox, gbc_fruttaVerduraCheckBox);
		
		JCheckBox bibiteCheckBox = new JCheckBox("Bibite");
		GridBagConstraints gbc_bibiteCheckBox = new GridBagConstraints();
		gbc_bibiteCheckBox.anchor = GridBagConstraints.WEST;
		gbc_bibiteCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_bibiteCheckBox.gridx = 5;
		gbc_bibiteCheckBox.gridy = 0;
		RicercaGenericaPanel.add(bibiteCheckBox, gbc_bibiteCheckBox);
		
		JLabel codiceProdottoLabel = new JLabel("Codice Prodotto");
		GridBagConstraints gbc_codiceProdottoLabel = new GridBagConstraints();
		gbc_codiceProdottoLabel.anchor = GridBagConstraints.WEST;
		gbc_codiceProdottoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_codiceProdottoLabel.gridx = 0;
		gbc_codiceProdottoLabel.gridy = 1;
		RicercaGenericaPanel.add(codiceProdottoLabel, gbc_codiceProdottoLabel);
		
		codiceProdottoextFIeld = new JTextField();
		GridBagConstraints gbc_codiceProdottoextFIeld = new GridBagConstraints();
		gbc_codiceProdottoextFIeld.gridwidth = 2;
		gbc_codiceProdottoextFIeld.insets = new Insets(0, 0, 5, 5);
		gbc_codiceProdottoextFIeld.fill = GridBagConstraints.HORIZONTAL;
		gbc_codiceProdottoextFIeld.gridx = 1;
		gbc_codiceProdottoextFIeld.gridy = 1;
		RicercaGenericaPanel.add(codiceProdottoextFIeld, gbc_codiceProdottoextFIeld);
		codiceProdottoextFIeld.setColumns(10);
		
		JCheckBox farinaceiCheckBox = new JCheckBox("Farinacei");
		GridBagConstraints gbc_farinaceiCheckBox = new GridBagConstraints();
		gbc_farinaceiCheckBox.anchor = GridBagConstraints.WEST;
		gbc_farinaceiCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_farinaceiCheckBox.gridx = 4;
		gbc_farinaceiCheckBox.gridy = 1;
		RicercaGenericaPanel.add(farinaceiCheckBox, gbc_farinaceiCheckBox);
		
		JCheckBox conserveCheckBox = new JCheckBox("Conserve");
		GridBagConstraints gbc_conserveCheckBox = new GridBagConstraints();
		gbc_conserveCheckBox.anchor = GridBagConstraints.WEST;
		gbc_conserveCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_conserveCheckBox.gridx = 5;
		gbc_conserveCheckBox.gridy = 1;
		RicercaGenericaPanel.add(conserveCheckBox, gbc_conserveCheckBox);
		
		JLabel prezzoLabel = new JLabel("Prezzo");
		GridBagConstraints gbc_prezzoLabel = new GridBagConstraints();
		gbc_prezzoLabel.anchor = GridBagConstraints.WEST;
		gbc_prezzoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_prezzoLabel.gridx = 0;
		gbc_prezzoLabel.gridy = 2;
		RicercaGenericaPanel.add(prezzoLabel, gbc_prezzoLabel);
		
		prezzo1textField = new JTextField();
		GridBagConstraints gbc_prezzo1textField = new GridBagConstraints();
		gbc_prezzo1textField.insets = new Insets(0, 0, 5, 5);
		gbc_prezzo1textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_prezzo1textField.gridx = 1;
		gbc_prezzo1textField.gridy = 2;
		RicercaGenericaPanel.add(prezzo1textField, gbc_prezzo1textField);
		prezzo1textField.setColumns(10);
		
		prezzo2textFeield = new JTextField();
		GridBagConstraints gbc_prezzo2textFeield = new GridBagConstraints();
		gbc_prezzo2textFeield.insets = new Insets(0, 0, 5, 5);
		gbc_prezzo2textFeield.fill = GridBagConstraints.HORIZONTAL;
		gbc_prezzo2textFeield.gridx = 2;
		gbc_prezzo2textFeield.gridy = 2;
		RicercaGenericaPanel.add(prezzo2textFeield, gbc_prezzo2textFeield);
		prezzo2textFeield.setColumns(10);
		
		JCheckBox uovaCheckBox = new JCheckBox("Uova");
		GridBagConstraints gbc_uovaCheckBox = new GridBagConstraints();
		gbc_uovaCheckBox.anchor = GridBagConstraints.WEST;
		gbc_uovaCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_uovaCheckBox.gridx = 4;
		gbc_uovaCheckBox.gridy = 2;
		RicercaGenericaPanel.add(uovaCheckBox, gbc_uovaCheckBox);
		
		JCheckBox altroCheckBox = new JCheckBox("Altro");
		GridBagConstraints gbc_altroCheckBox = new GridBagConstraints();
		gbc_altroCheckBox.anchor = GridBagConstraints.WEST;
		gbc_altroCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_altroCheckBox.gridx = 5;
		gbc_altroCheckBox.gridy = 2;
		RicercaGenericaPanel.add(altroCheckBox, gbc_altroCheckBox);
		
		JCheckBox sfusoCheckBox = new JCheckBox("Sfuso");
		GridBagConstraints gbc_sfusoCheckBox = new GridBagConstraints();
		gbc_sfusoCheckBox.anchor = GridBagConstraints.WEST;
		gbc_sfusoCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_sfusoCheckBox.gridx = 1;
		gbc_sfusoCheckBox.gridy = 3;
		RicercaGenericaPanel.add(sfusoCheckBox, gbc_sfusoCheckBox);
		
		JCheckBox carnePesceCheckBox = new JCheckBox("Carne e Pesce");
		GridBagConstraints gbc_carnePesceCheckBox = new GridBagConstraints();
		gbc_carnePesceCheckBox.anchor = GridBagConstraints.WEST;
		gbc_carnePesceCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_carnePesceCheckBox.gridx = 4;
		gbc_carnePesceCheckBox.gridy = 3;
		RicercaGenericaPanel.add(carnePesceCheckBox, gbc_carnePesceCheckBox);
		
		JScrollPane scrollPane = new JScrollPane();
		prodottiPanel.add(scrollPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		prodottiPanel.add(scrollPane_2, BorderLayout.SOUTH);
		
		JPanel carrelloPanel = new JPanel();
		tabbedPanel.addTab("Carrello", null, carrelloPanel, null);
		
		JPanel clientiPanel = new JPanel();
		tabbedPanel.addTab("Clienti", null, clientiPanel, null);
		
		JPanel scontriniPanel = new JPanel();
		tabbedPanel.addTab("Scontrini", null, scontriniPanel, null);
	}

}
