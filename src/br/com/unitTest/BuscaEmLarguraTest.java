package br.com.unitTest;

import java.util.ArrayList;

import org.junit.Test;

import br.com.algoritmos.busca.BuscaEmLargura;
import br.com.algoritmos.solucao.Arvore;
import br.com.algoritmos.solucao.No;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Classe que representa o tesste unitario da clase BuscaEmLargura
 * 
 * Classe<code>BuscaEmLarguraTest</code>
 * 
 * @author Bruno
 * @author Gabriel
 * @author Yasmin Farias
 * 
 * @version 1.0 (12/12/2015)
 */
public class BuscaEmLarguraTest {
	
	/**
	 * método que testa a busca em largura
	 */
	@Test
	public void test() {
		
		Arvore arvore = new Arvore(5);
		
		arvore.inserir(2);
		arvore.inserir(6);
		arvore.inserir(3);
		arvore.inserir(7);
		
		Requisicao<Integer> requisicao = new Requisicao<Integer>(TipoRequisicao.BUSCA_ARVORE, arvore, 8);
		
		BuscaEmLargura buscador = new BuscaEmLargura();
		//BuscaEmProfundidade buscador = new BuscaEmProfundidade();
		
		No no = (No)buscador.buscarElemento(requisicao.getArvore(), requisicao.getValor());
		
		System.out.println(no.getValor());
	}

}
