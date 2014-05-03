package adapter;

import java.util.List;

import com.app.TaTeTi.Seleccion_oponenteActivity;
import entidades.Oponente;
import entidades.Wrapper_template;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * 
 * @author pablo
 *
 */
public class Adapter_Oponentes extends ArrayAdapter<Oponente>{
	
	private LayoutInflater inflater = null;
	private View vista = null;
	private Wrapper_template template = null;
	private Oponente oponentes = null;
	
	//TODO:
	public void setActivity(Seleccion_oponenteActivity contex){
		
	}
	
	//TODO:
	public Adapter_Oponentes(Context context, int resource, List<Oponente> objects) {
		super(context, resource, objects);
		this.inflater = LayoutInflater.from(context);
	}
	

	//TODO:
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
		this.template.getTxtNombre_Oponente().setText(this.oponentes.getNombre_Oponente());
		
		return this.vista;
	}
	
}
