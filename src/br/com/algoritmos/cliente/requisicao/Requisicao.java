package br.com.algoritmos.cliente.requisicao;

import java.io.Serializable;
import java.util.Collection;
/**
 * 
 * Classe<code>Requisicao</code>
 * 
 * @author Tiago
 * @author Bruno
 * 
 * @version 1.0 (12/12/2015)
 *
 */
public class Requisicao <T> implements Serializable {
	
	/** Constante serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** dados cliente */
	private DadosClient dados;
	
	/** valor */
	private T valor;
	
	/** Tipo Requisicao */
	private TipoRequisicao tipoRequisicao;
	
	/** lista valores */
	private Collection<T> listaValores;
	
	//Dados De Recebimento
	
	/** posicao */
	private Integer posicao;
	
	/** no */
	private Object no;

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
	public Requisicao(DadosClient dados, T valor, TipoRequisicao tipoRequisicao, Collection<T> listaValores,
			Integer posicao, Object no) {
		this.dados = dados;
		this.valor = valor;
		this.tipoRequisicao = tipoRequisicao;
		this.listaValores = listaValores;
		this.posicao = posicao;
		this.no = no;
	}
	
	/**
	 * Instancia uma nova Requisicao
	 */
	public Requisicao() {
		this(null, null, null, null, null, null);
	}
	
	/**
	 * Construtor de Resposta de Ordenação
	 * 
	 * @param _dados
	 * 			dados cliente
	 * @param _listaValores
	 * 			lista valores
	 */
	public Requisicao(DadosClient _dados, Collection<T> _listaValores) {
		this(_dados, null, null, _listaValores, null, null);
	}
	
	/**
	 * Construtor de Resposta de Busca
	 * 
	 * @param _dados
	 * 			dados cliente
	 * @param _posicao
	 * 			posicao
	 */
	public Requisicao(DadosClient _dados, Integer _posicao) {
		this(_dados, null, null, null, _posicao, null);
	}
	
	/**
	 * Construtor de Resposta de Arvore ou Grafos
	 * 
	 * @param _dados
	 * 			dados cliente
	 * @param _no
	 * 			no
	 */
	public Requisicao(DadosClient _dados, Object _no) {
		this(_dados, null, null, null, null, _no);
	}
	
	/**
	 * Requisicão de Ordenação
	 * 
	 * @param _tipoRequisicao
	 * 			tipo requisicao
	 * @param _listaValores
	 * 			lista valores
	 */
	public Requisicao(TipoRequisicao _tipoRequisicao, Collection<T> _listaValores) {
		this(null, null, _tipoRequisicao, _listaValores, null, null);
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
	public Requisicao(TipoRequisicao _tipoRequisicao, Collection<T> _listaValores, T _valor) {
		this(null, _valor, _tipoRequisicao, _listaValores, null, null);
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
	public Collection<T> getListaValores() {
		return listaValores;
	}
	
	/**
	 * Define lista valores
	 * 
	 * @param listaValores
	 * 			lista valores
	 */
	public void setListaValores(Collection<T> listaValores) {
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
	public Object getNo() {
		return no;
	}

	/**
	 * Define no
	 * 
	 * @param no
	 * 			no
	 */
	public void setNo(Object no) {
		this.no = no;
	}
	
	
}
