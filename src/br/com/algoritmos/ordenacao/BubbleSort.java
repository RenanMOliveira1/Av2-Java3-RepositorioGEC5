package br.com.algoritmos.ordenacao;

import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.solucao.Solucao;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;

/**
 * Classe que implementa o Algoritmo de Ordenação Bubble.
 * 
 * A ideia é percorrer o vector diversas vezes, a cada 
 * passagem fazendo flutuar para o topo o maior elemento da sequência. 
 * 
 * Classe<code>BubbleSort</code>
 * 
 * @author Luis Gomes 
 * @author Rafael Viana
 * @author Vitor gomes
 * @author Yasmin Farias
 * @version 1.0 (12/12/2015)
 */
public class BubbleSort<T extends Number> extends Solucao implements IOrdenavel<T> {
	
	/**
	 * Instancia um novo Bubble sort
	 */
	public BubbleSort() {
		super("Bubble sort", TipoRequisicao.ORDENACAO, 50001);
		setMediaGeral(4.0);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.algoritmos.ordenacao.IOrdenavel#ordernarLista(java.util.ArrayList)
	 */
	@Override
	public void ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();
		setOcupado(true);
		bubbleSort(lista);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);
	}

	/**
	 * função que faz a ordenação bubble
	 * 
	 * @param lista
	 * 			lista
	 * @return resultado
	 */
	private ArrayList<T> bubbleSort(ArrayList<T> lista) {
		int tamanhoLista = lista.size();
		
		for (int contador = 0; contador < tamanhoLista; contador++) {
			for (int contadorAuxiliar = 0; contadorAuxiliar < tamanhoLista - contador - 1; contadorAuxiliar++) {								
				if (lista.get(contadorAuxiliar).intValue() > lista.get(contadorAuxiliar + 1).intValue()) {
					T temp = lista.get(contadorAuxiliar);
					lista.set(contadorAuxiliar, lista.get(contadorAuxiliar + 1));
					lista.set(contadorAuxiliar + 1, temp);
				}
			}
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			Requisicao<T> requisicao = receberRequisicao();
			ordernarLista((ArrayList<T>) requisicao.getListaValores());
			enviarRequisicao(requisicao);
		}		
	}
}