package br.com.algoritmos.solucao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.algoritmos.ordenacao.IOrdenavel;

public class MergeSort<T extends Comparable<T>> extends Solucao implements IOrdenavel<T> {

	public MergeSort(String _nomeSolucao) {
		super(_nomeSolucao);
	}

	public void ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();
		setOcupado(true);
		lista = mergeSort(lista);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);
	}

	public static <T extends Comparable<T>> ArrayList<T> mergeSort(List<T> m) {
		
		if (m.size() <= 1)
			return new ArrayList<T>(m);

		int middle = m.size() / 2;
		List<T> left = m.subList(0, middle);
		List<T> right = m.subList(middle, m.size());

		right = mergeSort(right);
		left = mergeSort(left);
		List<T> result = merge(left, right);
		
		return new ArrayList<T>(result);
	}

	public static <T extends Comparable<T>> ArrayList<T> merge(List<T> left, List<T> right) {
		ArrayList<T> result = new ArrayList<T>();
		Iterator<T> it1 = left.iterator();
		Iterator<T> it2 = right.iterator();

		T x = it1.next();
		T y = it2.next();
		while (true) {
			// change the direction of this comparison to change the direction
			// of the sort
			if (x.compareTo(y) <= 0) {
				result.add(x);
				if (it1.hasNext()) {
					x = it1.next();
				} else {
					result.add(y);
					while (it2.hasNext()) {
						result.add(it2.next());
					}
					break;
				}
			} else {
				result.add(y);
				if (it2.hasNext()) {
					y = it2.next();
				} else {
					result.add(x);
					while (it1.hasNext()) {
						result.add(it1.next());
					}
					break;
				}
			}
		}
		return result;
	}

}
