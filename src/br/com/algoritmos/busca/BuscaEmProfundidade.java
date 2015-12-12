package br.com.algoritmos.busca;

import java.util.Date;
import java.util.Stack;

import br.com.algoritmos.cliente.requisicao.Requisicao;
import br.com.algoritmos.solucao.Arvore;
import br.com.algoritmos.solucao.No;
import br.com.algoritmos.solucao.Solucao;
/**
 * 
 * @author Vinicius Viana (O cara da Samantha)
 *
 */
public class BuscaEmProfundidade<T extends Comparable<T>> extends Solucao implements Runnable, IArvoreBuscavel<T> {
	public BuscaEmProfundidade() {
		super("Busca em profundidade");
	}

	@Override
	public void run() {		
		while(true){
			Requisicao<T> requisicao = receberRequisicao();
			requisicao.setNo( buscarElemento(requisicao.getListaValores(), requisicao.getValor()) );
			requisicao.setListaValores(null);
			enviarRequisicao(requisicao);
		}	
	}

	@Override
	public <T> T buscarElemento(T colecao, Comparable valor) {
		Date dataInicial = new Date();
		setOcupado(true);
		Arvore arvore = (Arvore) colecao;
		T resultado = buscaEmProfundidade(arvore.getRaiz(), valor);
		arvore.zerar(arvore.getRaiz());
		setOcupado(false);

		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);

		return resultado;
	}

	private <T> T buscaEmProfundidade(No no, Comparable valor) {
		/* 
		 * Uma árvore é um grafo não direcionado em que quaisquer 
		 * dois vértices são conectados por exatamente um caminho.
		 * Em outras palavras, qualquer gráfico ligado sem ciclos simples é uma árvore.
		 * Com isso uma árvore, não existe qualquer loop, de modo que a verificação de no já visitados é redundante.
		 * */

		if(no == null){
			return null;
		}
		Stack<No> stack = new Stack<No>();
		stack.push(no);
		while(!stack.isEmpty()){
			No novoNo = stack.pop();
			if(valor.compareTo(novoNo.getValor()) == 0 ){
				return (T) novoNo;                        
			}
			if(novoNo.getFilhoDireita() != null){
				stack.push(novoNo.getFilhoDireita());
			}
			if(novoNo.getFilhoEsquerda() != null){       
				stack.push(novoNo.getFilhoEsquerda());  
			}
		}
		return null; 
	}
}