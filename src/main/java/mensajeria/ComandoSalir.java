package mensajeria;

import java.io.IOException;

import mensajeria.Comando;
import mensajeria.Paquete;

public class ComandoSalir extends ComandosCliente {

	@Override
	public void procesar() {
		try {
			cliente.getPaqueteUsuario().setInicioSesion(false);
			cliente.getSalida().writeObject(gson.toJson(new Paquete(Comando.DESCONECTAR), Paquete.class));
			cliente.getSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
