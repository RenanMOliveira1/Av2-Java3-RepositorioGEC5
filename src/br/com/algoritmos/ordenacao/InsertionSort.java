package br.com.algoritmos.ordenacao;

import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.solucao.Solucao;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Classe que implementa o Algoritmo de Ordenação Insertion.
 * 
 * Em termos gerais, ele percorre um vetor de elementos da 
 * esquerda para a direita e à medida que avança vai deixando 
 * os elementos mais à esquerda ordenados.
 * 
 * Classe<code>InsertionSort</code>
 * 
 * @author Thaynara Santos
 * @author Renan Oliveira
 * @version 1.0(12/12/2015)
 *
 */
public class InsertionSort<T> extends Solucao implements IOrdenavel<T>, Runnable{

	/**
	 * Instancia um novo Insertion Sort.
	 */
	public InsertionSort() {
		super("Insertion Sort", TipoRequisicao.ORDENACAO);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.algoritmos.ordenacao.IOrdenavel#ordernarLista(java.util.ArrayList)
	 */
	@Override
	public void ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();
		setOcupado(true);
		lista = insertionSort(lista);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);

	}

	/**
	 * função que faz a ordenação Insertion
	 * 
	 * @param lista
	 * 			lista
	 * @return resultado
	 */
	public ArrayList<T> insertionSort(ArrayList<T> lista){
		int tamanhoLista = lista.size();

		for(int index = 1; index < tamanhoLista; index++){
			Comparable valor = (Comparable) lista.get(index);
			int indexAux;

			for(indexAux = index - 1; indexAux >= 0 && valor.compareTo(lista.get(indexAux)) < 0; indexAux--){
				lista.remove(indexAux+1);
				lista.add(indexAux+1, lista.get(indexAux));
			}
			lista.remove(indexAux+1);
			lista.add(indexAux+1, (T)valor);
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
