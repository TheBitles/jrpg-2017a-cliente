package juego;

import java.io.IOException;

import javax.swing.JOptionPane;

import cliente.Cliente;
import mensajeria.Comando;
import recursos.Recursos;

public class CargarRecursos extends Thread {

	private Cliente cliente;

	public CargarRecursos(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
		synchronized (cliente) {
			try {
				Recursos.cargar(cliente.getMenuCarga());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Fallo la carga de recursos del juego.");
			}

			cliente.setAccion(Comando.SALIR);
			cliente.notify();
		}
	}

}
