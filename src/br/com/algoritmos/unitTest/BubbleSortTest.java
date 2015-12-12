package br.com.algoritmos.unitTest;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import br.com.algoritmos.ordenacao.BubbleSort;

public class BubbleSortTest {
	@Test
	public void test() {
		Random random = new Random();
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (long  index = 0; index < 5000; index++) {
			lista.add(random.nextInt(50000));
		}
		
		BubbleSort<Integer> bubbleSort = new BubbleSort<Integer>("Bubble sort");
		bubbleSort.ordernarLista(lista);
		
		System.out.println("Lista ordenada: " + lista.toString());
	}
}
