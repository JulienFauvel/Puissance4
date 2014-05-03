package puissance.pkg4;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Julien
 */
public class Board extends JPanel {
	
	private final Plateau plateau;
	
	public Board() {
		plateau = new Plateau();
	}
	
	public Plateau getPlateau() { return plateau; }
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(0x0000AA));
		g.fillRect(0, 0, 500, 540);
		for (int i=0; i<Plateau.TAILLE; i++) {
			for (int j=0; j<Plateau.TAILLE; j++) {
				if ( plateau.getJeton(i, j) != null ) {
					if ( plateau.getJeton(i, j).getCouleur() == 'J' )
						g.setColor(Color.YELLOW);
					else
						g.setColor(Color.RED);					
				} else {
					g.setColor(Color.WHITE);
				}
				g.fillOval( i*66+20, 425-j*65, 50, 50);
			}
		}
	}

	public void reset() {
		plateau.reset();
		repaint();
	}
}
