package br.com.algoritmos.ordenacao;

import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.solucao.Solucao;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Classe que implementa o Algoritmo de Ordena��o Quick.
 * 
 * Ele adota a estrat�gia de divis�o e conquista. Consiste 
 * em rearranjar as chaves para que as chaves "menores" 
 * precedam as chaves "maiores". Em seguida o ele ordena as 
 * duas sublistas de chaves menores e maiores recursivamente 
 * at� que a toda a lista seja ordenada.
 * 
 * Classe<code>QuickSort</code>
 * 
 * @author Ramon
 * @author Nyelson
 * @author Yasmin Farias
 * @version 1.0 (12/12/2015)
 */
public class QuickSort<T> extends Solucao implements IOrdenavel<T> {

	/**
	 * Instancia uma nova ordena��o Quick.
	 */
	public QuickSort() {
		super("QuickSort", TipoRequisicao.ORDENACAO, 50009);
		setMediaGeral(8.0);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.algoritmos.ordenacao.IOrdenavel#ordernarLista(java.util.ArrayList)
	 */
	@Override
	public void ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();
		setOcupado(true);
		lista = quickSort(lista);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);

	}

	/**
	 * fun��o que faz a ordena��o Quick
	 * 
	 * @param lista
	 * 			lista
	 * @return resultado
	 */
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

	/**
	 * Concatena adiciona os valores menores, o pivot e os maiores,
	 * nesta ordem e o retorna.
	 * 
	 * @param lista
	 * 			lista
	 * @param menores
	 * 			valores menores que pivot
	 * @param pivot
	 * 			pivot
	 * @param maiores
	 * 			valores maiores que pivot
	 * @return lista
	 */
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true) {
			Requisicao requisicao = receberRequisicao();
			ordernarLista((ArrayList<T>) requisicao.getListaValores());
			enviarRequisicao(requisicao);
		}

	}

}
