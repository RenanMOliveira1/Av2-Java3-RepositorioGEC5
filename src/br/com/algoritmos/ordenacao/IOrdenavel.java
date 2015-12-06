package br.com.algoritmos.ordenacao;

import java.util.ArrayList;

public interface IOrdenavel<T extends Comparable<T>> {

	public ArrayList<T> ordernarLista(ArrayList<T> lista);
}
