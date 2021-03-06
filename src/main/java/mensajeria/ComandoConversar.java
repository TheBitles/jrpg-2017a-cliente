package mensajeria;

import java.util.Map;
import frames.Chat;
import mensajeria.PaqueteMensaje;

public class ComandoConversar extends ComandoCliente {

	public final static String NOMBRESALA = "Chat General";

	@Override
	public void procesar() {
		juego.getCliente().setPaqueteMensaje((PaqueteMensaje) gson.fromJson(objetoLeido, PaqueteMensaje.class));

		Map<String, Chat> chatsActivos = juego.getChatsActivos();

		PaqueteMensaje pm = juego.getCliente().getPaqueteMensaje();
		String emisor = pm.getEmisor();
		String receptor = pm.getReceptor();
		String contenido = pm.getContenido();
		String objetivo = receptor == null ? NOMBRESALA : emisor;

		if (!(chatsActivos.containsKey(objetivo))) {
			Chat chat = new Chat(juego, objetivo);
			chat.setVisible(true);
		}

		chatsActivos.get(objetivo).getChat().append(emisor + ": " + contenido + "\n");
		chatsActivos.get(objetivo).getTexto().grabFocus();
	}
}
