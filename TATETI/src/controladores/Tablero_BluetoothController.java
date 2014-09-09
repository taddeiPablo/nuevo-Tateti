package controladores;

import modelo.Manejador_de_mensajes;
import modelo.Modelo_bluetooth;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.TextView;
import bluetooth_modelo.BluetoothServices_MultiJugador;

import com.app.TaTeTi.R;
import com.app.TaTeTi.Seleccion_oponenteActivity;
import com.app.TaTeTi.Tablero_bluetoothActivity;

import entidades.TableroAuxiliar_bluetooth;
import entidades.Wrapper_controls;

/**
 * 
 * @author pablo Taddei
 *
 */
public class Tablero_BluetoothController  implements DialogInterface.OnClickListener {
	
	private BluetoothAdapter bAdapter = null;
	private Tablero_bluetoothActivity act = null;
	private Intent Listado_POpup = null;
	private BluetoothServices_MultiJugador bsMultiJugador = null;
	private Manejador_de_mensajes menHandler = null;
	private String nombre_Dispositivo = null;
	private String mi_ficha = null;
	private String index_rival = null;
	private String ficha_oponente = null;
	private int bandera = 0;
	private Modelo_bluetooth modeloBluetooth = null;
	private int result = 0;
	
	@JavascriptInterface
	public int get_bandera(){
		return this.bandera;
	}
	
	public void set_bandera(int bandera){
		this.bandera = bandera;
	}

	public void set_movimientoOponente(String movimiento){
		this.modeloBluetooth.colocar_ficha(movimiento);
	}
	
	@JavascriptInterface
	public String get_ficha_oponente(){
		return this.ficha_oponente;
	}
	
	public void set_ficha_oponente(String fichaOponente){
		this.ficha_oponente = fichaOponente;
	}
	
	@JavascriptInterface
	public String get_index_rival(){
		return this.index_rival;
	}
	
	public void set_index_rival(String index){
		this.set_bandera(2);
		String[] str_valor = index.split(",");
		int f = Integer.parseInt(str_valor[0]);
		int c = Integer.parseInt(str_valor[1]);
		this.index_rival = Integer.toString(TableroAuxiliar_bluetooth.getPosicionTablero(f, c));
	}
	
	/**
	 * 
	 * @return
	 */
	@JavascriptInterface
	public String get_mi_ficha(){
		return this.mi_ficha;
	}
	
	/**
	 * 
	 * @param ficha
	 */
	public void set_mi_ficha(String ficha){
		this.mi_ficha = ficha;
	}
	
	/**
	 * 
	 * @return
	 */
	@JavascriptInterface
	public String get_nombre_Dispositivo(){
		return this.nombre_Dispositivo;
	}
	
	/**
	 * 
	 * @param nombre
	 */
	public void set_nombre_Dispositivo(String nombre){
		this.nombre_Dispositivo = nombre;
	}
	
	/**
	 * 
	 * @return
	 */
	public BluetoothAdapter getdataAdapter(){
		return this.bAdapter;
	}
	
	
	/**
	 * 
	 * @param activity
	 */
	public Tablero_BluetoothController(Tablero_bluetoothActivity activity){
		this.act = activity;
		TableroAuxiliar_bluetooth.iniciar_Tablero();
		this.bAdapter = BluetoothAdapter.getDefaultAdapter();
		this.modeloBluetooth = new Modelo_bluetooth();
	}
	
	
	/**
	 * 
	 */
	public void inicio_bluetooth(){
		try{
			this.Listado_POpup = new Intent(this.act,Seleccion_oponenteActivity.class);
			this.act.startActivityForResult(Listado_POpup, 1);
		}catch(Exception ex){
			
		}
	}
	
	/**
	 * 
	 */
	private void iniciar_vinculacion(){
		try{
			this.menHandler = new Manejador_de_mensajes(this.act,this);
			this.bsMultiJugador = new BluetoothServices_MultiJugador(this.act,this.menHandler);
			this.bsMultiJugador.start();
		}catch(Exception ex){
			Log.e("Error", "Clase (Seleccion_oponenteActivity)Funcion iniciar_vinculacion" + ex.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public void controller_start(){
		try{
			if(this.bsMultiJugador == null){
				iniciar_vinculacion();
			}
		}catch(Exception ex){
			
		}
	}
	
	/**
	 * 
	 */
	public void controller_stop(){
		try{
			this.bsMultiJugador.stop();
		}catch(Exception ex){
			
		}
	}
	
	/**
	 * 
	 * @param device
	 */
	public void controller_connect(BluetoothDevice device){
		this.bsMultiJugador.connect(device);
	}
	
	/**
	 * 
	 */
	@JavascriptInterface
	public void cerrar_ventana(){
		this.act.finish();
	}
	
	/**
	 * 
	 * @param id
	 */
	@JavascriptInterface
	public void mandar_mensaje(String id){
		try{
			this.set_bandera(2);
			int index = Integer.parseInt(id);
			String indexStr = TableroAuxiliar_bluetooth.getPosicionMatriz(index);
			this.modeloBluetooth.colocarFicha(indexStr);
			byte[] mensajeArray = indexStr.getBytes();
			this.bsMultiJugador.write(mensajeArray);
		}catch(Exception ex){
			
		}
	}

	
	/**
	 * 
	 * @return
	 */
	@JavascriptInterface
	public int get_ganador(){
		if(this.modeloBluetooth.ganadorPartida_Diagonal_contraDiagonal() != -1){
			result = this.modeloBluetooth.get_valor();
		}else if(this.modeloBluetooth.ganadorVertical_Horizontal() != -1){
			result = this.modeloBluetooth.get_valor();
		}else{
			result = this.modeloBluetooth.get_valor();
		}
		return result;
	}
	
	
	
	/**
	 *FUNCION POR LA CUAL MOSTRAMOS UNA VENTANA AL JUGADOR AVISANDOLE QUIEN A GANADO LA
	 *PARTIDA.
	 * @param ganador
	 */
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

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		dialog.cancel();
	}
}
