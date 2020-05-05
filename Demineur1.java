public class Demineur1{
	
	private int nbmines ;
	private int taille;    // private permet de bloquer les variables 
  	private int [][] monde;
	private boolean [][] stock; 
	
	
	
	public Demineur1 (int taille, int nbmines){
    	this.nbmines=nbmines;
    	this.taille=taille;
    	monde= new int [taille][taille];
    	PlaceMines(); //nous plaçons les mines aléatoirement dans le tableau
    	InitBoolean(); // nous créons un tableau de boolean qui stocke les cases déjà jouées
	}
		
		
	public void InitBoolean (){	
        this.stock = new boolean [this.taille][this.taille];
		for (int i=0; i<monde.length; i++){
			for (int j=0; j<monde.length; j++){
				this.stock[i][j]=false; //on associe toutes les cases à des false car elles ne sont pas jouées au départ, y compris les mines 
			}
		}
	}
		
    	
	
	
	
	public void PlaceMines(){
    	
    	for (int i=0;i<this.nbmines; i++){
			int j;
        	int k;
			do {
				j= (int)(Math.random()*this.taille); //nous prennons une ligne au hasard
				k= (int)(Math.random()*this.taille); //nous prenons une colonne au hasard
			} while ((this.monde[j][k]==-1)||(j==0)&&(k==0)); //on change de case s'il y a déjà une mine ou si c'est la case (0,0) car elle n'est pas affecté
			this.monde[j][k]=-1; 
    	}
	} 

	public int CompteMines (int l, int c){ // permet de compter le nombre de mines autour d'une case
        int voisins = 0;
			for (int i=(l-1); i<=(l+1);i++){
				for (int j=(c-1); j<=(c+1);j++){
					if ((i>=0) && (i<monde.length) && (j>=0) && (j<monde.length) && (this.monde[i][j]==-1)){ 
						voisins++;
					}
				}
			}
    return voisins;
}
	public void AffectationNombreMines (){ // Affecte à chaque case le nombre total de mines qui se trouvent autour 
		for (int i=0; i<monde.length;i++){
			for (int j=0; j<monde.length; j++){
				if (monde[i][j]!=-1){
				this.monde [i][j]=CompteMines(i,j);
				}
			}
		} 		
	}




	public boolean[][] getstock(){
    	return stock;
	}
	
	public int[][] getplateau(){
    	return monde;
	}

	
	public void afficherJeu (boolean [][] stock, int[][]monde){ //nous affichons l'état du jeu, c'est-à-dire, le tableau monde
		System.out.print("   ");
	
		for(int i = 0; i<monde.length; i++){ //afficher une ligne avec le numéro correspondant à chaque colonne
			if(i<10){
				System.out.print(" "+i+"|"); // il faut un espace en plus si c’est un numéro à 1 chiffre pour la présentation
			}else{
				System.out.print(i+"|");
			}
		}
		System.out.println();
		
		for (int j = 0; j<monde.length; j++){
			if(j<10){
				System.out.print(" "+j+"|");//afficher le numéro de chaque ligne au début de celle-ci
			}else {
				System.out.print(j+"|");
			}
			for(int k = 0; k<monde.length; k++){

				if ((stock[j][k] == true)&&(monde[j][k]!=-1)){
					System.out.print(" "+monde[j][k]+" "); //afficher la valeur dans la case que le joueur a décidé de dévoiler
				}
				else
					System.out.print(" _ ");
			}
		System.out.println();
		}
	}
	
	public void Jouer (boolean [][]stock, int a, int b, int[][]monde) { 
    	stock[a][b] = true;//met à jour le tableau de boolean en fonction des cases déjà jouées
          
        //si la case choisie est zéro, les cases autour contenant des zéros jusqu’aux premiers chiffres rencontrés sont mises à jour comme des coups joués = elles s’affichent aussi 
    	if(monde[a][b]==0){
        	if(b+1<monde.length){
				if(stock[a][b+1] == false)
					Jouer(stock,a,b+1,monde);
				}
        	if(b-1>=0){
				if(stock[a][b-1] == false)                	
                    Jouer(stock,a,b-1,monde);
				} 
        	if(a+1<monde.length){
				if(stock[a+1][b] == false)                	
                    Jouer(stock,a+1,b,monde);
				}
        	if(a-1>=0){
				if(stock[a-1][b] == false)            	
                    Jouer (stock,a-1,b,monde);
			}
        	if((a+1<monde.length)&&(b+1<monde.length)){
				if(stock[a+1][b+1] == false)                	
                    Jouer(stock,a+1,b+1,monde);
			}
        	if((a-1>=0)&&(b-1>=0)){
				if(stock[a-1][b-1] == false)                	
                    Jouer(stock,a-1,b-1,monde);
				}
        	if((a-1>=0)&&(b+1<monde.length)){
				if(stock[a-1][b+1] == false)                	
                    Jouer(stock,a-1,b+1,monde);
				}	
        	if((a+1<monde.length)&&(b-1>=0)){
				if(stock[a+1][b-1] == false)              	
					Jouer(stock,a+1,b-1,monde);
			}
    	}
	}

	public boolean Jeufini (){
    	boolean fini = true;
    	for (int i=1; i<stock.length; i++){
        	for(int j=1; j<stock[i].length; j++){
            	if ((stock[i][j] == false) && (monde [i][j]!= -1)){//s'il reste encore des cases non retournées qui ne cachent pas des mines, le jeu n’est pas fini
                	fini = false; 
                	break;
            	}
        	}
    	}
    	return fini;
	}
}
	
