package ramses;

import modelo.Instrucao;

public class InstrucaoRamses extends Instrucao {
	public enum CodRamses{
		MEM,
		NOP,
		STR,
		LDR,
		ADD,
		OR,
		AND,
		NOT,
		SUB,
		JMP,
		JN,
		JZ,
		JC,
		JSR,
		NEG,
		SHR,
		HLT	
	}
	
	public enum TipoEndereco{
		DIR,
		IND,
		IMD,
		IDX
	}
	
	public enum Registrador{
		A,
		B,
		X
	}
	
	CodRamses codigo;
	Registrador reg;
	TipoEndereco tipoEnd;
	public InstrucaoRamses() {
		// TODO Auto-generated constructor stub
	}
	
	public InstrucaoRamses(CodRamses nCodigo) {
		// TODO Auto-generated constructor stub
		codigo = nCodigo;
	}
	public InstrucaoRamses(CodRamses nCodigo, int endereco) {
		// TODO Auto-generated constructor stub
		super(endereco);
		codigo = nCodigo;
		this.setEndereco(endereco);
	}
	
	public InstrucaoRamses(CodRamses nCodigo, int endereco,TipoEndereco nTEnd) {
		// TODO Auto-generated constructor stub
		super(endereco);
		codigo = nCodigo;
		this.tipoEnd = nTEnd;
		
	}
		
	public Registrador getRegPorNome(String nome){
		for(Registrador r : Registrador.values()){
			if(r.name().equals(nome)){
				return r;
			}
		}
		return null;
	}
	
	public TipoEndereco getEndPorNome(String nome){
		for(TipoEndereco e : TipoEndereco.values()){
			if(e.name().equals(nome)){
				return e;
			}
		}
		return null;
	}
	
	public CodRamses getCodPorNome(String nome){
		for(CodRamses c : CodRamses.values()){
			if(c.name().equals(nome)){
				return c;
			}
		}
		return null;
	}
	
	public Registrador getReg() {
		return reg;
	}

	public void setReg(Registrador reg) {
		this.reg = reg;
	}

	public CodRamses getCodigo() {
		return codigo;
	}

	public void setCodigo(CodRamses codigo) {
		this.codigo = codigo;
	}

	public TipoEndereco getTipoEnd() {
		return tipoEnd;
	}

	public void setTipoEnd(TipoEndereco tipoEnd) {
		this.tipoEnd = tipoEnd;
	}
	
}
