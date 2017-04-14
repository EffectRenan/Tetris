package modelo;

import modelo.DesenhoJogo.EstadoPeca;

public abstract class GiroDaPeca {

	protected DesenhoJogo desenhoJogo;

	public GiroDaPeca() {
		desenhoJogo = null;
	}

	protected DesenhoJogo girarEsquerda() {

		if (desenhoJogo.getPecaDesenho() == EstadoPeca.QUADRADO_DESENHO)
			return desenhoJogo;

		DesenhoJogo resultado = new DesenhoJogo(CoordenadasDasPecas.getCoordenadas());
		resultado.setPecaDesenho(desenhoJogo.getPecaDesenho());
		girandoParaEsquerda(resultado);

		return resultado;
	}

	private void girandoParaEsquerda(DesenhoJogo resultado) {
		for (int i = 0; i < 4; ++i) {
			resultado.setX(i, desenhoJogo.getY(i));
			resultado.setY(i, -desenhoJogo.getX(i));
		}
	}

	protected DesenhoJogo girarDireita() {

		if (desenhoJogo.getPecaDesenho() == EstadoPeca.QUADRADO_DESENHO)
			return desenhoJogo;

		DesenhoJogo resultado = new DesenhoJogo(CoordenadasDasPecas.getCoordenadas());
		resultado.setPecaDesenho(desenhoJogo.getPecaDesenho());
		girandoParaDireita(resultado);

		return resultado;
	}
	
	private void girandoParaDireita(DesenhoJogo resultado) {
		for (int i = 0; i < 4; ++i) {
			resultado.setX(i, -desenhoJogo.getY(i));
			resultado.setY(i, desenhoJogo.getX(i));
		}
	}
}
