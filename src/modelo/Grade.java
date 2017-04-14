package modelo;

import java.io.Serializable;

public class Grade<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Object[][] matriz;
	private Integer numLinhas;
	private Integer numColunas;

	@SuppressWarnings("unchecked")
	public Grade(Integer numLinhas, Integer numColunas) {
		if (numLinhas > 0 && numColunas > 0) {
			this.numLinhas = numLinhas;
			this.numColunas = numColunas;
			this.matriz = (T[][]) new Object[numLinhas][numColunas];
		}
	}

	public void insere(Integer linha, Integer coluna, T elemento) {
		this.matriz[linha][coluna] = elemento;
	}

	@SuppressWarnings("unchecked")
	public T retorna(Integer linha, Integer coluna) {
		return (T) this.matriz[linha][coluna];
	}

	public Integer retornaNumLinhas() {
		return this.numLinhas;
	}

	public Integer retornaNumColunas() {
		return this.numColunas;
	}
}