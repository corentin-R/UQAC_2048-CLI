//package LabPOO;

import java.util.Random;
import java.util.Scanner;

public class Plateau {
	
	private int length;
	private int board [][];
	
	public Plateau(int X)
	{
		length=X;
		board = new int [X][X];
	}
	
	/*Initialize the board by filling it with 0s*/
	public void init()
	{
		for (int i =0;i<length;i++)
		{
			for (int j=0;j<length;j++)
			{
				board[i][j]=0;
			}
		}
	}
	public void jouer()
	{
		CAffichage mAffiche = new CAffichage(length);
		while(!verifDefaite())
		{
			nouveauNombre();	
			mAffiche.dessinerMatriceAffichable(board);
			jouercoup();
		}
	}
	
	
	public void afficher()
	{
		for (int i =0;i<length;i++)
		{
			System.out.print("|");
			for (int j=0;j<length;j++)
			{
				if(board[j][i]!=0) System.out.print(board[j][i]);
				else System.out.print(" ");
				System.out.print("|");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	public void nouveauNombre()
	{
		if(!verifDefaite()){
			//generer un nouveau 2
			//le placer sur une case aleatoire ou il y a un 0
			int nb_cases=length*length;
			Random nouvelEmplacement = new Random();
			nouvelEmplacement.nextInt(nb_cases);
			int place = nouvelEmplacement.nextInt(nb_cases);
			int x=place/length;
			int y=place%length;
			if(board[x][y] == 0) 
				board[x][y]=2;
			else	
				nouveauNombre();
		}
	}
	
	private void jouercoup()
	{

			/*deplacerLigneDroite(length-1,0);
			deplacerLigneDroite(length-1,1);
			deplacerLigneDroite(length-1,2);
			deplacerLigneDroite(length-1,3);**/
			System.out.println("\tHaut(8) Bas(2) Droit (6) Gauche (4) :");
			
			switch (randomInput())
			{
				case 8:
				for(int x=0;x<length;x++)deplacerColonneHaut(x,0);		
					break;
				case 2:
				for(int x=0;x<length;x++)deplacerColonneBas(x, length-1);		
					break;
				case 6:
				for(int y=0;y<length;y++)deplacerLigneDroite(length-1,y);
					break;
				case 4:
				for(int y=0;y<length;y++)deplacerLigneGauche(0,y);
					break;
				default:
				System.out.println("\tveuillez rentrez un des 4 chiffres");
				break;
			}

		//deplacer les cases vers une direction
		//Remplacer les cases qui sont desormais inoccuper par 0
		//fusionner les cases indentique qui se collisionne.
		//Attention au nombre de ligne, eventuelement decouper 
		//en "deplacerLigne" et "deplacerColone"
		}

		private int demandeInput(){
			Scanner scan = new Scanner(System.in);			
			//try catch a implementer pour eviter une exeption
			return scan.nextInt();
		}

		private int randomInput(){
			Random input = new Random();
			int nb = input.nextInt(4)*2 +2;
			System.out.println(nb);
			return nb;
		}

		private void deplacerLigneDroite(int x,int y)
		{
		//test si on est toujours dans le tableau
			if (x<1 || x>length-1 || y<0 || y>length-1) return;
		//Si 0 plus a gauche decaller le nombre
			boolean IsTrue=false;
			int i=1;
			while(!IsTrue)
			{
				//Si la case teste a gauche est a 0
				if(board[x-i][y]==0)
				{
					if(x-i>0)i++;
					else IsTrue=true;
				}
				else
				{	
					//si les 2 cases sont egales
					if(board[x-i][y]==board[x][y])
					{
						board[x][y]+=board[x-i][y];
						board[x-i][y]=0;
					}
					else
					{
						//si la case a droite =0
						if(board[x][y]==0)
						{
							board[x][y]=board[x-i][y];
							board[x-i][y]=0;
						}
						else if(i!=1)
						{		
							board[x-1][y]=board[x-i][y];
							board[x-i][y]=0;
						}
					}	
					IsTrue=true;
				}				
			}
			if(x>=0)deplacerLigneDroite(x-1,y);	
		}



		private void deplacerLigneGauche(int x,int y)
		{
		//test si on est toujours dans le tableau
			if (x<0|| x>length-2 || y<0 || y>length-1) return;
		//Si 0 plus a gauche decaller le nombre
			boolean IsTrue=false;
			int i=1;
			while(!IsTrue)
			{
				//Si la case teste a gauche est a 0
				if(board[x+i][y]==0)
				{
					if(x+i<length-1)i++;
					else IsTrue=true;
				}
				else
				{	
					//si les 2 cases sont egales
					if(board[x+i][y]==board[x][y])
					{
						board[x][y]+=board[x+i][y];
						board[x+i][y]=0;
					}
					else
					{
						//si la case a gauche =0
						if(board[x][y]==0)
						{
							board[x][y]=board[x+i][y];
							board[x+i][y]=0;
						}
						else if(i!=1)
						{		
							board[x+1][y]=board[x+i][y];
							board[x+i][y]=0;
						}
					}	
					IsTrue=true;
				}				
			}
			if(x<length-2)deplacerLigneGauche(x+1,y);	
		}
		private void deplacerColonneHaut(int x,int y)
		{
			//test si on est toujours dans le tableau
			if (x<0 || x>length-1 || y<0 || y>length-2) return;

			//Si 0 plus a gauche decaller le nombre
			boolean IsTrue=false;
			int i=1;
			while(!IsTrue)
			{
				//Si la case teste en bas est a 0
				if(board[x][y+i]==0)
				{
					if(y+i<length-1)i++;
					else IsTrue=true;
				}
				else
				{	
					//si les 2 cases sont egales
					if(board[x][y+i]==board[x][y])
					{
						board[x][y]+=board[x][y+i];
						board[x][y+i]=0;						
					}
					else
					{
						//si la case en haut =0
						if(board[x][y]==0)
						{
							board[x][y]=board[x][y+i];
							board[x][y+i]=0;
						}
						else if(i!=1)
						{							
							board[x][y+1]=board[x][y+i];
							board[x][y+i]=0;
						}
					}
					IsTrue=true;
					
				}					
			}			
			
			if(y<=length-1)deplacerColonneHaut(x,y+1);
		}

		private void deplacerColonneBas(int x,int y)
		{
		//test si on est toujours dans le tableau
			if (x<0 || x>length-1 || y<1 || y>length-1) return;

		//Si 0 plus a gauche decaller le nombre
			boolean IsTrue=false;
			int i=1;
			while(!IsTrue)
			{
				//Si la case teste en haut est a 0
				if(board[x][y-i]==0)
				{
					if(y-i>0)i++;
					else IsTrue=true;
				}
				else
				{	
					//si les 2 cases sont egales
					if(board[x][y-i]==board[x][y])
					{
						board[x][y]+=board[x][y-i];
						board[x][y-i]=0;						
					}
					else
					{
						//si la case en bas =0
						if(board[x][y]==0)
						{
							board[x][y]=board[x][y-i];
							board[x][y-i]=0;
						}
						else if(i!=1)
						{							
							board[x][y-1]=board[x][y-i];
							board[x][y-i]=0;
						}
					}
					IsTrue=true;
					
				}					
			}			
			
			if(y>0)deplacerColonneBas(x,y-1);
		}

	/**
	 * verifDefaite(): fonction qui parcours la matrice pour 
	 * verifier si il n'y a plus de cases de libre et que
	 *  la tuile 2048 n'as pas ete atteinte
	 * @return true si la partie est finie
	 */
	private boolean verifDefaite(){
		int compteurCasesVides = 0;
		for(int i=0;i<length; i++)
		{
			for(int j=0;j<length; j++)
			{
				if(board[i][j] == 0) compteurCasesVides++;
				else if(board[i][j] == 2048)
				{
					System.out.println("\tBravo vous avez gagne!!");
					return true;
				}
			}
		}
		if(compteurCasesVides==0)
		{
			System.out.println("\tDesole vous avez perdu!!");
			return true;
		}
		else return false;
	}

}



