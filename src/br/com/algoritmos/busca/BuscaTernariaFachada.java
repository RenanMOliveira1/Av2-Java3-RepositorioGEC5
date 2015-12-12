package br.com.algoritmos.busca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.algoritmos.cliente.requisicao.Requisicao;
import br.com.algoritmos.ordenacao.QuickSort;
import br.com.algoritmos.solucao.Solucao;

/**
 ** Programa que chama o Algoritmo de Busca Ternaria
 *	Classe <code>BuscaTernariaFachada</code>
 *
 *  @author Yasmin Farias
 *  @author Tiago Henrique
 *  @version 1.0 (12/12/2015)
 **/
public class BuscaTernariaFachada<T extends Comparable<T>> extends Solucao implements IBuscavel<T> {
	
	public BuscaTernariaFachada() {
		super("Busca Ternária");
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

	@Override
	public <T> T buscarElemento(Collection<T> colecao, Comparable valor) {
		ArrayList<T> lista = (ArrayList<T>) colecao;
		
		QuickSort<T> ordenador = new QuickSort<T>();
		
		ordenador.ordernarLista(lista); 
		
		Date dataInicio = new Date();
		setOcupado(true);
		T resultado = BuscaTernaria.busca(lista, valor);
		setOcupado(false);
		Date dataFim = new Date();
		
		adicionarTempoDuracao(dataInicio, dataFim);
		
		return resultado;
	}
	
}
