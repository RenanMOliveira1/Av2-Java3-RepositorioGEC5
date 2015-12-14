package br.com.algoritmos.busca;

import java.util.Date;
import java.util.Stack;

import br.com.algoritmos.solucao.Arvore;
import br.com.algoritmos.solucao.No;
import br.com.algoritmos.solucao.Solucao;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Esta Classe representa o algoritmo Busca em Profundidade.
 * Ela � um algoritmo usado para buscar numa �rvore, estrutura 
 * de �rvore ou grafo. O algoritmo come�a num n� raiz e explora
 *  tanto quanto poss�vel cada um dos seus ramos, antes de retroceder.
 * 
 * Classe<code>BuscaEmProfundidade</code>
 * 
 * @author Vinicius Viana(O cara da Samantha)
 * @version(12/12/2015)
 *
 */
public class BuscaEmProfundidade<T> extends Solucao implements Runnable, IArvoreBuscavel<T> {
	
	/**
	 * Instancia uma nome busca em profundidade
	 */
	public BuscaEmProfundidade() {
		super("Busca em profundidade", TipoRequisicao.BUSCA_ARVORE, 50005);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {		
		while(true){
			Requisicao<Comparable> requisicao = receberRequisicao();
			requisicao.setNo( buscarElemento(requisicao.getArvore(), requisicao.getValor()) );
			requisicao.setListaValores(null);
			enviarRequisicao(requisicao);
		}	
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.algoritmos.busca.IArvoreBuscavel#buscarElemento(java.lang.Object, java.lang.Comparable)
	 */
	@Override
	public <T> T buscarElemento(Object colecao, Comparable valor) {
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

	/**
	 * 
	 * @param no
	 * 			no
	 * @param valor
	 * 			valor
	 * @return resultado
	 */
	private <T> T buscaEmProfundidade(No no, Comparable valor) {
	/* 
	 * Uma �rvore � um grafo n�o direcionado em que quaisquer 
	 * dois v�rtices s�o conectados por exatamente um caminho.
	 * Em outras palavras, qualquer gr�fico ligado sem ciclos simples � uma �rvore.
	 * Com isso uma �rvore, n�o existe qualquer loop, de modo que a verifica��o de no j� visitados � redundante.
	 */

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