package br.com.algoritmos.busca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.algoritmos.solucao.Solucao;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * 
 * Esta Classe representa o algoritmo Busca Sequencial,
 * Tipo de pesquisa em vetores ou listas de modo 
 * sequencial, elemento por elemento,
 * 
 * Classe<code>BuscaSequencial</code>
 * 
 * @author Luis Gomes
 * @author Vitor Gomes
 * @author Rafael Viana
 * @version 1.0 (12/12/2015)
 *
 */
public class BuscaSequencial<T extends Comparable<T>> extends Solucao implements IBuscavel<T> {
	
	/**
	 * Instancia uma nova busca sequencial.
	 */
	public BuscaSequencial() {
		super("Busca sequêncial", TipoRequisicao.BUSCA, 50003);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			Requisicao<T> requisicao = receberRequisicao();
			Collection<T> lista = requisicao.getListaValores();
			Comparable<T> valor = (Comparable<T>) requisicao.getValor();
			
			requisicao.setPosicao((Integer) buscarElemento(lista, valor));			
			
		}
		
	}	
	
	/**
	 * Implementa a busca sequencial a partir de uma lista.
	 * 
	 * @param lista
	 * 			lista
	 * @param valor
	 * 			valor
	 * @return resultado
	 */
	private Integer buscaSequencial(ArrayList<Comparable> lista, Comparable valor) {
		for (Comparable item : lista) {
			if (item.compareTo(valor) == 0) {
				return lista.indexOf(item);
			}
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.algoritmos.busca.IBuscavel#buscarElemento(java.util.Collection, java.lang.Comparable)
	 */
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
