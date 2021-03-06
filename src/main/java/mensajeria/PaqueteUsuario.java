package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

public class PaqueteUsuario extends Paquete implements Serializable, Cloneable {

	private static final long serialVersionUID = -6748621185922488055L;
	private int idPj;
	private String username;
	private String password;
	private boolean inicioSesion;
	
	private ArrayList<String> usuariosConectados;
	private boolean estado;
	
	public PaqueteUsuario() { }

	public PaqueteUsuario(int pj, String user, String pw){
		idPj = pj;
		username = user;
		password = pw;
		inicioSesion = false;
		
		estado = true;
	}
	
	public int getIdPj() {
		return idPj;
	}

	public void setIdPj(int idPj) {
		this.idPj = idPj;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isInicioSesion() {
		return inicioSesion;
	}

	public void setInicioSesion(boolean inicioSesion) {
		this.inicioSesion = inicioSesion;
	}
	
	public ArrayList<String> getUsuariosConectados() {
		return usuariosConectados;
	}

	public void setUsuariosConectados(ArrayList<String> usuariosConectados) {
		this.usuariosConectados = usuariosConectados;
	}
	
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
	
	
}
