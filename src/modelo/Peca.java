package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Peca implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static void adicionarCoordenadas(ArrayList<Integer> coordenadas, Integer x1, Integer y1, Integer x2,
			Integer y2, Integer x3, Integer y3, Integer x4, Integer y4) {
		
		adicinarPrimeiraCoordenada(coordenadas, x1, y1);
		adicinarSegundaCoordenada(coordenadas, x2, y2);
		adicinarTerceiraCoordenada(coordenadas, x3, y3);
		adicinarQuartaCoordenada(coordenadas, x4, y4);
	}
	
	private static void adicinarPrimeiraCoordenada(ArrayList<Integer> coordenadas, Integer x1, Integer y1) {
		coordenadas.add(x1);
		coordenadas.add(y1);
	}

	private static void adicinarSegundaCoordenada(ArrayList<Integer> coordenadas, Integer x2, Integer y2) {
		coordenadas.add(x2);
		coordenadas.add(y2);
	}

	private static void adicinarTerceiraCoordenada(ArrayList<Integer> coordenadas, Integer x3, Integer y3) {
		coordenadas.add(x3);
		coordenadas.add(y3);
	}

	private static void adicinarQuartaCoordenada(ArrayList<Integer> coordenadas, Integer x4, Integer y4) {
		coordenadas.add(x4);
		coordenadas.add(y4);
	}
}
