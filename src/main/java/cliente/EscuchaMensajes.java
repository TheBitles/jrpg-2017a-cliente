package cliente;

import java.io.ObjectInputStream;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import juego.Juego;
import mensajeria.Comando;
import mensajeria.ComandoCliente;
import mensajeria.Paquete;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;


public class EscuchaMensajes extends Thread {

	private ObjectInputStream entrada;
	private Juego juego;
	private Cliente cliente;
	private final Gson gson = new Gson();

	public EscuchaMensajes(final Juego juego) {
		this.juego = juego;
		this.cliente = juego.getCliente();
		this.entrada = cliente.getEntrada();
	}

	@Override
	public void run() {

		Paquete paquete;
		ComandoCliente comando;

		juego.setPersonajesConectados(new HashMap<Integer, PaquetePersonaje>());
		juego.setUbicacionPersonajes(new HashMap<Integer, PaqueteMovimiento>());

			while (true) {

				try {
					String objetoLeido = (String) entrada.readObject();
					paquete = gson.fromJson(objetoLeido , Paquete.class);

					comando = (ComandoCliente) paquete.getByReflection("mensajeria");
					comando.setJuego(juego);
					comando.setObjetoLeido(objetoLeido);
					comando.procesar();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error al intentar leer la entrada");
				}
			}
	}
}
