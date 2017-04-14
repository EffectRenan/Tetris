package visao;

import java.awt.BorderLayout;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JLabel;

import modelo.excecoes.ExcecaoPosicaoIncorreta;


public class VisaoTetris extends JFrame implements Serializable{

	private static final long serialVersionUID = 1L;

	JLabel status;

	public VisaoTetris() throws ExcecaoPosicaoIncorreta {
		status = new JLabel("Pontuacao 0");
		add(status, BorderLayout.SOUTH);
		setSize(468, 700);
		setTitle("Tetris");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public JLabel getStatus() {
		return status;
	}
}