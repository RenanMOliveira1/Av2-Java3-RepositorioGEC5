package br.com.algoritmos.busca;

import br.com.algoritmos.solucao.Arvore;
import br.com.algoritmos.solucao.No;

/**
 * Interface que representa uma busca
 * a partir de uma Arvore.
 * 
 * Interface <code>IArvoreBuscavel</code>
 * 
 * @author Bruno
 * @version 1.0(12/12/2015)
 *
 */
public interface IArvoreBuscavel <T> {
	
	/**
	 * Realiza a busca de um n� a apartir
	 * de uma �rvore.
	 * 
	 * @param colecao
	 * 				arvore colecao
	 * @param valor
	 * 				n� valor
	 * @return resultado
	 */
	public <T> No buscarElemento(Arvore colecao, Comparable valor);

}
