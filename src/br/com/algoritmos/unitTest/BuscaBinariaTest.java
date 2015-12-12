package br.com.algoritmos.unitTest;

import java.util.ArrayList;

import org.junit.Test;

import br.com.algoritmos.requisicao.Requisicao;
import br.com.algoritmos.requisicao.TipoRequisicao;
import br.com.algoritmos.solucao.BuscaBinaria;

public class BuscaBinariaTest {
	
	@Test
	public void test() {
		BuscaBinaria<Integer> buscaBinaria = new BuscaBinaria<Integer>();
		
		Requisicao<Integer> requisicao = new Requisicao<>(TipoRequisicao.BUSCA, new ArrayList<Integer>(), 9);
		
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
		
		System.out.println(buscaBinaria.buscarElemento(requisicao.getListaValores(), requisicao.getValor()));
	}
}