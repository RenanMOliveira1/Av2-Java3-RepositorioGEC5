package br.com.algoritmos.ordenacao;

import java.util.ArrayList;

public interface IOrdenavel<T extends Number> {
	
	public ArrayList<T> ordernarLista(ArrayList<T> lista);
}
