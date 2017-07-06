package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

import dominio.Item;

public class PaqueteInventario extends Paquete implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Item> items;
	private Integer personajeId;

	public PaqueteInventario() {
		setComando(Comando.GUARDARINVENTARIO);
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void setPersonajeId(Integer personajeId) {
		this.personajeId = personajeId;
	}
	
	public Integer getPersonajeId() {
		return personajeId;
	}
}
