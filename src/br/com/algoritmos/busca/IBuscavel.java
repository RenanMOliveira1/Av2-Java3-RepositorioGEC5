package br.com.algoritmos.busca;

/**
 * @author Tiago
 *
 */
public interface IBuscavel<T> {

	public <T> T buscarElemento(T colecao, Comparable valor);
}
