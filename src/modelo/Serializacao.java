package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import modelo.excecoes.ExcecaoPosicaoIncorreta;



public class Serializacao implements Serializable {

	private static final long serialVersionUID = 1L;
	private Serializable visaoQuadro;
	private DesenhoJogo desenhoJogo;
	
        
	public void escreverMemoria(Serializable visaoQuadro,  DesenhoJogo desenhoJogo) throws IOException, ExcecaoPosicaoIncorreta {
		FileOutputStream caminhoEscrita = new FileOutputStream("resources/visaoQuadro.ser");
		ObjectOutputStream escritaVisaoQuadro = new ObjectOutputStream(caminhoEscrita);
		escritaVisaoQuadro.writeObject(visaoQuadro);
		escritaVisaoQuadro.close();
		
		FileOutputStream caminhoEscritaDesnehoJogo = new FileOutputStream("resources/desenhoJogo.ser");
		ObjectOutputStream escritaDesenhoJogo = new ObjectOutputStream(caminhoEscritaDesnehoJogo);
		escritaDesenhoJogo.writeObject(desenhoJogo);
		escritaDesenhoJogo.close();
	}

	public void lerMomoria() throws IOException, ClassNotFoundException {
		FileInputStream caminhoLerQuadro = new FileInputStream("resources/visaoQuadro.ser");
		ObjectInputStream visaoQuadroLido = new ObjectInputStream(caminhoLerQuadro);
		Serializable visaoQuadro = (Serializable) visaoQuadroLido.readObject();
		this.visaoQuadro = visaoQuadro;
		visaoQuadroLido.close();
		
		FileInputStream caminhoLerDesenhoJogo = new FileInputStream("resources/desenhoJogo.ser");
		ObjectInputStream visaoDesenhoJogoLido = new ObjectInputStream(caminhoLerDesenhoJogo);
		DesenhoJogo desenhoJogo = (DesenhoJogo) visaoDesenhoJogoLido.readObject();
		this.desenhoJogo = desenhoJogo;
		visaoDesenhoJogoLido.close();
	}

	public Serializable getVisaoQuadro() {
		return visaoQuadro;
	}
	
	public DesenhoJogo getDesenhoJogo() {
		return desenhoJogo;
	}

}