package ramses;

import java.util.ArrayList;
import java.util.List;

import modelo.Par;
import modelo.Processador;
import ramses.InstrucaoRamses.CodRamses;
import ramses.InstrucaoRamses.Registrador;

public class processadorRamses extends Processador	{
	boolean negativo=false;
	boolean zero=false;
	boolean carry=false;
	List<Par<Integer,Integer>> pontoRetorno;
	int CP;
	private List<InstrucaoRamses> instrucoes;
	boolean parada=false;

	public processadorRamses(List<InstrucaoRamses> nInstrucoes, int memoria) {
		super(memoria+1, 3);
		
		pontoRetorno = new ArrayList<>();
		
		instrucoes = nInstrucoes;
	}

	public List<InstrucaoRamses> getInstrucoes() {
		return instrucoes;
	}

	public void setInstrucoes(List<InstrucaoRamses> instrucoes) {
		this.instrucoes = instrucoes;
	}

	public boolean isNegativo() {
		return negativo;
	}

	public boolean isZero() {
		return zero;
	}
	
	public boolean isCarry(){
		return this.carry;
	}
	
	public void interpretarInstrucao(InstrucaoRamses i){
		switch(i.getCodigo()){
			case MEM:
				getMemoria().set(i.getLinha(),i.getEndereco());
			break;
			case NOP :

			break;
			case STR:
				switch(i.getTipoEnd()){
					case DIR:
						/*
						System.out.println("Registrador: " + i.getReg());
						System.out.println("Endereco: " + i.getEndereco());
						System.out.println("Valor no registrador:"+this.getRegistradores().get(i.getReg().ordinal()));
						*/
						getMemoria().set(i.getEndereco(),this.getRegistradores().get(i.getReg().ordinal()));
					break;
					case IND:
						getMemoria().set(getMemoria().get(i.getEndereco()),this.getRegistradores().get(i.getReg().ordinal()));
					break;
					case IMD:
						getRegistradores().set(i.getReg().ordinal(), i.getEndereco());
					break;
					case IDX:
						if(i.getEndereco()+getRegistradores().get(2)>1 && i.getEndereco()+getRegistradores().get(3)<=getMemoria().size()){
							getMemoria().set(i.getEndereco()+getRegistradores().get(2), this.getRegistradores().get(i.getReg().ordinal()));;
						}else{
							System.out.println("Erro na obtenção do endereço, verifique linha "+ i.getLinha());
						}
					break;	
					default:
						System.out.println("Erro na obtenção do modo de endereçamento, verifique linha "+ i.getLinha());
					break;	
				}
				
				verificarRegistradoresDeEstado();
			break;
			case LDR:	
				switch(i.getTipoEnd()){
					case DIR:
						getRegistradores().set(i.getReg().ordinal(), this.getMemoria().get(i.getEndereco()));
					break;
					case IND:
						getRegistradores().set(i.getReg().ordinal(), this.getMemoria().get(this.getMemoria().get(i.getEndereco())));
					break;
					case IMD:
						getRegistradores().set(i.getReg().ordinal(), i.getEndereco());
					break;
					case IDX:
						getRegistradores().set(i.getReg().ordinal(), this.getMemoria().get(i.getEndereco()+getRegistradores().get(2)));
					break;	
					default:
						System.out.println("Erro na obtenção do modo de endereçamento, verifique linha "+ i.getLinha());
					break;	
				}
				
				verificarRegistradoresDeEstado();
			break;
			case ADD:
				switch(i.getTipoEnd()){
					case DIR:
						getRegistradores().set(i.getReg().ordinal(), getRegistradores().get(i.getReg().ordinal()) + getMemoria().get(i.getEndereco()));
					break;
					case IND:
						getRegistradores().set(i.getReg().ordinal(), getRegistradores().get(i.getReg().ordinal()) + getMemoria().get(getMemoria().get(i.getEndereco())));
					break;
					case IMD:
						getRegistradores().set(i.getReg().ordinal(),getRegistradores().get(i.getReg().ordinal())+ i.getEndereco());
					break;
					case IDX:
						getRegistradores().set(i.getReg().ordinal(), this.getMemoria().get(i.getEndereco()+getRegistradores().get(2)));
					break;	
					default:
						System.out.println("Erro na obtenção do modo de endereçamento, verifique linha "+ i.getLinha());
					break;	
				}
				verificarRegistradoresDeEstado();

			break;
			case OR:
				switch(i.getTipoEnd()){
					case DIR:
						getRegistradores().set(i.getReg().ordinal(), getRegistradores().get(i.getReg().ordinal()) | this.getMemoria().get(i.getEndereco()));
					break;
					case IND:
						getRegistradores().set(i.getReg().ordinal(), getRegistradores().get(i.getReg().ordinal()) | getMemoria().get(getMemoria().get(i.getEndereco())));
					break;
					case IMD:
						getRegistradores().set(i.getReg().ordinal(),getRegistradores().get(i.getReg().ordinal()) | i.getEndereco());
					break;
					case IDX:
						getRegistradores().set(i.getReg().ordinal(), getRegistradores().get(i.getReg().ordinal()) | this.getMemoria().get(i.getEndereco()+getRegistradores().get(2)));
					break;	
					default:
						System.out.println("Erro na obtenção do modo de endereçamento, verifique linha "+ i.getLinha());
					break;	
				}
				
				verificarRegistradoresDeEstado();
			
			break;
			case AND:
				switch(i.getTipoEnd()){
				case DIR:
					getRegistradores().set(i.getReg().ordinal(), getRegistradores().get(i.getReg().ordinal()) & this.getMemoria().get(i.getEndereco()));
				break;
				case IND:
					getRegistradores().set(i.getReg().ordinal(), getRegistradores().get(i.getReg().ordinal()) & getMemoria().get(getMemoria().get(i.getEndereco())));
				break;
				case IMD:
					getRegistradores().set(i.getReg().ordinal(),getRegistradores().get(i.getReg().ordinal()) & i.getEndereco());
				break;
				case IDX:
					getRegistradores().set(i.getReg().ordinal(), getRegistradores().get(i.getReg().ordinal()) & this.getMemoria().get(i.getEndereco()+getRegistradores().get(2)));
				break;	
				default:
					System.out.println("Erro na obtenção do modo de endereçamento, verifique linha "+ i.getLinha());
				break;	
			}
				verificarRegistradoresDeEstado();
			break;
			case NOT:
				getRegistradores().set(i.getReg().ordinal(), ~getRegistradores().get(i.getReg().ordinal()));

				verificarRegistradoresDeEstado();
			break;
			case SUB:
				switch(i.getTipoEnd()){
					case DIR:
						getRegistradores().set(i.getReg().ordinal(), getRegistradores().get(i.getReg().ordinal()) - getMemoria().get(i.getEndereco()));
					break;
					case IND:
						getRegistradores().set(i.getReg().ordinal(), getRegistradores().get(i.getReg().ordinal()) - getMemoria().get(getMemoria().get(i.getEndereco())));
					break;
					case IMD:
						getRegistradores().set(i.getReg().ordinal(),getRegistradores().get(i.getReg().ordinal()) - i.getEndereco());
					break;
					case IDX:
						getRegistradores().set(i.getReg().ordinal(), this.getMemoria().get(i.getEndereco() - getRegistradores().get(2)));
					break;	
					default:
						System.out.println("Erro na obtenção do modo de endereçamento, verifique linha "+ i.getLinha());
					break;	
				}
				verificarRegistradoresDeEstado();
			break;	
			case JMP:
				switch(i.getTipoEnd()){
					case DIR:
						this.CP = i.getEndereco();
					break;
					case IND:
						boolean olharAinda = true;
						/*
						System.out.println("Linha: " + pontoRetorno.get(0).getPrimeiro());
						System.out.println("Linha do enereço guardado: " + pontoRetorno.get(0).getSegundo());
						System.out.println("Endereco: " + i.getEndereco());
						*/
						if(!pontoRetorno.isEmpty()){
							for(int j=0;j<this.pontoRetorno.size();j++){
								if(i.getEndereco()==(pontoRetorno.get(j).getPrimeiro())){
									CP = pontoRetorno.get(j).getSegundo();
									pontoRetorno.remove(j);
									olharAinda=false;
								}
							}
						}
						if(olharAinda){
							this.CP = getMemoria().get(i.getEndereco());
						}
					break;
					case IMD:
						CP= getMemoria().get(getMemoria().get(i.getEndereco()));
					break;
					case IDX:
						CP= getMemoria().get(i.getEndereco()+getRegistradores().get(2));
					break;	
					default:
						System.out.println("Erro na obtenção do modo de endereçamento, verifique linha "+ i.getLinha());
					break;	
				}
				/*
				if(!pontoRetorno.isEmpty()){
					for(int j=0;j<this.getInstrucoes().size();j++){
						if(this.getInstrucoes().get(j).getLinha()==CP){
							i=j-1;
							CP=-1;
						}
					}
				}
				*/
				//this.CP = i.getEndereco();
			break;
			case JN:
				switch(i.getTipoEnd()){
					case DIR:
						if(this.negativo){
							this.CP = i.getEndereco();	
						}
					break;
					case IND:
						if(this.negativo){
								
							boolean olharAinda = true;
							/*
							System.out.println("Linha: " + pontoRetorno.get(0).getPrimeiro());
							System.out.println("Linha do enereço guardado: " + pontoRetorno.get(0).getSegundo());
							System.out.println("Endereco: " + i.getEndereco());
							*/
							if(!pontoRetorno.isEmpty()){
								for(int j=0;j<this.pontoRetorno.size();j++){
									if(i.getEndereco()==(pontoRetorno.get(j).getPrimeiro())){
										CP = pontoRetorno.get(j).getSegundo();
										pontoRetorno.remove(j);
										olharAinda=false;
									}
								}
							}
							if(olharAinda){
								this.CP = getMemoria().get(getMemoria().get(i.getEndereco()));
							}
						}
					break;
					case IMD:
						if(this.negativo){
							this.CP = instrucoes.get(i.getEndereco()).getLinha();	
						}
					break;
					case IDX:
						if(this.negativo){
							this.CP = i.getEndereco()+getRegistradores().get(2);	
						}
					break;	
					default:
						System.out.println("Erro na obtenção do modo de endereçamento, verifique linha "+ i.getLinha());
					break;	
			}
			case JZ:
				switch(i.getTipoEnd()){
					case DIR:
						if(this.zero){
							this.CP = i.getEndereco();	
						}
					break;
					case IND:
						if(this.zero){
								
							boolean olharAinda = true;
							/*
							System.out.println("Linha: " + pontoRetorno.get(0).getPrimeiro());
							System.out.println("Linha do enereço guardado: " + pontoRetorno.get(0).getSegundo());
							System.out.println("Endereco: " + i.getEndereco());
							*/
							if(!pontoRetorno.isEmpty()){
								for(int j=0;j<this.pontoRetorno.size();j++){
									if(i.getEndereco()==(pontoRetorno.get(j).getPrimeiro())){
										CP = pontoRetorno.get(j).getSegundo();
										pontoRetorno.remove(j);
										olharAinda=false;
									}
								}
							}
							if(olharAinda){
								this.CP = getMemoria().get(getMemoria().get(i.getEndereco()));
							}
						}
					break;
					case IMD:
						if(this.zero){
							this.CP = instrucoes.get(i.getEndereco()).getLinha();	
						}
					break;
					case IDX:
						if(this.zero){
							this.CP = i.getEndereco()+getRegistradores().get(2);	
						}
					break;	
					default:
						System.out.println("Erro na obtenção do modo de endereçamento, verifique linha "+ i.getLinha());
					break;	
				}
			break;
			case JC:
				if(this.carry){
					this.CP = i.getEndereco();
				}
			break;	
			case JSR:
				CP = i.getEndereco()+1;
				//System.out.println("CP: " + CP);
				
				pontoRetorno.add(new Par<Integer, Integer>(i.getEndereco(),instrucoes.get(instrucoes.indexOf(i)+1).getLinha()));
			break;
			case NEG:
				getRegistradores().set(i.getReg().ordinal(), (-1)*getRegistradores().get(i.getReg().ordinal()));
				
				verificarRegistradoresDeEstado();
			break;
			case SHR:
				getRegistradores().set(i.getReg().ordinal(), getRegistradores().get(i.getReg().ordinal())/2);
				
				verificarRegistradoresDeEstado();
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
		
		this.zero=false;	
		this.negativo=false;
		this.carry = false;
		
		for(Integer reg : getRegistradores()){
		
			if(reg==0){
				this.zero=true;
			}
			if(reg<0){
				this.negativo= true;
			}
			if(reg>256){
				this.carry=true;
			}
		}
		
	}

	private void insterpretarInstrucoesAritmetica(){
		CP=-1;

		for(int i=0;i<this.getInstrucoes().size();i++){
			
			if(!this.getInstrucoes().get(i).equals(CodRamses.MEM)){

				interpretarInstrucao(this.getInstrucoes().get(i));
				
				//System.out.println("INSTRUÇÃO: " + this.getInstrucoes().get(i).getCodigo().name() + "  :"+ CP); 
				
				if(CP!=-1){
					for(int j=0;j<this.getInstrucoes().size();j++){
						//System.out.println("Valores das linhas: " + this.getInstrucoes().get(j).getLinha());
						if(this.getInstrucoes().get(j).getLinha()==CP){
							//System.out.println("Valor de j:" + j);
							i=j-1;
							CP=-1;
						}
					}
				}	
				
				if(CP!=-1){					
					System.out.println("Houve erro no endereçamento de pulo, olhe a linha " +this.getInstrucoes().get(i).getLinha());
					System.exit(0);
				}
				

				if(parada){
					break;
				}

			}
		}
	}

	public void start(){

		int cont=0;

		for(InstrucaoRamses i : this.getInstrucoes()){
			if(i.getCodigo().equals(CodRamses.MEM)){
				cont++;
				interpretarInstrucao(i);
			}
		}

		System.out.println("Encontradas " + (this.getInstrucoes().size()-cont) + " instruções");

		insterpretarInstrucoesAritmetica();

		System.out.println("CPI: " + this.cpi());
		for(int i=0;i<this.getRegistradores().size();i++){
			System.out.println("Registrador "+ Registrador.values()[i].name()  + " : " + this.getRegistradores().get(i));
		}
		System.out.println("Memória:");
		for(int i=1;i<this.getMemoria().size();i++){
			System.out.println(i + " " + getMemoria().get(i));
		}

	}
	
	@Override
	public double cpi() {
		double cont=0;
		int instMem=0;
		for(InstrucaoRamses i : this.instrucoes){
			
			if(!i.getCodigo().equals(CodRamses.MEM)){
				cont+=quantCicloDaInstrucao(i);
			}else{
				instMem++;
			}
		}
		return cont/(this.instrucoes.size()-instMem);
	}
	
	private int quantCicloDaInstrucao(InstrucaoRamses i) {
		switch(i.getCodigo()){
			case STR:
			case LDR:
			case ADD:
			case OR:
			case AND:
			case SUB:
			case JSR:	
				return 3;
			default:
				return 1;	
		}
	}
}
