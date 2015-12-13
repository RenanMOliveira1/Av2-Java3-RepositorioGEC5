package br.com.algoritmos.cliente.requisicao;

import java.io.Serializable;
/**
 * Enumeração que representa os tipos de requisições.
 * 
 * Enum<Code>TipoRequisicao</code>
 * 
 * @author Tiago
 * @version 1.0 (12/12/2015)
 */
public enum TipoRequisicao implements Serializable {
	
	ORDENACAO, BUSCA, BUSCA_ARVORE;
}
