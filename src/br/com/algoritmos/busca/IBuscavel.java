package br.com.algoritmos.busca;

import java.util.Collection;

/**
 * @author Tiago
 *
 */
public interface IBuscavel<T> {

	public <T> T buscarElemento(Collection<T> colecao, Comparable valor);
}
