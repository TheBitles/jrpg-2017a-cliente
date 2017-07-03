package juego;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import cliente.Cliente;
import frames.MenuInventario;
import frames.MenuJugar;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;

public class Pantalla {

	private JFrame pantalla;
	private Canvas canvas;
	private MenuInventario inventario;

	private final Gson gson = new Gson();
	protected int invX;
	protected int invY;

	public Pantalla(final String NOMBRE, final int ANCHO, final int ALTO, final Cliente cliente) {
		pantalla = new JFrame(NOMBRE);

		pantalla.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
			new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(),
			new Point(0,0),"custom cursor"));

		pantalla.setSize(ANCHO, ALTO);
		pantalla.setResizable(false);
		pantalla.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pantalla.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				try {
					Paquete p = new Paquete();
					p.setComando(Comando.DESCONECTAR);
					p.setIp(cliente.getMiIp());
					cliente.getSalida().writeObject(gson.toJson(p));
					cliente.getEntrada().close();
					cliente.getSalida().close();
					cliente.getSocket().close();
					System.exit(0);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Fallo al intentar cerrar la aplicación.");
					System.exit(1);
					e.printStackTrace();
				}
			}
		});
		

		pantalla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_I == e.getKeyCode()) {
					/**
					 * acá está el problema
					 * el cliente que invoca getPaquetePersonaje NO tiene actualizado el inventario.
					 * tengo que mandarle al server un mensaje pidiendo actualizar y leer lo actualizado
					 * how the hell, idk
					 */
					
					
						
					PaquetePersonaje pqupdatepj = cliente.getJuego().actualizarPersonaje();
					
					if (inventario == null){
						inventario = new MenuInventario(pqupdatepj);
						inventario.setLocation(pantalla.getLocation());
					} else {
						invX = inventario.getX();
						invY = inventario.getY();
						inventario.dispose();
						inventario = new MenuInventario(pqupdatepj);
						inventario.setLocation(invX, invY);
					}
					inventario.setVisible(true);
					inventario.toFront();
					
					
						
					
					

				}
			}
		});

		pantalla.setLocationRelativeTo(null);
		pantalla.setVisible(false);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(ANCHO, ALTO));
		canvas.setMaximumSize(new Dimension(ANCHO, ALTO));
		canvas.setMinimumSize(new Dimension(ANCHO, ALTO));
		canvas.setFocusable(false);

		pantalla.add(canvas);
		pantalla.pack();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return pantalla;
	}

	public void mostrar() {
		pantalla.setVisible(true);
	}

	public static void centerString(Graphics g, Rectangle r, String s) {
	    FontRenderContext frc = new FontRenderContext(null, true, true);

	    Rectangle2D r2D = g.getFont().getStringBounds(s, frc);
	    int rWidth = (int) Math.round(r2D.getWidth());
	    int rHeight = (int) Math.round(r2D.getHeight());
	    int rX = (int) Math.round(r2D.getX());
	    int rY = (int) Math.round(r2D.getY());

	    int a = (r.width / 2) - (rWidth / 2) - rX;
	    int b = (r.height / 2) - (rHeight / 2) - rY;

	    g.drawString(s, r.x + a, r.y + b);
	}
}
