package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

import dominio.Item;
import estados.Estado;

public class PaquetePersonaje extends Paquete implements Serializable, Cloneable {

	private static final int ITEM_FUE_EQUIPADO = 1;
	private static final int ITEM_FUE_ELIMINADO = 0;
	
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
	
	private int espacioInventario;
	private ArrayList<Item> inventario = new ArrayList<Item>();
	
	public PaquetePersonaje() {
		estado = Estado.estadoOffline;
	}

	public ArrayList<Item> getItems() {
		return inventario;
	}
	
	public void agregarItem(Item item) {
		if(tieneEspacio()) {
			inventario.add(item);
			actualizarAtributos(item, ITEM_FUE_EQUIPADO);
		}
	}
	
	public void eliminarItem(Item item) {
		if(inventario.size() > 0) {
			inventario.remove(item);
			actualizarAtributos(item, ITEM_FUE_ELIMINADO);
		}
	}

	public void actualizarAtributos(Item item, int estadoItem) {
		if(estadoItem == ITEM_FUE_EQUIPADO) {
			this.fuerza += item.getFuerza();
			this.saludTope += item.getSalud();
			this.inteligencia += item.getInteligencia();
			this.destreza += item.getDestreza();
			this.energiaTope += item.getEnergia();
		}
		if(estadoItem == ITEM_FUE_ELIMINADO) {
			this.fuerza -= item.getFuerza();
			this.saludTope -= item.getSalud();
			this.inteligencia -= item.getInteligencia();
			this.destreza -= item.getDestreza();
			this.energiaTope -= item.getEnergia();
		}
	}
	
	// para el login
	public void aplicarAtributosItem() {
		for(int i = 0 ; i < inventario.size() ; i++) {
			this.fuerza += inventario.get(i).getFuerza();
			this.saludTope += inventario.get(i).getSalud();
			this.inteligencia += inventario.get(i).getInteligencia();
			this.destreza += inventario.get(i).getDestreza();
			this.energiaTope += inventario.get(i).getEnergia();
		}
	}
	
	// para el logout
	public void removerAtributosItem() {
		for(int i = 0 ; i < inventario.size() ; i++) {
			this.fuerza -= inventario.get(i).getFuerza();
			this.saludTope -= inventario.get(i).getSalud();
			this.inteligencia -= inventario.get(i).getInteligencia();
			this.destreza -= inventario.get(i).getDestreza();
			this.energiaTope -= inventario.get(i).getEnergia();
		}
	}
	
	private boolean tieneEspacio() {
		return this.espacioInventario - this.inventario.size() > 0 ? true : false;
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
	
}
