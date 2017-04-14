package controle;

import java.io.IOException;
import java.io.Serializable;

import modelo.excecoes.ExcecaoPosicaoIncorreta;
import visao.VisaoTelaInicial;

public class Principal implements Serializable{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws ExcecaoPosicaoIncorreta, IOException {
		VisaoTelaInicial telaInicial = new VisaoTelaInicial();
		telaInicial.iniciarTela();
	}
}