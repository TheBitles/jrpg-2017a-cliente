package mensajeria;

import javax.swing.JOptionPane;

import mensajeria.PaquetePersonaje;

public class ComandoCrearPersonaje extends Comando {

	@Override
	public void procesar() {
		JOptionPane.showMessageDialog(null, "Registro exitoso.");
		cliente.setPaquetePersonaje((PaquetePersonaje) gson.fromJson(objetoLeido, PaquetePersonaje.class)); 
		cliente.getPaqueteUsuario().setInicioSesion(true);
	}

}
