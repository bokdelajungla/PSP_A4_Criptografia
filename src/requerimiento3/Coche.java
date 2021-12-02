package requerimiento3;

public class Coche {
	
	private String matricula;
	private String modelo;
	private String marca;
	private float precio;
	
	public Coche (String matricula, String marca, String modelo, float precio) {
		this.matricula=matricula;
		this.marca=marca;
		this.modelo=modelo;
		this.precio=precio;
	}

	@Override
	public String toString() {
		return "Coche:\nmatricula:" + matricula + "\nmodelo:" + modelo + "\nmarca:" + marca + "\nprecio=" + precio + "\n";
	}

	
}
