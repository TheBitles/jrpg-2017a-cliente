package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.ComandoConversar;

public class Chat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField texto;
	private JTextArea chat;
	private Juego juego;
	private Cliente cliente;
	
	/**
	 * Create the frame. 
	 */
	public Chat(final Cliente cliente) {
		this.cliente = cliente;

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 414, 201);
		contentPane.add(scrollPane);
		
		chat = new JTextArea();
		chat.setEditable(false);
		scrollPane.setViewportView(chat);
		
		texto = new JTextField();
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

		JButton enviar = new JButton("Enviar");
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMensaje();
			}
		});
		enviar.setBounds(334, 225, 81, 23);
		contentPane.add(enviar);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				cliente.getChatsActivos().remove(getTitle());

				if(!cliente.getChatsActivos().containsKey(ComandoConversar.NOMBRESALA)) {
					MenuChat.getBotonChatPublico().setEnabled(true);
				}
				dispose();
			}
		});
		texto.setBounds(10, 223, 314, 27);
		contentPane.add(texto);
		texto.setColumns(10);
	}
	
	private void enviarMensaje() {
		if(!texto.getText().equals("")) {
			chat.append("Yo: " + texto.getText() + "\n");

			cliente.setAccion(Comando.CONVERSAR);
			cliente.getPaqueteMensaje().setEmisor(cliente.getPaqueteUsuario().getUsername());
			cliente.getPaqueteMensaje().setReceptor(getTitle());
			cliente.getPaqueteMensaje().setContenido(texto.getText());
			
			synchronized (cliente) {
				cliente.notify();
			}
			texto.setText("");
		}
		texto.requestFocus();

	}
	
	public JTextArea getChat() {
		return chat;
	}

	public JTextField getTexto() {
		return texto;
	}
}
