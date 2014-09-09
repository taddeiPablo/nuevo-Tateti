package com.app.TaTeTi;


import org.apache.cordova.CordovaActivity;

import controladores.Tablero_BluetoothController;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * 
 * @author Pablo
 *
 */
public class Tablero_bluetoothActivity extends CordovaActivity {

	private Tablero_BluetoothController controller = null;
	
	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.init();
		this.controller = new Tablero_BluetoothController(this);
		this.controller.inicio_bluetooth();
		this.appView.addJavascriptInterface(this.controller,"Tablero_BluetoothController");
		super.loadUrl(this.getString(R.string.tableromultijugador));
	}

	/**
	 * 
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.controller.controller_start();
	}

	/**
	 * 
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.controller.controller_stop();
	}

	/**
	 * 
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		this.controller.controller_start();
	}
	

	/**
	 * 
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		
		switch(requestCode){
		case 1:
			if(resultCode == Activity.RESULT_OK){
				String address = intent.getExtras().getString("device_address");
				String name = intent.getExtras().getString("device_name");
				this.controller.set_nombre_Dispositivo(name);
				this.controller.set_mi_ficha("cruz");
				this.controller.set_ficha_oponente("circulo");
				this.controller.set_bandera(1);
				BluetoothDevice device = this.controller.getdataAdapter().getRemoteDevice(address);
				this.controller.controller_connect(device);
			}else{
				this.controller.set_mi_ficha("circulo");
				this.controller.set_ficha_oponente("cruz");
				this.controller.set_bandera(2);
			}
			break;
		case 2:
			if(resultCode == Activity.RESULT_OK){
				
			}
			break;
		}	
	}

	public void setMensaje(String mensaje){
		int duracion = Toast.LENGTH_SHORT;
		View vista_comienzo = View.inflate(this.getApplicationContext(), R.layout.comienzopartida, null);
		Toast mensaje_toast = Toast.makeText(this.getApplicationContext(), mensaje, duracion);
		mensaje_toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		mensaje_toast.setView(vista_comienzo);
		mensaje_toast.show();
	}
	

}
