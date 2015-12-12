package br.com.algoritmos.solucao;

import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.busca.IBuscavel;
import br.com.algoritmos.requisicao.Requisicao;

public class BuscaBinaria<T extends Comparable<T>> extends Solucao implements IBuscavel<T> {

	public BuscaBinaria() {
		super("Busca Binária");
	}

	@Override
	public <T> T buscarElemento(T colecao, Comparable valor) {
		
		ArrayList<Comparable> lista = (ArrayList<Comparable>) colecao;
		
		Date dataInicial = new Date();
		setOcupado(true);
		T posicao = (T) buscaBinaria(lista, valor);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);

		return posicao;
	}

	private Integer buscaBinaria(ArrayList<Comparable> lista, Comparable valor){
		int inicio = 0;
		int fim = lista.size()-1;

		while(inicio <= fim){
			int meio = (inicio+fim)/2;
			Comparable valorMeio = lista.get(meio);

			if(valor.compareTo(valorMeio) == 0){
				return meio;
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
			buscarElemento((ArrayList<T>) requisicao.getListaValores(), requisicao.getValor());
			enviarRequisicao(requisicao);
		}
	}
}
