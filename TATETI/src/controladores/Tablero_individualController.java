package controladores;

import modelo.Modelo;

import org.apache.cordova.CordovaActivity;

import com.app.TaTeTi.R;

import entidades.TableroAuxiliar;
import entidades.Wrapper_controls;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*****************************************
 * DEFINICION DEL CONTROLADOR PARA LA VENTANA DEL TABLERO DE JUEGO INDIVIDUAL
 * @author pablo
 *
 *****************************************/
public class Tablero_individualController implements DialogInterface.OnClickListener{
	private CordovaActivity act = null;
	private String ficha = null;
	private String ficha_oponente = null;
	private Modelo model = null;
	
	
	//TODO:METODO SETTER AQUI SE SETEA LA FICHA QUE HEMOS SELECCIONADO
	public void SetFicha_seleccionada(String fichaSelect){
		this.ficha = fichaSelect;
	}
	
	//TODO:METODO GETTER DEVUELVE LA FICHA QUE SE A SELECCIONADO.
	@JavascriptInterface
	public String getFicha_seleccionada(){
		return this.ficha;
	}
	
	
	public void setFicha_Oponente(String fichaOponente){
		this.ficha_oponente = fichaOponente;
	}
	
	//TODO:DEVOLVEMOS LA FICHA SELECCIONADA POR EL OPONENTE
	@JavascriptInterface
	public String getFicha_Oponente(){
		return this.ficha_oponente;
	}
	
	public Tablero_individualController(CordovaActivity activity,Modelo mod){
		this.act = activity;
		this.model = mod;
		TableroAuxiliar.inicializarTablero();
	}
	
	//TODO:METODO POR EL CUAL SE VUELVE HACIA LA VENTANA DE SELECCION DE FICHAS
	@JavascriptInterface
	public void volverVentanaSeleccion_onclick(){
		this.act.finish();
	}
	
	//TODO: METODO POR EL CUAL SE REINICIA LA PARTIDA PARA VOLVER A JUGAR DESDE EL PRINCIPIO
	@JavascriptInterface
	public void RecargarJuego(){
		this.model.empezarPartida();
	}
	
	
	
	//TODO:EVENTO QUE SE EJECUTA CUANDO SE SELECCIONA UNA POSICION EN EL TABLERO
	//TODO:DEL JUEGO. AQUI SE COLOCA LA FICHA DEL JUGADOR...
	@JavascriptInterface
	public void poner_Ficha_onclick(String index){
		int indexInt = Integer.parseInt(index);
		String valores = TableroAuxiliar.getPosicionMatriz(indexInt);
		this.model.colocarFicha(valores);
	}
	
	//TODO:FUNCION QUE DEVUELVE SI LA PARTIDA A TERMINADO O NO
	@JavascriptInterface
	public boolean getFinPartida(){
		boolean var = this.model.finPartida();
		return var;
	}
	
	//TODO:FUNCION QUE DETERMINA QUIEN A GANADO LA PARTIDA QUE ACABA DE FINALIZAR
	@JavascriptInterface
	public int getGanador(){
		return this.model.ganaPartida();
	}
	
	//TODO:FUNCION QUE DEVUELVE LA POSICION EN DONDE LA MAQUINA VA A COLOCAR SU FICHA EN LA PARTIDA
	@JavascriptInterface
	public int getIndexTablero(){
		return this.model.getIndexTablero();
	}
	
	//TODO:FUNCION QUE DEVUELVE LA FICHA CON LA QUE JUEGA LA MAQUINA
	@JavascriptInterface
	public String getFicha_oponente(){
		return this.ficha_oponente;
	}
	
	//TODO:FUNCION POR LA CUAL MOSTRAMOS UNA VENTANA AL JUGADOR AVISANDOLE QUIEN A GANADO LA
	//TODO:PARTIDA.
	@JavascriptInterface
	public void mostrar_Ganador(int ganador){
		View vista = View.inflate(this.act,com.app.TaTeTi.R.layout.viewganadores, null);
		Wrapper_controls controles = new Wrapper_controls();
		controles.setTxtControl((TextView)vista.findViewById(com.app.TaTeTi.R.id.textGanador));
		controles.setTxtTitulo((TextView)vista.findViewById(com.app.TaTeTi.R.id.textV));
		controles.setImagen((ImageView)vista.findViewById(com.app.TaTeTi.R.id.imageView1));
		Resources recursos = this.act.getResources();
		AlertDialog.Builder mensaje = new AlertDialog.Builder(this.act);
		mensaje.setView(vista);
		if(ganador == 1){
			String titulo_Ventana = "Resultado de la Partida";
			String ganador_str = "A GANADO !!!";
			controles.getTxtTitulo().setText(titulo_Ventana);
			controles.getTxtControl().setText(ganador_str);
			controles.getImagen().setImageDrawable(recursos.getDrawable(R.drawable.ganador));
		}else if(ganador == 2){
			String titulo_Ventana = "Resultado de la Partida";
			String ganador_str = "A PERDIDO !!!";
			controles.getTxtTitulo().setText(titulo_Ventana);
			controles.getTxtControl().setText(ganador_str);
			controles.getImagen().setImageDrawable(recursos.getDrawable(R.drawable.perdedor));
		}else{
			String titulo_Ventana = "Resultado de la Partida";
			String empate = "HUBO UN EMPATE !!!";
			controles.getTxtTitulo().setText(titulo_Ventana);
			controles.getTxtControl().setText(empate);
			controles.getImagen().setImageDrawable(recursos.getDrawable(R.drawable.empate));
		}
		mensaje.setTitle("partida");
		mensaje.setCancelable(true);
		mensaje.setPositiveButton("CERRAR VENTANA", this);
		
		AlertDialog alerta = mensaje.create();
		alerta.show();
	}

	//TODO:metodo que cierra la aplicacion por completo
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		dialog.cancel();
	}
	
	//TODO:metodo que muestra el comienzo de la partida
	@JavascriptInterface
	public void mostrar_comienzo(){
		String mensaje = "La Partida a Comenzado!";
		int duracion = Toast.LENGTH_SHORT;
		View vista_comienzo = View.inflate(this.act.getApplicationContext(), R.layout.comienzopartida, null);
		Toast mensaje_toast = Toast.makeText(this.act.getApplicationContext(), mensaje, duracion);
		mensaje_toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		mensaje_toast.setView(vista_comienzo);
		mensaje_toast.show();
	}
	
}
