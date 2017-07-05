package frames;

import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.Item;
import mensajeria.PaquetePersonaje;
import java.awt.Color;

public class MenuInventario extends JFrame {

	private static final long serialVersionUID = 2740459661263747445L;
	private JPanel contentPane;

	private static MenuInventario instancia = null;
	
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
	}
	
	public void dibujar(final PaquetePersonaje personaje, final Point location) {
		setLocation(location);

		contentPane.removeAll();
		
		ArrayList<Item> items = personaje.getInventario();

		ItemViewSoltar view;

		for(int i = 0; i < items.size(); i++) {
			view = new ItemViewSoltar(items.get(i), i, this);
			contentPane.add(view);
		}

		int i = items.size();

		while(i < 12) {
			view = new ItemViewSoltar(null, 0, this);
			contentPane.add(view);
			i++;
		}

		setVisible(true);
	}
	
	public void botonSoltarPresionado(final int index) {
		
	}
}
