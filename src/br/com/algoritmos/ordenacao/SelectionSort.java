package br.com.algoritmos.ordenacao;

import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.cliente.requisicao.Requisicao;
import br.com.algoritmos.cliente.requisicao.TipoRequisicao;
import br.com.algoritmos.solucao.Solucao;
/**
 * Classe que implementa o Algoritmo de Ordenação Selection.
 * 
 * Ele é baseado em se passar sempre o menor valor do vetor 
 * para a primeira posição (ou o maior dependendo da ordem 
 * requerida), depois o de segundo menor valor para a segunda 
 * posição, e assim é feito sucessivamente com os (n-1) 
 * elementos restantes, até os últimos dois elementos.
 * 
 * Classe<code>SelectionSort</code>
 * 
 * @author Tiago Bastos
 * @author Marcus Hildebrandt
 * @version 1.0 (12/12/2015)
 */
public class SelectionSort<T extends Comparable<T>> extends Solucao implements IOrdenavel<T> {

	/**
	 * Instancia um novo Selection sort.
	 * 
	 * @param _nomeSolucao
	 * 			nome solucao
	 * @param port
	 * 			port
	 */
	public SelectionSort() {
		super("Selection Sort", TipoRequisicao.ORDENACAO);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.algoritmos.ordenacao.IOrdenavel#ordernarLista(java.util.ArrayList)
	 */
	@Override
	public void ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();		
		setOcupado(true);		
		lista = selectionSort(lista);			
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);
	}

	/**
	 * função que faz a ordenação Selection
	 * 
	 * @param lista
	 * 			lista
	 * @return resultado
	 */
	private ArrayList<T> selectionSort(ArrayList<T> lista) {
		for(int index = 0; index < lista.size() - 1; index++) {
			int menor = index;
			
			for(int indexAux = menor + 1; indexAux < lista.size(); indexAux++)
				//if(lista.get(indexAux).intValue() < lista.get(menor).intValue())
				if(lista.get(indexAux).compareTo(lista.get(menor)) < 0)
					menor = indexAux;
			if(menor != index) {
				T aux = lista.get(index);
				lista.set(index, lista.get(menor));
				lista.set(menor, aux);
			}
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		while(true) {
			Requisicao<T> requisicao = receberRequisicao();
			ordernarLista((ArrayList<T>) requisicao.getListaValores());
			enviarRequisicao(requisicao);
		}
	}
}
