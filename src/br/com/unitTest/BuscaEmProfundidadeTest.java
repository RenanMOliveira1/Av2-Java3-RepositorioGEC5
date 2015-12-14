package br.com.unitTest;

import org.junit.Test;

import br.com.algoritmos.busca.BuscaEmProfundidade;
import br.com.algoritmos.solucao.Arvore;
import br.com.algoritmos.solucao.No;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Classe que representa o tesste unitario da clase BuscaEmProfundidade
 * 
 * Classe<code>BuscaEmProfundidadeTest</code>
 * 
 * @author Yasmin Farias
 * 
 * @version 1.0 (13/12/2015)
 */
public class BuscaEmProfundidadeTest {
	
	/**
	 * método que testa a busca em profundidade
	 */
	@Test
	public void test() {
		
		Arvore arvore = new Arvore(5);
		
		arvore.inserir(2);
		arvore.inserir(6);
		arvore.inserir(3);
		arvore.inserir(7);
		
		Requisicao<Integer> requisicao = new Requisicao<Integer>(TipoRequisicao.BUSCA_ARVORE, arvore, 8);
		
		BuscaEmProfundidade<No> buscador = new BuscaEmProfundidade<No>();
		
		No no = (No)buscador.buscarElemento(requisicao.getArvore(), requisicao.getValor());
		
	}

}