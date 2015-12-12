package br.com.algoritmos.busca;

import java.util.ArrayList;
/**
 * @author Tiago
 *
 */
public interface IBuscavel<T extends Comparable<T>> {

	public <T> T buscarElemento(T colecao, Comparable valor);
}
