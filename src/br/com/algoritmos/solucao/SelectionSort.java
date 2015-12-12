package br.com.algoritmos.solucao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.ordenacao.IOrdenavel;
import br.com.algoritmos.requisicao.Requisicao;
import br.com.algritmos.util.RedeUtil;

public class SelectionSort<T extends Comparable<T>> extends Solucao implements IOrdenavel<T> {

	public SelectionSort(String _nomeSolucao, int port) {
		super(_nomeSolucao, port);
	}

	@Override
	public void ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();		
		setOcupado(true);		
		lista = selectionSort(lista);			
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);
	}

	private ArrayList<T> selectionSort(ArrayList<T> lista) {
		for(int index = 0; index < lista.size() - 1; index++) {
			int menor = index;
			
			for(int indexAux = menor + 1; indexAux < lista.size(); indexAux++)
				//if(lista.get(indexAux).intValue() < lista.get(menor).intValue())
				if(lista.get(indexAux).compareTo(lista.get(menor)) < 0)
					menor = indexAux;
			if(menor != index) {
				T aux = lista.get(index);
				lista.set(index, lista.get(menor));
				lista.set(menor, aux);
			}
		}
		return lista;
	}

	@Override
	public void run() {
		while(true){
			DatagramPacket receivePacket = waitForPackets();
			Requisicao<T> obj;
			try {
				obj = (Requisicao<T>) RedeUtil.deserialize(receivePacket.getData());
				ordernarLista((ArrayList<T>) obj.getListaValores());
				sendPacketToClient(obj);
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
