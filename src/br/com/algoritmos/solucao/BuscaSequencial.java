package br.com.algoritmos.solucao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/*
 * @author Luis Carlos Gomes e Rafael Viana
 * 
 */





import br.com.algoritmos.busca.IBuscavel;
import br.com.algoritmos.requisicao.Requisicao;

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
