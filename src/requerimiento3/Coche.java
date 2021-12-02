package requerimiento3;

public class Coche {
	
	public final static String MATRICULA_VALIDA = "^[0-9]{4}[A-Z]{3}$";
	private String matricula;
	private String modelo;
	private String marca;
	private float precio;
	
	public Coche (String matricula, String marca, String modelo, float precio) throws MatriculaNoValida{
		if(validarMatricula(matricula)) {
			this.matricula=matricula.toUpperCase();
			this.marca=marca;
			this.modelo=modelo;
			this.precio=precio;
		}
		else {
			throw new MatriculaNoValida("Matrícula No válida. Debe cumplir el formato 0000XXX");
		}
		
	}

	@Override
	public String toString() {
		return "Coche:\nmatricula:" + matricula + "\nmodelo:" + modelo + "\nmarca:" + marca + "\nprecio=" + precio + "\n";
	}

	public static boolean validarMatricula (String matricula) {
		if(matricula.toUpperCase().matches(MATRICULA_VALIDA)) {
            return true;
        } else {
            return false;
        }
	}
	
}
