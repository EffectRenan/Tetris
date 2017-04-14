package visao;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

public class VisaoPainelTelaInicial extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private transient BufferedImage imagemFundoTelaInicial;
	
	public VisaoPainelTelaInicial() throws IOException {
		imagemFundoTelaInicial = VisaoLeituraDeImagens.getImagemFundoTelaInicial();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imagemFundoTelaInicial.getScaledInstance(450, 700, 0), 0, 0, null);
	}
}
