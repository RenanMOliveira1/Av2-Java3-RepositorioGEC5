package br.com.executavel;

import java.util.ArrayList;

import br.com.cliente.Cliente;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Esta classe inicializa um cliente com uma requisição de busca
 * comum (por ArrayList), adiciona valores desordenados a essa lista
 * e cria uma Thread com a requisição de busca do cliente.
 * 
 * Classe <code>MainCliente</code>
 * 
 * @author Tiago Henrique
 * @version 1.0 (13/12/2015)
 *
 */
public class MainCliente {
	public static void main(String[] args) {
		Requisicao<Integer> requisicao = new Requisicao<>(TipoRequisicao.BUSCA, new ArrayList<Integer>(), 8);

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
		
		Thread t = new Thread(new Cliente(requisicao));
		t.start();
	}
}
