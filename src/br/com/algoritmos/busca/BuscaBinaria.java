
/**
 * @author Thaynara, , Renan, Ramon, Vinicius, Bruno, Luis
 *
 */

package br.com.algoritmos.busca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.algoritmos.cliente.requisicao.Requisicao;
import br.com.algoritmos.ordenacao.QuickSort;
import br.com.algoritmos.solucao.Solucao;

public class BuscaBinaria<T extends Comparable<T>> extends Solucao implements IBuscavel<T> {

	public BuscaBinaria() {
		super("Busca Binária");
	}

	@Override
	public <T> T buscarElemento(Collection<T> colecao, Comparable valor) {

		ArrayList<T> lista = (ArrayList<T>) colecao;

		QuickSort<T> ordenador = new QuickSort<T>();

		ordenador.ordernarLista(lista);

		Date dataInicial = new Date();
		setOcupado(true);
		T posicao = buscaBinaria(lista, valor);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);

		return posicao;
	}

	private <T> T buscaBinaria(ArrayList<T> lista, Comparable valor){
		int inicio = 0;
		int fim = lista.size()-1;

		while(inicio <= fim){
			Integer meio = (inicio+fim)/2;
			T valorMeio = lista.get(meio);

			if(valor.compareTo(valorMeio) == 0){
				return (T) meio;
			}

			if(valor.compareTo(valorMeio) > 0){
				inicio = meio+1;
			} else {
				fim = meio-1;
			}
		}

		return null;
	}

	@Override
	public void run() {
		while(true) {
			Requisicao<T> requisicao = receberRequisicao();
			buscarElemento(requisicao.getListaValores(), (T) requisicao.getValor());
			requisicao.setPosicao((Integer)buscarElemento(requisicao.getListaValores(), (T) requisicao.getValor()));
			enviarRequisicao(requisicao);
		}
	}
}
