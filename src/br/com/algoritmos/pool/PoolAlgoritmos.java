package br.com.algoritmos.pool;

import java.util.ArrayList;

import br.com.algoritmos.busca.*;
import br.com.algoritmos.ordenacao.*;
import br.com.algoritmos.solucao.Solucao;

public class PoolAlgoritmos {
	private static volatile PoolAlgoritmos instance = null;
	
	ArrayList<Solucao> listaAlgoritmos;
	
	private PoolAlgoritmos() {
		listaAlgoritmos = new ArrayList<Solucao>();
		
		criarPoolAlgoritmos();
	}
	
	public static PoolAlgoritmos getInstance() {
		
		if (instance == null) {
			synchronized (PoolAlgoritmos.class) {
				if (instance == null)
					instance = new PoolAlgoritmos();
			}
		}
		
		return instance;
	}
	
	public ArrayList<Solucao> getListaAlgoritmos() {
		return listaAlgoritmos;
	}
	
	public Solucao getAlgoritmo(String nome) {
		
		for (Solucao s : listaAlgoritmos) {
			if (nome == s.getNomeSolucao())
				return s;
		}
		
		return null;
	}
	
	private void criarPoolAlgoritmos() {
		
		listaAlgoritmos.add(new BuscaBinaria());
		listaAlgoritmos.add(new BuscaEmLargura());
		listaAlgoritmos.add(new BuscaEmProfundidade());
		listaAlgoritmos.add(new BuscaSequencial());
		listaAlgoritmos.add(new BuscaTernaria());
		
		listaAlgoritmos.add(new BubbleSort());
		listaAlgoritmos.add(new InsertionSort());
		listaAlgoritmos.add(new MergeSort());
		listaAlgoritmos.add(new QuickSort());
		listaAlgoritmos.add(new SelectionSort());
	}
}
