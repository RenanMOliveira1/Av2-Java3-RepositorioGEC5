package br.com.algoritmos.busca;

import java.util.Collection;

/**
 * Interface que representa uma busca
 * a partir de um array de elementos.
 * 
 * Interface<code>IBuscavel</code>
 * 
 * @author Tiago
 * @author Ramon
 * @author Luis
 * @author Gabriel 
 *
 * @version 1.0 (12/12/2015)
 */
public interface IBuscavel<T> {

	/**
	 * Realiza a busca de um elemento a partir de um array
	 * 
	 * @param colecao
	 * 			array colecao
	 * @param valor
	 * 			valor
	 * @return resultado
	 */
	public <T> T buscarElemento(Collection<T> colecao, Comparable valor);
}
