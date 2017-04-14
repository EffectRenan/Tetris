package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import modelo.DesenhoJogo.EstadoPeca;
import modelo.FachadaTetris;
import modelo.Serializacao;
import modelo.excecoes.ExcecaoPosicaoIncorreta;
import modelo.interfaces.Interrompivel;

public class VisaoQuadro extends JPanel implements ActionListener,
		Serializable, Interrompivel {

	private static final long serialVersionUID = 1L;
	private final int larguraQuadro = 12;
	private final int alturaQuadro = 20;
	private Timer tempo;
	private JLabel status;
	private VisaoTetris tela;
	private transient BufferedImage imagemFundoJogo;
	private Serializacao serializacao;
	private FachadaTetris fachadaTetris;
	private VisaoMensagensDiversas visaoMensagensDiversas;

	public VisaoQuadro(VisaoTetris tela) throws IOException {
		instanciaVariaveis();
		this.tela = tela;
		setFocusable(true);
		status = tela.getStatus();
		addKeyListener(new TAdapter());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imagemFundoJogo.getScaledInstance(465, 700, 0), 0, 0, null);
	}

	public void iniciarJogoGravado() throws IOException {
		imagemFundoJogo = VisaoLeituraDeImagens.getImagemFundoJogo();
		tela.setLocationRelativeTo(null);
		tela.setVisible(true);
		setFocusable(true);
		status = tela.getStatus();
		addKeyListener(new TAdapter());
		tela.setAlwaysOnTop(true);
		pausar();
	}

	public void pararTempo() {
		tempo.stop();
	}

	public void iniciarJogo() throws ExcecaoPosicaoIncorreta {
		if (fachadaTetris.getEstaPausado())
			return;

		tempo.start();
		fachadaTetris.executarJogo();
	}

	public void atualizarStatusFimDeJogo() {
		status.setText("FIM DE JOGO");
	}

	private void instanciaVariaveis() throws IOException {
		tempo = new Timer(400, this);
		fachadaTetris = null;
		serializacao = new Serializacao();
		visaoMensagensDiversas = new VisaoMensagensDiversas();
		imagemFundoJogo = VisaoLeituraDeImagens.getImagemFundoJogo();
	}

	public void setFachadaTetris(FachadaTetris fachadaTetris) {
		this.fachadaTetris = fachadaTetris;
	}

	private Integer calcularLarguraDoQuadrado() {
		return (int) getSize().getWidth() / larguraQuadro;
	}

	private Integer calcularAlturaDoQuadrado() {
		return (int) getSize().getHeight() / alturaQuadro;
	}

	public void iniciarTempo() {
		tempo.start();
	}

	public void pausar() {
		if (!fachadaTetris.getEstaIniciado())
			return;

		fachadaTetris.setEstaPausado(!fachadaTetris.getEstaPausado());
		verificaSeOJogoJaEstaPausado();
		repaint();
	}

	private void verificaSeOJogoJaEstaPausado() {
		if (fachadaTetris.getEstaPausado()) {
			tempo.stop();
			status.setText("Pausa!");
		} else {
			tempo.start();
			status.setText("Pontuacao "
					+ String.valueOf(fachadaTetris.getLinhasRemovidas()));
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Dimension tamanho = getSize();
		Integer quadroTopo = (int) tamanho.getHeight() - alturaQuadro
				* calcularAlturaDoQuadrado();

		desenhar(quadroTopo, g);
		prepararDesenharQuadrado(quadroTopo, g);

	}

	private void desenhar(Integer quadroTopo, Graphics g) {
		for (int h = 0; h < alturaQuadro; h++)
			for (int w = 0; w < larguraQuadro; w++)
				desenhando(h, w, g, quadroTopo);
	}

	private void desenhando(Integer h, Integer w, Graphics g, Integer quadroTopo) {
		EstadoPeca desenho = fachadaTetris.desenharEm(w, alturaQuadro - h - 1);

		if (desenho != EstadoPeca.SemDesenho) {
			desenharQuadrado(g, 0 + w * calcularLarguraDoQuadrado(), quadroTopo
					+ h * calcularAlturaDoQuadrado(), desenho);
		}
	}

	private void prepararDesenharQuadrado(Integer quadroTopo, Graphics g) {
		if (fachadaTetris.getDesenhoJogo().getPecaDesenho() != EstadoPeca.SemDesenho) {
			desenhandoQuadrado(quadroTopo, g);
		}
	}

	private void desenhandoQuadrado(Integer quadroTopo, Graphics g) {
		for (int i = 0; i < 4; i++) {
			Integer x = fachadaTetris.calculoXDoQuadroParaExibirNaTela(i);
			Integer y = fachadaTetris.calculoYDoQuadroParaExibirNaTela(i);

			desenharQuadrado(g, 0 + x * calcularLarguraDoQuadrado(), quadroTopo
					+ (alturaQuadro - y - 1) * calcularAlturaDoQuadrado(),
					fachadaTetris.getDesenhoJogo().getPecaDesenho());
		}
	}

	public void removerLinhaCheia() throws ExcecaoPosicaoIncorreta {
		status.setText("Pontucao " + fachadaTetris.getLinhasRemovidas());
		repaint();
	}

	private void desenharQuadrado(Graphics g, int x, int y, EstadoPeca desenho) {
		ArrayList<Color> cores = new VisaoCores().getCores();
		Color color = cores.get(desenho.ordinal());

		g.setColor(color);
		g.fillRect(x + 1, y + 1, calcularLarguraDoQuadrado() - 2,
				calcularAlturaDoQuadrado() - 2);

		g.setColor(color.brighter());
		g.drawLine(x, y + calcularAlturaDoQuadrado() - 1, x, y);
		g.drawLine(x, y, x + calcularLarguraDoQuadrado() - 1, y);

		g.setColor(color.darker());
		g.drawLine(x + 1, y + calcularAlturaDoQuadrado() - 1, x
				+ calcularLarguraDoQuadrado() - 1, y
				+ calcularAlturaDoQuadrado() - 1);
		g.drawLine(x + calcularLarguraDoQuadrado() - 1, y
				+ calcularAlturaDoQuadrado() - 1, x
				+ calcularLarguraDoQuadrado() - 1, y + 1);
	}

	public class TAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (fachadaTetris.verificaSeJogoNaoFoiIniciadoOUPecaSemDesenho())
				return;

			Integer keycode = e.getKeyCode();

			if (keycode == 'p' || keycode == 'P') {
				pausar();
				return;
			}

			if (fachadaTetris.getEstaPausado())
				return;

			try {
				executaSwitch(keycode);
			} catch (ExcecaoPosicaoIncorreta e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (fachadaTetris.getEstaCaindoAcabou()) {
			fachadaTetris.setEstaCaindoAcabou(false);
			try {
				fachadaTetris.novaPeca();
			} catch (ExcecaoPosicaoIncorreta e1) {
				tratarErroPosicaoIncorreta();
			}
		} else {
			try {
				fachadaTetris.descerUmaLinha();
			} catch (ExcecaoPosicaoIncorreta e1) {
				tratarErroPosicaoIncorreta();
			}
		}
	}

	private void tratarErroPosicaoIncorreta() {
		try {
			serializacao.escreverMemoria(this, fachadaTetris.getDesenhoJogo());
		} catch (IOException | ExcecaoPosicaoIncorreta e2) {
			visaoMensagensDiversas.mostrarErroAoEscreverNaMemoria();
		}
		visaoMensagensDiversas.mostrarErroPosicaoIncorreta();
		tela.dispose();
	}

	private void executaSwitch(int keycode) throws ExcecaoPosicaoIncorreta,
			IOException {

		switch (keycode) {
		case KeyEvent.VK_LEFT:
			fachadaTetris.tentarMoverParaEsquerda();
			break;
		case KeyEvent.VK_RIGHT:
			fachadaTetris.tentarMoverParaDireita();
			break;
		case KeyEvent.VK_DOWN:
			try {
				fachadaTetris.descerUmaLinha();
			} catch (ExcecaoPosicaoIncorreta e1) {
				e1.printStackTrace();
			}
			break;
		case KeyEvent.VK_X:
			fachadaTetris.tentarMoverComAPecaViradaParaDireita();
			break;
		case KeyEvent.VK_Z:
			fachadaTetris.tentarMoverComAPecaViradaParaEsquerda();
			break;
		case KeyEvent.VK_S:
			pausar();
			serializacao.escreverMemoria(this, fachadaTetris.getDesenhoJogo());
			visaoMensagensDiversas.mostrarJogoGravadoComSucesso();
			pausar();
			break;
		case KeyEvent.VK_SPACE:
			try {
				fachadaTetris.pularParaBaixo();
			} catch (ExcecaoPosicaoIncorreta e1) {
				e1.printStackTrace();
			}
			break;
		}
	}

	public FachadaTetris getFachadaTetris() {
		return fachadaTetris;
	}

	public void fecharTela() {
		tela.dispose();
	}

	@Override
	public void avisarVisaoRepaint() {
		repaint();
	}

	@Override
	public void avisarVisaoRemoverLinhaCheia() throws ExcecaoPosicaoIncorreta {
		removerLinhaCheia();
		
	}

	@Override
	public void avisarVisaoFecharTela() {
		tela.dispose();
		
	}

	@Override
	public void avisarVisaoPararTempo() {
		tempo.stop();
	}

	@Override
	public void avisarVisaoAtualizarStatusFimDeJogo() {
		atualizarStatusFimDeJogo();
	}

	@Override
	public void avisarVisaoMostrarPontuacaoFinal() {
		visaoMensagensDiversas.mostrarPontuacaoFinal(String.valueOf(fachadaTetris.getLinhasRemovidas()));
	}
}
