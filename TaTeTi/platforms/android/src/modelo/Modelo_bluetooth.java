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
             
                //ponerFichaOrdenador();
            }
        }
	}
	

	
	public int ganadorPartida_Diagonal_contraDiagonal(){
		/*for(int i=this.aux_index; i < 8; i++){
			int columna = i+1;
			if(tablero[i][i] != -1 && tablero[columna][columna] != -1 && tablero[i][i] == tablero[columna][columna]){
				this.aux_index = columna;
				this.contador_ganador_Diagonal++;
				this.valor = tablero[columna][columna];
			}
		}*/
		this.index = this.anterior;
		for(int i=this.aux_index_contra; i > 0; i--){
			int info = tablero[index][i];
			int ind = info;
			if(tablero[index][i] != -1){
				int var = index == 7 ? 7 : index+1;
				int vard = i == 0 ? 0 : i-1;
				if(tablero[index][i] == tablero[var][vard]){
					this.contador_ganador_contra_diagonal++;
					if(var == 7){
						this.aux_index_contra = 7;
						this.anterior = 0;
					}/*else{
						this.anterior = var;
						this.aux_index_contra = vard;
					}*/
				}
			}
			index++;
		}
			
		
		if(this.contador_ganador_Diagonal == 5 || this.contador_ganador_contra_diagonal == 5){
			this.contador_ganador_Diagonal = 0;
			this.contra_diagonal = 7;
			return this.valor;
		}else{
			this.contador_ganador = 0;
			this.contra_diagonal = 7;
			this.valor = -1;
			this.index = 0;
			return this.valor;
		}
	}
	
	public int ganadorPosiciones(){
		for(int i=0; i <= 7; i++){
			int columna = i+1;
			for(int j=columna; j < 7; j++){
				if(tablero[i][i] != -1 && tablero[j][j] != -1 && tablero[i][i] == tablero[j][j]){
					this.contador_ganador++;
					this.valor = tablero[i][i];
				}
				if(tablero[i][i] != -1 && tablero[j][j] != -1 && tablero[i][i] == tablero[j][j]){
					this.contador_ganador++;
					this.valor = tablero[i][i];
				}else{
					break;
				}
			}
		}
		
		if(this.contador_ganador == 5)
		{
			this.contador_ganador = 0;
		}
		return this.valor;
	}
	
}
