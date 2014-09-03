package src;

//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Plateau {

	// taille du tableau
	private int length;
	// matrice de int
	private int board[][];
	int score;

	/**
	 * constructeur,
	 *
	 * @args taille que devra faire le tableau
	 */
	public Plateau(int X) {
		length = X;
		board = new int[X][X];
		score =0;
	}

	/**
	 * Initialize the board by filling it with 0s
	 */
	public void init() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				board[i][j] = 0;
			}
		}
	}

	/**
	 * creer la matrice d'affichage demande si la partie sera random
	 * ou manuelle boucle de jeu: -ajoute un nouceau nombre -affiche
 	 * le plateau -demande un cou a jouer
	 * le plateau -demande un cou à jouer
 	 */
	public void jouer() {
		CAffichage mAffiche = new CAffichage(length);
		// int i =0;
		int mode = choixMode();
		while (!verifFinPartie()/* && i<20 */) {
			nouveauNombre();
			mAffiche.dessinerMatriceAffichable(board);
			jouercoup(mode);
			// i++;
		}
	}

	/**
	 * methode pour afficher la matrice de int pb pour entiers > 9
	 * méthode pour afficher la matrice de int pb pour entiers > 9
	 * (décalage)
	 */
	public void afficher() {
		for (int i = 0; i < length; i++) {
			System.out.print("|");
			for (int j = 0; j < length; j++) {
				if (board[j][i] != 0)
					System.out.print(board[j][i]);
				else
					System.out.print(" ");
				System.out.print("|");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	/**
	 * ajoute un 2 ou un 4 dans une case random du tableau
	 */
	public void nouveauNombre() {
		if (!verifFinPartie()) {
			// generer un nouveau 2
			// le placer sur une case aleatoire ou il y a un 0
			int nb_cases = length * length;
			Random nouvelEmplacement = new Random();
			nouvelEmplacement.nextInt(nb_cases);
			int place = nouvelEmplacement.nextInt(nb_cases);
			int x = place / length;
			int y = place % length;
			if (board[x][y] == 0)
				board[x][y] = random2ou4();
			else
				nouveauNombre();
		}
	}

	/**
	 * choisit si il fau tplacer un 4 ou un 2 (1/5 -> 4, 4/5 -> 2)
	 *
	 * @return 2 ou 4
	 */
	private int random2ou4() {
		Random proportion = new Random();
		int nb = proportion.nextInt(5);
		if (nb >= 4) {
			return 4;
		} else
			return 2;
	}

	/**
 	 * demande a l'utilisateur si il veut jouer en random ou au
	 * demande à l'utilisateur si il veut jouer en random ou au
 	 * clavier
	 *
	 * @return 1 si random 2 si manuel
	 */
	private int choixMode() {
		System.out.println("\n\tChoisissez le mode:");
		System.out.println("\t1- mode automatique (random)");
		System.out.print("\t2- mode manuel (clavier) \n \t");
		Scanner scan = new Scanner(System.in);

		int choix;
		try {
			choix = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("\n\tce n'est pas un nombre\n ");
			choix = choixMode();
		}
		if (choix == 1 || choix == 2) {
			return choix;
		} else {
			choixMode();
			return 1;
		}
	}

	/**
	 * demande une input, decale tous les nombres, les fusionnent si
 	 * il faut puis redecale pour boucher les trous si besoin
	 * il faut puis redécale pour boucher les trous si besoin
	 *
	 * @param mode
	 */
	private void jouercoup(int mode) {

		int ch = 0;
		System.out.println("\tscore : "+score);
		System.out.print("\tHaut(8) Bas(2) Droit (6) Gauche (4) :  ");
		if (mode == 1)
			ch = randomInput();
		else if (mode == 2)
			ch = demandeInput();

		for (int i = 0; i < length; i++) {
			switch (ch) {
			case 8:
				deplacerColonneHaut(i, 0);
				fusionnerColonneHaut(i, 0);
				deplacerColonneHaut(i, 0);
				break;
			case 2:
				deplacerColonneBas(i, length - 1);
				fusionnerColonneBas(i, length - 1);
				deplacerColonneBas(i, length - 1);
				break;
			case 6:
				deplacerLigneDroite(length - 1, i);
				fusionnerLigneDroite(length - 1, i);
				deplacerLigneDroite(length - 1, i);
				break;
			case 4:
				deplacerLigneGauche(0, i);
				fusionnerLigneGauche(0, i);
				deplacerLigneGauche(0, i);
				break;
			default:
				if(i==1)
					System.out.println("\tveuillez rentrez un des 4 chiffres");
				break;
			}
		}
		// deplacer les cases vers une direction
		// Remplacer les cases qui sont desormais inoccuper par 0
		// fusionner les cases indentique qui se collisionne.
		// Attention au nombre de ligne, eventuelement decouper
		// en "deplacerLigne" et "deplacerColone"
	}

	/**
	 * @return le nombre tape au clavier
	 */
	private int demandeInput() {

		/*
		 * KeyListener listener = new CEvent();
		 * addKeyListener(listener); setFocusable(true);
		 */
		int X;
		Scanner scan = new Scanner(System.in);
		try {
			X = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ce n'est pas un nombre");
			X = demandeInput();
		}
		return X;
	}

	/**
	 * @return un des 4 chiffres (8,2,4,6) au hasard
	 */
	private int randomInput() {
		Random input = new Random();
		int nb = input.nextInt(4) * 2 + 2;
		System.out.println(nb);
		return nb;
	}

	/**
	 * decale tous les nombres d'une ligne y vers la droie on
 	 * commence de la case la plus a droite (x=length-1) si la case
	 * testee est == 0 on passe a la case de gauche sinon si la case
	 * a droite existe et est == 0 alors on echange la case testee
	 * et la case de droite puis on passe a la case de gauche
	 * commence de la case la plus a droite (x=length-1) si la case
	 * testee est == 0 on passe à la case de gauche sinon si la case
	 * à droite existe et est == 0 alors on échange la case testee
	 * et la case de droite puis on passe à la case de gauche
 	 * suivante (methode recursive)
	 *
	 * @args coordonnees de la case a tester
	 */
	private void deplacerLigneDroite(int x, int y) {
		// test si on est toujours dans le tableau
		if (x < 0 || x > length - 1 || y < 0 || y > length - 1)
			return;

		if (board[x][y] == 0)
			deplacerLigneDroite(x - 1, y);
		else if (x + 1 < length && board[x + 1][y] == 0) {
			board[x + 1][y] = board[x][y];
			board[x][y] = 0;
		}
		deplacerLigneDroite(x - 1, y);
	}

	/**
	 * permet de fusionner les cases d'une ligne quand necessaire on
 	 * part de la case la plus a droite de la ligne si la case a
	 * droite existe et est egale a la case testee alors on fusionne
	 * et la case testee devient =0 puis on passe a la case de gauche
	 * part de la case la plus a droite de la ligne si la case à
	 * droite existe et est egale a la case testee alors on fusionne
	 * et la case testee devient =0 puis on passe a la case de gauche
	 * suivante (methode recursive)
	 *
	 * @args coordonnees de la case a tester
	 */
	private void fusionnerLigneDroite(int x, int y) {
		// test si on est toujours dans le tableau
		if (x < 0 || x > length - 1 || y < 0 || y > length - 1
				|| board[x][y] == 0)
			return;

		if (x + 1 < length && board[x + 1][y] == board[x][y]) {
			score+=board[x + 1][y] += board[x][y];
			board[x][y] = 0;
		}
		fusionnerLigneDroite(x - 1, y);
	}

	/**
	 * cf methode deplacerLigneDroite
	 */
	private void deplacerLigneGauche(int x, int y) {
		// test si on est toujours dans le tableau
		if (x < 0 || x > length - 1 || y < 0 || y > length - 1)
			return;

		if (board[x][y] == 0)
			deplacerLigneGauche(x + 1, y);
		else if (x - 1 >= 0 && board[x - 1][y] == 0) {
			board[x - 1][y] = board[x][y];
			board[x][y] = 0;
		}
		deplacerLigneGauche(x + 1, y);
	}

	/**
	 * cf methode fusionnerLigneDroite
	 */
	private void fusionnerLigneGauche(int x, int y) {
		// test si on est toujours dans le tableau
		if (x < 0 || x > length - 1 || y < 0 || y > length - 1
				|| board[x][y] == 0)
			return;

		if (x - 1 >= 0 && board[x - 1][y] == board[x][y]) {
			score+=board[x - 1][y] += board[x][y];
			board[x][y] = 0;
		}
		fusionnerLigneGauche(x + 1, y);
	}

	/**
	 * cf methode deplacerLigneDroite
	 */
	private void deplacerColonneHaut(int x, int y) {
		// test si on est toujours dans le tableau
		if (x < 0 || x > length - 1 || y < 0 || y > length - 1)
			return;

		if (board[x][y] == 0)
			deplacerColonneHaut(x, y + 1);
		else if (y - 1 >= 0 && board[x][y - 1] == 0) {
			board[x][y - 1] = board[x][y];
			board[x][y] = 0;
		}
		deplacerColonneHaut(x, y + 1);
	}

	/**
	 * cf methode fusionnerLigneDroite
	 */
	private void fusionnerColonneHaut(int x, int y) {
		// test si on est toujours dans le tableau
		if (x < 0 || x > length - 1 || y < 0 || y > length - 1
				|| board[x][y] == 0)
			return;

		if (y - 1 >= 0 && board[x][y - 1] == board[x][y]) {
			score+=board[x][y - 1] += board[x][y];
			board[x][y] = 0;
		}
		fusionnerColonneHaut(x, y + 1);
	}

	/**
	 * cf methode deplacerLigneDroite
	 */
	private void deplacerColonneBas(int x, int y) {
		// test si on est toujours dans le tableau
		if (x < 0 || x > length - 1 || y < 0 || y > length - 1)
			return;

		if (board[x][y] == 0)
			deplacerColonneBas(x, y - 1);
		else if (y + 1 < length && board[x][y + 1] == 0) {
			board[x][y + 1] = board[x][y];
			board[x][y] = 0;
		}
		deplacerColonneBas(x, y - 1);
	}

	/**
	 * cf methode fusionnerLigneDroite
	 */
	private void fusionnerColonneBas(int x, int y) {
		// test si on est toujours dans le tableau
		if (x < 0 || x > length - 1 || y < 0 || y > length - 1
				|| board[x][y] == 0)
			return;

		if (y + 1 < length && board[x][y + 1] == board[x][y]) {
			score+=board[x][y + 1] += board[x][y];
			board[x][y] = 0;
		}
		fusionnerColonneBas(x, y - 1);
	}

	/**
	 *  fonction qui parcours la matrice pour
	 * verifier si il n'y a plus de cases de libre et que la tuile
	 * 2048 n'as pas ete atteinte
	 *
	 * @return true si la partie est finie
	 */
	private boolean verifFinPartie() {
		int compteurCasesVides = 0;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (board[i][j] == 0)
					compteurCasesVides++;
				else if (board[i][j] == 2048) {
					System.out.println("\tBravo vous avez gagne!!");
					return true;
				}
			}
		}
		if (compteurCasesVides == 0) {
			System.out.println("\tDesole vous avez perdu!!");
			return true;
		} else
		return false;
	}


}
