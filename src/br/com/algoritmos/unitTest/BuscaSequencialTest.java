package br.com.algoritmos.unitTest;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import br.com.algoritmos.busca.BuscaSequencial;
import br.com.algoritmos.ordenacao.BubbleSort;

public class BuscaSequencialTest {
	@Test
	public void test() {
		Random random = new Random();
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (long  index = 0; index < 5000; index++) {
			lista.add(random.nextInt(50000));
		}
		
		BuscaSequencial<Integer> buscaSequencial = new BuscaSequencial<Integer>();
		Integer numero = buscaSequencial.buscarElemento(lista, 10);
		
		System.out.println("Lista: " + lista.toString());

		if(numero == null){
			System.out.println("nao encontrado");
		}else{
			System.out.println(numero);
		}
	}
}
