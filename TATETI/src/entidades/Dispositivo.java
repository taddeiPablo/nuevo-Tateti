package entidades;

/**********************************************
 * 
 * @author pablo
 *
 **********************************************/
public class Dispositivo {
	
	private String nombre_dispositivo = null;
	private String direccion_dispositivo = null;
	
	/***********************************
	 * 
	 * @return
	 ***********************************/
	public String getNombreDispositivo(){
		return this.nombre_dispositivo;
	}
	
	/*********************************************
	 * 
	 * @param nombre
	 *********************************************/
	public void setNombreDispositivo(String nombre){
		this.nombre_dispositivo = nombre;
	}
	
	/***************************************
	 * 
	 * @return
	 ***************************************/
	public String getDireccion_Dispositivo(){
		return this.direccion_dispositivo;
	}
	
	/*****************************************************
	 * 
	 * @param direccion
	 *****************************************************/
	public void setDireccion_dispositivo(String direccion){
		this.direccion_dispositivo = direccion;
	}
	
	/*********************************************************************
	 * 
	 * @param nombre_dispositivo
	 * @param dir_Dispositivo
	 **********************************************************************/
	public Dispositivo(String nombre_dispositivo, String dir_Dispositivo){
		this.nombre_dispositivo = nombre_dispositivo;
		this.direccion_dispositivo = dir_Dispositivo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre_dispositivo + this.getDireccion_Dispositivo();
	}
	
	
	
	
	
}
