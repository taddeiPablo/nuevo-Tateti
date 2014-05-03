package controladores;


import org.apache.cordova.CordovaActivity;
import com.app.TaTeTi.Seleccion_FichasActivity;
import com.app.TaTeTi.Tablero_bluetoothActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.webkit.JavascriptInterface;

/**
 * DEFINICION DEL CONTROLADOR PARA LA VENTANA INICIAL DE LA APP.
 * @author pablo
 *
 */
public class TatetiController implements DialogInterface.OnClickListener{
	
	private Intent intentView = null;
	private CordovaActivity act = null;
	private boolean cierre = false;
	
	
	
	/**
	 * 
	 * @param activity
	 */
	public TatetiController(CordovaActivity activity){
		this.act = activity;
	}
	
	 /**
	  * METODO POR EL CUAL SE ABRE LA VENTANA DE SELECCION DE FICHAS
	  * ATRAVES DE @JavascriptInterface se determina que esta funcion podra ser accedida
	  *desde javascript o cualquier framework de js.
	  **/ 
	@JavascriptInterface
	public void abrirVentana_onclick(){
		this.intentView = new Intent(this.act,Seleccion_FichasActivity.class);
    	this.act.startActivity(this.intentView);
	}
	
	/**
	 *	METODO POR EL CUAL SE ABRE LA VENTANA DE SELECCION DEL OPONENTE
	 **/
	@JavascriptInterface
	public void abrirVentanaOponente_onclick(){
		Intent listado = new Intent(this.act,Tablero_bluetoothActivity.class);
		this.act.startActivity(listado);
	}
	
	/**
	 * METODO POR EL CUAL SE CIERRA LA APP
	 */
	@JavascriptInterface
	public void cerrar_app(){
		this.cierre = true;
		AlertDialog.Builder mensaje = new AlertDialog.Builder(this.act);
		mensaje.setTitle("ATENCION");
		mensaje.setMessage("ESTA SEGURO QUE DESEA CERRAR ESTA APLICACION");
		mensaje.setCancelable(true);
		mensaje.setPositiveButton("salir", this);
		mensaje.setNegativeButton("Cancelar",this);
		
		AlertDialog alerta = mensaje.create();
		alerta.show();
	}
	
	/**
	 * metodo para cerrar la aplicacion
	 */
	public void cerrar(){
		this.act.finish();
	}
	
	/**
	 * metodo onclick de la ventana de alerta
	 */
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		switch(which){
		case -1:
			if(this.cierre){
				cerrar();
			}else{
				dialog.cancel();
			}
			break;
		case -2:
			//aca nada
			break;
		}
	}
}
