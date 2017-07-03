package mensajeria;

import estados.Estado;
import mensajeria.PaqueteFinalizarBatalla;

public class ComandoFinalizarBatalla extends Comando {

	@Override
	public void procesar() {
		PaqueteFinalizarBatalla paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson.fromJson(objetoLeido, PaqueteFinalizarBatalla.class);
		juego.getPersonaje().setEstado(Estado.estadoJuego);
		Estado.setEstado(juego.getEstadoJuego());
	}
	
}
