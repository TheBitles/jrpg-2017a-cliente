package mensajeria;

import java.io.Serializable;

public class PaqueteMensaje extends Paquete implements Serializable, Cloneable {
	
		private static final long serialVersionUID = 1L;
		private String emisor;
		private String receptor;
		private String contenido;

		public PaqueteMensaje(){}

		public String getContenido() {
			return contenido;
		}

		public void setContenido(String contenido) {
			this.contenido = contenido;
		}

		public String getEmisor() {
			return emisor;
		}

		public void setEmisor(String idEmisor) {
			this.emisor = idEmisor;
		}

		public String getReceptor() {
			return receptor;
		}

		public void setReceptor(String idReceptor){
			this.receptor = idReceptor;
		}
		
		public Object clone() {
			Object obj = null;
			obj = super.clone();
			return obj;
		}

		@Override
		public String toString() {
			return "PaqueteMensaje [emisor=" + emisor + ", receptor=" + receptor + ", contenido=" + contenido + "]";
		}

}
