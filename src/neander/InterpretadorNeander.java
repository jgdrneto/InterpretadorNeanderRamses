package neander;

import java.util.ArrayList;
import java.util.List;

import modelo.Interpretador;
import neander.InstrucaoNeander.CodNeander;

public class InterpretadorNeander extends Interpretador {

	private List<InstrucaoNeander> instrucoes;

	public InterpretadorNeander(String nomeDoArquivo) {
		super(nomeDoArquivo);
		/*
		for(int i=0;i<this.getListaStrings().size();i++){

			System.out.println("Código:" + this.getListaStrings().get(i).get(0));
			System.out.println("Endereco:" + this.getListaStrings().get(i).get(1));
			System.out.println("Linha:" + this.getListaStrings().get(i).get(2));
		}
		*/
		analiseSemantica();

	}

	public void analiseSemantica() {

		instrucoes = new ArrayList<InstrucaoNeander>();

		for (int i = 0; i < this.getListaStrings().size(); i++) {

			if (!(this.getListaStrings().get(i).get(0).equals("")
					|| this.getListaStrings().get(i).get(0).equals(" "))) {
				instrucoes.add(construirInstrucao(this.getListaStrings().get(i)));
			}
		}
	}

	private InstrucaoNeander construirInstrucao(List<String> list) {

		InstrucaoNeander n = new InstrucaoNeander();

		for(CodNeander c : CodNeander.values()){
			if(c.name().equals(list.get(0))){
				n.setCodigo(c);
			}
		}

		if(n.getCodigo()==null){

	    	System.out.println("Nome de instrução não existente: " + list.get(0));
	    	System.exit(0);

		}else{
			if(list.size()==2){
				n.setEndereco(0);
				n.setLinha(Integer.parseInt(list.get(1)));
			}else{
				if(list.size()==3){
					n.setEndereco(Integer.parseInt(list.get(1)));
					n.setLinha(Integer.parseInt(list.get(2)));
				}
			}	
		}

		return n;
	}

	public List<InstrucaoNeander> getInstrucoes() {
		return instrucoes;
	}

}
