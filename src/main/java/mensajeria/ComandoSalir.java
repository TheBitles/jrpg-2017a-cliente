package mensajeria;

import java.io.IOException;

import javax.swing.JOptionPane;

import mensajeria.Comando;
import mensajeria.Paquete;

public class ComandoSalir extends ComandoCliente {

	@Override
	public void procesar() {
		cliente.getPaqueteUsuario().setInicioSesion(false);

		try {
			cliente.getSalida().writeObject(gson.toJson(new Paquete(Comando.DESCONECTAR), Paquete.class));
			cliente.getSocket().close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo al desconectar la aplicacion.");
		}

	}

}
