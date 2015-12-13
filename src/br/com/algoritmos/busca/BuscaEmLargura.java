/**
 * @author Thaynara,Bruno,Gabriel
 *
 */
package br.com.algoritmos.busca;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import br.com.algoritmos.cliente.requisicao.Requisicao;
import br.com.algoritmos.cliente.requisicao.TipoRequisicao;
import br.com.algoritmos.solucao.Arvore;
import br.com.algoritmos.solucao.No;
import br.com.algoritmos.solucao.Solucao;

public class BuscaEmLargura<T extends Comparable<T>> extends Solucao implements Runnable, IArvoreBuscavel<T>{//tem que colocar extends Comparable<T> sem isso da erro
	//private DatagramSocket socket;
	//private DatagramPacket sendPacket;


	public BuscaEmLargura() {

		super("Busca em largura", TipoRequisicao.BUSCA_ARVORE);
	}

	@Override
	public void run() {
		while (true) {
			Requisicao<T> requisicao = receberRequisicao();//ja faz todo processo do recebimento
//			
			//buscarElemento(requisicao.getListaValores(), requisicao.getValor());
			requisicao.setNo(buscarElemento(requisicao.getListaValores(), requisicao.getValor()));// adiciona o nó encontrado 
			requisicao.setListaValores(null);                                                     // para enviar ao cliente
			enviarRequisicao(requisicao);// ja faz todo processo de envio                         
//			
		}

	}


	/*private void ReceberDado() throws IOException, ClassNotFoundException {
		byte[] data = new byte[252];
		Requisicao<T> requisicao = receberRequisicao();
//		DatagramPacket receivePacket = new DatagramPacket(data, data.length);
//		socket.receive(receivePacket);
//		dado = (Dado) RedeUtil.deserialize(receivePacket.getData());//
		//this.buscarElemento(dado.getDado(), );

	}

	private void MandarDado() throws IOException {
		byte[] byteTopico = RedeUtil.serializar(dado);//
		sendPacket = new DatagramPacket(byteTopico, byteTopico.length,
				InetAddress.getLocalHost(), 5000);// new
													// DatagramPacket(byteTopico,
													// byteTopico.length,
													// dados.getIp(),
													// dados.getPorta());
		socket.send(sendPacket);
	}
     */
	@Override
	public <T> T buscarElemento(T colecao, Comparable valor) {
		
		Date dataInicial = new Date();
		
		setOcupado(true);
		
		T resultado = buscaEmLargura(colecao, valor);
		
		setOcupado(false);
		
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);
		
		return resultado;
	}
	
	private <T> T buscaEmLargura(T colecao, Comparable valor){
		Arvore arvore = (Arvore) colecao;
		
		Queue<No> visitados = new LinkedList<No>();

		if (arvore.getRaiz() == null)
			return null;

		if (valor.compareTo(arvore.getRaiz().getValor()) == 0)
			return (T) arvore.getRaiz();
		else {
			arvore.getRaiz().setVisitado(true);

			visitados.add(arvore.getRaiz());

			while (!visitados.isEmpty()) {
				No node = visitados.remove();

				if (node.getFilhoEsquerda() != null) {
					if (!node.getFilhoEsquerda().isVisitado()) {
						if (valor.compareTo(node.getFilhoEsquerda().getValor()) == 0) {
							arvore.zerar(arvore.getRaiz());
							return (T) node.getFilhoEsquerda();
						}
						visitados.add(node.getFilhoEsquerda());
						node.getFilhoEsquerda().setVisitado(true);
					}
				}

				if (node.getFilhoDireita() != null) {
					if (!node.getFilhoDireita().isVisitado()) {
						if (valor.compareTo(node.getFilhoDireita().getValor()) == 0) {
							arvore.zerar(arvore.getRaiz());
							return (T) node.getFilhoDireita();
						}
						visitados.add(node.getFilhoDireita());
						node.getFilhoDireita().setVisitado(true);
					}
				}

			}
		}
		
		return null;
	}
}
