package ramses;

import java.util.ArrayList;
import java.util.List;

import modelo.Interpretador;
import ramses.InstrucaoRamses.Registrador;
import ramses.InstrucaoRamses.TipoEndereco;

public class InterpretadorRamses extends Interpretador {

	private List<InstrucaoRamses> instrucoes;

	public InterpretadorRamses(String nomeDoArquivo) {
		super(nomeDoArquivo);
		/*
		for(int i=0;i<this.getListaStrings().size();i++){
			
			for(String s : this.getListaStrings().get(i)){
				System.out.println("String: " + s);
			}
			System.out.println("----------------------");
			
		}
		*/
		analiseSemantica();

	}

	public void analiseSemantica() {

		instrucoes = new ArrayList<InstrucaoRamses>();

		for (int i = 0; i < this.getListaStrings().size(); i++) {

			if (!(this.getListaStrings().get(i).get(0).equals("")
					|| this.getListaStrings().get(i).get(0).equals(" "))) {
				instrucoes.add(construirInstrucao(this.getListaStrings().get(i)));
			}
		}
	}

	private InstrucaoRamses construirInstrucao(List<String> list) {

		InstrucaoRamses n = new InstrucaoRamses();
		
		n.setCodigo(n.getCodPorNome(list.get(0)));

		if(n.getCodigo()==null){

	    	System.out.println("Nome de instrução não existente: " + list.get(0));
	    	System.exit(0);

		}else{
			if(list.size()>1){
				switch(list.size()){
					case 2:
						analisarTamanho2(n,list);
					break;
					case 3:
						analisarTamanho3(n,list);
					break;
					case 4:
						analisarTamanho4(n, list);
					break;	
					case 5:	
						analisarTamanho5(n,list);
					break;
					default:
						System.out.println("Instrução com mais de 4 parâmetros na linha " + list.get(list.size()-1));
					break;	
				}
				//Setando a linha
				n.setLinha(Integer.parseInt(list.get(list.size()-1)));
			}
		}

		return n;
	}
	
	private void analisarTamanho2(InstrucaoRamses n, List<String> list) {
		/*NULL*/
	}
	
	private void analisarTamanho3(InstrucaoRamses n, List<String> list) {
		switch(n.getCodigo()){
			case NOT:
			case NEG:
			case SUB:	
			
				Registrador r = n.getRegPorNome(list.get(1));
				
				if(r==null){
					System.out.println("Erro ao obter o registrador  na linha " + list.get(list.size()-1));
				}else{
					n.setReg(r);
				}
			
			break;
			case MEM:
				n.setEndereco(Integer.parseInt(list.get(1)));
			break;
			default:
				System.out.println("Erro de má formação da instrução, verifique linha "+ list.get(list.size()-1));
			break;	
		}	
	}
	
	private void analisarTamanho4(InstrucaoRamses n,List<String> list) {
		switch(n.getCodigo()){
			case JMP:	
			case JN:
			case JZ:
			case JC:
			case JSR:
				TipoEndereco e = n.getEndPorNome(list.get(1));
				
				if(e==null){
					System.out.println("Erro ao obter o endereço  na linha " + list.get(list.size()-1));
				}else{
					n.setTipoEnd(e);
				}
				
				n.setEndereco(Integer.parseInt(list.get(2)));
			break;	
			default:
				System.out.println("Erro de má formação da instrução,olhe a linha " + list.get(list.size()-1));
			break;	
		}
	}
	
	private void analisarTamanho5(InstrucaoRamses n,List<String> list) {
		
		Registrador r = n.getRegPorNome(list.get(1));
		
		if(r==null){
			System.out.println("Erro ao obter o registrador  na linha " + list.get(list.size()-1));
		}else{
			n.setReg(r);
		}
		
		TipoEndereco e = n.getEndPorNome(list.get(2));
		
		if(e==null){
			System.out.println("Erro ao obter o endereço  na linha " + list.get(list.size()-1));
		}else{
			n.setTipoEnd(e);
		}
		
		n.setEndereco(Integer.parseInt(list.get(3)));
		
	}

	public List<InstrucaoRamses> getInstrucoes() {
		return instrucoes;
	}

}
