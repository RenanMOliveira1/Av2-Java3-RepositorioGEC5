package br.com.algoritmos.busca;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import br.com.algoritmos.solucao.Arvore;
import br.com.algoritmos.solucao.No;
import br.com.algoritmos.solucao.Solucao;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Esta classe representa o algoritmo de busca Breadth First Search.
 * 
 * É um algoritmo de busca em grafos utilizado para realizar uma busca 
 * ou travessia num grafo e estrutura de dados do tipo árvore. 
 * Intuitivamente, você começa pelo vértice raiz e explora todos os 
 * vértices vizinhos. Então, para cada um desses vértices mais próximos,
 *  exploramos os seus vértices vizinhos inexplorados e assim por diante
 *  , até que ele encontre o alvo da busca.
 * 
 * Classe <code>BuscaEmLargura</code>
 * 
 * @author Thaynara
 * @author Bruno
 * @author Gabriel
 *
 * @version 1.0 (13/12/2015)
 * @param <T>
 */
public class BuscaEmLargura<T extends Comparable<T>> extends Solucao implements Runnable, IArvoreBuscavel<T>{//tem que colocar extends Comparable<T> sem isso da erro
	
	//private DatagramSocket socket;
	//private DatagramPacket sendPacket;

	/**
	 * Instancia uma nova busca em Largura
	 */
	public BuscaEmLargura() {

		super("Busca em largura", TipoRequisicao.BUSCA_ARVORE, 50007);
		setMediaGeral(6.0);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.algoritmos.solucao.Solucao#run()
	 */
	@Override
	public void run() {
		while (true) {
			Requisicao requisicao = receberRequisicao();//ja faz todo processo do recebimento
			
			requisicao.setNo(buscarElemento(requisicao.getArvore(), (Comparable) requisicao.getValor()));
			enviarRequisicao(requisicao);          
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.algoritmos.busca.IArvoreBuscavel#buscarElemento(br.com.algoritmos.solucao.Arvore, java.lang.Comparable)
	 */
	@Override
	public <T> No buscarElemento(Arvore colecao, Comparable valor) {
		
		Date dataInicial = new Date();
		
		setOcupado(true);
		
		No resultado = buscaEmLargura(colecao, valor);
		
		setOcupado(false);
		
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);
		
		return resultado;
	}
	
	/**
	 * Implementa o algoritmo de busca em largura.
	 * 
	 * @param colecao
	 * 			coleção
	 * @param valor
	 * 			valor
	 * @return resultado
	 */
	private <T> T buscaEmLargura(Arvore colecao, Comparable valor){
		Arvore arvore = colecao;
		
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
