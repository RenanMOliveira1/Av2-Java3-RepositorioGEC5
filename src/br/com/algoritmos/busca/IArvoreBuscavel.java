package br.com.algoritmos.busca;
/**
 * Interface que representa uma busca
 * a partir de uma Arvore.
 * 
 * Interface<code>IArvoreBuscavel</code>
 * 
 * @author Bruno
 * @version 1.0(12/12/2015)
 *
 */
public interface IArvoreBuscavel <T> {
	
	/**
	 * Realiza a busca de um nó a apartir
	 * de uma árvore.
	 * 
	 * @param colecao
	 * 				arvore colecao
	 * @param valor
	 * 				nó valor
	 * @return resultado
	 */
	public <T> T buscarElemento(T colecao, Comparable valor);

}
