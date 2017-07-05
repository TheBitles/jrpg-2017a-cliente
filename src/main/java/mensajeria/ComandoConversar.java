package mensajeria;

import java.util.Map;
import frames.Chat;
import mensajeria.PaqueteMensaje;

public class ComandoConversar extends Comando {

	public final static String NOMBRESALA = "Chat General";
	
	@Override
	public void procesar() {
		juego.getCliente().setPaqueteMensaje((PaqueteMensaje) gson.fromJson(objetoLeido, PaqueteMensaje.class));
		
		Chat chat;
		Map<String, Chat> chatsActivos = juego.getChatsActivos();

		PaqueteMensaje pm = juego.getCliente().getPaqueteMensaje();
		String emisor = pm.getEmisor();
		String receptor = pm.getReceptor();
		String contenido = pm.getContenido();
		String objetivo = receptor == null ? NOMBRESALA : emisor;

		System.out.println(pm.toString() + ":objetivo: " + objetivo);
		for(Map.Entry<String, Chat> c : chatsActivos.entrySet()) {
			System.out.println(c.getKey());
		}

		if (!(chatsActivos.containsKey(objetivo))) {
			chat = new Chat(juego);
			chat.setTitle(objetivo);
			chat.setVisible(true);
			chatsActivos.put(objetivo, chat);	
			juego.setChatsActivos(chatsActivos);
		}

		chatsActivos.get(objetivo).getChat().append(emisor + ": " + contenido + "\n");
		chatsActivos.get(objetivo).getTexto().grabFocus();
	}
}
