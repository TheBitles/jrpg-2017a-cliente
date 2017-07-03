package mensajeria;

import mensajeria.PaqueteDeMovimientos;

public class ComandoMovimiento extends Comando {

	@Override
	public void procesar() {
		PaqueteDeMovimientos pdm = (PaqueteDeMovimientos) gson.fromJson(objetoLeido,PaqueteDeMovimientos.class);
		juego.setUbicacionPersonajes(pdm.getPersonajes());
		
	}

}
