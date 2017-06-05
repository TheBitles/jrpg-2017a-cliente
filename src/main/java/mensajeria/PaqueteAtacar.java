package mensajeria;

import java.io.Serializable;
import java.util.HashMap;
import dominio.Personaje;

public class PaqueteAtacar extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idEnemigo;
	private int nuevaSaludPersonaje;
	private int nuevaEnergiaPersonaje;
	private int nuevaSaludEnemigo;
	private int nuevaEnergiaEnemigo;

	public PaqueteAtacar(int id, int idEnemigo, int nuevaSalud, int nuevaEnergia, int nuevaSaludEnemigo,
			int nuevaEnergiaEnemigo) {
		setComando(Comando.ATACAR);
		this.id = id;
		this.idEnemigo = idEnemigo;
		this.nuevaSaludPersonaje = nuevaSalud;
		this.nuevaEnergiaPersonaje = nuevaEnergia;
		this.nuevaSaludEnemigo = nuevaSaludEnemigo;
		this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEnemigo() {
		return idEnemigo;
	}

	public void setIdEnemigo(int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}

	public int getNuevaSaludPersonaje() {
		return nuevaSaludPersonaje;
	}

	public void setNuevaSaludPersonaje(int nuevaSaludPersonaje) {
		this.nuevaSaludPersonaje = nuevaSaludPersonaje;
	}

	public int getNuevaEnergiaPersonaje() {
		return nuevaEnergiaPersonaje;
	}

	public void setNuevaEnergiaPersonaje(int nuevaEnergiaPersonaje) {
		this.nuevaEnergiaPersonaje = nuevaEnergiaPersonaje;
	}

	public int getNuevaSaludEnemigo() {
		return nuevaSaludEnemigo;
	}

	public void setNuevaSaludEnemigo(int nuevaSaludEnemigo) {
		this.nuevaSaludEnemigo = nuevaSaludEnemigo;
	}

	public int getNuevaEnergiaEnemigo() {
		return nuevaEnergiaEnemigo;
	}

	public void setNuevaEnergiaEnemigo(int nuevaEnergiaEnemigo) {
		this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
	}

	/**
	 * Inicializa un HashMap con los atributos salud y energia del personaje
	 * @return mapaPersonaje
	 */

	public HashMap<String, Object> getAllPersonaje() {
		HashMap<String, Object> mapaPersonaje = new HashMap<>();
		mapaPersonaje.put(Personaje.ATRIBUTO_SALUD, getNuevaSaludPersonaje());
		mapaPersonaje.put(Personaje.ATRIBUTO_ENERGIA, getNuevaEnergiaPersonaje());
		return mapaPersonaje;
	}

	/**
	 * Inicializa un HashMap con los atributos salud y energia del enemigo
	 * @return mapaEnemigo
	 */

	public HashMap<String, Object> getAllEnemigo() {
		HashMap<String, Object> mapaEnemigo = new HashMap<>();
		mapaEnemigo.put(Personaje.ATRIBUTO_SALUD, getNuevaSaludEnemigo());
		mapaEnemigo.put(Personaje.ATRIBUTO_ENERGIA, getNuevaEnergiaEnemigo());
		return mapaEnemigo;
	}

}
