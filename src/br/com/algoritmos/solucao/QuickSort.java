package br.com.algoritmos.solucao;

import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.ordenacao.IOrdenavel;

public class QuickSort<T> extends Solucao implements IOrdenavel<T> {

	public QuickSort() {
		super("QuickSort", 5000);
	}

	@Override
	public void ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();
		setOcupado(true);
		lista = quickSort(lista);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);

	}

	private ArrayList<T> quickSort(ArrayList<T> lista) {
	    if (lista.size() <= 1) {
	        return lista;
	      }

		int middle = (int) Math.ceil((double)lista.size() / 2);
		T pivot = lista.get(middle);

		ArrayList<T> menores = new ArrayList<T>();
		ArrayList<T> maiores = new ArrayList<T>();

		for (T numero : lista) {
			if(((Comparable) numero).compareTo(pivot) <= 0){
				if(((Comparable) numero).compareTo(lista.get(middle)) == 0){
					continue;
				}
				menores.add(numero);
			}
			else{
				maiores.add(numero);
			}
		}

	    return concatenar(lista, quickSort(menores), pivot, quickSort(maiores));
	}

	private ArrayList<T> concatenar(ArrayList<T>lista, ArrayList<T> menores, T pivot, ArrayList<T> maiores){

		lista.clear();

		for (T numero : menores) {
			lista.add(numero);
		}

		lista.add(pivot);

		for (T numero : maiores) {
			lista.add(numero);
		}

		return lista;
	}

}
