package modelo;

public abstract class Instrucao {
		
	private int endereco;
	private int linha;
	
	public Instrucao() {
		
	}

	public Instrucao(int linha) {
		this.linha = linha;
	}

	public Instrucao(int endereco, int linha) {
		this.endereco = endereco;
		this.linha = linha;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getEndereco() {
		return endereco;
	}

	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}	
}
