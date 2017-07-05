package mensajeria;


import cliente.Cliente;
import juego.Juego;

public abstract class ComandoCliente extends Comando {

	protected Juego juego;
	protected Cliente cliente;

	public void setJuego(final Juego juego) {
		this.juego = juego;
	}
	
	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}
}
