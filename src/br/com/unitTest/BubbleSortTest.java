package br.com.unitTest;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import br.com.algoritmos.ordenacao.BubbleSort;
import br.com.algoritmos.ordenacao.InsertionSort;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Classe que representa o teste unitario da clase BubbleSort
 * 
 * Classe<code>BubbleSortTest</code>
 * 
 * @author Luis Gomes 
 * @author Rafael Viana
 * @author Vitor gomes
 * @author Yasmin Farias
 * 
 * @version 1.0 (12/12/2015)
 */
public class BubbleSortTest {
	/**
	 * método que testa a ordenação da classe BubbleSort
	 */
	@Test
	public void test() {
		
		BubbleSort<Integer> bubbleSort = new BubbleSort<Integer>();
		
		Requisicao<Integer> requisicao = new Requisicao<>(TipoRequisicao.ORDENACAO, new ArrayList<Integer>());
		
		
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
		
		
		bubbleSort.ordernarLista((ArrayList<Integer>) requisicao.getListaValores());
		
		ArrayList<Integer> lista = (ArrayList<Integer>) requisicao.getListaValores();
		
		for(int i = 0; i < (requisicao.getListaValores().size() - 1); i++){
			if(lista.get(i) > lista.get(i + 1)){
				fail("Lista nao foi ordenada");
			}
		}
	}
}
