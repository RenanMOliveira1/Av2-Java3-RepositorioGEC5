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
	private int porta;
	
	/** IP */
	private InetAddress ip;
	
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
	public DadosClient(int _porta, InetAddress _ip) {
		porta = _porta;
		ip = _ip;
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
	public int getPorta() {
		return porta;
	}
	
	/**
	 * Define porta
	 * 
	 * @param porta
	 * 			porta
	 */
	public void setPorta(int porta) {
		this.porta = porta;
	}
}
