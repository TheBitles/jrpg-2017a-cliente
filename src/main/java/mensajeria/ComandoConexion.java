package mensajeria;

import java.util.Map;

import frames.MenuChat;
import mensajeria.PaqueteDePersonajes;
import mensajeria.PaquetePersonaje;

public class ComandoConexion extends ComandoCliente {

	
	@Override
	public void procesar() {
		PaqueteDePersonajes paqueteDePersonajes = (PaqueteDePersonajes) gson.fromJson(objetoLeido, PaqueteDePersonajes.class);
		Map<Integer, PaquetePersonaje> personajesConectados = paqueteDePersonajes.getPersonajes();

		juego.setPersonajesConectados(personajesConectados);
		MenuChat.setPersonajesConectados(juego);
	}
}
