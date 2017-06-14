package mensajeria;

import java.io.Serializable;
import java.util.Map;

public class PaqueteDeMovimientos extends Paquete implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4785987956070027880L;
	private Map<Integer, PaqueteMovimiento> personajes;

	public PaqueteDeMovimientos(){

	}
	
	public PaqueteDeMovimientos(Map<Integer, PaqueteMovimiento> personajes){
		this.personajes = personajes;
	}
	
	public Map<Integer, PaqueteMovimiento> getPersonajes(){
		return personajes;
	}

	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

}