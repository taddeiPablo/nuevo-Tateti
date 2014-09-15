package adapter;

import java.util.List;

import entidades.Oponente;
import entidades.Wrapper_template;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * =========================================================
 * ADAPTADOR PARA EL LISTADO DE OPONENTES
 * @author Pablo Taddei
 *==========================================================
 */
@SuppressLint("InflateParams")
public class Adapter_Oponentes extends ArrayAdapter<Oponente>{
	
	private LayoutInflater inflater = null;
	private View vista = null;
	private Wrapper_template template = null;
	private Oponente oponentes = null;
	private Activity act = null;
	
	/**
	 * METODO QUE RECIBE UN TIPO ACTIVITY
	 * @param activity
	 */
	public void setActivity(Activity activity){
		this.act = activity;
	}
	
	/**
	 * CONSTRUCTOR DE LA CLASE QUE RECIBE VARIOS PARAMETROS
	 * @param context (o activity)
	 * @param resource (aqui le pasamos la lista xml)
	 * @param objects (una coleccion que sera mostrada en la lista)
	 */
	public Adapter_Oponentes(Context context, int resource, List<Oponente> objects) {
		super(context, resource, objects);
		this.inflater = LayoutInflater.from(context);
	}
	

	/**
	 * METODO POR EL CUAL INFLAMOS NUESTRO LAYOUT
	 * CUSTOM, LUEGO LE CARGAMOS LOS DATOS DE LA COLECCION
	 * Y SE DEVUELVE LA VIEW COMPLETA
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView == null){
			this.vista = this.inflater.inflate(com.app.TaTeTi.R.layout.item_oponente, null);
			this.template = new Wrapper_template();
			this.template.setTextNombre_Oponente((TextView)this.vista.findViewById(com.app.TaTeTi.R.id.textOponente));
			this.vista.setTag(template);
		}else{
			this.vista = convertView;
			this.template = (Wrapper_template)this.vista.getTag();
		}
		this.oponentes = getItem(position);
		this.template.getTxtNombre_Oponente().setTypeface(getFont());
		this.template.getTxtNombre_Oponente().setText(this.oponentes.getNombre_Oponente());
		
		return this.vista;
	}
	
	/**
	 * METODO DESDE EL CUAL MODIFICAMOS EL TIPO DE LETRA 
	 * EN LOS ITEMS DE LA LISTA
	 * @return
	 */
	public Typeface getFont(){
		Typeface fuente = Typeface.createFromAsset(this.act.getAssets(), "www/font/ComicRelief.ttf");
		return fuente;
	}
}
