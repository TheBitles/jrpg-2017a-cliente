package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mensajeria.PaquetePersonaje;
import recursos.Recursos;
import java.awt.Component;
import java.awt.Color;

public class MenuInventario extends JFrame {

	private JPanel contentPane;
	private JButton cerrar;
	
	public static void main(String[] args) {
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
	}

	public MenuInventario(PaquetePersonaje personaje) {
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 228, 190);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		SlotPanel itemSlot1 = new SlotPanel();
		itemSlot1.setBounds(3, 4, 60, 60);
		contentPane.add(itemSlot1);

		SlotPanel itemSlot2 = new SlotPanel();
		itemSlot2.setBounds(73, 4, 60, 60);
		contentPane.add(itemSlot2);

		SlotPanel itemSlot3 = new SlotPanel();
		itemSlot3.setBounds(143, 4, 60, 60);
		contentPane.add(itemSlot3);

		SlotPanel itemSlot4 = new SlotPanel();
		itemSlot4.setBounds(3, 86, 60, 60);
		contentPane.add(itemSlot4);

		SlotPanel itemSlot5 = new SlotPanel();
		itemSlot5.setBounds(143, 86, 60, 60);
		contentPane.add(itemSlot5);

		SlotPanel itemSlot6 = new SlotPanel();
		itemSlot6.setBounds(73, 86, 60, 60);
		contentPane.add(itemSlot6);
	}
}

class SlotPanel extends JPanel{

	private BufferedImage itemEquipado;

    public SlotPanel() {
       try {
          itemEquipado = ImageIO.read(new File("recursos//inventario_ranura_vacia.jpg"));
       } catch (IOException e) {
    	   e.printStackTrace();
       }
    }

    public void setItemEquipado(BufferedImage item) {
    	this.itemEquipado = item;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(itemEquipado, 0, 0, this);
    }

}
