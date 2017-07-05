package mensajeria;

import java.util.Map;

import mensajeria.PaquetePersonaje;

public class ComandoActualizarPersonaje extends ComandoCliente {

	@Override
	public void procesar() {
		Map<Integer, PaquetePersonaje> personajesConectados = juego.getPersonajesConectados();
		PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson.fromJson(objetoLeido, PaquetePersonaje.class);
		Integer personajeId = paquetePersonaje.getId();

		personajesConectados.remove(personajeId);
		personajesConectados.put(personajeId, paquetePersonaje);
		
		if (personajeId == juego.getPersonaje().getId()) {
			juego.actualizarPersonaje();
			juego.getEstadoJuego().actualizarPersonaje();

			juego.getCliente().setPaquetePersonaje(personajesConectados.get(personajeId));
			juego.getCliente().setItems(paquetePersonaje);
		}

	}

}
