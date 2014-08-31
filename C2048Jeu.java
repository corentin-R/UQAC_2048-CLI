//package LabPOO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class C2048Jeu {

	public static void main(String[] args) {

		System.out.println("\t Choisissez la taille du Plateau\n");
		int X = 0;
		X = demandeTaillePlateau();

		Plateau P = new Plateau(X);
		P.init();
		P.jouer();
	}

	/**
	 * demande � l'utilistateur un nombre entier
	 * 
	 * @return un chiffre positif qui sera la taille du tableau
	 */
	private static int demandeTaillePlateau() {
		int X;
		Scanner scan = new Scanner(System.in);
		try {
			X = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ce n'est pas un nombre");
			X = demandeTaillePlateau();
		}
		if (X <= 0) {
			System.out.println("rentrez un nombre positif!!");
			X = demandeTaillePlateau();
		}
		return X;
	}
}
