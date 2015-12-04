package br.com.algoritmos.busca;

import java.util.ArrayList;

public interface IBuscavel<T extends Number> {
	
	public int buscarElemento(ArrayList<T> lista, T valor);
}
