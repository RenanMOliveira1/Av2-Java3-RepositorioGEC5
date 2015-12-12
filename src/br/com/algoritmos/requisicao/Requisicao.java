package br.com.algoritmos.requisicao;

import java.io.Serializable;
import java.util.Collection;
/**
 * @author Tiago, Bruno
 *
 */
public class Requisicao <T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private DadosClient dados;
	private T valor;
	private TipoRequisicao tipoRequisicao;
	private Collection<T> listaValores;
	
	//Dados De Recebimento
	private Integer posicao;
	private Object no;

	public Requisicao(DadosClient dados, T valor, TipoRequisicao tipoRequisicao, Collection<T> listaValores,
			Integer posicao, Object no) {
		this.dados = dados;
		this.valor = valor;
		this.tipoRequisicao = tipoRequisicao;
		this.listaValores = listaValores;
		this.posicao = posicao;
		this.no = no;
	}
	
	public Requisicao() {
		this(null, null, null, null, null, null);
	}
	
	
	//Construtor de Resposta de Ordenação
	public Requisicao(DadosClient _dados, Collection<T> _listaValores) {
		this(_dados, null, null, _listaValores, null, null);
	}
	
	//Construtor de Resposta de Busca
	public Requisicao(DadosClient _dados, Integer _posicao) {
		this(_dados, null, null, null, _posicao, null);
	}
	
	//Construtor de Resposta de Arvore ou Grafos
	public Requisicao(DadosClient _dados, Object _no) {
		this(_dados, null, null, null, null, _no);
	}
	
	//Requisicão de Ordenação
	public Requisicao(TipoRequisicao _tipoRequisicao, Collection<T> _listaValores) {
		this(null, null, _tipoRequisicao, _listaValores, null, null);
	}
	
	//Requisicão de Busca
	public Requisicao(TipoRequisicao _tipoRequisicao, Collection<T> _listaValores, T _valor) {
		this(null, _valor, _tipoRequisicao, _listaValores, null, null);
	}
	
	public T getValor() {
		return valor;
	}
	
	public void setValor(T valor) {
		this.valor = valor;
	}
	
	public TipoRequisicao getTipoRequisicao() {
		return tipoRequisicao;
	}
	
	public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}
	
	public Collection<T> getListaValores() {
		return listaValores;
	}
	public void setListaValores(Collection<T> listaValores) {
		this.listaValores = listaValores;
	}

	public DadosClient getDados() {
		return dados;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

	public Object getNo() {
		return no;
	}

	public void setNo(Object no) {
		this.no = no;
	}

	public void setDados(DadosClient dados) {
		this.dados = dados;
	}
	
	
}
