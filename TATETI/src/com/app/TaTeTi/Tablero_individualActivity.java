package com.app.TaTeTi;

import modelo.Modelo;

import org.apache.cordova.CordovaActivity;

import controladores.Tablero_individualController;

import android.os.Bundle;

/**
 * ACTIVITY LAYOUT tablero_individual.html
 * @author pablo
 *
 */
public class Tablero_individualActivity extends CordovaActivity {

	private Tablero_individualController tableroController = null;
	private Modelo model = null;
	private Bundle extras = null;
	
	//TODO:aqui se lanza la ventana del juego
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.init();
		this.extras = this.getIntent().getExtras();
		//TODO: aqui se crea la logica (MINIMAX)
		this.model = new Modelo();
		//TODO: aqui se crea el controlador para los eventos del tablero
		this.tableroController = new Tablero_individualController(this,this.model);
		this.tableroController.SetFicha_seleccionada(this.extras.getString("fichaSeleccion"));
		this.tableroController.setFicha_Oponente(this.extras.getString("fichaOponente"));
		this.appView.addJavascriptInterface(this.tableroController,"Tablero_individualController");
		super.loadUrl(this.getString(R.string.tableroindividual));
	}
	
}
