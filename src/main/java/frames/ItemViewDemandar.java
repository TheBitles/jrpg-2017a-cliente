package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import dominio.Item;

class ItemViewDemandar extends ItemView {
	private static final long serialVersionUID = 1L;
	JButton botonDemandar; 
	
    public ItemViewDemandar(final Item item, final int index, final MenuMercado menuMercado) {
    	super(item, false);
    	
    	if(item != null) {
        	botonDemandar = new JButton("Pedir");
            this.add(botonDemandar);
    
            botonDemandar.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		menuMercado.botonDemandarPresionado(index);
            	}
            });
    	}
    }
}
