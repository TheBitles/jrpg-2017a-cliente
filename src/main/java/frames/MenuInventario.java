package frames;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import dominio.Item;
import dominio.Personaje;
import juego.Juego;
import mensajeria.PaqueteInventario;
import mensajeria.PaquetePersonaje;
import java.awt.Color;

public class MenuInventario extends JFrame {

	private static final long serialVersionUID = 2740459661263747445L;
	private JPanel contentPane;

	private static MenuInventario instancia = null;
	
	private Juego juego;
	private Cliente cliente;
	private Point location;
	private PaquetePersonaje personaje;
	
	private final Gson gson = new Gson();
	
	public static MenuInventario getInstance() {
		if(instancia == null) {
			instancia = new MenuInventario();
		}
		return instancia;
	}
	
	public MenuInventario() {
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 550);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0,4));
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				enviarDatos();
			}
		});

	}
	
	public void dibujar(final Juego juego, final PaquetePersonaje personaje, final Point location) {
		this.juego = juego;
		this.cliente = juego.getCliente();
		this.personaje = personaje;
		this.location = location;
		
		redibujar();
	}
	
	public void redibujar() {
		setLocation(location);

		contentPane.removeAll();
		
		ArrayList<Item> items = personaje.getInventario();

		ItemViewSoltar view;

		for(int i = 0; i < items.size(); i++) {
			view = new ItemViewSoltar(items.get(i), i, this);
			contentPane.add(view);
		}

		int i = items.size();

		while(i < Personaje.MAX_ITEMS) {
			view = new ItemViewSoltar(null, 0, this);
			contentPane.add(view);
			i++;
		}

		setVisible(true);
	}
	
	public void botonSoltarPresionado(final int index) {
		personaje.getInventario().remove(index);
		
		redibujar();
	}
	
	public void enviarDatos() {
		synchronized(cliente){
			PaqueteInventario paqueteInventario = new PaqueteInventario();
			paqueteInventario.setPersonajeId(cliente.getPaquetePersonaje().getId());
			paqueteInventario.setItems(cliente.getPaquetePersonaje().getInventario());

			try {
				cliente.getSalida().writeObject(gson.toJson(paqueteInventario));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
