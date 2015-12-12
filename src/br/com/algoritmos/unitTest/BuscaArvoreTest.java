package br.com.algoritmos.unitTest;

import br.com.algoritmos.busca.BuscaEmLargura;
import br.com.algoritmos.busca.BuscaEmProfundidade;
import br.com.algoritmos.solucao.Arvore;
import br.com.algoritmos.solucao.No;

public class BuscaArvoreTest {
	
	public static void main(String[] args) {
		
		Arvore arvore = new Arvore(5);
		
		arvore.inserir(2);
		arvore.inserir(6);
		arvore.inserir(3);
		arvore.inserir(7);
		
		BuscaEmLargura buscador = new BuscaEmLargura();
		//BuscaEmProfundidade buscador = new BuscaEmProfundidade();
		
		No no = (No)buscador.buscarElemento(arvore, 7);
		
		System.out.println(no.getValor());
		
	
		
	}

}
