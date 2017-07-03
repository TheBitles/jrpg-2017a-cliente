package mensajeria;

import java.util.Map;

import frames.Chat;
import frames.MenuChat;
import juego.Pantalla;
import mensajeria.PaqueteMensaje;

public class ComandoConversar extends Comando {

	public final static String NOMBRESALA = "Chat General";
	
	@Override
	public void procesar() {
		Chat chat = null;

		juego.getCliente().setPaqueteMensaje((PaqueteMensaje) gson.fromJson(objetoLeido, PaqueteMensaje.class));

		PaqueteMensaje pm = juego.getCliente().getPaqueteMensaje();
		String emisor = pm.getEmisor();
		String receptor = pm.getReceptor();
		String mensaje = pm.getContenido();
		
		Map<String, Chat> chatsActivos = juego.getChatsActivos();
		
		if (receptor != null){
			emisor = NOMBRESALA;
		} else {
			//if (Pantalla.ventContac != null) {
			MenuChat.getBotonChatPublico().setEnabled(false);					
			//}
		}
		
			
		if (!(juego.getChatsActivos().containsKey(emisor))) {
			chat = new Chat(juego.getCliente()); // wtf
			chat.setTitle(emisor);
			chat.setVisible(true);

			chatsActivos.put(emisor, chat);			
		}

		chatsActivos.get(emisor).getChat().append(emisor + ": "  + mensaje + "\n");
		chatsActivos.get(emisor).getTexto().grabFocus();
	}
}
