package br.com.algoritmos.busca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.algoritmos.ordenacao.QuickSort;
import br.com.algoritmos.solucao.Solucao;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Esta Classe representa o algoritmo Busca Bin�ria.
 * Ela parte do pressuposto de que o vetor est� ordenado
 * e realiza sucessivas divis�es do espa�o de busca 
 * comparando o elemento buscado com o elemento no meio do vetor.
 * 
 * Classe <code>BuscaBinaria</code>
 * 
 * @author Thaynara Santos
 * @author Renan Oliveira
 * @author Ramon
 * @author Vinicius
 * @author Bruno
 * @author Luis
 * 
 * @version 1.0(12/12/2015)
 *
 */
public class BuscaBinaria<T extends Comparable<T>> extends Solucao implements IBuscavel<T> {

	/**
	 * Instancia uma nova Busca Bin�ria
	 */
	public BuscaBinaria() {
		super("Busca Bin�ria", TipoRequisicao.BUSCA, 50006);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.algoritmos.busca.IBuscavel#buscarElemento(java.util.Collection, java.lang.Comparable)
	 */
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

	/**
	 * Implementa a busca bin�ria
	 * 
	 * @param lista
	 * 			lista
	 * @param valor
	 * 			valor
	 * 
	 * @return resultado
	 */
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
