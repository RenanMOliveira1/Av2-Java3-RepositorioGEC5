package br.com.algoritmos.solucao;

import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.ordenacao.IOrdenavel;
import br.com.algoritmos.requisicao.Requisicao;

public class BubbleSort<T extends Number> extends Solucao implements IOrdenavel<T> {

	protected BubbleSort(String _nomeSolucao) {
		super(_nomeSolucao);
	}

	@Override
	public void ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();
		setOcupado(true);
		bubbleSort(lista);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);
	}

	private void bubbleSort(ArrayList<T> lista) {
		int tamanhoLista = lista.size();

		for (int contador = 0; contador < tamanhoLista; contador++) {
			for (int contadorAuxiliar = 1; contadorAuxiliar <= tamanhoLista; contadorAuxiliar++) {
				T currentValor = lista.get(contador);
				T proximoValor = lista.get(contadorAuxiliar);

				if (currentValor.intValue() < proximoValor.intValue()) {
					lista.add(contador, proximoValor);
					lista.add(contadorAuxiliar, currentValor);
				}
			}
		}

	}

	@Override
	public void run() {
		Requisicao<T> requisicao = receberRequisicao();
		ordernarLista((ArrayList<T>) requisicao.getListaValores());
		enviarRequisicao(requisicao);
		
	}

}
