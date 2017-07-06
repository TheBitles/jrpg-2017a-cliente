package frames;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import dominio.Intercambiable;
import dominio.Item;
import dominio.Personaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.ComandoConversar;
import mensajeria.PaqueteIntercambiable;
import mensajeria.PaqueteIntercambio;
import mensajeria.PaqueteInventario;
import mensajeria.PaquetePersonaje;
import java.awt.Color;

public class MenuMercado extends JFrame {

	private static final long serialVersionUID = 2740459661263747445L;

	private static final int MERCADO_Y = 680;

	private PaquetePersonaje personaje;
	private Juego juego = null;
	private JFrame pantalla;

	private JPanel panelOferta;
	private JPanel panelIntercambioOferta;
	private JPanel panelIntercambioFlecha;
	private JPanel panelIntercambioDemanda;
	private JPanel panelDemanda;
	
	private final Gson gson = new Gson();

	private ArrayList<Intercambiable> intercambiables = new ArrayList<Intercambiable>(Personaje.MAX_ITEMS/2);

	private static MenuMercado instancia = null;

	public static MenuMercado getInstance() {
		if(instancia == null) {
			instancia = new MenuMercado();
		}
		return instancia;
	}

	public MenuMercado() {
		setTitle("Mercado");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(0, 0, 800, MERCADO_Y + 20);
		setResizable(false);
		setLayout(null);

		intercambiables = new ArrayList<>(Personaje.MAX_ITEMS/2);

		for(int i = 0; i < Personaje.MAX_ITEMS/2; i++) {
			intercambiables.add(new Intercambiable());
		}
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				pantalla.setVisible(true);
			}
		});
	}
	
	public void dibujar(final Juego juego, final PaquetePersonaje personaje, final JFrame pantalla) {
		if( this.juego == null ) {
    		this.juego = juego;
    		this.pantalla = pantalla;
    		this.personaje = personaje;
    		setLocation(pantalla.getLocation());
    
    		panelOferta = nuevoPanel(0, 2);
    		panelIntercambioOferta = nuevoPanel(250, 1);
    		panelIntercambioFlecha = nuevoPanel(350, 1);
    		panelIntercambioDemanda = nuevoPanel(450, 1);
    		panelDemanda = nuevoPanel(600, 2);
    
    		redibujar();
    
    		add(panelOferta);
    		add(panelIntercambioOferta);
    		add(panelIntercambioFlecha);
    		add(panelIntercambioDemanda);
    		add(panelDemanda);
		}
		
		pantalla.setVisible(false);
		setVisible(true);
	}

	private JPanel nuevoPanel(final int posicion, final int columnas) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(0, columnas));
		panel.setBounds(posicion, 0, 100 * columnas, MERCADO_Y);
		return panel;
	}

	private void redibujar(){
		dibujarPanelOferta();
		dibujarPanelIntercambioOferta();
		dibujarPanelIntercambioFlecha();
		dibujarPanelIntercambioDemanda();
		dibujarPanelDemanda();
	}

	private void dibujarPanelOferta() {
		ArrayList<Item> items = personaje.getInventario();

		ItemViewOfertar view;


		for(int i = 0; i < items.size(); i++) {
			view = new ItemViewOfertar(items.get(i), i, this);
			panelOferta.add(view);
		}

		int i = items.size();

		while(i < Personaje.MAX_ITEMS) {
			view = new ItemViewOfertar(null, 0, this);
			panelOferta.add(view);
			i++;
		}
	}

	private void dibujarPanelIntercambioOferta() {
		ItemViewEliminarOferta view;
		Item oferta;

		for(int i = 0; i < Personaje.MAX_ITEMS/2 ; i++) {
			oferta = intercambiables.get(i).getOferta();
			view = new ItemViewEliminarOferta(oferta, i, this);
			panelIntercambioOferta.add(view);
		}

	}

	private void dibujarPanelIntercambioFlecha() {
		JLabel label;

		for(int i = 0; i < Personaje.MAX_ITEMS/2 ; i++) {
			label = new JLabel("<-------->");
			panelIntercambioFlecha.add(label);
		}
	}

	private void dibujarPanelIntercambioDemanda() {
		ItemViewEliminarDemanda view;
		Item demanda;

		for(int i = 0; i < Personaje.MAX_ITEMS/2 ; i++) {
			demanda = intercambiables.get(i).getDemanda();
			view = new ItemViewEliminarDemanda(demanda, i, this);
			panelIntercambioDemanda.add(view);
		}
	}


	private void dibujarPanelDemanda() {
		ArrayList<Item> items = personaje.getInventarioCompleto();

		ItemViewDemandar view;

		for(int i = 0; i < items.size(); i++) {
			view = new ItemViewDemandar(items.get(i), i, this);
			panelDemanda.add(view);
		}

	}

	// Menores Indices

	private int getMenorIndiceOferta() {
		int menor = -1;
		int i = 0;

		while(menor == -1 && i < Personaje.MAX_ITEMS) {
			ItemView iv = (ItemView) panelOferta.getComponent(i);
			if(iv.getItem() == null) {
				menor = i;
			}
			i++;
		}

		return menor;
	}

	private int getMenorIndiceIntercambioOferta() {
		int menor = -1;
		int i = 0;

		while(menor == -1 && i < Personaje.MAX_ITEMS/2) {
			if(intercambiables.get(i).getOferta() == null) {
				menor = i;
			}
			i++;
		}

		return menor;
	}

	private int getMenorIndiceIntercambioDemanda() {
		int menor = -1;
		int i = 0;
		while(menor == -1 && i < Personaje.MAX_ITEMS/2) {
			if(intercambiables.get(i).getDemanda() == null) {
				menor = i;
			}
			i++;
		}
		return menor;
	}


	// Botones

	public void botonOfertarPresionado(final int index) {
		ItemView panel = (ItemView) panelOferta.getComponent(index);
		Item item = panel.getItem();

		int menorIndiceVacio = getMenorIndiceIntercambioOferta();

		if(menorIndiceVacio != -1) {

    		panelOferta.remove(panelOferta.getComponent(index));
    		ItemViewOfertar ivo = new ItemViewOfertar(null, index, this);
    		panelOferta.add(ivo, null, index);
    		panelOferta.revalidate();

    		panelIntercambioOferta.remove(panelIntercambioOferta.getComponent(menorIndiceVacio));
    		ItemViewEliminarOferta iveo = new ItemViewEliminarOferta(item, menorIndiceVacio, this);
    		panelIntercambioOferta.add(iveo, null, menorIndiceVacio);
    		panelIntercambioOferta.revalidate();

    		intercambiables.get(menorIndiceVacio).setOferta(item);
    		repaint();
			enviarDatos();
		}

	}

	public void botonDemandarPresionado(final int index) {
		ItemView panel = (ItemView) panelDemanda.getComponent(index);
		Item item = panel.getItem();

		int menorIndiceVacio = getMenorIndiceIntercambioDemanda();

		if(menorIndiceVacio != -1) {
    		panelIntercambioDemanda.remove(panelIntercambioDemanda.getComponent(menorIndiceVacio));
    		ItemViewEliminarDemanda b = new ItemViewEliminarDemanda(item, menorIndiceVacio, this);
    		panelIntercambioDemanda.add(b, null, menorIndiceVacio);
    		panelIntercambioDemanda.revalidate();

    		intercambiables.get(menorIndiceVacio).setDemanda(item);
    		repaint();
			enviarDatos();
		}
	}

	public void botonEliminarOfertaPresionado(final int index) {
		ItemView panel = (ItemView) panelIntercambioOferta.getComponent(index);
		Item item = panel.getItem();

		int menorIndiceVacio = getMenorIndiceOferta();

		if(menorIndiceVacio != -1) {
    		panelIntercambioOferta.remove(panelIntercambioOferta.getComponent(index));
    		ItemViewEliminarOferta a = new ItemViewEliminarOferta(null, index, this);
    		panelIntercambioOferta.add(a, null, index);
    		panelIntercambioOferta.revalidate();

    		panelOferta.remove(panelOferta.getComponent(menorIndiceVacio));
    		ItemViewOfertar iveo = new ItemViewOfertar(item, menorIndiceVacio, this);
    		panelOferta.add(iveo, null, menorIndiceVacio);
    		panelOferta.revalidate();

    		intercambiables.get(index).setOferta(null);
    		repaint();
			enviarDatos();
		}
	}

	public void botonEliminarDemandaPresionado(final int index) {
		panelIntercambioDemanda.remove(panelIntercambioDemanda.getComponent(index));
		ItemViewEliminarDemanda a = new ItemViewEliminarDemanda(null, index, this);
		panelIntercambioDemanda.add(a, null, index);
		panelIntercambioDemanda.revalidate();

		intercambiables.get(index).setDemanda(null);
		repaint();
		enviarDatos();
	}

	public ArrayList<Intercambiable> getIntercambiables() {
		return intercambiables;
	}

	public void enviarDatos() {
		PaqueteIntercambiable paqueteIntercambiable = new PaqueteIntercambiable();
		paqueteIntercambiable.setIntercambiables(intercambiables);
		paqueteIntercambiable.setPersonajeId(juego.getPersonaje().getId());

		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteIntercambiable, PaqueteIntercambiable.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizar(final Integer index) {
		ItemView panel = (ItemView) panelIntercambioDemanda.getComponent(index);
		Item item = panel.getItem();

		int menorIndiceVacio = getMenorIndiceOferta();

		if(menorIndiceVacio != -1) {
    		panelIntercambioDemanda.remove(panelIntercambioDemanda.getComponent(index));
    		ItemViewEliminarDemanda a = new ItemViewEliminarDemanda(null, index, this);
    		panelIntercambioDemanda.add(a, null, index);
    		panelIntercambioDemanda.revalidate();
    		
    		panelIntercambioOferta.remove(panelIntercambioOferta.getComponent(index));
    		ItemViewEliminarOferta b = new ItemViewEliminarOferta(null, index, this);
    		panelIntercambioOferta.add(b, null, index);
    		panelIntercambioOferta.revalidate();

    		panelOferta.remove(panelOferta.getComponent(menorIndiceVacio));
    		ItemViewOfertar iveo = new ItemViewOfertar(item, menorIndiceVacio, this);
    		panelOferta.add(iveo, null, menorIndiceVacio);
    		panelOferta.revalidate();

    		intercambiables.get(index).setOferta(null);
    		intercambiables.get(index).setDemanda(null);

    		repaint();
		}
		
		personaje.getInventario().remove(menorIndiceVacio);
		personaje.getInventario().add(item);
		MenuInventario.getInstance().enviarDatos();
	}
}
