package entidades;


/*******************
 * 
 * @author pablo
 *
 *******************/
public class Figura {
	
	private int img = 0;
	private String nombre_figura = null;
	
	/******************
	 * 
	 * @return
	 ******************/
	public int getIMG(){
		return this.img;
	}
	
	/**************************
	 * 
	 * @param img
	 **************************/
	public void setIMG(int img){
		this.img = img;
	}
	
	/*******************************
	 * 
	 * @return
	 *******************************/
	public String getNOMBRE_FIGURA(){
		return this.nombre_figura;
	}
	
	/*****************************************
	 * 
	 * @param nombre
	 *****************************************/
	public void setNOMBRE_FIGURA(String nombre){
		this.nombre_figura = nombre;
	}
	
	/***********************************
	 * 
	 * @param img
	 * @param figura
	 ***********************************/
	public Figura(int img, String figura){
		this.img = img;
		this.nombre_figura = figura;
	}
	
}
