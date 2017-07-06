package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

public class PaqueteIntercambio extends Paquete implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> intercambios;

	public PaqueteIntercambio(){
	}

  public ArrayList<Integer> getIntercambiados() {
		return intercambios;
	}

	public void setIntercambiados(ArrayList<Integer> intercambios) {
		this.intercambios = intercambios;
	}
}
