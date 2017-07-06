package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

import dominio.Intercambiable;

public class PaqueteIntercambiable extends Paquete implements Serializable, Cloneable {

	@Override
	public String toString() {
		return "PaqueteIntercambiable [intercambiables=" + intercambiables + ", personajeId=" + personajeId + "]";
	}

	private static final long serialVersionUID = 1L;
	private ArrayList<Intercambiable> intercambiables;
	private Integer personajeId;
	
	public PaqueteIntercambiable(){
		setComando(Comando.INTERCAMBIO);
	}

	public ArrayList<Intercambiable> getIntercambiables() {
		return intercambiables;
	}
	
	public void setIntercambiables(ArrayList<Intercambiable> intercambiables) {
		this.intercambiables = intercambiables;
	}
	
	public Integer getPersonajeId() {
		return personajeId;
	}
	
	public void setPersonajeId(Integer personajeId) {
		this.personajeId = personajeId;
	}
}
