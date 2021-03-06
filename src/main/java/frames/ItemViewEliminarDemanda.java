package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import dominio.Item;

class ItemViewEliminarDemanda extends ItemView {
	private static final long serialVersionUID = 1L;
	JButton boton; 
	
    public ItemViewEliminarDemanda(final Item item, final int index, final MenuMercado menuMercado) {
    	super(item, false);

    	if(item != null) {
        	boton = new JButton("X");
            this.add(boton);
    
            boton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		menuMercado.botonEliminarDemandaPresionado(index);
            		boton.setVisible(false);
            	}
            });
    	}
    }
    
}