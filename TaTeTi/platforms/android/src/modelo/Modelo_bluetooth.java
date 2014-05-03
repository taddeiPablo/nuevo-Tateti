package modelo;

public class Modelo_bluetooth {
	
	private static int DIMENCION = 8;
	private int tablero[][] = new int[DIMENCION][DIMENCION];
	private int ganadores = -1;
	private int indexTablero = 0;
	
	
	public Modelo_bluetooth(){
		empezarPartida();
	}
	
	
	public void empezarPartida(){
		for(int fila = 0; fila < DIMENCION; fila++)
			for(int columna = 0; columna < DIMENCION; columna++)
				tablero[fila][columna] = -1;
	}
	
	
	public boolean colocarFicha(String valor){
		String valores[] = valor.split(",");
		int fila = Integer.parseInt(valores[0]);
		int columna = Integer.parseInt(valores[1]);
		
        if (tablero[fila][columna]==-1){
            if (this.ganadores == -1){
                tablero[fila][columna]=1;
                this.ganadores = ganaPartida();
                //ponerFichaOrdenador();
            }
            return true;
        }else{
        	return false;
        }
	}
	
	
	public int ganaPartida(){
		 //aqui verificamos la diagonal
		 if (tablero[0][0] != -1 && tablero[0][0] == tablero[1][1]
	              && tablero[0][0] == tablero[2][2])
	         return tablero[0][0];
	     //aqui verificamos la contradiagonal 
	     if (tablero[0][2] != -1 && tablero[0][2] == tablero[1][1]
	              && tablero[0][2] == tablero[2][0])
	         return tablero[0][2];
	     //aqui hacemos un for con el cual recorreremos todas las celdas del tablero
	     for (int index = 0;index < DIMENCION; index++){
	        //aqui verificamos las posiciones horizontales
	        if (tablero[index][0] != -1 && tablero[index][0] == tablero[index][1]
	              && tablero[index][0] == tablero[index][2])
	          return tablero[index][0];
	          //aqui verificamos las posiciones verticales
	        if (tablero[0][index] != -1 && tablero[0][index] == tablero[1][index]
	              && tablero[0][index] == tablero[2][index])
	          return tablero[0][index];
	     }
	  return -1;
	}
	
}
