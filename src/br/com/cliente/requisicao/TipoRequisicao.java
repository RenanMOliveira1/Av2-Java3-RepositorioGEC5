package br.com.cliente.requisicao;

import java.io.Serializable;
/**
 * Enumera��o que representa os tipos de requisi��es.
 * 
 * Enum<Code>TipoRequisicao</code>
 * 
 * @author Tiago
 * @author Renan Oliveira
 * @version 1.0 (12/12/2015)
 */
public enum TipoRequisicao implements Serializable {
	
	ORDENACAO, BUSCA, BUSCA_ARVORE;
}
