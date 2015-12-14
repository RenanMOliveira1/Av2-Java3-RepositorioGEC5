package br.com.executavel;

import br.com.servidor.ServidorCentral;
/**
 * Esta classe cria e inicia uma Thread do servidor.
 * 
 * Classe <code>MainServidor</code>
 * 
 * @author Tiago Henrique
 * @version 1.0 (13/12/2015)
 *
 */
public class MainServidor {
	
	public static void main(String[] args) {
		
		Thread threadServidor = new Thread(ServidorCentral.getInstancia(12345));
		threadServidor.start();
	}
}
