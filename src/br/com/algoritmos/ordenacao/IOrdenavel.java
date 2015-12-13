package br.com.algoritmos.ordenacao;

import java.util.ArrayList;
/**
 * Interface que representa uma ordenação
 * a partir de um array de elementos.
 * 
 * Interface<code>IOrdenavel</code>
 * 
 * @author Tiago
 * @author Gabriel
 * @version 1.0 (12/12/2015)
 *
 */
public interface IOrdenavel<T> {

	/**
	 * Realiza uma ordenação a partir de um array
	 * 
	 * @param lista
	 * 			lista
	 */
	public void ordernarLista(ArrayList<T> lista);
}
