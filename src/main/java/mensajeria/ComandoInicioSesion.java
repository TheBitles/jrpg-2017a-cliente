package mensajeria;

import javax.swing.JOptionPane;

import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;

public class ComandoInicioSesion extends ComandoCliente {

	@Override
	public void procesar() {
		Paquete paquete = (Paquete) gson.fromJson(objetoLeido, Paquete.class);
		String respuesta = paquete.getMensaje();
		
		if (respuesta.equals(Paquete.msjExito)) {
			cliente.getPaqueteUsuario().setInicioSesion(true);
			cliente.setPaquetePersonaje(gson.fromJson(objetoLeido, PaquetePersonaje.class));
		} else if (respuesta.equals(Paquete.msjFracaso)) {
			JOptionPane.showMessageDialog(null, "Error al iniciar sesión. Revise el usuario y la contraseña");
			cliente.getPaqueteUsuario().setInicioSesion(false);
		}

	}

}
