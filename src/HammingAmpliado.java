
public class HammingAmpliado {
	//codigo Hamming Ampliado. Ejemplo 3.1.4 apuntes Teoría de Códigos ESEI
	//Creación de las matrices H*(H) H*tr(Htr) y G*(G)
	private static int [][] H = {	{0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0},
					{0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0},
					{0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0},
					{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
				 };
	private static int [][] Htr = {{0,0,0,1,1},
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
	private static int [][] G = {{0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1},
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
	public static int [][] getH(){
		return H;
	}
	
	public static int [][] getHtr(){
		return Htr;
	}
	public static int [][] getG(){
		return G;
	}
	public static void mostrarH(){
		for (int x=0; x < getH().length; x++) {
			System.out.printf("|");  
			for (int y=0; y < getH()[x].length; y++) {
				  System.out.printf(" %d ",getH()[x][y]);	
			  }
			System.out.printf("|");
			System.out.println();	
		}
	}
	public static void mostrarHtr(){
		for (int x=0; x < getHtr().length; x++) {
			System.out.printf("|");  
			for (int y=0; y < getHtr()[x].length; y++) {
				  System.out.printf(" %d ",getHtr()[x][y]);	
			  }
			System.out.printf("|");
			System.out.println();	
		}
	}
	
	public static void mostrarG(){
		for (int x=0; x < getG().length; x++) {
			System.out.printf("|");  
			for (int y=0; y < getG()[x].length; y++) {
				  System.out.printf(" %d ",getG()[x][y]);	
			  }
			System.out.printf("|");
			System.out.println();	
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mostrarH();
		System.out.println("-----------------------------------------------------------------");
		mostrarHtr();
		System.out.println("-----------------------------------------------------------------");
		mostrarG();
	}

}
