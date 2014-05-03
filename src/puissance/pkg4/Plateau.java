package puissance.pkg4;

/**
 *
 * @author Julien
 */
public class Plateau {

	public static final int TAILLE = 7;
	
	private Jeton[][] tabJeton;
	private char joueur;	
	private boolean estRempli;
	
	public Plateau() {
		tabJeton = new Jeton[TAILLE][TAILLE];
		joueur = 'J';
		estRempli = false;
	}
	
	public void refreshRempli() {
		this.estRempli = true;
		for (int i=0; i<TAILLE; i++)
			for (int j=0; j<TAILLE; j++)
				if (tabJeton[i][j] == null)
					estRempli = false;
	}
	
	public Jeton getJeton(int col, int lig) {
		return tabJeton[col][lig];
	}
	
	public boolean ajouterJeton(int i) {
		if (tabJeton[i][TAILLE-1] != null) return false;
		
		int j = 0;
		while( tabJeton[i][j] != null ) j++;
		tabJeton[i][j] = new Jeton( joueur );
		changerJoueur();
		refreshRempli();
		return true;
	}
	
	private void changerJoueur(){
		joueur = (joueur=='J')?'R':'J';
	}
	
	public char aGagne() {
		char c = 'N';
		int nbJeton = 0;
		for (int i=0; i<TAILLE; i++) {
			for (int j=0; j<TAILLE; j++) {
								
				for (int k=-3;k<4;k++) {
					if ( k==0 ) k++;
					try {
						if (tabJeton[i][j].getCouleur() == tabJeton[i+k][j].getCouleur() )
								nbJeton++;
						else 
							nbJeton=0;
					} catch ( Exception e) {
						nbJeton=0;
					}
					if ( nbJeton >= 3)
						c = tabJeton[i][j].getCouleur();
				}

				nbJeton = 0;
				for (int k=-3;k<4;k++) {
					if ( k==0 ) k++;
					try {
						if (tabJeton[i][j].getCouleur() == tabJeton[i][j+k].getCouleur() )
							nbJeton++;
						else nbJeton=0;
					} catch ( Exception e) {
						nbJeton=0;
					}
					if ( nbJeton >= 3)
						c = tabJeton[i][j].getCouleur();
				}

				nbJeton = 0;
				for (int k=-3;k<4;k++) {
					if ( k==0 ) k++;
					try {
						if (tabJeton[i][j].getCouleur() == tabJeton[i+k][j+k].getCouleur() )
							nbJeton++;
						else nbJeton=0;
					} catch ( Exception e) {
						nbJeton=0;
					}
					if ( nbJeton >= 3)
						c = tabJeton[i][j].getCouleur();
				}

				nbJeton = 0;
				for (int k=-3;k<4;k++) {
					if ( k==0 ) k++;
					try {
						if (tabJeton[i][j].getCouleur() == tabJeton[i+k][j-k].getCouleur() )
							nbJeton++;
						else nbJeton=0;
					} catch ( Exception e) {
						nbJeton = 0;
					}
					if ( nbJeton >= 3)
						c = tabJeton[i][j].getCouleur();
				}
				
			}
		}		
		return c;
	}
	
	public boolean estRempli() { return estRempli; }

	public char getJoueur() { return joueur; }

	void reset() { 
		tabJeton = new Jeton[TAILLE][TAILLE];
		joueur = 'J';
	}

		
}
