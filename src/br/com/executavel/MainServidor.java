package br.com.executavel;

import br.com.servidor.ServidorCentral;

public class MainServidor {
	
	public static void main(String[] args) {
		
		Thread threadServidor = new Thread(ServidorCentral.getInstancia(12345));
		threadServidor.start();
	}
}
