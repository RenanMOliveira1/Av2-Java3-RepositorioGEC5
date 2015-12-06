package br.com.algoritmos.solucao;

import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.ordenacao.IOrdenavel;

public class QuickSort<T extends Comparable<T>> extends Solucao implements IOrdenavel<T> {

	protected QuickSort(String _nomeSolucao) {
		super(_nomeSolucao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<T> ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();
		setOcupado(true);
		ArrayList<T> listaOrdenada = quickSort(lista);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);

		return listaOrdenada;
	}

	private ArrayList<T> quickSort(ArrayList<T> lista) {
	    if (lista.size() <= 1) {
	        return lista;
	      }


		return null;
	}

}
