package entidades;

/**
 * 
 * @author pablo
 *
 */
public class Oponente {
	
	private String nombre_oponente = null;
	private String direccion_oponente = null;
	
	//TODO:metodo getter del atributo nombre
	public String getNombre_Oponente(){
		return this.nombre_oponente;
	}
	
	//TODO:metodo setter del atributo nombre
	public void setNombre_Oponente(String nombre){
		this.nombre_oponente = nombre;
	}
	
	//TODO:metodo getter del atributo direccion
	public String getDireccion_oponente(){
		return this.direccion_oponente;
	}
	
	//TODO:metodo setter del atributo direccion
	public void setDireccion_oponente(String direccion){
		this.direccion_oponente = direccion;
	}
	
	public Oponente(String nombre, String direccion){
		this.nombre_oponente = nombre;
		this.direccion_oponente = direccion;
	}
}
