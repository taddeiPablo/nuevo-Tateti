package entidades;

/***************************************************
 * @author pablo
 * 
 ***************************************************/
public class TableroAuxiliar {
	
	private static int[][] MATRIZ = new int[3][3];
	private static int VALIDAR = 0;

	/**************************************
	 * 
	 **************************************/
	public static void inicializarTablero(){
		MATRIZ[0][0] = 0;
		MATRIZ[0][1] = 1;
		MATRIZ[0][2] = 2;
		MATRIZ[1][0] = 3;
		MATRIZ[1][1] = 4;
		MATRIZ[1][2] = 5;
		MATRIZ[2][0] = 6;
		MATRIZ[2][1] = 7;
		MATRIZ[2][2] = 8;
	}
	
	
	/************************************************
	 * @param arg2
	 * @return
	 ************************************************/
	public static String  getPosicionMatriz(int arg2){
		String valores = null;
		for(int i = 0; i < MATRIZ.length; i++){
			if(VALIDAR != 1){
				for(int j = 0; j < MATRIZ.length; j++){
					if(MATRIZ[i][j] == arg2){
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
	
	/************************************************************
	 * @param fila
	 * @param columna
	 * @return
	 ************************************************************/
	public static int getPosicionTablero(int fila, int columna){
		int valorMatrix = MATRIZ[fila][columna];
		return valorMatrix;
	}
	
	
	
}
