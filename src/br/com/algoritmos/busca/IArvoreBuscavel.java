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
	 * Realiza a busca de um n� a apartir
	 * de uma �rvore.
	 * 
	 * @param colecao
	 * 				arvore colecao
	 * @param valor
	 * 				n� valor
	 * @return resultado
	 */
	public <T> T buscarElemento(Object colecao, Comparable valor);

}
