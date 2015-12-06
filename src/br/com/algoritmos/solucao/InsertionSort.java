package br.com.algoritmos.solucao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.ordenacao.IOrdenavel;

public class InsertionSort<T extends Number> extends Solucao implements IOrdenavel<T>, Runnable {
	
	ArrayList<T> lista;
	
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

			for(indexAux = index - 1; indexAux >= 0 && valor.intValue() < lista.get(indexAux).intValue(); indexAux--){
				lista.remove(indexAux+1);
				lista.add(indexAux+1, lista.get(indexAux));
			}
			lista.remove(indexAux+1);
			lista.add(indexAux+1, valor);
		}
		
		return lista;
	}

	@Override
	public void run() {
		DatagramPacket receive = waitForPackets();
		
		//codigo maluco de converter
		
		ArrayList<T> ordenado = ordernarLista(new ArrayList<T>());
		
		//converte para byte
		
		try {
			sendPacketToClient(receive);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
