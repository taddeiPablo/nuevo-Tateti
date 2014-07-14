package modelo;

public class Modelo_bluetooth {
	
	private static int DIMENCION = 8;
	private int tablero[][] = new int[DIMENCION][DIMENCION];
	private int ganadores = -1;
	private int indexTablero = 0;
	private int contador_ganador = 0;
	private int contra_diagonal = 7;
	private int valor = -1;
	private int contador_ganador_contra_diagonal = 1;
	private int contador_ganador_Diagonal = 1;
	private int aux_index = 0;
	private int aux_index_contra = 7;
	private int aux = 0;
	private int index = 0;
	private int anterior = 0;
	private int ganadorDiagonal = 0;
	private int coincidencias = 0;
	private int coincidenciasV = 0;
	private int ganador = 0;
	private int evaluar = 0;
	
	public int get_valor(){
		return this.valor;
	}
	
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
                if(this.evaluar == 6){
                  int valor1 = ganadorPartida_Diagonal_contraDiagonal();
                }
                this.evaluar++;
            }
            return true;
        }else{
        	return false;
        }
	}
	
	public void colocar_ficha(String valor){
		String valores[] = valor.split(",");
		int fila = Integer.parseInt(valores[0]);
		int columna = Integer.parseInt(valores[1]);
		
        if (tablero[fila][columna]==-1){
            if (this.ganadores == -1){
                tablero[fila][columna]=2;
                
            }
        }
	}
	

	/**
	 * VERIFICO SI HAY GANADORES EN LA DIAGONAL Y LA CONTRA-DIAGONAL
	 * @return
	 */
	public int ganadorPartida_Diagonal_contraDiagonal(){
		try{
			int cont = 1;
			int contAux = 0;
			int coincidenciasDiagonal = 1;
			int coincidenciasContraDiagonal = 1;
			
			//DIAGONAL
			for(int i=0; i < 7; i++){
				if(this.tablero[i][i] != -1 && this.tablero[cont][cont] != -1 
						&& this.tablero[i][i] == this.tablero[cont][cont]){
					this.ganador = this.tablero[i][i];
					coincidenciasDiagonal++;
				}
				cont++;
				if(cont == 7){
					break;
				}
			}
			
			//CONTRA-DIAGONAL
			for(int j=7; j > 0; j--){
				int contAux1 = contAux + 1;
				int jaux = j - 1;
				if(jaux != -1){
					if(this.tablero[contAux][j] != -1 && this.tablero[contAux1][jaux] != -1 
							&& this.tablero[contAux][j] == this.tablero[contAux1][jaux]){
						this.ganador = this.tablero[contAux][j];
						coincidenciasContraDiagonal++;
					}
					contAux++;
					if(contAux == 7){
						break;
					}
				}
			}
			
			//EVALUO SI HAY 6 COINCIDENCIAS Y SI LAS HAY DEVUELVO AL GANADOR DE LA PARTIDA
			if(coincidenciasDiagonal == 6 || coincidenciasContraDiagonal == 6){
				this.valor = this.ganador;
				return this.ganador;
			}else{
				return -1;
			}
		}catch(Exception ex){
			return -1;
		}
	}
	
	
	
	/**
	 * VERIFICO SI HAY GANADORES EN HORIZONTAL Y VERTICAL 
	 * @return
	 */
	public int ganadorVertical_Horizontal(){
		try{
			int coincidenciasHorizontal = 1;
			int coincidenciasVertical = 1;
			
			//HORIZONTAL
			for(int i=0; i < 7; i++){
				int cont = 1;
				for(int j=i; j < 7; j++){
					if(this.tablero[i][j] != -1 && this.tablero[i][cont] != -1 
							&& this.tablero[i][j] == this.tablero[i][cont]){
						this.ganador = this.tablero[i][j];
						coincidenciasHorizontal++;
					}
					if(cont == 7){
						break;
					}
					cont++;
				}
			}
			
			//VERTICAL
			for(int j=0; j < 7; j++){
				int cont = 1;
				for(int i=j; i < 7; i++){
					if(this.tablero[i][j] != -1 && this.tablero[cont][j] != -1 
							&& this.tablero[i][j] == this.tablero[cont][j]){
						this.ganador = this.tablero[i][j];
						coincidenciasHorizontal++;
					}
					if(cont == 7){
						break;
					}
					cont++;
				}
			}
			
			if(coincidenciasHorizontal == 6 ||coincidenciasVertical == 6){
				this.valor = this.ganador;
				return this.ganador;
			}else{
				return -1;
			}
		}catch(Exception ex){
			return -1;
		}
	}
	
}
