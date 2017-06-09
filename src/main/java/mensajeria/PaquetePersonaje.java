package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

import dominio.Item;
import estados.Estado;

public class PaquetePersonaje extends Paquete implements Serializable, Cloneable {

	private static final int SOLTAR = -1;
	private static final int EQUIPAR = 1;

	private int id;
	private int idMapa;
	private int estado;
	private String casta;
	private String nombre;
	private String raza;
	private int saludTope;
	private int energiaTope;
	private int fuerza;
	private int destreza;
	private int inteligencia;
	private int nivel;
	private int experiencia;

	private int espacioInventarioMaximo;
	private ArrayList<Item> inventario = new ArrayList<Item>();

	public PaquetePersonaje() {
		estado = Estado.estadoOffline;
	}

	public ArrayList<Item> getItems() {
		return inventario;
	}

	public final void anadirItem(int idItem, String nombre, int wearLocation, int bonusSalud, int bonusEnergia, int bonusAtaque, int bonusDefensa, int bonusMagia, String foto, String fotoEquipado) {
		try {
			items.add(new Item(idItem,nombre,wearLocation,bonusSalud,bonusEnergia,bonusAtaque, bonusDefensa, bonusMagia, foto, fotoEquipado));
			useBonus(bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa, bonusMagia);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void agregarItem(Item item) {
		if(espacioSuficiente()) {
			inventario.add(item);
			actualizarAtributos(item, EQUIPAR);
		}
	}

	public void eliminarItem(Item item) {
		inventario.remove(item);
		actualizarAtributos(item, SOLTAR);
	}

	public void actualizarAtributos(Item item, int accion) {
		this.fuerza += item.getFuerza() * accion;
		this.inteligencia += item.getInteligencia() * accion;
		this.destreza += item.getDestreza() * accion;

		this.saludTope += item.getSalud() * accion;
		this.energiaTope += item.getEnergia() * accion;
	}

	public void setAtributosSegunItems(int accion) {
		for(Item item : inventario) {
			actualizarAtributos(inventario.get(item), accion);
		}
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getMapa(){
		return idMapa;
	}

	public void setMapa(int mapa){
		idMapa = mapa;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCasta() {
		return casta;
	}


	public void setCasta(String casta) {
		this.casta = casta;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getRaza() {
		return raza;
	}


	public void setRaza(String raza) {
		this.raza = raza;
	}


	public int getSaludTope() {
		return saludTope;
	}


	public void setSaludTope(int saludTope) {
		this.saludTope = saludTope;
	}


	public int getEnergiaTope() {
		return energiaTope;
	}


	public void setEnergiaTope(int energiaTope) {
		this.energiaTope = energiaTope;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}

	public int getDestreza() {
		return destreza;
	}

	public int getInteligencia() {
		return inteligencia;
	}


	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

	private boolean espacioSuficiente() {
		return this.espacioInventarioMaximo - this.inventario.size() > 0;
	}

}
