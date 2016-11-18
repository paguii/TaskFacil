package taskfacil.ex;

public class DuplicatedValues extends RuntimeException{
	/**
	 *
	 */
	private static final long serialVersionUID = 6414202327658252460L;

	public DuplicatedValues() {
		System.err.println("Valores Duplicados na Inserção");
	}
}
