package br.com.algoritmos.solucao;

import java.io.Serializable;

/**
 * Classe que representa o nó de uma arvore.
 * 
 * Classe<code>No</code>
 * 
 * @author Gabriel
 * @author Bruno
 * @version 1.0 (12/12/2015)
 *
 */
public class No implements Serializable {
	private static final long serialVersionUID = 1L;

	/** valor */
	private int valor;
	
	/** no direito */
	private No filhoDireita;
	
	/** no esquerdo */
	private No filhoEsquerda;
	
	/** boolean visitado */
	private boolean visitado;
	
	/**
	 * Instancia um novo nó a partir de um valor
	 * 
	 * @param valor
	 * 			valor
	 */
	public No(int valor) {
		this.setValor(valor);
		this.visitado = false;
		this.filhoEsquerda = null;
		this.filhoDireita = null;
	}
	
	/**
	 * Obtem visitado.
	 * 
	 * @return visitado
	 */
	public boolean isVisitado() {
		return visitado;
	}

	/**
	 * Define visitado
	 * 
	 * @param visitado
	 * 			visitado
	 */
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	/**
	 * Obtem valor
	 * 
	 * @return valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Define valor
	 * 
	 * @param valor
	 * 			valor
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}

	/**
	 * Obtem no direito.
	 * 
	 * @return no direito
	 */
	public No getFilhoDireita() {
		return filhoDireita;
	}

	/**
	 * Define no direito
	 * 
	 * @param filhoDireita
	 * 			no direito
	 */
	public void setFilhoDireita(No filhoDireita) {
		this.filhoDireita = filhoDireita;
	}

	/**
	 * Obtem no esquerdo
	 * 
	 * @return no esquerdo
	 */
	public No getFilhoEsquerda() {
		return filhoEsquerda;
	}

	/**
	 * Define no esquerdo
	 * 
	 * @param filhoEsquerda
	 * 			no esquerdo
	 */
	public void setFilhoEsquerda(No filhoEsquerda) {
		this.filhoEsquerda = filhoEsquerda;
	}

	/**
	 * Define filho a partir do seu nó e do valor
	 * 
	 * @param node
	 * 			nó
	 * @param valor
	 * 			valor
	 */
	public void setFilho(No node, int valor) {
		if (valor < node.valor) { 
			if (node.filhoEsquerda != null) { 
				setFilho(node.filhoEsquerda, valor);
			} 
			else { 
				//System.out.println(" Inserindo " + valor + " a esquerda de " + node.valor);
				node.filhoEsquerda = new No(valor);
			}
		} 
		else if (valor > node.valor) { 
			if (node.filhoDireita != null) {
				setFilho(node.filhoDireita, valor);
			}
			else { 
				//System.out.println(" Inserindo " + valor + " a direita de " + node.valor); 
				node.filhoDireita = new No(valor);
			} 
		}
			
	}

}
