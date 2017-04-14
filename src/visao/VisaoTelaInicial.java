package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.FachadaTetris;
import modelo.Serializacao;
import modelo.excecoes.ExcecaoPosicaoIncorreta;

public class VisaoTelaInicial extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JButton botaoNovoJogo;
	private JButton botaoCarregarJogo;
	private Container principal;
	private VisaoMensagensDiversas visaoMensagensDiversas;
	private VisaoPainelTelaInicial painel;
	private JPanel botoes;

	public VisaoTelaInicial() throws ExcecaoPosicaoIncorreta, IOException {
		painel = new VisaoPainelTelaInicial();
		visaoMensagensDiversas = new VisaoMensagensDiversas();
		botaoNovoJogo = new JButton("Novo jogo");
		botaoCarregarJogo = new JButton("Carregar jogo");
		principal = new Container();
		botoes = new JPanel();
	}
	
	public void iniciarTela() {
		preparaBotaoNovoJogo();
		preparaBotaoCarregarJogo();
		adicionarBotoesNoPainel();
		preparaTelaPrincipal();
	}

	private void adicionarBotoesNoPainel() {
		painel.add(botaoNovoJogo);
		painel.add(botaoCarregarJogo);
	}

	private void preparaTelaPrincipal() {
		painel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 300));
		principal = getContentPane();
		principal.add(painel);
		principal.add(botoes, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tetris - Tela inicial");
		setSize(450, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	private void preparaBotaoNovoJogo() {
		botaoNovoJogo.addActionListener(new TratadorDeClickNovoJogo());
	}

	private void preparaBotaoCarregarJogo() {
		botaoCarregarJogo.addActionListener(new TratadorDeClickCarregarJogo());
	}

	private class TratadorDeClickNovoJogo implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			try {
				visaoMensagensDiversas.mostrarOrientacoesDoJogo();
				VisaoTetris visaoTetris = new VisaoTetris();
				VisaoQuadro visaoQuadro = new VisaoQuadro(visaoTetris);
				visaoQuadro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				FachadaTetris fachadaTetris = new FachadaTetris(visaoQuadro);
				
				visaoQuadro.setFachadaTetris(fachadaTetris);
				
				visaoTetris.add(visaoQuadro);
				visaoQuadro.iniciarJogo();
				
				visaoTetris.setLocationRelativeTo(null);
				visaoTetris.setVisible(true);
				dispose();
				
			} catch (ExcecaoPosicaoIncorreta e) {
				dispose();
			} catch (IOException e) {
				
				dispose();
			}
		}
	}

	private class TratadorDeClickCarregarJogo implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			Serializacao serializacao = new Serializacao();
			try {
				serializacao.lerMomoria();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			VisaoQuadro visaoQuadro =  (VisaoQuadro) serializacao.getVisaoQuadro();
			visaoQuadro.getFachadaTetris().setDesenhoJogo(serializacao.getDesenhoJogo());
			visaoMensagensDiversas.mostrarJogoCarregadoComSucesso();
			dispose();
			try {
				visaoQuadro.iniciarJogoGravado();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
