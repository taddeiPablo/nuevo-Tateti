package entidades;

/**
 * 
 * @author Pablo
 *
 */
public class TableroAuxiliar_bluetooth {
	
	private static int DIMENCION = 8;
	private static int[][] MATRIZ = new int[DIMENCION][DIMENCION];
	private static int VALIDAR = 0;
	
	/**
	 * 
	 */
	public static void iniciar_Tablero(){
		MATRIZ[0][0] = 0;
		MATRIZ[0][1] = 1;
		MATRIZ[0][2] = 2;
		MATRIZ[0][3] = 3;
		MATRIZ[0][4] = 4;
		MATRIZ[0][5] = 5;
		MATRIZ[0][6] = 6;
		MATRIZ[0][7] = 7;
 		
		MATRIZ[1][0] = 8;
		MATRIZ[1][1] = 9;
		MATRIZ[1][2] = 10;
		MATRIZ[1][3] = 11;
		MATRIZ[1][4] = 12;
		MATRIZ[1][5] = 13;
		MATRIZ[1][6] = 14;
		MATRIZ[1][7] = 15;
		
		
		MATRIZ[2][0] = 16;
		MATRIZ[2][1] = 17;
		MATRIZ[2][2] = 18;
		MATRIZ[2][3] = 19;
		MATRIZ[2][4] = 20;
		MATRIZ[2][5] = 21;
		MATRIZ[2][6] = 22;
		MATRIZ[2][7] = 23;
		
		MATRIZ[3][0] = 24;
		MATRIZ[3][1] = 25;
		MATRIZ[3][2] = 26;
		MATRIZ[3][3] = 27;
		MATRIZ[3][4] = 28;
		MATRIZ[3][5] = 29;
		MATRIZ[3][6] = 30;
		MATRIZ[3][7] = 31;
		
		MATRIZ[4][0] = 32;
		MATRIZ[4][1] = 33;
		MATRIZ[4][2] = 34;
		MATRIZ[4][3] = 35;
		MATRIZ[4][4] = 36;
		MATRIZ[4][5] = 37;
		MATRIZ[4][6] = 38;		
		MATRIZ[4][7] = 39;
				
				
		MATRIZ[5][0] = 40;
		MATRIZ[5][1] = 41;
		MATRIZ[5][2] = 42;
		MATRIZ[5][3] = 43;
		MATRIZ[5][4] = 44;
		MATRIZ[5][5] = 45;
		MATRIZ[5][6] = 46;
		MATRIZ[5][7] = 47;
				
				
		MATRIZ[6][0] = 48;
		MATRIZ[6][1] = 49;
		MATRIZ[6][2] = 50;
		MATRIZ[6][3] = 51;
		MATRIZ[6][4] = 52;
		MATRIZ[6][5] = 53;
		MATRIZ[6][6] = 54;
		MATRIZ[6][7] = 55;
		
		
		MATRIZ[7][0] = 56;
		MATRIZ[7][1] = 57;
		MATRIZ[7][2] = 58;
		MATRIZ[7][3] = 59;
		MATRIZ[7][4] = 60;
		MATRIZ[7][5] = 61;
		MATRIZ[7][6] = 62;
		MATRIZ[7][7] = 63;
	}

	/**
	 * 
	 * @param posicion
	 * @return
	 */
	public static String getPosicionMatriz(int posicion){
		String valores = null;
		for(int i = 0; i < MATRIZ.length; i++){
			if(VALIDAR != 1){
				for(int j = 0; j < MATRIZ.length; j++){
					if(MATRIZ[i][j] == posicion){
						valores = Integer.toString(i) + "," + Integer.toString(j);
						VALIDAR = 1;
					}
				}
			}else{
				break;
			}
		}
		VALIDAR = 0;
		return valores;
	}
	
	/**
	 * 
	 * @param fila
	 * @param columna
	 * @return
	 */
	public static int getPosicionTablero(int fila, int columna){
		int valorMatrix = MATRIZ[fila][columna];
		return valorMatrix;
	}
	
}
