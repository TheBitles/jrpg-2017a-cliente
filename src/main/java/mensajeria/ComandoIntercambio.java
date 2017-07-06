package mensajeria;

import frames.MenuMercado;

public class ComandoIntercambio extends ComandoCliente {

	@Override
	public void procesar() {
		PaqueteIntercambio pm = (PaqueteIntercambio) gson.fromJson(objetoLeido, PaqueteIntercambio.class);
		MenuMercado.getInstance().actualizar(pm.getIntercambio());
		System.out.println("HOLA MUNDO CRUELO " + pm.getIntercambio());
	}

}
