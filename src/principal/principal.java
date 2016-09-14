package principal;

import javax.swing.JFileChooser;

import neander.InterpretadorNeander;
import neander.processadorNeander;
import ramses.InterpretadorRamses;
import ramses.processadorRamses;


public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		JFileChooser abrirArquivo = new JFileChooser();

		abrirArquivo.showOpenDialog(null);

		System.out.println(abrirArquivo.getSelectedFile().getPath());
		*/
		
		InterpretadorNeander n = new InterpretadorNeander("/home/neto/Google Drive/Coisas UFRN/OAC/Atividade 2/Qd - Neander.txt");

		processadorNeander processadorN = new processadorNeander(n.getInstrucoes(), 10);

		processadorN.start();
		
		System.out.println("\n\n\n\n\n");
		
		
		InterpretadorRamses r = new InterpretadorRamses("/home/neto/Google Drive/Coisas UFRN/OAC/Atividade 2/Qd - Ramses.txt");
		processadorRamses processadorR = new processadorRamses(r.getInstrucoes(), 10);
		processadorR.start();
		
	}

}
