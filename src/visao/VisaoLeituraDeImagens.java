package visao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class VisaoLeituraDeImagens {
	
	private static transient BufferedImage imagemFundoTelaInicial;
	private static transient BufferedImage imagemFundoJogo;
	
	private VisaoLeituraDeImagens() {
		imagemFundoTelaInicial = null;
		imagemFundoJogo = null;
	}
	
	public static BufferedImage getImagemFundoTelaInicial() throws IOException {
		if (imagemFundoTelaInicial == null)
			imagemFundoTelaInicial = ImageIO.read(new File("resources/ImagemFundoTelaInicial.jpg"));
		
		return imagemFundoTelaInicial;
	}
	
	public static BufferedImage getImagemFundoJogo() throws IOException {
		if (imagemFundoJogo == null)
			imagemFundoJogo = ImageIO.read(new File("resources/ImagemFundoJogo.jpg"));
		
		return imagemFundoJogo;
	}
}
