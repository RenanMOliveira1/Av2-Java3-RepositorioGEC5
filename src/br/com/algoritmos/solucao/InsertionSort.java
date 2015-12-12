package br.com.algoritmos.solucao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.cliente.requisicao.Requisicao;
import br.com.algoritmos.ordenacao.IOrdenavel;

public class InsertionSort<T> extends Solucao implements IOrdenavel<T>, Runnable{

	public InsertionSort() {
		super("Insertion Sort", 5000);
	}

	@Override
	public void ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();
		setOcupado(true);
		lista = insertionSort(lista);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);

	}

	public ArrayList<T> insertionSort(ArrayList<T> lista){
		int tamanhoLista = lista.size();

		for(int index = 1; index < tamanhoLista; index++){
			Comparable valor = (Comparable) lista.get(index);
			int indexAux;

			for(indexAux = index - 1; indexAux >= 0 && valor.compareTo(lista.get(indexAux)) < 0; indexAux--){
				lista.remove(indexAux+1);
				lista.add(indexAux+1, lista.get(indexAux));
			}
			lista.remove(indexAux+1);
			lista.add(indexAux+1, (T)valor);
		}

		return lista;
	}

	public void run() {
		while(true) {
			Requisicao<T> requisicao = receberRequisicao();
			ordernarLista((ArrayList<T>) requisicao.getListaValores());
			enviarRequisicao(requisicao);
		}
	}

}
