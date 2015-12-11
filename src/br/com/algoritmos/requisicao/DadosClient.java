package br.com.algoritmos.requisicao;

import java.io.Serializable;
import java.net.InetAddress;

public class DadosClient implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer porta;
	private InetAddress ip;
	private String nomeClient;
	
	public DadosClient(Integer _porta, InetAddress _ip, String _nomeClient) {
		porta = _porta;
		ip = _ip;
		nomeClient = _nomeClient;
	}
	
	public InetAddress getIp() {
		return ip;
	}
	
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	
	public Integer getPorta() {
		return porta;
	}
	
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
	
	public String getNomeClient() {
		return nomeClient;
	}
	
	public void setNomeClient(String nomeClient) {
		this.nomeClient = nomeClient;
	}
}
