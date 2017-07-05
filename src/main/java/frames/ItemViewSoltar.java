package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import dominio.Item;

class ItemViewSoltar extends ItemView {
	private static final long serialVersionUID = 1L;
	JButton botonSoltar; 
	
    public ItemViewSoltar(final Item item, final int index, final MenuInventario menuInventario) {
    	super(item, true);
    	
    	if(item != null) {
        	botonSoltar = new JButton("Soltar");
            this.add(botonSoltar);
    
            botonSoltar.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		menuInventario.botonSoltarPresionado(index);
            		botonSoltar.setVisible(false);
            	}
            });
    	}
    }
    
}

