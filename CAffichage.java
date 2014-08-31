//package LabPOO;

class CAffichage {

	// matrice de char qu'on affichera
	private char[][] matriceAffichable;
	// taille de la matrice carre de base
	private int tailleMatrice;
	private int hauteurMatriceAffichable;
	private int largeurMatriceAffichable;
	private int hauteurCase = 6;
	private int largeurCase = 10;

	/**
	 * contructeur par default sans arguments
	 */
	public CAffichage() {
		tailleMatrice = 4;
		hauteurMatriceAffichable = hauteurCase * tailleMatrice + 1;
		largeurMatriceAffichable = largeurCase * tailleMatrice + 1;
		matriceAffichable 
		= new char[hauteurMatriceAffichable][largeurMatriceAffichable];
	}

	/**
	 * constructeur avec argument
	 * 
	 * @args taille de la matrice de base
	 */
	public CAffichage(int taille) {
		tailleMatrice = taille;
		hauteurMatriceAffichable = hauteurCase * tailleMatrice + 1;
		largeurMatriceAffichable = largeurCase * tailleMatrice + 1;
		matriceAffichable 
		= new char[hauteurMatriceAffichable][largeurMatriceAffichable];
		initialiserMatrice();
	}

	/**
	 * fonction permettant de transformer la matrice int[][] de base en une
	 * matrice de char[][] plus jolie à afficher
	 *
	 * @args matrice de base (int[][])
	 */
	public void dessinerMatriceAffichable(int[][] matrice) {
		remplirCases(matrice);
		// TODO System.clear(); -->clearscreen à implementer
		clearConsole();
		for (int i = 0; i < hauteurMatriceAffichable; i++) {
			System.out.print("\t");
			for (int j = 0; j < largeurMatriceAffichable; j++)
				System.out.print(matriceAffichable[i][j]);

			System.out.print("\n");
		}
	}

	/**
	 * remplit les ligne appropriees (hauteurMatrice%hauteurCase) 
	 * avec des char '-'
	 */
	private void creerLignesHorizontales() {
		for (int i = 0; i < hauteurMatriceAffichable; i++) {
			for (int j = 0; j < largeurMatriceAffichable; j++) {
				if (i % hauteurCase == 0)
					matriceAffichable[i][j] = '-';
			}
		}
	}

	/**
	 * remplit les colonnes appropriees (largeurMatrice%largeurCase)
	 * avec des char '|'
	 */
	private void creerLignesVerticales() {
		for (int i = 0; i < hauteurMatriceAffichable; i++) {
			for (int j = 0; j < largeurMatriceAffichable; j++) {
				if (j % largeurCase == 0)
					matriceAffichable[i][j] = '|';
			}
		}
	}

	/**
	 * remplit la matrice de char avec des ' ' puis met en place les
	 * separateurs de lignes et de colonnes
	 */
	private void initialiserMatrice() {
		initialiserMatriceVide();
		creerLignesVerticales();
		creerLignesHorizontales();
	}

	/**
	 * remplit la matrice de char avec des ' '
	 */
	private void initialiserMatriceVide() {
		for (int i = 0; i < hauteurMatriceAffichable; i++) {
			for (int j = 0; j < largeurMatriceAffichable; j++)
				matriceAffichable[i][j] = ' ';
		}
	}

	/**
	 * met les chiifres de la matrice de base dans la matrice de char qui sera
	 * affichee
	 *
	 * @args matrice de base
	 */
	private void remplirCases(int[][] matrice) {
		for (int i = 0; i < tailleMatrice; i++) {
			for (int j = 0; j < tailleMatrice; j++)
				decomposerNb(matrice[j][i], i, j);
		}
	}

	/**
	 * decompose un nombre en chiffre par unite, dizaine, centaine et millier
	 * et les place au bons endroits transformation d'un int en plusieur char
	 * avce la table ASCII (+48) c'est sale mais ça marche
	 *
	 * @args le nb et ses coordonnees dans la matrice de base
	 */
	private void decomposerNb(int nb, int i, int j) {

		if (nb >= 1000)
			matriceAffichable[i * hauteurCase + 3][j * largeurCase + 3]
					= (char) (nb / 1000 + 48);
		else
			matriceAffichable[i * hauteurCase + 3][j * largeurCase + 3] = ' ';
		if (nb >= 100)
			matriceAffichable[i * hauteurCase + 3][j * largeurCase + 4] 
					= (char) (nb % 1000 / 100 + 48);
		else
			matriceAffichable[i * hauteurCase + 3][j * largeurCase + 4] = ' ';
		if (nb >= 10)
			matriceAffichable[i * hauteurCase + 3][j * largeurCase + 5] 
					= (char) (nb % 1000 % 100 / 10 + 48);
		else
			matriceAffichable[i * hauteurCase + 3][j * largeurCase + 5] = ' ';
		if (nb >= 1)
			matriceAffichable[i * hauteurCase + 3][j * largeurCase + 6]
					= (char) (nb % 1000 % 100 % 10 + 48);
		else
			matriceAffichable[i * hauteurCase + 3][j * largeurCase + 6] = ' ';
	}

	private void clearConsole() {

		/*
		 * try { final String os = System.getProperty("os.name");
		 * System.out.println(os); if (os.contains("Windows")) {
		 * Runtime.getRuntime().exec("cls"); } else {
		 * //Runtime.getRuntime().exec("clear"); System.out.print((char)27 +
		 * "[2J"); //System.out.println("cvbggggggggggggggggggggg"); } } catch
		 * (final Exception e) { // Handle any exceptions. }
		 */
	}

}
