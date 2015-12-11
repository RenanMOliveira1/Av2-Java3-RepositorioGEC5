package br.com.algoritmos.solucao;


public class No {
	private int valor;
	private No filhoDireita;
	private No filhoEsquerda;
	private boolean visitado;
	
	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public No getFilhoDireita() {
		return filhoDireita;
	}

	public void setFilhoDireita(No filhoDireita) {
		this.filhoDireita = filhoDireita;
	}

	public No getFilhoEsquerda() {
		return filhoEsquerda;
	}

	public void setFilhoEsquerda(No filhoEsquerda) {
		this.filhoEsquerda = filhoEsquerda;
	}

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

	public No(int valor) {
		this.setValor(valor);
		this.visitado = false;
		this.filhoEsquerda = null;
		this.filhoDireita = null;
	}

}
