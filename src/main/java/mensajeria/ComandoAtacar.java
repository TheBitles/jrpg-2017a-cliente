package mensajeria;

import mensajeria.PaqueteAtacar;

public class ComandoAtacar extends ComandoCliente {

	@Override
	public void procesar() {
		PaqueteAtacar paqueteAtacar = (PaqueteAtacar) gson.fromJson(objetoLeido, PaqueteAtacar.class);
		juego.getEstadoBatalla().getEnemigo().actualizar(paqueteAtacar.getMapaPersonaje());
		juego.getEstadoBatalla().getPersonaje().actualizar(paqueteAtacar.getMapaEnemigo());
		juego.getEstadoBatalla().setMiTurno(true);
	}

}
