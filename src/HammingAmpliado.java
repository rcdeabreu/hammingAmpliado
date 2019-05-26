import java.util.Scanner;
import java.util.*;

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
		private static int [][] G = { {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
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
		private static Scanner sc;
		
		public static int [][] getH(){ return H; }
		public static int [][] getHtr(){ return Htr; }
		public static int [][] getG(){ return G; }

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
		
		public static int[][] traspuesta(int[][] a){
			int[][] tr = new int [a[0].length][a.length];
			for (int x=0; x < a.length; x++) {
				  for (int y=0; y < a[x].length; y++) {
					  tr[y][x] = a[x][y];
				  }
			}
			
			return tr;
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

		public static int [][] getSindrome(int c [][]) { return multiplicarMatrices(c,getHtr()); }

		public static int [][] introduceError(int[][] a, int posError){
			if(posError <0 || posError >15){
					return a;
			}else if(a[0][posError] == 1)
					{
						a[0][posError]=0;
					}else{
						a[0][posError]=1;
					}
					return a;
				}
	
		public static int [][] decodificarPalabra (int [][]pCodificada){
			///cojemos los datos en las posiciones que no son potencia de 2	
			int [][] palabraDecod = new int [1][11];
			int j=0;
			for (int i = 0; i<15 ;i++){
				//i+1 porque empieza en cero
				if (i+1 !=1 && i+1 != 2 && i+1 != 4 && i+1!=8 && i+1 !=16){
				palabraDecod [0][j]=pCodificada [0][i];
					//System.out.println(pCodificada [0][i]);
				j++;
					}
				}
				
				return palabraDecod;
			}
	
		public static void corregirError(int [][] palabraCodficada) {
			//introducir error en la palabra codificada
			sc = new Scanner(System.in);
			
			
			int numErrores = -1;
			while(numErrores < 0 | numErrores > 3  ) {
				System.out.println("Introduce 0 para no producir ningun ERROR.\n"
						+ "Introduce 1,2 o 3 para producir uno, dos o tres ERRORES.\n");
				numErrores = sc.nextInt();
			}
			String errores="";
			int posicionError = -1;
			int [][]  palabraConError = palabraCodficada;
			for(int n=0;n<numErrores;n++) {
				System.out.println("Introduce una posicion conprendida entre 0 y "+(palabraCodficada[0].length)+" para introducir un error.");
				posicionError = sc.nextInt();
				errores+="pos "+posicionError;
				errores+=" ";
				palabraConError = introduceError(palabraConError, posicionError-1);
			}
			
			System.out.println("Se han introducido erroes en: "+errores);


			mostrarMatriz(palabraConError,"Palabra Codificada con Error: ");
		
			//Calculamos el sindrome de la palabra con el error
			int [][] sindrome = getSindrome(palabraConError);
//			mostrarMatriz(sindrome,"Sindrome palabra Codficada con Error: ");
			String sin="";
			for (int i=0; i < sindrome.length; i++) {  
				for (int j=0; j < sindrome[i].length; j++) {
					 sin+=sindrome[i][j];	
				  }
			}
			System.out.println("sindrome:"+sindrome[0][0]+sindrome[0][1]+sindrome[0][2]+sindrome[0][3]+"|"+sindrome[0][4]);
			
			/**comprobamos que existe el error, que tipo de error es y si se puede corregir se corrige
			 *El vector fuente u = 1011, que en el código H(3, 2) se codifica como c = 0110011, 
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
			switch(sin) {
			//Casos en los que se produce un error simple, se corrige
			  case "00000":
				  			System.out.println("No hay errores en la palabra codificada");
			    break;
			  case "00001":
				  			System.out.println("Se ha producido un error simple en la ultima posicion");
				  			if(palabraConError[0][15]==1) {
				  				palabraConError[0][15]=0;
				  			}else {
				  				palabraConError[0][15]=1;
				  			}
			    break;
			  case "00011":
		  			System.out.println("Se ha producido un error simple en la posicion 1");
		  			if(palabraConError[0][0]==1) {
		  				palabraConError[0][0]=0;
		  			}else {
		  				palabraConError[0][0]=1;
		  			}
		  		break;
			  case "00101":
		  			System.out.println("Se ha producido un error simple en la posicion 2");
		  			if(palabraConError[0][1]==1) {
		  				palabraConError[0][1]=0;
		  			}else {
		  				palabraConError[0][1]=1;
		  			}
		  		break;
			  case "00111":
		  			System.out.println("Se ha producido un error simple en la posicion 3");
		  			if(palabraConError[0][2]==1) {
		  				palabraConError[0][2]=0;
		  			}else {
		  				palabraConError[0][2]=1;
		  			}
		  		break;
			  case "01001":
		  			System.out.println("Se ha producido un error simple en la posicion 4");
		  			if(palabraConError[0][3]==1) {
		  				palabraConError[0][3]=0;
		  			}else {
		  				palabraConError[0][3]=1;
		  			}
		  		break;
			  case "01011":
		  			System.out.println("Se ha producido un error simple en la posicion 5");
		  			if(palabraConError[0][4]==1) {
		  				palabraConError[0][4]=0;
		  			}else {
		  				palabraConError[0][4]=1;
		  			}
		  		break;
			  case "01101":
		  			System.out.println("Se ha producido un error simple en la posicion 6");
		  			if(palabraConError[0][5]==1) {
		  				palabraConError[0][5]=0;
		  			}else {
		  				palabraConError[0][5]=1;
		  			}
		  		break;
			  case "01111":
		  			System.out.println("Se ha producido un error simple en la posicion 7");
		  			if(palabraConError[0][6]==1) {
		  				palabraConError[0][6]=0;
		  			}else {
		  				palabraConError[0][6]=1;
		  			}
		  		break;
			  case "10001":
		  			System.out.println("Se ha producido un error simple en la posicion 8");
		  			if(palabraConError[0][7]==1) {
		  				palabraConError[0][7]=0;
		  			}else {
		  				palabraConError[0][7]=1;
		  			}
		  		break;
			  case "10011":
		  			System.out.println("Se ha producido un error simple en la posicion 9");
		  			if(palabraConError[0][8]==1) {
		  				palabraConError[0][8]=0;
		  			}else {
		  				palabraConError[0][8]=1;
		  			}
		  		break;
			  case "10101":
		  			System.out.println("Se ha producido un error simple en la posicion 10");
		  			if(palabraConError[0][9]==1) {
		  				palabraConError[0][9]=0;
		  			}else {
		  				palabraConError[0][9]=1;
		  			}
		  		break;
			  case "10111":
		  			System.out.println("Se ha producido un error simple en la posicion 11");
		  			if(palabraConError[0][10]==1) {
		  				palabraConError[0][10]=0;
		  			}else {
		  				palabraConError[0][10]=1;
		  			}
		  		break;
			  case "11001":
		  			System.out.println("Se ha producido un error simple en la posicion 12");
		  			if(palabraConError[0][11]==1) {
		  				palabraConError[0][11]=0;
		  			}else {
		  				palabraConError[0][11]=1;
		  			}
		  		break;
			  case "11011":
		  			System.out.println("Se ha producido un error simple en la posicion 13");
		  			if(palabraConError[0][12]==1) {
		  				palabraConError[0][12]=0;
		  			}else {
		  				palabraConError[0][12]=1;
		  			}
		  		break;
			  case "11101":
		  			System.out.println("Se ha producido un error simple en la posicion 14");
		  			if(palabraConError[0][13]==1) {
		  				palabraConError[0][13]=0;
		  			}else {
		  				palabraConError[0][13]=1;
		  			}
		  		break;
			  case "11111":
		  			System.out.println("Se ha producido un error simple en la posicion 15");
		  			if(palabraConError[0][14]==1) {
		  				palabraConError[0][14]=0;
		  			}else {
		  				palabraConError[0][14]=1;
		  			}
		  		break;
		  		//Casos en los que se produce al menos un error doble que no se puede solucionar, se informa al susuario
			  case "00010":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "00100":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "00110":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "01000":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "01010":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "01100":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "01110":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "10000":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "10010":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "10100":
					System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "10110":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "11000":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "11010":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "11100":
				  System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  case "11110":
					System.out.println("Se ha producido un error al menos doble que no es posible corregir.");
				break;
			  default:
				  	System.out.println("Se ha producido un error.");
			}
			mostrarMatriz(palabraConError,"Palabra Codificada con Error arreglado: ");
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

			int [][] palabra = {{0,1,0,1,0,0,1,1,1,1,0}};
			mostrarMatriz(palabra,"Vector palabra");
			System.out.println("Palabra=> Filas:"+palabra.length+" Columnas: "+palabra[0].length);
			
			
			//Codificar palabra
			int [][] palabraCodficada = multiplicarMatrices(palabra,getG());
			mostrarMatriz(palabraCodficada,"palabraCodficada: ");
			System.out.println("palabraCodficada=> filas:"+palabraCodficada.length+" Columnas: "+palabraCodficada[0].length);
			
			
			System.out.println("--------------decodificar------------------");
			int [][] palabraDecodificada = decodificarPalabra (palabraCodficada);
			mostrarMatriz(palabraDecodificada,"palabraDecodificada: ");
			
			
			mostrarMatriz(getSindrome(palabraCodficada),"Sindrome palabra Codficada (sin error): ");
			corregirError(palabraCodficada);
			//int [][] palabraDecodificada = decodificarPalabra (palabraCodficada);
			//mostrarMatriz(palabraDecodificada,"palabraDecodificada: ");
			
			
			
			
			
			
		}

	}