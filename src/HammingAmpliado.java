//HammingAmpliado
public class HammingAmpliado {
	//codigo Hamming Ampliado. Ejemplo 3.1.4 apuntes Teoría de Códigos ESEI
	//Para m=4, H(4,2)  codigo (15,11)
	//Creación de las matrices H*(H) H*tr(Htr) y G*(G)
	private static int [][] H = {   {0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0},
									{0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0},
									{0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0},
									{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
									{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
								};
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
//esta mal la multiplicacion, al ser en binario no se si tiene en cuenta el acarreo o no, creo que no
	public static int[][] multiplicarMatriz(int matrizA[][], int matrizB[][]){
	    if(matrizA[0].length!=matrizB.length){
	         throw new RuntimeException("Dimensiones Incompatibles");
	    }else{
	         int [][] matrizC = new int[matrizA.length][matrizB[0].length];
	         for(int i = 0;i < matrizA.length;i++ ){
	           for(int j = 0;j < matrizB[0].length;j++){
	             for(int k = 0;k <matrizA[0].length;k++) {
	            	 if(matrizC[i][j] == 1 & matrizA[i][k] == 1) {
	            		 matrizC[i][j] = 0 * matrizB[k][j];
	            	 }
	            	 if(matrizC[i][j] == 0 & matrizA[i][k] == 1) {
	            		 matrizC[i][j] = 1 * matrizB[k][j];
	            	 }
	            	 if(matrizC[i][j] == 1 & matrizA[i][k] == 0) {
	            		 matrizC[i][j] = 1 * matrizB[k][j];
	            	 }
	            	 if(matrizC[i][j] == 0 & matrizA[i][k] == 0) {
	            		 matrizC[i][j] = 0 * matrizB[k][j];
	            	 }
	             }
	           }
	         }
	      return matrizC;
	    } 
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
		return multiplicarMatriz(c,getHtr());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mostrarMatriz(getH(),"Matriz H*");
		mostrarMatriz(getHtr(),"Matriz Htr*");
		mostrarMatriz(getG(),"Matriz G*");
		int [][] m = {{0},
					  {0},
					  {0},
					  {0},
					  {0},
					  {0},
					  {1},
					  {0},
					  {0},
					  {0},
					  {0}
					 };
		mostrarMatriz(m,"Vector m");
		System.out.println("filas:"+m[0].length+" Columnas: "+m.length);
		int [][] c = multiplicarMatriz(getG(),m);
		mostrarMatriz(c,"vector * c");
		System.out.println("filas:"+c[0].length+" Columnas: "+c.length);
		mostrarMatriz(getSindrome(c),"Sindrome");
		int [][] palabra = multiplicarMatriz(getG(),c);
		mostrarMatriz(palabra,"palaba: ");
	}

}
