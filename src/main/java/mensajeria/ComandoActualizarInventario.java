package mensajeria;

import mensajeria.PaquetePersonaje;

public class ComandoActualizarInventario extends ComandoCliente {

	@Override
	public void procesar() {
		PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson.fromJson(objetoLeido, PaquetePersonaje.class);
		juego.getPersonajesConectados().remove(paquetePersonaje.getId());
		juego.getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);
		if (juego.getPersonaje().getId() == paquetePersonaje.getId()) {
			juego.actualizarPersonaje();
			juego.getEstadoJuego().actualizarPersonaje();
			juego.getCliente().setItems(paquetePersonaje);
			juego.getCliente().setPaquetePersonaje(juego.getPersonajesConectados().get(paquetePersonaje.getId()));
		}

	}

}
