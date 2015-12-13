package br.com.unitTest;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import br.com.algoritmos.busca.BuscaTernaria;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Classe que representa o tesste unitario da clase BuscaTernaria
 * 
 * Classe<code>BuscaTernariaTest</code>
 * 
 * @author Yasmin Farias
 * 
 * @version 1.0 (12/12/2015)
 */
public class BuscaTernariaTest {
	
	/**
	 * método que testa a busca ternaria
	 */
	@Test
	public void test() {
		BuscaTernaria<Integer> buscaTernaria = new BuscaTernaria<Integer>();

		Requisicao<Integer> requisicao = new Requisicao<Integer>(TipoRequisicao.BUSCA, new ArrayList<Integer>(), 7);

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

		Integer numero = buscaTernaria.buscarElemento(requisicao.getListaValores(), requisicao.getValor());

		if(numero == null){
			fail("Nao encontrado.");
		} else {
			System.out.println(numero);
		}

	}
}

