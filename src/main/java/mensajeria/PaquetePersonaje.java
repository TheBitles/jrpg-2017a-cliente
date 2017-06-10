package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dominio.Item;
import estados.Estado;

public class PaquetePersonaje extends Paquete implements Serializable, Cloneable {
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

	private ArrayList<Item> inventario = new ArrayList<Item>();
	
	private ArrayList<Item> inventarioCompleto = new ArrayList<Item>();

	public PaquetePersonaje() {
		estado = Estado.estadoOffline;
	}

	public ArrayList<Item> getInventario() {
		return inventario;
	}
	
	public void setInventario(final ArrayList<Item> inventario) {
		this.inventario = inventario;
	}

	public void agregarItem(final Item item) {
		inventario.add(item);
	}
	
	public void agregarATodos(final Item item) {
		inventarioCompleto.add(item);
	}

	public void eliminarItem(final Item item) {
		inventario.remove(item);
	}

	public Item randomItem() {
		int index = (new Random()).nextInt(12);
		return inventarioCompleto.get(index);
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(final int estado) {
		this.estado = estado;
	}

	public int getMapa(){
		return idMapa;
	}

	public void setMapa(final int mapa){
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
