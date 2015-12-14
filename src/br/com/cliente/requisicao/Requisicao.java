package br.com.cliente.requisicao;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.algoritmos.solucao.Arvore;
import br.com.algoritmos.solucao.No;
/**
 * Esta classe representa uma requisição de uma ordenação ou busca de um cliente.
 * 
 * Classe <code>Requisicao</code>
 * 
 * @author Tiago
 * @author Bruno
 * @author Luis Carlos
 * 
 * @version 1.0 (12/12/2015)
 *
 */
public class Requisicao <T extends Serializable> implements Serializable {
	
	/** Constante serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** dados cliente */
	private DadosClient dados;
	
	/** valor */
	private T valor;
	
	/** arvore */
	private Arvore arvore;
	
	/** Tipo Requisicao */
	private TipoRequisicao tipoRequisicao;
	
	/** lista valores */
	private ArrayList<T> listaValores;
	
	//Dados De Recebimento
	
	/** posicao */
	private int posicao;
	
	/** no */
	private No no;
	
	/** erro */
	private boolean erro;

	/**
	 * Instancia uma nova requisicao
	 * 
	 * @param dados
	 * 			dados cliente
	 * @param valor
	 * 			valor
	 * @param tipoRequisicao
	 * 			tipo requisicao
	 * @param listaValores
	 * 			lista valores
	 * @param posicao
	 * 			posicao
	 * @param no
	 * 			no
	 */
	public Requisicao(DadosClient dados, T valor, TipoRequisicao tipoRequisicao, ArrayList<T> listaValores,
			int posicao, No no, Arvore arvore) {
		this.dados = dados;
		this.valor = valor;
		this.tipoRequisicao = tipoRequisicao;
		this.listaValores = listaValores;
		this.posicao = posicao;
		this.no = no;
		this.arvore = arvore;
	}
	
	/**
	 * Instancia uma nova Requisicao
	 */
	public Requisicao() {
		this(null, null, null, null, 0, null, null);
	}
	
	/**
	 * Construtor de Resposta de Ordenação
	 * 
	 * @param _dados
	 * 			dados cliente
	 * @param _listaValores
	 * 			lista valores
	 */
	public Requisicao(DadosClient _dados, ArrayList<T> _listaValores) {
		this(_dados, null, null, _listaValores, 0, null, null);
	}
	
	/**
	 * Construtor de Resposta de Busca
	 * 
	 * @param _dados
	 * 			dados cliente
	 * @param _posicao
	 * 			posicao
	 */
	public Requisicao(DadosClient _dados, int _posicao) {
		this(_dados, null, null, null, _posicao, null, null);
	}
	
	/**
	 * Construtor de Resposta de Arvore ou Grafos
	 * 
	 * @param _dados
	 * 			dados cliente
	 * @param _no
	 * 			no
	 */
	public Requisicao(DadosClient _dados, No _no) {
		this(_dados, null, null, null, 0, _no, null);
	}
	
	/**
	 * Requisicão de Ordenação
	 * 
	 * @param _tipoRequisicao
	 * 			tipo requisicao
	 * @param _listaValores
	 * 			lista valores
	 */
	public Requisicao(TipoRequisicao _tipoRequisicao, ArrayList<T> _listaValores) {
		this(null, null, _tipoRequisicao, _listaValores, 0, null, null);
	}
	
	/**
	 * Requisicão de Busca
	 * 
	 * @param _tipoRequisicao
	 * 			tipo requisicao
	 * @param _listaValores
	 * 			lista valores
	 * @param _valor
	 * 			valor
	 */
	public Requisicao(TipoRequisicao _tipoRequisicao, ArrayList<T> _listaValores, T _valor) {
		this(null, _valor, _tipoRequisicao, _listaValores, 0, null, null);
	}
	
	/**
	 * Requisicão de Busca
	 * 
	 * @param _tipoRequisicao
	 * 			tipo requisicao
	 * @param _valor
	 * 			valor
	 * @param _arvore
	 * 			arvore
	 */
	public Requisicao(TipoRequisicao _tipoRequisicao, Arvore _arvore, T _valor) {
		this(null, _valor, _tipoRequisicao, null, 0, null, _arvore);
	}
	
	/**
	 * Requisição de Erro
	 * 
	 * @param _erro
	 * @param _mensagemErro
	 */
	public Requisicao(TipoRequisicao _tipoRequisicao, String _mensagemErro) {
		setErro(true);
		setTipoRequisicao(_tipoRequisicao);
	}
	
	/**
	 * Obtem valor
	 * 
	 * @return valor
	 */
	public T getValor() {
		return valor;
	}
	
	/**
	 * Define valor
	 * 
	 * @param valor
	 * 			valor
	 */
	public void setValor(T valor) {
		this.valor = valor;
	}
	
	/**
	 * Obtem tipo requisicao
	 * 
	 * @return tipo requisicao
	 */
	public TipoRequisicao getTipoRequisicao() {
		return tipoRequisicao;
	}
	
	/**
	 * Define tipo requisicao
	 * 
	 * @param tipoRequisicao
	 * 			tipo requisicao
	 */
	public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}
	
	/**
	 * Obtem lista valores
	 * 
	 * @return lista valores
	 */
	public ArrayList<T> getListaValores() {
		return listaValores;
	}
	
	/**
	 * Define lista valores
	 * 
	 * @param listaValores
	 * 			lista valores
	 */
	public void setListaValores(ArrayList<T> listaValores) {
		this.listaValores = listaValores;
	}

	/**
	 * Obtem dados cliente
	 * 
	 * @return dados cliente
	 */
	public DadosClient getDados() {
		return dados;
	}
	
	/**
	 * Defina dados cliente
	 * 
	 * @param dados
	 * 			dados cliente
	 */
	public void setDados(DadosClient dados) {
		this.dados = dados;
	}

	/**
	 * Obtem posicao
	 * 
	 * @return posicao
	 */
	public Integer getPosicao() {
		return posicao;
	}

	/**
	 * Define posicao
	 * 
	 * @param posicao
	 * 			posicao
	 */
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

	/**
	 * Obtem no
	 * 
	 * @return no
	 */
	public No getNo() {
		return no;
	}

	/**
	 * Define no
	 * 
	 * @param no
	 * 			no
	 */
	public void setNo(No no) {
		this.no = no;
	}

	/**
	 * Obtem arvore
	 * 
	 * @return arvore
	 */
	public Arvore getArvore() {
		return arvore;
	}

	/**
	 * Define arvore
	 * 
	 * @param arvore
	 * 			arvore
	 */
	public void setArvore(Arvore arvore) {
		this.arvore = arvore;
	}
	
	/**
	 * Houve erro
	 * 
	 * @return erro
	 */
	public boolean isErro() {
		return erro;
	}

	/**
	 * Define se houve erro
	 * 
	 * @param erro
	 */
	public void setErro(boolean erro) {
		this.erro = erro;
	}

}
