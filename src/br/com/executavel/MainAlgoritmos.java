package br.com.executavel;

import br.com.algoritmos.pool.PoolAlgoritmos;
import br.com.algoritmos.solucao.Solucao;

public class MainAlgoritmos {
	public static void main(String[] args) {
		PoolAlgoritmos pool = PoolAlgoritmos.getInstance();
		
		for (Solucao s : pool.getListaAlgoritmos()) {
			Thread t = new Thread(s);
			t.start();
		}
	}
}
