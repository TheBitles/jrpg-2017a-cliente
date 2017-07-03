package mensajeria;

import estados.Estado;
import estados.EstadoBatalla;
import mensajeria.PaqueteBatalla;

public class ComandoBatalla extends Comando {

	@Override
	public void procesar() {
		PaqueteBatalla paqueteBatalla = (PaqueteBatalla) gson.fromJson(objetoLeido, PaqueteBatalla.class);
		juego.getPersonaje().setEstado(Estado.estadoBatalla);
		Estado.setEstado(null);
		juego.setEstadoBatalla(new EstadoBatalla(juego, paqueteBatalla));
		Estado.setEstado(juego.getEstadoBatalla());
	}

}
