package neander;

import modelo.Instrucao;

public class InstrucaoNeander extends Instrucao {
	enum CodNeander{
		MEM,
		NOP,
		STA,
		LDA,
		ADD,
		OR,
		AND,
		NOT,
		JMP,
		JN,
		JZ,
		HLT	
	}
	
	CodNeander codigo;
	
	public InstrucaoNeander() {
		// TODO Auto-generated constructor stub
	}
	
	public InstrucaoNeander(CodNeander nCodigo) {
		// TODO Auto-generated constructor stub
		codigo = nCodigo;
	}
	public InstrucaoNeander(CodNeander nCodigo, int endereco) {
		// TODO Auto-generated constructor stub
		codigo = nCodigo;
	}
	
	public CodNeander getCodigo() {
		return codigo;
	}

	public void setCodigo(CodNeander codigo) {
		this.codigo = codigo;
	}
	
	
}
