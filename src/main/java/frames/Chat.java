package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.ComandoConversar;
import mensajeria.PaqueteMensaje;
import java.awt.Color;
import java.awt.Font;

public class Chat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField texto;
	private JTextArea chat;
	private Juego juego;
	private Cliente cliente;
	private String nombreSala;
	private final Gson gson = new Gson();

	/**
	 * Create the frame.
	 * @param nombresala
	 */
	public Chat(final Juego juego, final String nombreSala) {
		this.juego = juego;
		this.cliente = juego.getCliente();
		this.nombreSala = nombreSala;

		juego.getChatsActivos().put(nombreSala, this);

		setTitle(nombreSala);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 434, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 414, 201);
		contentPane.add(scrollPane);

		chat = new JTextArea();
		chat.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 13));
		chat.setEditable(false);
		scrollPane.setViewportView(chat);

		texto = new JTextField();
		texto.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				texto.requestFocus();
			}
		});

		texto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMensaje();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				juego.getChatsActivos().remove(nombreSala);
				dispose();
			}
		});

		texto.setBounds(10, 223, 414, 49);
		contentPane.add(texto);
		texto.setColumns(10);
	}

	private void enviarMensaje() {
		if(!texto.getText().equals("")) {
			chat.append("Yo: " + texto.getText() + "\n");

			PaqueteMensaje paqueteMensaje = cliente.getPaqueteMensaje();
			paqueteMensaje.setEmisor(juego.getPersonaje().getNombre());
			paqueteMensaje.setReceptor(nombreSala.equals(ComandoConversar.NOMBRESALA) ? null : nombreSala);
			paqueteMensaje.setContenido(texto.getText());
			paqueteMensaje.setComando(Comando.CONVERSAR);
			cliente.setPaqueteMensaje(paqueteMensaje);

			try {
				juego.getCliente().getSalida().writeObject(gson.toJson(cliente.getPaqueteMensaje(), PaqueteMensaje.class));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor");
			}

			texto.requestFocus();
			texto.setText("");
		}
	}

	public JTextArea getChat() {
		return chat;
	}

	public JTextField getTexto() {
		return texto;
	}
}
