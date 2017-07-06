package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

public class PaqueteIntercambio extends Paquete implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private Integer intercambio;

	public PaqueteIntercambio() {
		setComando(Comando.INTERCAMBIO);
	}

  public Integer getIntercambio() {
		return intercambio;
	}

	public void setIntercambio(Integer intercambio) {
		this.intercambio = intercambio;
	}
}
