package requerimiento2;

public class MatriculaNoValida extends Exception{
	
	private static final long serialVersionUID = 1L;

	public MatriculaNoValida(String errorMessage) {
        super(errorMessage);
    }
}
