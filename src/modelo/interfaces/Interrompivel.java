package modelo.interfaces;

import modelo.excecoes.ExcecaoPosicaoIncorreta;

public interface Interrompivel {

	void avisarVisaoRepaint();

	void avisarVisaoRemoverLinhaCheia() throws ExcecaoPosicaoIncorreta;

	void avisarVisaoFecharTela();

	void avisarVisaoPararTempo();

	void avisarVisaoAtualizarStatusFimDeJogo();

	void avisarVisaoMostrarPontuacaoFinal();
	

}
