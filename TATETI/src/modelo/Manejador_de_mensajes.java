package modelo;


import com.app.TaTeTi.Tablero_bluetoothActivity;

import controladores.Tablero_BluetoothController;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 
 * @author Pablo
 *
 */
public class Manejador_de_mensajes extends Handler {

	private Tablero_bluetoothActivity tablero_bluetooth = null;
	private Tablero_BluetoothController tablero_bluetooth_controller= null;
	
	//contantes
	public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
	
    /**
     * 
     * @param tablero_bluetooth
     * @param tableroBC
     */
    public Manejador_de_mensajes(Tablero_bluetoothActivity tablero_bluetooth,Tablero_BluetoothController tableroBC){
    	this.tablero_bluetooth = tablero_bluetooth;
    	this.tablero_bluetooth_controller = tableroBC;
    }
    
    
    
    /**
     * 
     */
	@Override
	public void handleMessage(Message msg) {
		switch(msg.what){
		case MESSAGE_STATE_CHANGE:
			//Aqui no estamos utilizando nada, aunque se podria utilizar para 
			//saber el estado de la conexion
			break;
		case MESSAGE_READ :
			byte[] readBuf = (byte[]) msg.obj;
			String mensaje = getString(readBuf);
			this.tablero_bluetooth_controller.set_movimientoOponente(mensaje);
			this.tablero_bluetooth_controller.set_index_rival(mensaje);
			this.tablero_bluetooth_controller.set_bandera(3);
			break;
		case MESSAGE_WRITE :
			Log.e("aqui entro", "aca entro" + "MESSAGE_WRITE");
			//Aqui no escribimos sino se escribe desde otro lado
			break;
		case MESSAGE_DEVICE_NAME :
			Bundle data = msg.getData();
			String nombre_del_dispositivo = data.getString("device_name");
			this.tablero_bluetooth_controller.set_nombre_Dispositivo(nombre_del_dispositivo);
			this.tablero_bluetooth.setMensaje("La partida a comenzado");
			break;
		}
	}
	
	
	/**
	 * 
	 * @param bytes
	 * @return
	 */
	private String getString(byte[] bytes){
		String valores = "";
		char valor1 = 0;
		char valor2 = 0;
		char valor3 = 0;
		for(int i=0; i < bytes.length; i++){
			if(bytes[i] != 0){
				valor1 = (char)bytes[i];
				valor2 = (char)bytes[i+1];
				valor3 = (char)bytes[i+2];
				break;
			}
		}
		valores = Character.toString(valor1) + Character.toString(valor2) + Character.toString(valor3);
		return valores;
	}

}
