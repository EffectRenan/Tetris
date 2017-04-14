package modelo.excecoes;

public class ExcecaoPosicaoIncorreta extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcecaoPosicaoIncorreta(){
		super();
	}
	
	public ExcecaoPosicaoIncorreta(String msg){
		super(msg);
	}
}