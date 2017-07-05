package frames;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import dominio.Item;
import recursos.CargadorImagen;

class ItemView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4419481840706977716L;
	private BufferedImage foto;
	private JLabel labelTexto;
	private Item item;
	
    public ItemView(final Item item, boolean verAtributos) {
    	this.item = item;
    	String texto = "Vacio";
    	String imagen = "/item0.png";
    	
    	if( item != null ) {
    		texto = item.getNombre();
    		imagen = "/item" + item.getId() + ".png";
    	}

    	labelTexto = new JLabel(texto);
    	labelTexto.setBorder(new CompoundBorder(labelTexto.getBorder(), new EmptyBorder(0,0,55,0)));
        this.add(labelTexto);
    	
    	if( item != null && verAtributos) {
            String signo;
            JLabel attr;
                		
            if(item.getAtaque() != 0){
            	signo = item.getAtaque() > 0 ? "+" : "";
            	attr = new JLabel(signo + item.getAtaque() + " ataque");
            	this.add(attr);
            }
            
            if(item.getDefensa() != 0){
            	signo = item.getDefensa() > 0 ? "+" : "";
            	attr = new JLabel(signo + item.getDefensa() + " defensa");
            	this.add(attr);
            }
            
            if(item.getMagia() != 0){
            	signo = item.getMagia() > 0 ? "+" : "";
            	attr = new JLabel(signo + item.getMagia() + " magia");
            	this.add(attr);
            }
            
            if(item.getSalud() != 0){
            	signo = item.getSalud() > 0 ? "+" : "";
            	attr = new JLabel(signo + item.getSalud() + " salud");
            	this.add(attr);
            }
            
            if(item.getEnergia() != 0){
            	signo = item.getEnergia() > 0 ? "+" : "";
            	attr = new JLabel(signo + item.getEnergia() + " energia");
            	this.add(attr);
            }
    	}

        this.foto = CargadorImagen.cargarImagen(imagen);
    }
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(this.foto, 18, 20, this);
    }
    
    public Item getItem() {
    	return item;
    }
}
