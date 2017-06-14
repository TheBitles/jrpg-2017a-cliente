package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import dominio.Item;
import mensajeria.Comando;
import mensajeria.PaquetePersonaje;
import recursos.CargadorImagen;
import recursos.Recursos;
import java.awt.Component;
import java.awt.Color;

public class MenuInventario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2740459661263747445L;
	private JPanel contentPane;
	private JButton cerrar;

	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuInventario frame = new MenuInventario(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		new MenuInventario(null);
	}
	
	public MenuInventario(final PaquetePersonaje personaje) {
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 550);

		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0,4));

		 ArrayList<Item> items = personaje.getInventario();

		for(Item item : items) {
			ItemView itemView = new ItemView(item, personaje);
			contentPane.add(itemView);
		}

		int i = items.size();

		while(i < 12) {
			ItemView itemView = new ItemView();
			contentPane.add(itemView);
			i++;
		}
	}
}

class ItemView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4419481840706977716L;
	private BufferedImage foto;
	private JButton soltar;
	private JLabel texto;
	
    public ItemView(final Item item, final PaquetePersonaje personaje) {
        texto = new JLabel(item.getNombre());
        texto.setBorder(new CompoundBorder(texto.getBorder(), new EmptyBorder(0,0,55,0)));
        this.add(texto);
         
        String signo;
        		
        if(item.getAtaque() != 0){
        	signo = item.getAtaque() > 0 ? "+" : "";
        	JLabel attr = new JLabel(signo + item.getAtaque() + " ataque");
        	this.add(attr);
        }
        
        if(item.getDefensa() != 0){
        	signo = item.getDefensa() > 0 ? "+" : "";
        	JLabel attr = new JLabel(signo + item.getDefensa() + " defensa");
        	this.add(attr);
        }
        
        if(item.getMagia() != 0){
        	signo = item.getMagia() > 0 ? "+" : "";
        	JLabel attr = new JLabel(signo + item.getMagia() + " magia");
        	this.add(attr);
        }
        
        if(item.getSalud() != 0){
        	signo = item.getSalud() > 0 ? "+" : "";
        	JLabel attr = new JLabel(signo + item.getSalud() + " salud");
        	this.add(attr);
        }
        
        if(item.getEnergia() != 0){
        	signo = item.getEnergia() > 0 ? "+" : "";
        	JLabel attr = new JLabel(signo + item.getEnergia() + " energia");
        	this.add(attr);
        }

        soltar = new JButton("Soltar");
        this.add(soltar);
        soltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// hacer que el chango suelte el item
        	}
        });
        
        this.foto = CargadorImagen.cargarImagen("/item" + item.getId() + ".png");
    }
    
    public ItemView() {
    	texto = new JLabel("Vacio");
    	texto.setBorder(new CompoundBorder(texto.getBorder(), new EmptyBorder(0,0,55,0)));
        this.add(texto);

    	this.foto = CargadorImagen.cargarImagen("/item0.png");
    }
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(this.foto, 18, 20, this);
    }
}
