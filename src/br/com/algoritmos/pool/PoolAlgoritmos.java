package br.com.algoritmos.pool;

import java.util.ArrayList;

import br.com.algoritmos.busca.*;
import br.com.algoritmos.ordenacao.*;
import br.com.algoritmos.solucao.Solucao;
/**
 * Essa classe representa uma lista com todos os
 * nossos algoritmos de busca e de ordenação.
 * 
 * Classe <code>PoolAlgoritmos</code>
 * 
 * @author Tiago Henrique
 * @version 1.0 (13/12/2015)
 *
 */
public class PoolAlgoritmos {
	/** instancia Pool Algoritmos */
	private static volatile PoolAlgoritmos instance = null;
	
	/** lista algoritmos */
	ArrayList<Solucao> listaAlgoritmos;
	
	/**
	 * Instancia uma nova lista de algoritmos
	 * Este construtor é privado para implementar
	 * o padrão Singleton.
	 */
	private PoolAlgoritmos() {
		listaAlgoritmos = new ArrayList<Solucao>();
		
		criarPoolAlgoritmos();
	}
	
	/**
	 * pega a instancia de PoolAlgoritmos já que
	 * seu construtor é privado. Isso ocorre para
	 * implementar o padrão singleton.
	 * 
	 * @return instancia PoolAlgoritmos
	 */
	public static PoolAlgoritmos getInstance() {
		
		if (instance == null) {
			synchronized (PoolAlgoritmos.class) {
				if (instance == null)
					instance = new PoolAlgoritmos();
			}
		}
		
		return instance;
	}
	
	/**
	 * Obtem lista de algoritmos
	 * 
	 * @return lista de algoritmos
	 */
	public ArrayList<Solucao> getListaAlgoritmos() {
		return listaAlgoritmos;
	}
	
	/**
	 * Obtem algoritmo a partir do nome
	 * 
	 * @param nome
	 * 			nome
	 * @return Solucao
	 */
	public Solucao getAlgoritmo(String nome) {
		
		for (Solucao s : listaAlgoritmos) {
			if (nome == s.getNomeSolucao())
				return s;
		}
		
		return null;
	}
	
	/**
	 * Define a lista com todos os algoritmos, de busca e de ordenação.
	 */
	private void criarPoolAlgoritmos() {
		
		// define algoritmos de busca
		listaAlgoritmos.add(new BuscaBinaria());
		listaAlgoritmos.add(new BuscaEmLargura());
		listaAlgoritmos.add(new BuscaEmProfundidade());
		listaAlgoritmos.add(new BuscaSequencial());
		listaAlgoritmos.add(new BuscaTernaria());
		
		// Defina algoritmos de ordenação
		listaAlgoritmos.add(new BubbleSort());
		listaAlgoritmos.add(new InsertionSort());
		listaAlgoritmos.add(new MergeSort());
		listaAlgoritmos.add(new QuickSort());
		listaAlgoritmos.add(new SelectionSort());
	}
}
