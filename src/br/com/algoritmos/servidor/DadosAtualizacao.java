package br.com.algoritmos.servidor;

import br.com.algoritmos.cliente.requisicao.TipoRequisicao;

public class DadosAtualizacao {
	private TipoRequisicao tipoSolucao;
	private Double novaMedia;
	
	public DadosAtualizacao(TipoRequisicao tipoSolucao, Double novaMedia) {
		this.tipoSolucao = tipoSolucao;
		this.novaMedia = novaMedia;
	}
	
	public TipoRequisicao getTipoSolucao() {
		return tipoSolucao;
	}
	public void setTipoSolucao(TipoRequisicao tipoSolucao) {
		this.tipoSolucao = tipoSolucao;
	}
	public Double getNovaMedia() {
		return novaMedia;
	}
	public void setNovaMedia(Double novaMedia) {
		this.novaMedia = novaMedia;
	}
	
	
}
