package br.com.algoritmos.executavel;

import java.util.ArrayList;
import java.util.Random;

import br.com.algoritmos.solucao.BuscaBinaria;
import br.com.algoritmos.solucao.InsertionSort;

public class Main {

	public static void main(String[] args) {
		Random random = new Random();
		InsertionSort<Integer> insertionSort = new InsertionSort<Integer>("Insertion Sort");		
		BuscaBinaria<Integer> buscaBinaria = new BuscaBinaria<Integer>();		

		while(true){
			ArrayList<Integer> lista = new ArrayList<Integer>();
			for (long  index = 0; index < 5000; index++) {
				lista.add(random.nextInt(50000));
			}
			System.out.println("Entrada de dados: " + lista.size());
			insertionSort.getMediaGeral();
			insertionSort.isOcupado();			
			
			ArrayList<Integer> listaOrdenada = insertionSort.ordernarLista(lista);
			System.out.println("listaOrdenada"+listaOrdenada.toString());
			
			buscaBinaria.getMediaGeral();
			buscaBinaria.isOcupado();
			
			int valor = 10;
			int posicao = buscaBinaria.buscarElemento(lista, valor);	
			if(posicao == -1){
				System.out.println("Elemento não encontrado.");
			}else{
				System.out.println("Elemento encontrado na posição: "+posicao);
			}
		}
	}

}
