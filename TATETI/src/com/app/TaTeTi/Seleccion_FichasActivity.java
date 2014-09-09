package com.app.TaTeTi;

import org.apache.cordova.CordovaActivity;

import controladores.Seleccion_fichas_individualController;

import android.os.Bundle;

/**
 * ACTIVITY DEL LAYOUT seleccion_fichas.html
 * @author pablo
 *
 */
public class Seleccion_FichasActivity extends CordovaActivity {

	private Seleccion_fichas_individualController controller = null;
	
	//TODO: aqui se lanza la ventana de seleccion de fichas
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.init();
		// TODO: aqui se crea el controlador para los eventos de la ventana de seleccion de fichas
		this.controller = new Seleccion_fichas_individualController(this);
		//TODO: aqui se enlaza esta activity con el layout html5
		this.appView.addJavascriptInterface(this.controller,"Seleccion_fichas_individualController");
		super.loadUrl(this.getString(R.string.seleccionFichas));
	}
	
}
