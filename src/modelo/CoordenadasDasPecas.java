package modelo;

import java.util.ArrayList;

public abstract class CoordenadasDasPecas {

	private static ArrayList<ArrayList<Integer>> coordenadasDasPecas;

	private CoordenadasDasPecas() {
		coordenadasDasPecas = null;
	}

	public static ArrayList<ArrayList<Integer>> getCoordenadas() {
		if (coordenadasDasPecas == null) {
			coordenadasDasPecas = new ArrayList<ArrayList<Integer>>();
			adicionarCoordenadas();
		}
		return coordenadasDasPecas;
	}

	private static void adicionarCoordenadas() {
		coordenadasDasPecas.add(PecaSemDesenho.getCoordenadas());
		coordenadasDasPecas.add(PecaZDesenho.getCoordenadas());
		coordenadasDasPecas.add(PecaSDesenho.getCoordenadas());
		coordenadasDasPecas.add(PecaLinhaDesenho.getCoordenadas());
		coordenadasDasPecas.add(PecaTDesenho.getCoordenadas());
		coordenadasDasPecas.add(PecaQuadradoDesenho.getCoordenadas());
		coordenadasDasPecas.add(PecaLDesenho.getCoordenadas());
		coordenadasDasPecas.add(PecaEspelhadoLDesenho.getCoordenadas());
	}
}
