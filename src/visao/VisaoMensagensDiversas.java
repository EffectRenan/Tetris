package visao;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class VisaoMensagensDiversas  implements Serializable{

	private static final long serialVersionUID = 1L;

	public void mostrarPontuacaoFinal(String string) {
		JOptionPane.showMessageDialog(null, "Pontuacao final: " + string);
	}

	public void mostrarOrientacoesDoJogo() {
		JOptionPane.showMessageDialog(null,
				"Z: Girar peça para esquerda \n" + "X: Girar peça para direita \n" + "Espaço: Cair peça \n"
						+ "P: Pausar jogo \n" + "S: Salvar jogo \n" + "Seta direita: Mover peça para direita \n"
						+ "Seta esquerda: Mover peça para esquerda \n" + "Seta para baixo: Mover peça para baixo");
	}
	
	public void mostrarJogoGravadoComSucesso() {
		JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso");
	}
	
	public void mostrarJogoCarregadoComSucesso() {
		JOptionPane.showMessageDialog(null, "Jogo Carregado com sucesso");
	}
	
	public void mostrarErroAoEscreverNaMemoria() {
		System.out.println("Erro ao escrever na memómria \n" + "Inicie o jogo novamente");
	}
	
	public void mostrarErroPosicaoIncorreta() {
		System.out.println("Erro posicao incorreta \n" + "Seu jogo foi salvo, inicie o jovo novamente");
	}
}
