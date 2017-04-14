package modelo.interfaces;

import modelo.DesenhoJogo;
import modelo.DesenhoJogo.EstadoPeca;
import modelo.excecoes.ExcecaoPosicaoIncorreta;

public interface InterfaceFachadaTetris {

	public Integer getLinhasRemovidas();

	public Integer getAtualX();

	public Integer getAtualY();
	
	public Boolean getEstaIniciado();

	public DesenhoJogo getDesenhoJogo();
	
	public Boolean getEstaPausado();
	
	public Integer calculoXDoQuadroParaExibirNaTela(Integer posicao);
	
	public Integer calculoYDoQuadroParaExibirNaTela(Integer posicao);
	
	public void tentarMoverParaDireita() throws ExcecaoPosicaoIncorreta;
	
	public void tentarMoverParaEsquerda() throws ExcecaoPosicaoIncorreta;
	
	public void tentarMoverComAPecaViradaParaDireita() throws ExcecaoPosicaoIncorreta;
	
	public void tentarMoverComAPecaViradaParaEsquerda() throws ExcecaoPosicaoIncorreta;

	public void executarJogo() throws ExcecaoPosicaoIncorreta;

	public Boolean getEstaCaindoAcabou();

	public void setEstaCaindoAcabou(Boolean valor);

	public void novaPeca() throws ExcecaoPosicaoIncorreta;

	public void setEstaPausado(Boolean valor);

	public void descerUmaLinha() throws ExcecaoPosicaoIncorreta;

	public void pularParaBaixo() throws ExcecaoPosicaoIncorreta;

	public Boolean verificaSeJogoNaoFoiIniciadoOUPecaSemDesenho();

	public EstadoPeca desenharEm(Integer x, Integer y);
}
