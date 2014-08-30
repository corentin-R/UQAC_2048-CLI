//package LabPOO;



public class C2048Jeu {
	
	final static int X = 4 ;
	
	
	public static void main(String[] args) {
		Plateau P = new Plateau(X);
		P.init();
		P.jouer();
	}

}
