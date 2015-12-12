package br.com.algoritmos.unitTest;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import br.com.algoritmos.requisicao.Requisicao;
import br.com.algoritmos.requisicao.TipoRequisicao;
import br.com.algoritmos.solucao.QuickSort;

public class QuickSortTest {

	@Test
	public void test() {

		QuickSort<Integer> solution = new QuickSort<Integer>();

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


		solution.ordernarLista((ArrayList<Integer>) requisicao.getListaValores());

		ArrayList<Integer> lista = (ArrayList<Integer>) requisicao.getListaValores();

		for(int i = 0; i < (requisicao.getListaValores().size() - 1); i++){
			if(lista.get(i) > lista.get(i + 1)){
				fail("Lista nao foi ordenada");
			}
		}

	}

}
