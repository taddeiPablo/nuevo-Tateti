package controladores;

import java.util.ArrayList;

import com.app.TaTeTi.Seleccion_oponenteActivity;

import entidades.Oponente;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Seleccion_oponenteController implements OnItemClickListener,OnClickListener,OnCheckedChangeListener {

	private Activity act = null;
	private BluetoothAdapter bAdapter = null;
	private ArrayList<Oponente> listado = null;
	private Seleccion_oponenteActivity selectAct = null;
	
	/**
	 * 
	 * @param listado
	 */
	public void setListado_oponentes(ArrayList<Oponente> listado){
		this.listado = listado;
	}
	
	
	/**
	 * 
	 * @param activity
	 * @param Badapter
	 */
	public Seleccion_oponenteController(Activity activity,BluetoothAdapter Badapter,Seleccion_oponenteActivity selectAct){
		this.act = activity;
		this.bAdapter = Badapter;
		this.selectAct = selectAct;
	}
	
	
	/**
	 * 
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		try{
			this.bAdapter.cancelDiscovery();
			Oponente oponente = this.listado.get(position);
			String direccion = oponente.getDireccion_oponente();
			String nombre = oponente.getNombre_Oponente();
			Intent enviar = new Intent();
			enviar.putExtra("device_address", direccion);
			enviar.putExtra("device_name", nombre);
			this.act.setResult(Activity.RESULT_OK, enviar);
			this.act.finish();
		}catch(Exception ex){
			
		}
	}


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(isChecked){
			this.act.finish();
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.selectAct.busqueda_oponentes();
	}
	
	
	
	
}
