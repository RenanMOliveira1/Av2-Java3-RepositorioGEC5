package br.com.cliente.requisicao;

import java.io.Serializable;
import java.net.InetAddress;
/**
 * Essa classe representa os dados do cliente de uma rede
 * 
 * Classe<code>DadosClient</code>
 * 
 * @author Tiago
 * @version 1.0 (12/12/2015)
 */
public class DadosClient implements Serializable {
	
	/** Constante serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** porta */
	private Integer porta;
	
	/** IP */
	private InetAddress ip;
	
	/** nome cliente */
	private String nomeClient;
	
	/**
	 * Instancia um novo DadosClient
	 * 
	 * @param _porta
	 * 			porta
	 * @param _ip
	 * 			IP
	 * @param _nomeClient
	 * 			nome cliente
	 */
	public DadosClient(Integer _porta, InetAddress _ip, String _nomeClient) {
		porta = _porta;
		ip = _ip;
		nomeClient = _nomeClient;
	}
	
	/**
	 * Obtem IP
	 * 
	 * @return IP
	 */
	public InetAddress getIp() {
		return ip;
	}
	
	/**
	 * Define IP
	 * 
	 * @param ip
	 * 			ip
	 */
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	
	/**
	 * Obtem porta
	 * 
	 * @return porta
	 */
	public Integer getPorta() {
		return porta;
	}
	
	/**
	 * Define porta
	 * 
	 * @param porta
	 * 			porta
	 */
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
	
	/**
	 * Obtem nome cliente
	 * 
	 * @return nome cliente
	 */
	public String getNomeClient() {
		return nomeClient;
	}
	
	/**
	 * Define nome cliente
	 * 
	 * @param nomeClient
	 * 			nome cliente
	 */
	public void setNomeClient(String nomeClient) {
		this.nomeClient = nomeClient;
	}
}
