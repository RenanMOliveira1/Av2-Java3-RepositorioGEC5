package br.com.algoritmos.solucao;

import java.util.ArrayList;
import java.util.Date;
import br.com.algoritmos.ordenacao.IOrdenavel;

public class QuickSort<T extends Comparable<T>> extends Solucao implements IOrdenavel<T> {

	public QuickSort(String _nomeSolucao) {
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

		int middle = (int) Math.ceil((double)lista.size() / 2);
		T pivot = lista.get(middle);

		ArrayList<T> menores = new ArrayList<T>();
		ArrayList<T> maiores = new ArrayList<T>();

		for (T numero : lista) {
			if(numero.compareTo(pivot) <= 0){
				if(numero.compareTo(lista.get(middle)) == 0){
					continue;
				}
				menores.add(numero);
			}
			else{
				maiores.add(numero);
			}
		}

	    return concatenar(quickSort(menores), pivot, quickSort(maiores));
	}

	private ArrayList<T> concatenar(ArrayList<T> menores, T pivot, ArrayList<T> maiores){

		ArrayList<T> lista = new ArrayList<T>();

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
