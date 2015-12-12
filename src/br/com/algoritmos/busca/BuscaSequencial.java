package br.com.algoritmos.busca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.algoritmos.cliente.requisicao.Requisicao;
import br.com.algoritmos.solucao.Solucao;

public class BuscaSequencial<T> extends Solucao implements IBuscavel<T> {
	public BuscaSequencial() {
		super("Busca sequêncial");
	}

	@Override
	public void run() {
		while (true) {
			Requisicao<T> requisicao = receberRequisicao();
			Collection<T> lista = requisicao.getListaValores();
			Comparable<T> valor = (Comparable<T>) requisicao.getValor();
			
			requisicao.setPosicao((Integer) buscarElemento(lista, valor));			
			
		}
		
	}	
	
	private Integer buscaSequencial(ArrayList<Comparable> lista, Comparable valor) {
		for (Comparable item : lista) {
			if (item.compareTo(valor) == 0) {
				return lista.indexOf(item);
			}
		}
		
		return null;
	}

	@Override
	public <T> T buscarElemento(Collection<T> colecao, Comparable valor) {
		ArrayList<Comparable> lista = (ArrayList<Comparable>) colecao;
		
		Date dtInicio = new Date();
		setOcupado(true);
		T posicao = (T) buscaSequencial(lista, valor);
		setOcupado(false);
		Date dtFinal = new Date();
		adicionarTempoDuracao(dtInicio, dtFinal);

		return posicao;
	}
}
