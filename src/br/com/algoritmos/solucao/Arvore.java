/**
 * @author Bruno,Gabriel
 *
 */

package br.com.algoritmos.solucao;

public class Arvore{
	private No raiz;
	
	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public Arvore(int valor) {
		No raiz = new No (valor);
		this.setRaiz(raiz);
	}
	
	public void inserir(int valor){
		
		this.raiz.setFilho(this.raiz, valor);
	}
	
	public void zerar(No no) {
		if(no != null){
			zerar(no.getFilhoEsquerda());
			no.setVisitado(false);
			zerar(no.getFilhoDireita());
		}
	}

	
}
