package br.com.algoritmos.unitTest;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import br.com.algoritmos.busca.BuscaBinaria;
import br.com.algoritmos.busca.BuscaSequencial;
import br.com.algoritmos.cliente.requisicao.Requisicao;
import br.com.algoritmos.cliente.requisicao.TipoRequisicao;
import br.com.algoritmos.ordenacao.BubbleSort;
/**
 * Classe que representa o tesste unitario da clase BuscaSequencial
 * 
 * Classe<code>BuscaSequencialTest</code>
 * 
 * @author Luis Gomes
 * @author Vitor Gomes
 * @author Rafael Viana
 * @author Yasmin Farias
 * 
 * @version 1.0 (12/12/2015)
 */
public class BuscaSequencialTest {
	/**
	 * método que testa a busca sequencial
	 */
	@Test
	public void test() {
		BuscaBinaria<Integer> buscaBinaria = new BuscaBinaria<Integer>();
		
		Requisicao<Integer> requisicao = new Requisicao<>(TipoRequisicao.BUSCA, new ArrayList<Integer>(), 7);
		
		requisicao.getListaValores().add(10);
		requisicao.getListaValores().add(9);
		requisicao.getListaValores().add(8);
		requisicao.getListaValores().add(7);
		requisicao.getListaValores().add(6);
		requisicao.getListaValores().add(5);
		requisicao.getListaValores().add(4);
		requisicao.getListaValores().add(3);
		requisicao.getListaValores().add(2);
		requisicao.getListaValores().add(1);
		requisicao.getListaValores().add(0);
		
		Integer numero = buscaBinaria.buscarElemento(requisicao.getListaValores(), requisicao.getValor());
		
		if(numero == null){
			System.out.println("nao encontrado");
		} else{
			System.out.println(numero);
		}
	}
}
