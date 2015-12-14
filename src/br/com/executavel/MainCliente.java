package br.com.executavel;

import java.util.ArrayList;

import br.com.cliente.Cliente;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;

public class MainCliente {
	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		
		Requisicao<Integer> requisicao = new Requisicao<>(TipoRequisicao.ORDENACAO, new ArrayList<Integer>());


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
		
		cliente.enviarRequisicao(requisicao);
		
	}
}
