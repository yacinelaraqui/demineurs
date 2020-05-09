import java.util.Scanner;
public class Demineur1main {
	
	public static void main (String[] args) {

	boolean jeu = false;
    System.out.println ();
    System.out.println ("JEU DU DEMINEUR");
    System.out.println ();
   	System.out.println ("     Alors, un petit temps libre? Viens t'amuser avec notre demineur ! Voici les regles du jeu : ");
    System.out.println ();
    System.out.println ("REGLES DU JEU:");
    System.out.println ("     Tu disposes d'une grille contenant des mines cachees. En saisissant les numeros de la ligne et de la colonne d'une case, tu reveles son contenu.");
    System.out.println ("     Si tu devoiles une mine, BOOM ! Tu exploses ! Sinon, le jeu continue et tu decouvres une nouvelle case qui affichera le nombre de mines autour.");
    System.out.println ("     Ta mission, cher demineur, est de detecter toutes les mines sans finir en petits morceaux ! Bonne chance.");
    System.out.println ();
    
    do {
   	int choixNiv;
   	
   	System.out.println ("Commencons par choisir le niveau de difficulte :");
    System.out.println ();

    do {// interet du do-while : faire l'action car il s'agit de la condition (realiser l'action et si elle n'est pas conforme au while, la refaire.
   	System.out.println ("   - Facile (petit champ de bataille 5*5), 5 mines : tape 1");
   	System.out.println ("   - Moyen (gros champ de bataille 10*10), 15 mines : tape 2");
   	System.out.println ("   - Difficile (enooorme champ de bataille 15*15), 40 mines : tape 3");
   	
   	Scanner sc1 = new Scanner(System.in);
   	choixNiv = sc1.nextInt(); 
    System.out.println ();
    
    if ((choixNiv != 1) && (choixNiv != 2) && (choixNiv != 3)){
			System.out.println("Petit malin...Si tu veux vraiment jouer, choisis un numero valide");
			System.out.println();
		}

    } while ((choixNiv != 1) && (choixNiv != 2) && (choixNiv != 3)); //Redemande le niveau s'il ne correspond à aucun des trois niveaux

    
   	int mines = 0; 
   	int taille = 0;
   	
   	switch (choixNiv){ // le nombre de mines varie selon la difficulté
		case 1 :
		mines = 5;
		taille = 5;
		break;
		case 2 :
		mines = 15; 
		taille = 10;  
		break;
		case 3 :
		mines = 40;  
		taille = 15; 
	}
		
   
    Demineur1 champ = new Demineur1 (taille, mines); 
    
    boolean [][]s = champ.getstock (); 
	
    champ.AffectationNombreMines ();
   
    int [][] plateau = champ.getplateau ();
    int ligne;
    int colonne;
    
    boolean fin = champ.Jeufini ();
	
	do {
		champ.afficherJeu (s, plateau);
		
		Scanner sc = new Scanner (System.in);
			
			//RAJOUTER UN NOMBRE DE TOUUUR
            System.out.println("Choisis le numero de la colonne"); //saisie de la colonne de jeu
            colonne = sc.nextInt();
            while ((colonne < 0) || (colonne >=taille)){
            System.out.println("Non non non, choisis un nombre entre 0 et "+(taille-1));
            colonne = sc.nextInt(); 
		}
            
            
		
            System.out.println("Saisis maintenant le numero de ligne "); // saisie de la ligne de jeu
            ligne = sc.nextInt();
            while ((ligne < 0) || (ligne >=taille)){
            System.out.println("Non non non, choisis un nombre entre 0 et "+(taille-1));
            ligne = sc.nextInt();
            System.out.println (); 
		}
		champ.Jouer(s, ligne, colonne,plateau);
        fin = champ.Jeufini ();// le mettre apres chaque champ.jouer pour voir si le jeu est terminé.
        System.out.println ();
       
	} while ((plateau[ligne][colonne]!=-1)&&(fin==false));
	
	 
	  //affichage de tout le plateau quand le joueur tombe sur une mine ou qu'il a gagné
		
			System.out.print("   ");
	for(int c = 0; c<plateau.length; c++){  //première ligne avec indication des colonnes
		if(c<10){
			System.out.print(" "+c+"|");
		}else{
	System.out.print(c+"|");
		}
	}
	System.out.println();
    	for (int i = 0; i<plateau.length; i++){
        	if(i<10){  //première colonne avec indication des lignes
				System.out.print(" "+i+"|");
			}else{
				System.out.print(i+"|");
			}
        	for(int j = 0; j<plateau.length; j++){
            	if(plateau[i][j]!=-1){
					System.out.print(" "+plateau[i][j]+" ");
				} else {
					System.out.print(" * ");
				}
        	}
        	System.out.println();
    	}
    	
    	if (fin==true){ //si toutes les cases sont true sauf les cases contenant des mines = jeu gagné
        	System.out.println("Mission accomplie demineur! Toutes les mines ont ete localisees. Bien joue ! ");
    	}else{ //sinon c’est que le jeu est perdu
        	System.out.println("BOOOOOOOOM !! Tu as fini en petits morceaux  ");
    	}
    	
    	 System.out.println ();
    	 
    	 // demander si le joueur veut rejouer
    	 
    	 Scanner sc2 = new Scanner (System.in);
    	 System.out.println("Une revanche?  Alors, tape 1, sinon, tape autre chose");
			
			
				int rejouer=sc2.nextInt();
				System.out.println ();

						if (rejouer==1){
							jeu=false;
						
						}else if (rejouer != 1){
							jeu=true;
						
								System.out.println("Reviens vite nous voir, le monde a besoin de demineurs comme toi ! ");
						}
    	
	}while (jeu==false);
	
	

}
}
