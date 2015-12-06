package br.com.algoritmos.busca;

import java.util.ArrayList;

public interface IBuscavel<T extends Comparable<T>> {

	public int buscarElemento(ArrayList<T> lista, T valor);
}
