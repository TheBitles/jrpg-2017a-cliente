package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import juego.Juego;
import mensajeria.ComandoConversar;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;

public class MenuChat extends JFrame {

	private static final long serialVersionUID = 1L;
	private String user = null;
	private PaqueteUsuario paqueteUsuario;
	
	private JPanel contentPane;
	private DefaultListModel<String> modelo = new DefaultListModel<String>();
	private static JList<String> list = new JList<String>();
	private JTextField miNombre;
	private static JButton botonChatPublico;

	private Juego juego;
	private Cliente cliente;
	
	public MenuChat(final Juego juego) {
		this.juego = juego;
		this.cliente = juego.getCliente();

		actualizarLista();

		setTitle("Jugadores");
		setResizable(false);
		setBounds(100, 100, 327, 335);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 299, 188);
		contentPane.add(scrollPane);

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					if(list.getSelectedValue() != null) {
						if(!cliente.getChatsActivos().containsKey(list.getSelectedValue())) {
							if (cliente != null) {
								Chat chat = new Chat(cliente);
								cliente.getChatsActivos().put(list.getSelectedValue(), chat);
								chat.setTitle(list.getSelectedValue());
								chat.setVisible(true);
							}	
						}
					}
				}
			}
		});

		botonChatPublico = new JButton(ComandoConversar.NOMBRESALA);
		botonChatPublico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cliente.getChatsActivos().containsKey(ComandoConversar.NOMBRESALA)) {
					Chat chat = new Chat(cliente);
					cliente.getChatsActivos().put(ComandoConversar.NOMBRESALA, chat);
					chat.setTitle(ComandoConversar.NOMBRESALA);
					chat.setVisible(true);
					botonChatPublico.setEnabled(false);
				}
			}
		});

		botonChatPublico.setBounds(220, 264, 89, 23);
		contentPane.add(botonChatPublico);
		
		miNombre = new JTextField();
		miNombre.setHorizontalAlignment(SwingConstants.LEFT);
		miNombre.setEditable(false);
		miNombre.setBounds(67, 209, 242, 22);
		contentPane.add(miNombre);
		miNombre.setColumns(10);

		list.setModel(modelo);
		scrollPane.setViewportView(list);

		JLabel label = new JLabel("");
		label.setBounds(130, 267, 56, 16);
		contentPane.add(label);
	}

	private void actualizarLista() {
		if(cliente != null) {
			synchronized (cliente) {
				MenuChat.setPersonajesConectados(juego);
			}
		}
	}
	
	public PaqueteUsuario getPaqueteUsuario() {
		return paqueteUsuario;
	}
	
	public static JList<String> getList() {
		return list;
	}
	
	public static JButton getBotonChatPublico() {
		return botonChatPublico;
	}
	
	public static void setPersonajesConectados(Juego juego) {
		Map<Integer, PaquetePersonaje> personajesConectados = juego.getPersonajesConectados();
		Integer personajeId = juego.getPersonaje().getId();
		DefaultListModel<String> modeloDeLista = new DefaultListModel<String>();
		
		for (Map.Entry<Integer, PaquetePersonaje> personaje : personajesConectados.entrySet()) {
			if( personajeId != personaje.getValue().getId() ) {
				modeloDeLista.addElement(personaje.getValue().getNombre());
			}
		}

		getList().removeAll();
		getList().setModel(modeloDeLista);
	}

}
