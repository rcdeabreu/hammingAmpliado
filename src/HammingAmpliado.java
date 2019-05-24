//HammingAmpliado
public class HammingAmpliado {
	//codigo Hamming Ampliado. Ejemplo 3.1.4 apuntes Teoría de Códigos ESEI
	//Para m=4, H(4,2)  codigo (15,11)
	//Creación de las matrices H*(H) H*tr(Htr) y G*(G)
	//System.out.println("Matriz=> Filas:"+Matriz.length+" Columnas: "+ Matriz[0].length);
	//H 5 Filas x 16 Columnas
	private static int [][] H = {   {0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0},
									{0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0},
									{0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0},
									{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
									{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
								};
	//Htr 16 Filas x 5 Columnas
	private static int [][] Htr = { {0,0,0,1,1},
									{0,0,1,0,1},
									{0,0,1,1,1},
									{0,1,0,0,1},
									{0,1,0,1,1},
									{0,1,1,0,1},
									{0,1,1,1,1},
									{1,0,0,0,1},
									{1,0,0,1,1},
									{1,0,1,0,1},
									{1,0,1,1,1},
									{1,1,0,0,1},
									{1,1,0,1,1},
									{1,1,1,0,1},
									{1,1,1,1,1},
									{0,0,0,0,1},
								  };
	//G 11 Filas x 16 Columnas
	private static int [][] G = { {0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1},
								  {1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1},
								  {0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,1},
								  {1,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0},
								  {1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1},
								  {0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1},
								  {1,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0},
								  {0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1},
								  {1,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0},
								  {0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,0},
								  {1,1,0,1,0,0,0,1,0,0,0,0,0,0,1,1},
								};
	
	public static int [][] getH(){ return H; }
	public static int [][] getHtr(){ return Htr; }
	public static int [][] getG(){ return G; }
	
	//no se usa
	public static void mostrarMatriz(int[][] a) {
		for(int i=0;i<a.length;i++) { 
    		for(int j=0;j<a[0].length;j++) { 
    		System.out.print(a[i][j] + " "); 
    		} 
    		System.out.println(); 
    		} 
	}
	
	public static int[][] calcularIdentidad(int tam){
		int [][] I = new int[tam][tam];
			for(int i=0;i<tam;i++) { 
	    		for(int j=0;j<tam;j++) { 
		    		if(i==j) {I[i][j]=1;}
		    			else {I[i][j]=0;}
	    		}
    		}	
		return I;
    } 
	
	public static int[][] multiplicarMatrices(int[][] a, int[][] b) {
	    int[][] c = new int[a.length][b[0].length];
	    if (a[0].length == b.length) {
	        for (int i = 0; i < a.length; i++) {
	            for (int j = 0; j < b[0].length; j++) {
	                for (int k = 0; k < a[0].length; k++) {
	                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
	                    if(c[i][j]>1){c[i][j]=0;}
	                }
	            }
	        }
	    }
	    
	    return c;
	}
	
	
	public static int[][] sumaMatrices(int[][] a, int[][] b){
		int[][] c = a;
		for (int x=0; x < a.length; x++) {
			  for (int y=0; y < a[x].length; y++) {
				  c[x][y] = a[x][y]+b[x][y];	  
				  if(c[x][y]==2) {c[x][y]=0;}
			  }
		}
		return c;
    } 
	
	
	public static int[][] traspuesta(int[][] a){
		int[][] tr = new int [a[0].length][a.length];
		for (int x=0; x < a.length; x++) {
			  for (int y=0; y < a[x].length; y++) {
				  tr[y][x] = a[x][y];
			  }
		}
		
		return tr;
    } 

	public static int peso(int[][] matriz){
		int weight = 12;
		for (int x=0; x < matriz.length; x++) {
			int w = 0;
			  for (int y=0; y < matriz[x].length; y++) {
				  if (matriz[x][y] == 1) {w++;};
			  }
			  if (w<weight) {weight=w;}
		}
		
		return weight;
    }
	
	public static void mostrarMatriz(int matrizM [][], String mensaje ){
		System.out.println(mensaje+"\n");
		for (int x=0; x < matrizM.length; x++) {
			System.out.printf("|");  
			for (int y=0; y < matrizM[x].length; y++) {
				  System.out.printf(" %d ",matrizM[x][y]);	
			  }
			System.out.printf("|");
			System.out.println("");	
		}
		System.out.println("--------------------------------------------------");
	}

	public static int [][] getSindrome(int c [][]) {
		/**El vector fuente u = 1011, que en el código H(3, 2) se codifica como c = 0110011, 
		 * es codificado ahora como c* = 01100110. 
		 * Si c* es recibido como r* = 01100010, su síndrome es r* (H*)tr = 110|1, 
		 * esto nos indica que se ha producido un error simple en la sexta posición,
		 * correspondiente al valor binario 110. 
		 * Si c* es recibido como r* = 01100111, su síndrome es r* (H*)tr = 000|1, 
		 * esto nos indica que se ha producido un error simple, pero esta vez en la última posición,
		 * ya que el síndrome de c es 000. 
		 * Por último, si c* es recibido como r* = 00100010, al calcular su síndrome obtenemos 
		 * r* (H*)tr = 100|0, esto nos indica que se ha producido un error al menos doble que 
		 * no es posible corregir.
		 */
		return multiplicarMatrices(c,getHtr());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mostrarMatriz(getH(),"Matriz H*");
		System.out.println("H=> Filas:"+getH().length+" Columnas: "+getH()[0].length);
		
		mostrarMatriz(getHtr(),"Matriz Htr*");
		mostrarMatriz(traspuesta(getH()),"traspuesta por funcion");
		
		mostrarMatriz(getG(),"Matriz G*");
		System.out.println("G=> Filas:"+getG().length+" Columnas: "+getG()[0].length);
		//palabra 1 Filas x 11 Columnas
		//		int [][] palabra = {{0},{0},{0},{0},{0},{0},{1},{0},{0},{0},{0}};

		int [][] palabra = {{0,0,0,0,0,0,1,0,0,0,0}};
		mostrarMatriz(palabra,"Vector palabra");
		System.out.println("Palabra=> Filas:"+palabra.length+" Columnas: "+palabra[0].length);
		
		
		//Codificar palabra
		int [][] palabraCodficada = multiplicarMatrices(palabra,getG());
		mostrarMatriz(palabraCodficada,"palabraCodficada: ");
		System.out.println("palabraCodficada=> filas:"+palabraCodficada.length+" Columnas: "+palabraCodficada[0].length);
		
		mostrarMatriz(getSindrome(palabraCodficada),"Sindrome palabraCodficada: ");
		
		//nos falta decodificar No recuerdo como se decodifica
	}

}
