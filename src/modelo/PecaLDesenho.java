package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class PecaLDesenho extends Peca implements Serializable{

	private static final long serialVersionUID = 1L;
	private final static Integer x1 = -1;
	private final static Integer y1 = -1;
	private final static Integer x2 = 0;
	private final static Integer y2 = -1;
	private final static Integer x3 = 0;
	private final static Integer y3 = 0;
	private final static Integer x4 = 0;
	private final static Integer y4 = 1;
	private static ArrayList<Integer> coordenadas;

	private PecaLDesenho() {
	}

	public static ArrayList<Integer> getCoordenadas() {
		if (coordenadas == null) {
			coordenadas = new ArrayList<Integer>();
			adicionarCoordenadas(coordenadas, x1, y1, x2, y2, x3, y3, x4, y4);
		}
		return coordenadas;
	}
}
