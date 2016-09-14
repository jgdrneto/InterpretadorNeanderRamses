package neander;

import java.util.List;

import modelo.Processador;
import neander.InstrucaoNeander.CodNeander;

public class processadorNeander extends Processador	{
	boolean negativo=false;
	boolean zero=false;
	int CP;
	private List<InstrucaoNeander> instrucoes;
	boolean parada=false;

	public processadorNeander(List<InstrucaoNeander> nInstrucoes, int memoria) {
		super(memoria+1, 1);

		instrucoes = nInstrucoes;
	}

	public List<InstrucaoNeander> getInstrucoes() {
		return instrucoes;
	}

	public void setInstrucoes(List<InstrucaoNeander> instrucoes) {
		this.instrucoes = instrucoes;
	}

	public boolean isNegativo() {
		return negativo;
	}

	public boolean isZero() {
		return zero;
	}

	public void interpretarInstrucao(InstrucaoNeander i){
		switch(i.getCodigo()){
			case MEM:
				getMemoria().set(i.getLinha(),i.getEndereco());
			break;
			case NOP :

			break;
			case STA:
				getMemoria().set(i.getEndereco(),this.getRegistradores().get(0));
			break;
			case LDA:
				getRegistradores().set(0, this.getMemoria().get(i.getEndereco()));

				verificarRegistradoresDeEstado();
			break;
			case ADD:
				getRegistradores().set(0, getRegistradores().get(0)+this.getMemoria().get(i.getEndereco()));

				verificarRegistradoresDeEstado();

			break;
			case OR:
				getRegistradores().set(0, getRegistradores().get(0) | this.getMemoria().get(i.getEndereco()));

				verificarRegistradoresDeEstado();
			break;
			case AND:
				getRegistradores().set(0, getRegistradores().get(0) & this.getMemoria().get(i.getEndereco()));

				verificarRegistradoresDeEstado();
			break;
			case NOT:
				getRegistradores().set(0, ~getRegistradores().get(0));

				verificarRegistradoresDeEstado();
			break;
			case JMP:
				this.CP = i.getEndereco();
			break;
			case JN:
				if(this.negativo){
					this.CP = i.getEndereco();
				}
			break;
			case JZ:
				if(this.zero){
					this.CP = i.getEndereco();
				}
			break;
			case HLT:
				parada=true;
			break;
			default:
				System.out.println("Instrução não encontrada");
			break;
		}
	}

	private void verificarRegistradoresDeEstado(){
		if(getRegistradores().get(0)==0){
			this.zero=true;
		}else{
			this.zero=false;
		}
		if(getRegistradores().get(0)<0){
			this.negativo= true;
		}else{
			this.negativo=false;
		}
	}

	private void insterpretarInstrucoesAritmetica(){
		CP=-1;
		for(int i=0;i<this.getInstrucoes().size();i++){

			if(!this.getInstrucoes().get(i).equals(CodNeander.MEM)){

				interpretarInstrucao(this.getInstrucoes().get(i));

				if(CP!=-1){
					for(int j=0;j<this.getInstrucoes().size();j++){
						if(this.getInstrucoes().get(j).getLinha()==CP){
							i=j-1;
							CP=-1;
						}
					}

					if(CP!=-1){
						System.out.println("Houve erro no endereçamento de pulo, olhe a linha " +this.getInstrucoes().get(i).getLinha());
						System.exit(0);
					}
				}

				if(parada){
					break;
				}

			}
		}
	}

	public void start(){

		int cont=0;

		for(InstrucaoNeander i : this.getInstrucoes()){
			if(i.getCodigo().equals(CodNeander.MEM)){
				cont++;
				interpretarInstrucao(i);
			}
		}

		System.out.println("Encontradas " + (this.getInstrucoes().size()-cont) + " instruções");

		insterpretarInstrucoesAritmetica();

		System.out.println("CPI: "+ this.cpi());
		System.out.println("Acumulador: " + this.getRegistradores().get(0));
		System.out.println("Memória:");
		for(int i=1;i<this.getMemoria().size();i++){
			System.out.println(i + " " + getMemoria().get(i));
		}

	}

	@Override
	public double cpi() {
		double cont=0;
		int instMem=0;
		for(InstrucaoNeander i : this.instrucoes){
			
			//System.out.println("Instrução:" + i.getCodigo().name());
			
			if(!i.getCodigo().equals(CodNeander.MEM)){
				cont+=quantCicloDaInstrucao(i);
			}else{
				instMem++;
			}
		}
		
		return cont/(this.instrucoes.size()-instMem);
	}

	private int quantCicloDaInstrucao(InstrucaoNeander i) {
		switch(i.getCodigo()){
			case STA:
			case LDA:
			case ADD:
			case OR:
			case AND:
				return 3;
			default:
				return 1;	
		}
	}
}
