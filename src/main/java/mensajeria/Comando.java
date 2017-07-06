package mensajeria;

import com.google.gson.Gson;

import cliente.Cliente;
import juego.Juego;

public abstract class Comando {

	public static final int CONEXION = 0;
	public static final int CREACIONPJ = 1;
	public static final int DESCONECTAR = 2;
	public static final int INICIOSESION = 3;
	public static final int MOSTRARMAPAS = 4;
	public static final int MOVIMIENTO = 5;
	public static final int REGISTRO = 6;
	public static final int SALIR = 7;
	public static final int BATALLA = 8;
	public static final int ATACAR = 9;
	public static final int FINALIZARBATALLA = 10;
	public static final int ACTUALIZARPERSONAJE = 11;
	public static final int GUARDARINVENTARIO = 12;
	public static final int CONVERSAR = 13;
	public static final int INTERCAMBIO = 14;

	public static final String[] COMANDOS = {
			"Conexion",
			"CrearPersonaje",
			"Desconectar",
			"InicioSesion",
			"MostrarMapas",
			"Movimiento",
			"Registro",
			"Salir",
			"Batalla",
			"Atacar",
			"FinalizarBatalla",
			"ActualizarPersonaje",
			"GuardarInventario",
			"Conversar",
			"Intercambio"
	};

	public String objetoLeido;
	public final Gson gson = new Gson();

	public void setObjetoLeido(final String objetoLeido) {
		this.objetoLeido = objetoLeido;
	}

	public abstract void procesar();
}
