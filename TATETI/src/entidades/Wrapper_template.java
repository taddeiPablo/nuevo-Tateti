package entidades;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Wrapper_template {
	
	private TextView txtNombre_Oponente = null;
	private ProgressBar progress = null;
	private Button btnConexion = null;
	
	
	public TextView getTxtNombre_Oponente(){
		return this.txtNombre_Oponente;
	}
	
	public void setTextNombre_Oponente(TextView txtnombre){
		this.txtNombre_Oponente = txtnombre;
	}
	
	public ProgressBar getProgress(){
		return this.progress;
	}
	
	public void setProgress(ProgressBar progress){
		this.progress = progress;
	}
	
	public Button getBtnContext(){
		return this.btnConexion;
	}
	
	public void setBtnConexion(Button btnConex){
		this.btnConexion = btnConex;
	}
	
	
	public Wrapper_template(){
		//constructor por defecto
	}
	
}
