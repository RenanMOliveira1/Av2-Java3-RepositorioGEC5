package br.com.algoritmos.solucao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.ordenacao.IOrdenavel;

public class InsertionSort<T extends Comparable<T>> extends Solucao implements IOrdenavel<T>, Runnable{

	public InsertionSort(String _nomeSolucao) {
		super(_nomeSolucao, 5000);
	}

	@Override
	public ArrayList<T> ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();
		setOcupado(true);
		ArrayList<T> listaOrdenada = insertionSort(lista);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);

		return listaOrdenada;
	}

	public ArrayList<T> insertionSort(ArrayList<T> lista){
		int tamanhoLista = lista.size();

		for(int index = 1; index < tamanhoLista; index++){
			T valor = lista.get(index);
			int indexAux;

			for(indexAux = index - 1; indexAux >= 0 && valor.compareTo(lista.get(indexAux)) < 0; indexAux--){
				lista.remove(indexAux+1);
				lista.add(indexAux+1, lista.get(indexAux));
			}
			lista.remove(indexAux+1);
			lista.add(indexAux+1, valor);
		}

		return lista;
	}

	public void run() {
		
		DatagramPacket receive = waitForPackets();
		
		ArrayList<T> listaDesordenada;
		try {
			listaDesordenada = deserialize(receive.getData());
			ArrayList<T> ordenado = ordernarLista(listaDesordenada);
			serializar(ordenado);		
			sendPacketToClient(receive);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
