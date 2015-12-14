package br.com.servidor;

import java.io.Serializable;

import br.com.cliente.requisicao.TipoRequisicao;
/**
 * Classe que que representa dados para serem atualizados na tabela de tempo.S
 * 
 * Classe <code>DadosAtualizacao</code>
 * 
 * @author Tiago Henrique
 * @version 1.0 (13/12/2015)
 *
 */
public class DadosAtualizacao implements Serializable {
	
	/** Constante serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** tipo requisicao */
	private TipoRequisicao tipoSolucao;
	
	/** nova media */
	private Double novaMedia;
	
	/**
	 * Instancia uma nova classe DadosAtualização a partir
	 * de um tipo de requisição e da nova média.
	 * 
	 * @param tipoSolucao
	 * 			tipo requisicao
	 * @param novaMedia
	 * 			nova media
	 */
	public DadosAtualizacao(TipoRequisicao tipoSolucao, Double novaMedia) {
		this.tipoSolucao = tipoSolucao;
		this.novaMedia = novaMedia;
	}
	
	/**
	 * Obtem tipo solucao
	 * 
	 * @return tipo solucao
	 */
	public TipoRequisicao getTipoSolucao() {
		return tipoSolucao;
	}
	
	/**
	 * Define tipo solucao
	 * 
	 * @param tipoSolucao
	 * 			tipo solucao
	 */
	public void setTipoSolucao(TipoRequisicao tipoSolucao) {
		this.tipoSolucao = tipoSolucao;
	}
	
	/**
	 * Obtem nova media
	 * 
	 * @return nova media
	 */
	public Double getNovaMedia() {
		return novaMedia;
	}
	
	/**
	 * Define nova media
	 * 
	 * @param novaMedia
	 * 			nova media
	 */
	public void setNovaMedia(Double novaMedia) {
		this.novaMedia = novaMedia;
	}
	
	
}
