package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import modelo.excecoes.ExcecaoPosicaoIncorreta;
import modelo.interfaces.InterfaceDesenhoJogo;


public class DesenhoJogo implements InterfaceDesenhoJogo, Serializable{

	private static final long serialVersionUID = 1L;
	
	public enum EstadoPeca {
		SemDesenho, ZDesenho, SDesenho, LinhaDesenho, TDesenho, QUADRADO_DESENHO, LDesenho, EspelhadoLDesenho
	}

	private EstadoPeca estado;
	private ArrayList<ArrayList<Integer>> coordenadasDasPecas;
	private Grade<Integer> coordenadasDaPecaAtual;
	private final Integer quantidadeCoordenadas = 2; // (x,y)
	private final Integer quantidadedeQuadrados = 4;
	private Random geradorDeNumerosAleatorios;

	public DesenhoJogo(ArrayList<ArrayList<Integer>> coordenadasDasPecas) {
		geradorDeNumerosAleatorios = new Random();
		this.coordenadasDasPecas = coordenadasDasPecas;
		coordenadasDaPecaAtual = new Grade<Integer>(quantidadedeQuadrados, quantidadeCoordenadas);
	}

	public EstadoPeca getPecaDesenho() {
		return estado;
	}

	public void setPecaDesenho(EstadoPeca pecaDesenho) {
		this.estado = pecaDesenho;
	}

	public void setX(int posicao, int elemento) {
		coordenadasDaPecaAtual.insere(posicao, 0, elemento);
	}
	
	public void setY(int posicao, int elemento) {
		coordenadasDaPecaAtual.insere(posicao, 1, elemento);
	}
	
	public Integer getX(int posicao) {
		return getCoordenadasDaPecaAtual().retorna(posicao, 0);
	}
	
	public Integer getY(int posicao) {
		return getCoordenadasDaPecaAtual().retorna(posicao, 1);
	}
	
	public Grade<Integer> getCoordenadasDaPecaAtual() {
		return coordenadasDaPecaAtual;
	}

	public void setDesenho( EstadoPeca desenho) throws ExcecaoPosicaoIncorreta {
		ArrayList<Integer> coordenadasPeca = coordenadasDasPecas.get(desenho.ordinal());
		adicionarCoordenadasPeca(coordenadasPeca);
		estado = desenho;
	}

	private void adicionarCoordenadasPeca(ArrayList<Integer> coordenadasPeca) {
		Integer cont = 0;
		for (int i = 0; i < coordenadasDaPecaAtual.retornaNumLinhas(); i++)
			for (int j = 0; j < coordenadasDaPecaAtual.retornaNumColunas(); ++j)
				this.coordenadasDaPecaAtual.insere(i, j, coordenadasPeca.get(cont++));
	}

	public void setDesenhoAleatorio() throws ExcecaoPosicaoIncorreta {
		Integer x = Math.abs(geradorDeNumerosAleatorios.nextInt()) % 7 + 1;
		EstadoPeca[] valor = EstadoPeca.values();
		setDesenho(valor[x]);
	}

	public int valorMinimoX() {
		Integer valorMinimo = (Integer) coordenadasDaPecaAtual.retorna(0, 0);
		
		for (int i = 0; i < quantidadedeQuadrados; i++)
			valorMinimo = Math.min(valorMinimo, (Integer) coordenadasDaPecaAtual.retorna(i, 0));
		
		return valorMinimo;
	}

	public int valorMinimoY() {
		Integer valorMinimo = (Integer) coordenadasDaPecaAtual.retorna(0, 1);

		for (int i = 0; i < quantidadedeQuadrados; i++)
			valorMinimo = Math.min(valorMinimo, (Integer) coordenadasDaPecaAtual.retorna(i, 1));
		
		return valorMinimo;
	}
}