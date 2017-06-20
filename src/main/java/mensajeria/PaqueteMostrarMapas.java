package mensajeria;

public class PaqueteMostrarMapas extends Paquete implements IPaquete {

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 6023242063230954178L;

	@Override
	public void ejecutar() {
		System.out.println("hola, soy el ejecutar de mensajeria.PaqueteMostrarMapas");
	}
	
	

}
