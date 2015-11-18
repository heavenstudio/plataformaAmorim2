package br.com.muranodesign.resources;


public class calc {

	public static void main(String[] args) {
		
		int tempo = 60;
		double valor = 600;
		double constante = valor;
		double taxa = 0.018;
		
		for(int i = 0; i < tempo; i++){
			
			valor = valor * (1 + taxa) + constante;
			
		}
		
		

	}

}
