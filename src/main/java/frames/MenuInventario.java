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

	private JPanel contentPane;
	private JButton cerrar;

	public MenuInventario(final PaquetePersonaje personaje) {
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);

		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0,5));

		ArrayList<Item> items = personaje.getInventario();

		for(Item item : items) {
			ItemView itemView = new ItemView(item, personaje);
			contentPane.add(itemView);
		}

		int i = items.size();

		while(i < 20) {
			ItemView itemView = new ItemView();
			contentPane.add(itemView);
			i++;
		}
	}
}

class ItemView extends JPanel {

	private BufferedImage foto;
	private JButton soltar;
	private JLabel texto;

    public ItemView(final Item item, final PaquetePersonaje personaje) {
        texto = new JLabel(item.getNombre());
        texto.setBorder(new CompoundBorder(texto.getBorder(), new EmptyBorder(0,0,55,0)));
        this.add(texto);
        
        this.foto = CargadorImagen.cargarImagen("/item" + item.getId() + ".png");
       
        soltar = new JButton("Soltar");
        this.add(soltar);

        soltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//personaje.setAccion(Comando.ACTUALIZAR_INVENTARIO);
        	}
        });
    }

    public ItemView() {
    	texto = new JLabel("Vacio");
    	this.add(texto);
    	this.foto = CargadorImagen.cargarImagen("/item0.png");
    }
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(this.foto, 18, 20, this);
    }
}
