package br.com.algoritmos.ordenacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.algoritmos.cliente.requisicao.Requisicao;
import br.com.algoritmos.solucao.Solucao;
/**
 * Classe que implementa o Algoritmo de Ordenação Merge.
 * 
 * Sua ideia básica consiste em Dividir(o problema em vários 
 * sub-problemas e resolve-los através da recursividade) e 
 * Conquistar(após todos os sub-problemas terem sido resolvidos 
 * ocorre a conquista que é a união das resoluções dos sub-problemas).
 * 
 * Classe<code>MergeSort</code>
 * 
 * @author Ramon
 * @author Nyelson
 * @author Yasmin Farias
 * @version 1.0 (12/12/2015)
 */
public class MergeSort<T extends Comparable<T>> extends Solucao implements IOrdenavel<T> {

	/**
	 * Instancia um novo Merge Sort
	 * 
	 */
	public MergeSort() {
		super("Merge Sort");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.algoritmos.ordenacao.IOrdenavel#ordernarLista(java.util.ArrayList)
	 */
	@Override
	public void ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();
		setOcupado(true);
		lista = mergeSort(lista);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);
	}

	/**
	 * função que faz a ordenação Merge.
	 * 
	 * @param lista
	 * 			lista
	 * @return resultado
	 */
	public ArrayList<T> mergeSort(List<T> lista) {

		if (lista.size() <= 1)
			return new ArrayList<T>(lista);

		int middle = lista.size() / 2;
		List<T> noEsquerdo = lista.subList(0, middle);
		List<T> noDireito = lista.subList(middle, lista.size());

		noDireito = mergeSort(noDireito);
		noEsquerdo = mergeSort(noEsquerdo);
		ArrayList<T> result = mergeSort(noEsquerdo, noDireito);

		return result;
	}

	/**
	 * Função que faz a ordenação Merge
	 * 
	 * @param noEsquerdo
	 * 			no esquerdo
	 * @param noDireito
	 * 			no direito
	 * 
	 * @return resultado
	 */
	public ArrayList<T> mergeSort(List<T> noEsquerdo, List<T> noDireito) {
		
		ArrayList<T> result = new ArrayList<T>();
		Iterator<T> it1 = noEsquerdo.iterator();
		Iterator<T> it2 = noDireito.iterator();

		T x = it1.next();
		T y = it2.next();
		while (true) {
			// muda a direção da comparação da ordenação
			if (x.compareTo(y) <= 0) {
				result.add(x);
				if (it1.hasNext()) {
					x = it1.next();
				} else {
					result.add(y);
					while (it2.hasNext()) {
						result.add(it2.next());
					}
					break;
				}
			} else {
				result.add(y);
				if (it2.hasNext()) {
					y = it2.next();
				} else {
					result.add(x);
					while (it1.hasNext()) {
						result.add(it1.next());
					}
					break;
				}
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Requisicao<T> requisicao = receberRequisicao();
		ordernarLista((ArrayList<T>) requisicao.getListaValores());
		enviarRequisicao(requisicao);
	}

}
