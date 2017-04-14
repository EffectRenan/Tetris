package visao;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

public class VisaoCores implements Serializable {

	private static final long serialVersionUID = 1L;
	private final ArrayList<Color> cores;
	private final Color preto = new Color(0, 0, 0);
	private final Color azul = new Color(0, 0, 255);
	private final Color laranja = new Color(255, 127, 0);
	private final Color rosa = new Color(255, 20, 147);
	private final Color vermelho = new Color(255, 0, 0);
	private final Color amarelo = new Color(255, 255, 0);
	private final Color verde = new Color(0, 255, 0);
	private final Color roxo = new Color(255, 0, 255);

	public VisaoCores() {
		cores = new ArrayList<Color>();
		adicionarCores();
	}

	private void adicionarCores() {
		cores.add(preto);
		cores.add(azul);
		cores.add(laranja);
		cores.add(rosa);
		cores.add(vermelho);
		cores.add(amarelo);
		cores.add(verde);
		cores.add(roxo);
	}
	
	public ArrayList<Color> getCores() {
		return cores;
	}
}
