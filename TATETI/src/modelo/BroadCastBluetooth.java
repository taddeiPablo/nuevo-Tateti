package modelo;

import java.util.ArrayList;
import com.app.TaTeTi.Seleccion_oponenteActivity;
import entidades.Oponente;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/******************************************************
 * 
 * @author pablo
 *
 ******************************************************/
public class BroadCastBluetooth extends BroadcastReceiver{

	private ArrayList<Oponente> listOponentes = null;
	private Oponente oponente = null;
	private Seleccion_oponenteActivity select_op = null;
	private ArrayList<BluetoothDevice> listadoDisp = null;
	
	
	public ArrayList<BluetoothDevice> getListado(){
		return this.listadoDisp;
	}
	
	
	//TODO: constructor por defecto
	public BroadCastBluetooth(Seleccion_oponenteActivity selectOp){
		this.select_op = selectOp;
		this.listOponentes = new ArrayList<Oponente>();
		this.listadoDisp = new ArrayList<BluetoothDevice>();
	}
	
	
	//TODO: aqui se llama al receiver una ves que el bluetooth a descubierto a los dispositivos
	//TODO: con bluetooth activado
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		
		if(BluetoothDevice.ACTION_FOUND.equals(action)){
			BluetoothDevice disp = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			this.listadoDisp.add(disp);
			this.oponente = new Oponente(disp.getName(),disp.getAddress());
			this.listOponentes.add(oponente);
			this.select_op.cargarListado(listOponentes);
		}else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
			if(this.listOponentes.size() == 0){
				String nombre = Integer.toString(com.app.TaTeTi.R.string.nofunciona);
				String dir = "000000";
				this.oponente = new Oponente(nombre,dir);
				this.listOponentes.add(oponente);
				this.select_op.cargarListado(listOponentes);
			}
		}
	}

	
}
