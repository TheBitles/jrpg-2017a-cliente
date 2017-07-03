package mensajeria;

import java.io.Serializable;
import java.util.Map;

public class PaqueteDePersonajes extends Paquete implements Serializable, Cloneable {
	private static final long serialVersionUID = -1013654067204550789L;
	private Map<Integer, PaquetePersonaje> personajes;

	public PaqueteDePersonajes() { }
	
	public PaqueteDePersonajes(Map<Integer, PaquetePersonaje> personajes){
		this.personajes = personajes;
	}

	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

	public Map<Integer, PaquetePersonaje> getPersonajes(){
		return personajes;
	}
}