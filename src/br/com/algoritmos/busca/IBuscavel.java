package br.com.algoritmos.busca;

import java.util.ArrayList;

public interface IBuscavel<T extends Comparable<T>> {

	public <T> T buscarElemento(T colecao, Comparable valor);
}
