package entidades;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 
 * @author pablo
 *
 */
public class Wrapper_template {
	
	private TextView txtNombre_Oponente = null;
	private ProgressBar progress = null;
	private Button btnConexion = null;
	
	/**
	 * 
	 * @return
	 */
	public TextView getTxtNombre_Oponente(){
		return this.txtNombre_Oponente;
	}
	
	/**
	 * 
	 * @param txtnombre
	 */
	public void setTextNombre_Oponente(TextView txtnombre){
		this.txtNombre_Oponente = txtnombre;
	}
	
	/**
	 * 
	 * @return
	 */
	public ProgressBar getProgress(){
		return this.progress;
	}
	
	/**
	 * 
	 * @param progress
	 */
	public void setProgress(ProgressBar progress){
		this.progress = progress;
	}
	
	/**
	 * 
	 * @return
	 */
	public Button getBtnContext(){
		return this.btnConexion;
	}
	
	/**
	 * 
	 * @param btnConex
	 */
	public void setBtnConexion(Button btnConex){
		this.btnConexion = btnConex;
	}
	
	/**
	 * 
	 */
	public Wrapper_template(){
		//constructor por defecto
	}
	
}
