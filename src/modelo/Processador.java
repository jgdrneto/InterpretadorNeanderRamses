package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Processador {
	
	private List<Integer> memoria;
	private List<Integer> registradores;
	
	//private 
	public Processador(Integer nMemoria, int nRegistradores) {
		this.memoria = new ArrayList<Integer>();
		
		for(int i=0;i<nMemoria;i++){
			this.memoria.add(new Integer(0));
		}
		
		this.registradores = new ArrayList<>();
		
		for(int i=0;i<nRegistradores;i++){
			this.registradores.add(new Integer(0));
		}
	}
	
	public List<Integer> getMemoria() {
		return memoria;
	}
	
	public void setMemoria(int memoria) {
		this.memoria = new ArrayList<>();
	}
	
	public List<Integer> getRegistradores() {
		return registradores;
	}
	
	public void setRegistradores(Integer registradores) {
		this.registradores = new ArrayList<>(registradores);
	}
	
	public abstract double cpi();
	
}
