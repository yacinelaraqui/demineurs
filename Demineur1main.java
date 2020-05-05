import java.util.Scanner;
public class Demineur1main {
	
	public static void main (String[] args) {

	boolean jeu = false;
    System.out.println ("DEMINEUR");
    System.out.println ();
   	System.out.println ("REGLES DU JEU:");
    System.out.println ();
    System.out.println ("Vous disposez d'une grille contenant des mines cachees. En selectionnant une case, vous decouvrez la case.");
    System.out.println ("Si vous devoilez une bombe, vous avez perdu. Sinon vous decouvrez une case qui affichera le nombre de bombes autour.");
    System.out.println ("Le but du jeu est de detecter toutes les mines sans tomber sur l'une d'entre elles ! ");
    System.out.println ();
    
    do {
   	System.out.println ("Commencez par choisir le niveau de difficulte :");
    
    System.out.println ();

    int taille;
    
    do {
    System.out.println ("Veuillez rentrer un des trois niveaux de difficulte :");
   	System.out.println ("Facile (tableau 5*5), 5 mines : tapez 5");
   	System.out.println ("Moyen (tableau 10*10), 15 mines : tapez 10");
   	System.out.println ("Difficile (tableau 15*15), 40 mines : tapez 15");
   	
   	Scanner sc1 = new Scanner(System.in);
   	taille = sc1.nextInt(); 
    System.out.println ();

    } while ((taille != 5) && (taille != 10) && (taille!= 15)); //Redemande le niveau s'il ne correspond à aucun des trois niveaux
    
   	int mines = 0; 
   	switch (taille){ // le nombre de mines varie selon la difficulté
		case 5 :
		mines=5;  
		break;
		case 10 :
		mines=15;   
		break;
		case 15 :
		mines=40;   
	}
	
	
   
    Demineur1 p = new Demineur1 (taille, mines); 
    
    boolean [][]s = p.getstock (); 
	
    p.AffectationNombreMines ();
   
    int [][] plateau = p.getplateau ();
    int ligne;
    int colonne;
    
    boolean fin = p.Jeufini ();
	
	do {
		p.afficherJeu (s, plateau);
		
		Scanner sc = new Scanner (System.in);
			
			
            System.out.println("Choisissez la colonne"); //saisie de la colonne de jeu
            colonne = sc.nextInt();
            while ( (colonne < 0) || (colonne >=taille)){
            System.out.println("Impossible, faites un nouveau choix de colonne");
            colonne = sc.nextInt(); 
		}
            
            
		
            System.out.println("Choisissez la ligne "); // saisie de la ligne de jeu
            ligne = sc.nextInt();
            while ( (ligne < 0) || (ligne >=taille)){
            System.out.println("Impossible ! Faites un nouveau choix de ligne");
            ligne = sc.nextInt();
            System.out.println (); 
		}
		p.Jouer(s, ligne, colonne,plateau);
        fin = p.Jeufini ();
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
        	System.out.println("Bravo, vous avez gagne !!! :) ");
    	}else{ //sinon c’est que le jeu est perdu
        	System.out.println("Game Over ! :( ");
    	}
    	
    	 System.out.println ();
    	 
    	 // demander si le joueur veut rejouer
    	 
    	 Scanner sc2 = new Scanner (System.in);
    	 System.out.println("Voulez-vous recommencer ?(oui=1, non=2)");
			
			
				int rejouer=sc2.nextInt();
				System.out.println ();

						if (rejouer==1){
							jeu=false;
						
						}else if (rejouer==2){
							jeu=true;
						
								System.out.println("Merci d'avoir joue au Demineur ! A bientot !");
						}else if (rejouer!=1 && rejouer!=2){
							System.out.println("Veuillez saisir 1 ou 2 svp");
							rejouer=sc2.nextInt();
						}
    	
	}while (jeu==false);
	
	

}
}
