package com.app.TaTeTi;

import java.util.ArrayList;
import java.util.Set;
import controladores.Seleccion_oponenteController;
import modelo.BroadCastBluetooth;
import entidades.Oponente;
import adapter.Adapter_Oponentes;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

/**
 * 
 * @author pablo
 *
 */
public class Seleccion_oponenteActivity extends Activity {
	
	private BluetoothAdapter mBluetoothAdapter = null;
	private BroadCastBluetooth bcReceiver = null;
	private IntentFilter filter = null;
	private Oponente oponente = null;
	private Adapter_Oponentes adapterOp = null;
	private ListView listado = null;
	private Seleccion_oponenteController controller = null;
	private ArrayList<Oponente> listOponentes = null;
	/*private BluetoothServices_MultiJugador bsMultiJugador = null;
	private Manejador_de_mensajes menHandler = null;*/
	
	
	/**
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listado_oponentes);
		
		
		this.listado = (ListView)findViewById(R.id.listOponentes);
		
		activar_bluetooth();
		busqueda_oponentes();
		
		this.controller = new Seleccion_oponenteController(this,this.mBluetoothAdapter,this);
		this.listado.setOnItemClickListener(controller);
	}
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.mBluetoothAdapter.cancelDiscovery();
	}


	
	
	/**
	 * DESDE ESTA FUNCION SE ACTIVA EL BLUETOOTH
	 */
	public void activar_bluetooth(){
		try{
			this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			if (mBluetoothAdapter.getScanMode() !=
		       BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
		       Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		       discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
		       startActivity(discoverableIntent);
		    }
		}catch(Exception ex){
			Log.e("Error", "Clase (Seleccion_oponenteActivity) Funcion activar_bluetooth" + ex.getMessage());
		}
	}
	
	/**
	 * DESDE ESTA FUNCION SE REALIZA LA BUSQUEDA DE DISPOSITIVOS
	 * CON EL BLUETOOTH ACTIVADOS
	 */
	public void busqueda_oponentes(){
		ArrayList<Oponente> listado_oponente = new ArrayList<Oponente>();
		try{
			this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			this.bcReceiver = new BroadCastBluetooth(this);
			
			iniciar_busqueda();
			
			this.filter = new IntentFilter(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			this.registerReceiver(this.bcReceiver, filter);
			
			this.filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
			this.registerReceiver(this.bcReceiver, filter);
			
			this.filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
			this.registerReceiver(this.bcReceiver, filter);
			
			this.filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
			this.registerReceiver(this.bcReceiver, filter);
			
			this.filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
			this.registerReceiver(this.bcReceiver, filter);
			
			Set<BluetoothDevice> pairedDevices = this.mBluetoothAdapter.getBondedDevices();
			
			if(pairedDevices.size() > 0){
				for(BluetoothDevice opo_disp : pairedDevices){
					this.oponente = new Oponente(opo_disp.getName(),opo_disp.getAddress());
					listado_oponente.add(oponente);
				}
			}else{
				this.filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
				this.registerReceiver(this.bcReceiver, filter);
				
				this.filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
				this.registerReceiver(this.bcReceiver, filter);
				this.oponente = new Oponente("----","000000");
				listado_oponente.add(oponente);
			}
			
		}catch(Exception ex){
			Log.e("Error", "Clase (Seleccion_oponenteActivity)Funcion busqueda_Oponentes"+ex.getMessage());
		}
	}
	
	/**
	 * DESDE AQUI SE INICIA LA BUSQUEDA DE LOS DISPOSITIVOS CON BLUETOOTH ACTIVOS
	 */
	public void iniciar_busqueda(){
		try{
			if(this.mBluetoothAdapter.isDiscovering()){
				this.mBluetoothAdapter.cancelDiscovery();
			}
			this.mBluetoothAdapter.startDiscovery();
		}catch(Exception ex){
			Log.e("Error", "Clase (Seleccion_oponenteActivity)Funcion iniciar_busqueda" + ex.getMessage());
		}
	}
	
	/**
	 * DESDE ACA SE CARGA LA LISTA CON LOS DISPOSITIVOS YA ENCONTRADOS
	 * Y LISTOS PARA ENLAZAR Y REALIZAR LA CONEXION
	 * @param listOp
	 */
	public void cargarListado(ArrayList<Oponente> listOp){
		this.listOponentes = listOp;
		this.controller.setListado_oponentes(listOponentes);
		this.adapterOp = new Adapter_Oponentes(this,0,listOp);
		this.adapterOp.setActivity(this);
		this.listado.setAdapter(adapterOp);
	}
}
