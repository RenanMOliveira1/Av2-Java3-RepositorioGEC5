package br.com.unitTest;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import br.com.algoritmos.busca.BuscaBinaria;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Classe que representa o tesste unitario da clase BuscaBinaria
 * 
 * Classe<code>BuscaBinariaTest</code>
 * 
 * @author Thaynara Santos
 * @author Renan Oliveira
 * @author Yasmin Farias
 * 
 * @version 1.0 (12/12/2015)
 */
public class BuscaBinariaTest {

	/**
	 * metodo que testa a busca binaria
	 */
	@Test
	public void test() {
		BuscaBinaria<Integer> buscaBinaria = new BuscaBinaria<Integer>();

		Requisicao<Integer> requisicao = new Requisicao<>(TipoRequisicao.BUSCA, new ArrayList<Integer>(), 7);

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

		Integer numero = buscaBinaria.buscarElemento(requisicao.getListaValores(), requisicao.getValor());

		if(numero == null){
			fail("Nao encontrado.");
		}else{
			System.out.println(numero);
		}

	}
}
