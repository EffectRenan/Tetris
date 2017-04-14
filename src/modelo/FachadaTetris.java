package modelo;

import java.io.Serializable;

import modelo.DesenhoJogo.EstadoPeca;
import modelo.excecoes.ExcecaoPosicaoIncorreta;
import modelo.interfaces.InterfaceFachadaTetris;
import modelo.interfaces.Interrompivel;

public class FachadaTetris extends GiroDaPeca implements InterfaceFachadaTetris, Serializable  {
	
	private static final long serialVersionUID = 1L;
	private Boolean estaCaindoAcabou;
	private Boolean estaIniciado;
	private Boolean estaPausado;
	private Integer LinhasRemovidas;
	private Integer atualX;
	private Integer atualY;
	private final Integer larguraQuadro = 12;
	private final Integer alturaQuadro = 20;
	private EstadoPeca[] quadro;
	private final Integer quantidadedeQuadrados = 4;
	private Interrompivel comunicacaoVisao;
	
	public FachadaTetris(Interrompivel comunicacaoVisao) {
		this.comunicacaoVisao = comunicacaoVisao;
		instanciarVariaveis();
		limparQuadro();
	}
	
	public void setDesenhoJogo(DesenhoJogo desenhoJogo) {
		this.desenhoJogo = desenhoJogo;
	}
	
	private void instanciarVariaveis() {
		LinhasRemovidas = 0;
		atualX = 0;
		atualY = 0;
		quadro = new EstadoPeca[larguraQuadro * alturaQuadro];
		estaCaindoAcabou = new Boolean(false);
		estaIniciado = new Boolean(false);
		estaPausado = new Boolean(false);
		desenhoJogo = new DesenhoJogo(CoordenadasDasPecas.getCoordenadas());
	}
	
	public Integer calculoXDoQuadroParaExibirNaTela(Integer posicao) {
		return atualX + desenhoJogo.getX(posicao);
	}
	
	public Integer calculoYDoQuadroParaExibirNaTela(Integer posicao) {
		return atualY - desenhoJogo.getY(posicao);
	}

	public void executarJogo() throws ExcecaoPosicaoIncorreta {
		estaIniciado = true;
		estaCaindoAcabou = false;
		LinhasRemovidas = 0;
		limparQuadro();
		novaPeca();
	}
	
	
	private void limparQuadro() {
		for (int i = 0; i < alturaQuadro * larguraQuadro; i++)
			quadro[i] = EstadoPeca.SemDesenho;
	}
	
	public Boolean getEstaCaindoAcabou() {
		return estaCaindoAcabou;
	}
	
	public void setEstaCaindoAcabou(Boolean valor) {
		this.estaCaindoAcabou = valor;
	}
	
	public void novaPeca() throws ExcecaoPosicaoIncorreta {
		desenhoJogo.setDesenhoAleatorio();
		atualX = larguraQuadro / 2 + 1;
		atualY = alturaQuadro - 1 + desenhoJogo.valorMinimoY();

		if (!tentarMover(desenhoJogo, atualX, atualY))
			finalizarJogo(desenhoJogo);
	}
	
	private boolean tentarMover(DesenhoJogo novaPeca, Integer novoX, Integer novoY) throws ExcecaoPosicaoIncorreta {
		for (int i = 0; i < quantidadedeQuadrados; ++i) {
			Integer x = novoX + novaPeca.getX(i);
			Integer y = novoY - novaPeca.getY(i);
			
			if (!compararPecaSemDesenhoEQuadro(x, y))
				return false;
		}
		
		desenhoJogo = novaPeca;
		atualX = novoX;
		atualY = novoY;
		comunicacaoVisao.avisarVisaoRepaint();
		
		return true;
	}
	
	public void tentarMoverParaDireita() throws ExcecaoPosicaoIncorreta {
		tentarMover(desenhoJogo, atualX + 1, atualY);
	}
	
	public void tentarMoverParaEsquerda() throws ExcecaoPosicaoIncorreta {
		tentarMover(desenhoJogo, atualX - 1, atualY);
	}
	
	public void tentarMoverComAPecaViradaParaDireita() throws ExcecaoPosicaoIncorreta {
		tentarMover(girarDireita(), atualX, atualY);
	}
	
	public void tentarMoverComAPecaViradaParaEsquerda() throws ExcecaoPosicaoIncorreta {
		tentarMover(girarEsquerda(), atualX, atualY);
	}
	
	private boolean compararPecaSemDesenhoEQuadro(Integer x, Integer y) {
		if (x < 0 || x >= larguraQuadro || y < 0 || y >= alturaQuadro)
			return false;
		if (desenharEm(x, y) != EstadoPeca.SemDesenho)
			return false;

		return true;
	}
	
	public Integer getLinhasRemovidas() {
		return LinhasRemovidas;
	}
	
	public Integer getAtualX() {
		return atualX;
	}
	
	public Integer getAtualY() {
		return atualY;
	}
	
	public void setEstaPausado(Boolean valor) {
		this.estaPausado = valor;
	}
	
	public void descerUmaLinha() throws ExcecaoPosicaoIncorreta {
		if (!tentarMover(desenhoJogo, atualX, atualY - 1))
			cortarPeca();
	}
	
	private void cortarPeca() throws ExcecaoPosicaoIncorreta {
		atualizarQuadro();
		removerLinhaCheia();

		if (!estaCaindoAcabou)
			novaPeca();
	}
	
	public void pularParaBaixo() throws ExcecaoPosicaoIncorreta {
		Integer novoY = atualY;
		Boolean parar = false;

		do {
			if (!tentarMover(desenhoJogo, atualX, novoY - 1))
				parar = true;
			else
				novoY--;
			
		} while (novoY > 0 && !parar);
		cortarPeca();
	}
	
	private void atualizarQuadro() {
		for (int i = 0; i < quantidadedeQuadrados; ++i) {
			Integer x = atualX + desenhoJogo.getX(i);
			Integer y = atualY - desenhoJogo.getY(i);
			quadro[(y * larguraQuadro) + x] = desenhoJogo.getPecaDesenho();
		}
	}
	
	private void removerLinhaCheia() throws ExcecaoPosicaoIncorreta {
		contarLinhasCompletas();
		estaCaindoAcabou = true;
		desenhoJogo.setDesenho(EstadoPeca.SemDesenho);
		comunicacaoVisao.avisarVisaoRemoverLinhaCheia();
	}
	
	public Boolean getEstaIniciado() {
		return estaIniciado;
	}
	
	private void contarLinhasCompletas() {
		Integer linhasCompletas = 0;
		for (int i = alturaQuadro - 1; i >= 0; --i)
			linhasCompletas = verificaLinhasCheias(linhasCompletas, i);
	}
	
	public Boolean verificaSeJogoNaoFoiIniciadoOUPecaSemDesenho() {
		return !estaIniciado || desenhoJogo.getPecaDesenho() == EstadoPeca.SemDesenho;
	}
	
	private int verificaLinhasCheias(Integer linhasCompletas, Integer posicao) {
		boolean linhaEstaCheia = true;
		Integer cont = 0;
		
		while(cont < larguraQuadro && linhaEstaCheia == true) {
			if (desenharEm(cont, posicao) == EstadoPeca.SemDesenho)
				linhaEstaCheia = false;
			else 
				cont++;
		}
		verificaSeLinhaEstaCheia(linhasCompletas, linhaEstaCheia, posicao);
		return linhasCompletas;

	}
	
	private void verificaSeLinhaEstaCheia(Integer linhasCompletas, boolean linhaEstaCheia, Integer i) {
		if (linhaEstaCheia) {
			++linhasCompletas;
			LinhasRemovidas += linhasCompletas;
			apagarLinhas(i);
		}
	}
	
	private void apagarLinhas(Integer i) {
		for (int k = i; k < alturaQuadro - 1; ++k)
			for (int j = 0; j < larguraQuadro; ++j)
				quadro[(k * larguraQuadro) + j] = desenharEm(j, k + 1);
	}
	
	public EstadoPeca desenharEm(Integer x, Integer y) {
		return quadro[(y * larguraQuadro) + x];
	}
	
	private void finalizarJogo(DesenhoJogo atualPeca) throws ExcecaoPosicaoIncorreta {
		atualPeca.setDesenho(EstadoPeca.SemDesenho);
		comunicacaoVisao.avisarVisaoPararTempo();
		estaIniciado = false;
		comunicacaoVisao.avisarVisaoAtualizarStatusFimDeJogo();
		comunicacaoVisao.avisarVisaoMostrarPontuacaoFinal();
		comunicacaoVisao.avisarVisaoFecharTela();
	}

	@Override
	public DesenhoJogo getDesenhoJogo() {
		return desenhoJogo;
	}

	@Override
	public Boolean getEstaPausado() {
		return estaPausado;
	}
}
