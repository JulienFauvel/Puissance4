package puissance.pkg4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Julien
 */
public class Window extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem i1;
	private JMenuItem i2;
	private Board board;
	private JPanel bouton;
	private JPanel topBar;
	private JLabel joueur;
	private JButton[] tabBouton;
	
	private final int NB_BOUTON = 7;
	
	public Window() {
		
		setTitle("Puissance 4");
		setSize(500, 620);
		setLocationRelativeTo(null);
		
		init();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void init() {
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu = new JMenu("Menu");
		menuBar.add(menu);
		
		i1 = new JMenuItem("Nouvelle partie");
		i1.addActionListener(this);
		i2 = new JMenuItem("Quitter");
		i2.addActionListener(this);
		
		menu.add(i1);
		menu.add(i2);
				
		
		topBar = new JPanel();
		add(topBar, BorderLayout.NORTH);
		topBar.setBackground(Color.YELLOW);
		joueur = new JLabel("Joueur Jaune");
		topBar.add(joueur);
		
		board = new Board();
		add(board);
		
		bouton = new JPanel();
		bouton.setLayout(new FlowLayout(0,25,5));
		add(bouton, BorderLayout.SOUTH);
		
		tabBouton = new JButton[NB_BOUTON];
		
		for (int i=0; i<NB_BOUTON; i++) {
			tabBouton[i] = new JButton(Integer.toString(i+1));
			tabBouton[i].addActionListener(this);
			bouton.add(tabBouton[i]);
		}
	}
	
	private void reset() {
		board.reset();
		joueur.setText("Joueur Jaune");
		topBar.setBackground(Color.YELLOW);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ( e.getSource() == i1 ) {
			board.reset();
			joueur.setText("Joueur Jaune");
		}
		
		else if ( e.getSource() == i2 ) System.exit(0);
		
		else {
			for (int i=0; i<NB_BOUTON; i++)
				if (e.getSource() == tabBouton[i] ) {
					board.getPlateau().ajouterJeton(i);
					char j = board.getPlateau().getJoueur();
					String s="Joueur ";
					if ( j == 'J' ) {
						s += "Jaune";
						topBar.setBackground(Color.YELLOW);
					}
					else {
						s += "Rouge";
						topBar.setBackground(Color.RED);
					}
					joueur.setText(s);
					board.repaint();
				}
			if ( board.getPlateau().aGagne() != 'N' ) {
				String j = "joueur ";
				char c = board.getPlateau().aGagne();
				j += (c == 'J')?"Jaune":"Rouge";
				JOptionPane.showMessageDialog(this, "Le "+ j + " a gagné.");
				reset();
			}
			if ( board.getPlateau().estRempli() ) {
				JOptionPane.showMessageDialog(this, "Match nul");
				reset();
			}
		}
	}

}
