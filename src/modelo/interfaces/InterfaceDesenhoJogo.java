package modelo.interfaces;

import modelo.DesenhoJogo.EstadoPeca;
import modelo.excecoes.ExcecaoPosicaoIncorreta;
import modelo.Grade;

public interface InterfaceDesenhoJogo {

	public int valorMinimoX();

	public int valorMinimoY();

	public EstadoPeca getPecaDesenho();

	public void setX(int index, int elemento);

	public void setY(int index, int elemento);

	public Grade<Integer> getCoordenadasDaPecaAtual();

	public void setPecaDesenho(EstadoPeca pecaDesenho);

	public void setDesenho(EstadoPeca desenho) throws ExcecaoPosicaoIncorreta;

	public void setDesenhoAleatorio() throws ExcecaoPosicaoIncorreta;

}
