package cliente;

import java.io.ObjectInputStream;
import java.util.HashMap;

import com.google.gson.Gson;

import juego.Juego;
import mensajeria.Comando;
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

		try {
			Paquete paquete;
			Comando comando;

			juego.setPersonajesConectados(new HashMap<Integer, PaquetePersonaje>());
			juego.setUbicacionPersonajes(new HashMap<Integer, PaqueteMovimiento>());

			while (true) {
				String objetoLeido = (String) entrada.readObject();
				paquete = gson.fromJson(objetoLeido , Paquete.class);
				Integer comandoActivo = paquete.getComando();
				
				comando = (Comando) Class.forName("mensajeria.Comando" + Comando.COMANDOS[comandoActivo]).newInstance();
				comando.setJuego(juego);
				comando.setObjetoLeido(objetoLeido);
				comando.procesar();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
