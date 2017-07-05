package mensajeria;

import javax.swing.JOptionPane;
import frames.MenuCreacionPj;
import mensajeria.Paquete;

public class ComandoRegistro extends ComandoCliente {

	@Override
	public void procesar() {
		synchronized(this) {		
			Paquete paquete = (Paquete) gson.fromJson(objetoLeido, Paquete.class);

			if (paquete.getMensaje().equals(Paquete.msjExito)) {

				MenuCreacionPj menuCreacionPJ = new MenuCreacionPj(cliente, cliente.getPaquetePersonaje());
				menuCreacionPJ.setVisible(true);

			} else {
				if (paquete.getMensaje().equals(Paquete.msjFracaso)) {
					JOptionPane.showMessageDialog(null, "No se pudo registrar.");
				}
				// El usuario no pudo iniciar sesión
				cliente.getPaqueteUsuario().setInicioSesion(false);
			}
		}
		
	}
	
}