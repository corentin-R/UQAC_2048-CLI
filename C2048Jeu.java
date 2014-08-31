//package LabPOO;

import java.util.Scanner;

public class C2048Jeu {
	
	
	public static void main(String[] args) {

		System.out.println("\t Choisissez la taille du Plateau\n");
		Scanner scan = new Scanner(System.in);			
		//try catch a implementer pour eviter une exeption
		int X = scan.nextInt();
		Plateau P = new Plateau(X);
		P.init();
		P.jouer();
	}
}
