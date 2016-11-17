package taskfacil.ex;

public class DuplicatedValues extends RuntimeException{
	public DuplicatedValues() {
		System.err.println("Valores Duplicados na Inserção");
	}
}
