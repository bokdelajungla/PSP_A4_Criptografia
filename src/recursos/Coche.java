package recursos;

import java.io.Serializable;

public class Coche implements Serializable {

	private static final long serialVersionUID = 3526483192707204517L;
	private final String LETRAS = "BCDFGHJKLMNPRSTVWXYZ";
	public final static String MATTRICULA_VALIDA = "^[0-9]{4}[A-Z]{3}$";
	
	private String matricula;
	private String marca;
	private String modelo;
	private double precio;
	
	public Coche(String matricula, String marca, String modelo, double precio) {
		 if(matricula.toUpperCase().matches(MATTRICULA_VALIDA)) {
			 this.matricula = matricula.toUpperCase();
		 } else {
			 this.matricula = null;
		 }     
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
	}
	
	public Coche(String marca, String modelo, double precio) {
		matricula = generarMatricula();
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		if(matricula.toUpperCase().matches(MATTRICULA_VALIDA)) {
			 this.matricula = matricula.toUpperCase();
		 } else {
			 this.matricula = null;
		 }
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio + "]";
	}
	
	private String generarMatricula() {
		String str = ""; 
		int rnd;
		for(int i = 0; i < 4; i++) {
			rnd = (int) (Math.random() * 9);
			str += Integer.toString(rnd);
		}
		for(int i = 0; i < 3; i++) {
			rnd = (int) (Math.random() * LETRAS.length());
			str += LETRAS.charAt(rnd);
		}
		return str;
	}
	
	public static boolean matriculaValida(String matricula) {
		if(matricula.toUpperCase().matches(MATTRICULA_VALIDA)) {
			 return true;
		 } else {
			 return false;
		 }
	}
}
