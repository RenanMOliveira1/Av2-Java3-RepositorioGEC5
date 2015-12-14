package br.com.executavel;

import br.com.algoritmos.pool.PoolAlgoritmos;
import br.com.algoritmos.solucao.Solucao;
/**
 * Esta Classe cria e inicia os Threads de todos 
 * os algoritmos da nossa lista (PoolAlgoritmos).
 * 
 * Classe <code>MainAlgoritmos</code>
 * 
 * @author Tiago Henrique
 * @version 1.0 (13/12/2015)
 */
public class MainAlgoritmos {
	public static void main(String[] args) {
		PoolAlgoritmos pool = PoolAlgoritmos.getInstance();
		
		for (Solucao s : pool.getListaAlgoritmos()) {
			Thread t = new Thread(s);
			t.start();
		}
	}
}
