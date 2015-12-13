/**
 * @author Bruno,Gabriel
 *
 */

package br.com.algoritmos.solucao;
/**
 * Esta classe representa uma Arvore(Estrutura de dados).
 * 
 * Classe<code>Arvore</code>
 * 
 * @author Gabriel 
 * @author Tiago Henrique
 * @version 1.0(12/12/2015)
 *
 */
public class Arvore{
	/** raiz */
	private No raiz;
	
	/**
	 * Intancia uma nova arvore
	 * 
	 * @param valor
	 * 			valor
	 */
	public Arvore(int valor) {
		No raiz = new No (valor);
		this.setRaiz(raiz);
	}
	
	/**
	 * Obtem raiz
	 * 
	 * @return raiz
	 */
	public No getRaiz() {
		return raiz;
	}

	/**
	 * Define rais
	 * 
	 * @param raiz
	 * 			raiz
	 */
	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}
	
	/**
	 * Insere um filho na arvore
	 * 
	 * @param valor
	 * 			valor
	 */
	public void inserir(int valor){
		
		this.raiz.setFilho(this.raiz, valor);
	}
	
	/**
	 * Remove o nó passado por parametro.
	 * 
	 * @param no
	 * 			no
	 */
	public void zerar(No no) {
		if(no != null){
			zerar(no.getFilhoEsquerda());
			no.setVisitado(false);
			zerar(no.getFilhoDireita());
		}
	}

	
}
