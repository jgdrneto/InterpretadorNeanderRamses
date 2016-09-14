package modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Interpretador {
	private List<List<String>> listaStrings;

	 public Interpretador(String nomeDoArquivo){

	    //Dividindo o arquivo em linhas
	    List<String> dados = leitura(nomeDoArquivo);

	    //Dividindo a string em listas de strings para usar como atributos do objeto
	    this.listaStrings = analiseSintatica(dados);
	    /*
	    for(List<String> l : listaStrings){
	    	for(String m : l ){
	    		System.out.print(" " + m);
	    	}
	    	System.out.println("");
	    }
		*/
	
	 }

	 public static List<String> leitura(String nomeDoArquivo){

	    	List<String> linhas = new ArrayList<String>();

	    	try {
				BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(nomeDoArquivo)));

				while(buffer.ready()){
					linhas.add(buffer.readLine());
				}

				buffer.close();
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}

			if(linhas.isEmpty()){
				System.out.println("Linhas vazias");
			}

	    	return linhas;
	 }

	 public static List<List<String>> analiseSintatica(List<String> dados){
		 if(dados.isEmpty()){
	    	System.out.println("Está vazio, abortando programa");
	    	System.exit(0);
		 }

		//Lista de retorno para o matodo
	    List<List<String>> listaRetorno = new ArrayList<>();

	    //Lista com os atributos do objeto
	    List<String> listaObjeto = new ArrayList<>();
	    /*
	    for(String s : dados){
	    	System.out.println(s);
	    }
	    */
	    //Interando sobre todas as linhas
	    for(int i=0;i<dados.size();i++){

	    	if(!dados.get(i).equals("") && !dados.get(i).startsWith("--")){

			    //Analisa cada variavel
			    for(String s : dados.get(i).split(" ")){
		    		listaObjeto.add(s.toUpperCase());
		    	}

		    	listaObjeto.add(""+(i+1));

		    	listaRetorno.add(new ArrayList<String>(listaObjeto));
				listaObjeto.clear();
    		}

	    }

	    return listaRetorno;
	 }

	 public List<List<String>> getListaStrings() {
		return listaStrings;
	}

	 public abstract void analiseSemantica();
}
