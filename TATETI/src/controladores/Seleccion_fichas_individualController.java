package controladores;

import org.apache.cordova.CordovaActivity;

import com.app.TaTeTi.R;
import com.app.TaTeTi.Tablero_individualActivity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

/**
 * DEFINICION DEL CONTROLADOR PARA LA VENTANA DE SELECCION DE FICHAS
 * @author pablo
 *
 */
public class Seleccion_fichas_individualController {
	private Intent intentSeleccion = null;
	private CordovaActivity act = null;
	private Bundle extras = null;
	
	public Seleccion_fichas_individualController(CordovaActivity activity){
		this.act = activity;
	}
	
	//TODO:METODO POR EL CUAL SE SELECCIONA UNA FICHA Y SE ABRE LA NUEVA VENTANA QU ES EL TABLERO
    @JavascriptInterface
	public void seleccionFichas_onClick(String ficha){
		this.extras = new Bundle();
		if(ficha.equals(this.act.getString(R.string.ficha1))){
			this.extras.putString("fichaSeleccion", this.act.getString(R.string.ficha1));
			this.extras.putString("fichaOponente", this.act.getString(R.string.ficha2));
		}else if(ficha.equals(this.act.getString(R.string.ficha2))){
			this.extras.putString("fichaSeleccion", this.act.getString(R.string.ficha2));
			this.extras.putString("fichaOponente", this.act.getString(R.string.ficha1));
		}
		this.intentSeleccion = new Intent(this.act,Tablero_individualActivity.class);
		this.intentSeleccion.putExtras(this.extras);
		this.act.startActivity(intentSeleccion);
	}
	
    //TODO:METODO POR EL CUAL SE VUELVE A LA VENTANA PRINCIPAL
    @JavascriptInterface
    public void volver_inicio(){
    	this.act.finish();
    }
}
