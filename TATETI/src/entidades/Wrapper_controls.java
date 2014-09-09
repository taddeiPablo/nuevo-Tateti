package entidades;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author pablo
 *
 */
public class Wrapper_controls {
	
	private TextView txtTitulo = null;
	private TextView txtcontrol = null;
	private ImageView img = null;
	
	//TODO:metodo getter del titulo
	public TextView getTxtTitulo(){
		return this.txtTitulo;
	}
	
	//TODO:metodo setter del titulo
	public void setTxtTitulo(TextView txtTitulo){
		this.txtTitulo = txtTitulo;
	}
	
	//TODO:metodo getter del control
	public TextView getTxtControl(){
		return this.txtcontrol;
	}
	
	//TODO: metodo setter del control
	public void setTxtControl(TextView txt){
		this.txtcontrol = txt;
	}
	
	//TODO: metodo getter imagen
	public ImageView getImagen(){
		return this.img;
	}
	
	//TODO: metodo setter imagen
	public void setImagen(ImageView imageView){
		this.img = imageView;
	}
	
	public Wrapper_controls(){
		//nada
	}
}
