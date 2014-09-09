package modelo;

import entidades.TableroAuxiliar;

	
/***********************************************************************
 * AQUI CREACION DEL MODELO O LOGICA DEL JUEGO
 * AQUI SE IMPLEMENTA EL ALGORITMO MINIMAX QUE SERA LA FORMA
 * EN COMO LA MAQUINA JUGARA CONTRA NOSOTROS..
 * @author pablo
 * 
 ***********************************************************************/
public class Modelo {
	
 	private static int DIMENCION=3;
    private int tablero[][]=new int[DIMENCION][DIMENCION];
    private int ganadores=-1;
    private int indexTablero = 0;
    
    public Modelo() {
        empezarPartida();
    }
    
    //TODO : METODO GET QUE DEVUELVE LA POSICION DEL TABLERO
    public int getIndexTablero(){
    	return this.indexTablero;
    }
    
    //TODO : METODO GET QUE NOS INDICA QUIEN ES EL GANADOR
    public int GetGanador(){
    	return this.ganadores;
    }
    
    //TODO : METODO GET QUE DEVUELVE EL TABLERO A MEDIDA QUE SE VA JUNGANDO
    public int[][] getTablero(){
        return tablero;
    }
    
    //TODO :METODO QUE COMIENZA LA PARTIDA SETEANDO TODAS LAS POSICIONES CON VALOR -1
    //TODO : QUE EN NUESTRO CASO SERIA QUE EL CASILERO ESTA VACIO
    public void empezarPartida(){
        for (int n=0; n < DIMENCION ;n++)
            for (int m=0;m < DIMENCION;m++)
                tablero[n][m]=-1;
        this.ganadores = -1;
    }
    
    //TODO : METODO QUE DEVUELVE LA POSICION EN DONDE HEMOS PUESTO LA FICHA
    public int PosicionTablero(){
    	return this.indexTablero;
    }
    
    //TODO : METODO POR EL CUAL COLOCAMOS UNA FICHA EN EL TABLERO
    public boolean colocarFicha(String valor){
    	String valores[] = valor.split(",");
		int fila = Integer.parseInt(valores[0]);
		int columna = Integer.parseInt(valores[1]);
		
        if (tablero[fila][columna]==-1){
            if (this.ganadores == -1){
                tablero[fila][columna]=1;
                this.ganadores = ganaPartida();
                ponerFichaOrdenador();
            }
            return true;
        }else{
        	return false;
        }
    }
    
    //TODO : METODO POR EL CUAL NOS VA A IR INDICANDO SI HAY UN GANADOR Y 
    //TODO : DE LO CONTRARIO VERIFICA SI HAY POSICIONES VACIAS AUN
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
   
    //TODO : aqui determinamos si el tablero aun tiene posiciones vacias para continuar el juego
    private boolean tableroCompleto(){
        for (int fila = 0; fila < DIMENCION; fila++)
            for (int columna = 0; columna < DIMENCION; columna++)
                if (tablero[fila][columna]==-1)
                    return false;
        return true;
    }
    
    // TODO: aqui determinamos el fin de la partida preguntamos si el tablero esta completo
    // TODO: y si hubo un ganador o empate
    public boolean finPartida(){
        return tableroCompleto() || ganaPartida()!=-1;
    }
    
    
    //TODO: PRIMERO ALGORITMO NECESARIO PARA EMPEZAR A ARMAR LA LOGICA DEL MINIMAX
    //TODO: AQUI SE LLAMA A MIN Y SE COLOCA UNA FICHA DE LA MAQUINA.
    private void ponerFichaOrdenador(){
    	int f=0, c=0;
        if (!finPartida()){
            int v=Integer.MIN_VALUE;
            int aux;
            //en el limite de recorrido del for utilizar la variable que determina la dimension de la matriz y no 
            //la matriz en si
            for (int fila = 0; fila < DIMENCION; fila++){
                for (int columna =0; columna < DIMENCION; columna++){
                    if (tablero[fila][columna]==-1){
                    	//ficha de la maquina
                        tablero[fila][columna]=1;
                        aux=min();
                        if (aux>v) {
                            v=aux;
                            f=fila;
                            c=columna;
                        }
                        //limpio el tablero
                        tablero[fila][columna]=-1;
                    }
                }
            }
            movimientoMaquina(f,c);
        }
    }
    
    
    
    //TODO : metodo por el cual la maquina realiza su movimiento en el
    //TODO : tablero del juego
    private void movimientoMaquina(int fila, int columna){
    	if(tablero[fila][columna] != 1){
            //ficha de la maquiina
            tablero[fila][columna]=2;
            this.indexTablero = TableroAuxiliar.getPosicionTablero(fila,columna);
            this.ganadores = ganaPartida();
    	}
    }
    
    
    
    
    //TODO : MAX AQUI EMPIEZA EL MINIMAX, AQUI EVALUO LA MEJOR POSICION PARA LA MAQUINA
    //TODO : Y COLOCO NUEVAMENTE UNA FICHA DE LA MAQUINA.
    private int max(){
        if (finPartida()){
            if (ganaPartida()!=-1) return -1;
            else return 0;
        }
        int v=Integer.MIN_VALUE;
        int aux;
        for (int fila = 0; fila < DIMENCION; fila++){
            for (int columna = 0; columna < DIMENCION; columna++){
                if (tablero[fila][columna]==-1){
                	//coloco una ficha de la maquina
                    tablero[fila][columna]=2;
                    aux=min();
                    if (aux>v) v=aux;
                    //pongo esa posicion en blanco
                    tablero[fila][columna]=-1;
                    
                }
            }
        }
        return v;
    }
    
    
    //TODO : ALGORITMO MINIMAX AQUI EVALUO LA MINIMA POSIBILIDAD PARA EL JUGADOR.
    //TODO : COLOCO UNA FICHA DEL JUGADOR Y EVALUO HASTA QUE ME DEVUELVE EL MINIMO VALOR
    private int min(){
        if (finPartida()){
            if (ganaPartida()!=-1) return 1;
            else return 0;
        }
        int v=Integer.MAX_VALUE;
        int aux;
        for (int fila = 0; fila < DIMENCION ;fila++){
            for (int columna = 0; columna < DIMENCION ; columna++){
                if (tablero[fila][columna]==-1){
                	//coloco una ficha del jugador contrario para evaluar la minima posibilidad de triunfo
                	//que tiene ante una jugada de la maquina
                    tablero[fila][columna]=1;
                    aux=max();
                    if (aux<v) v=aux;
                    //quito la ficha colocada para poder seguir jugando
                    tablero[fila][columna]=-1;
                }
            }
        }
        return v;
    }
    
}