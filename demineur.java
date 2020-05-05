public class demineur{
	//attributs
	private int nbmines ;
	private int taille ;    // private permet de bloquer les variables 
  	private int [][] monde;
	private boolean [][] stock ; 
	
	//constructeurs
	public demineur (int taille, int nbmines){
    	this.nbmines = nbmines;
    	this.taille = taille;
    	monde = new int [taille][taille];
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
		
	public int CompteMinesVoisines (int l, int c){ // permet de compter le nombre de mines autour d'une case
        int voisins = 0;
            //parcours les cases autour de la cellule
			for (int i=(l-1); i<=(l+1);i++){
				for (int j=(c-1); j<=(c+1);j++){
					//tenir compte des bords
					if ((i>=0) && (i<monde.length) && (j>=0) && (j<monde[0].length) && (this.monde[i][j]== -1)){ 
						voisins++;
					}
				}
			}
			if (monde[l][c]== -1) { //post-corrections
				voisins--;
			}	
        return voisins;
    }
    
    public void AffecteNombreMines (){ // Affecte à chaque case le nombre total de mines qui se trouvent autour 
		for (int i=0; i<monde.length;i++){
			for (int j=0; j<monde.length; j++){
				if (monde[i][j]!=-1){
				this.monde [i][j] = CompteMinesVoisines(i,j);
				}
			}
		} 		
	}
	
	//getters && setters
	public double getnbmines(){
		return this.nbmines;
	}
	
	public void setnbmines(int nbmines) {
		this.nbmines= nbmines;
	}
	public double gettaille(){
		return this.taille;
	}
	
	public void settaille(int nbmines) {
		this.taille= taille;
	}	
	
    public boolean[][] getstock(){
    	return stock;
	}
	   
	public int[][] getmonde(){
    	return monde;
	}
	
	

   
   
}	
