package br.com.cliente;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import br.com.cliente.requisicao.DadosClient;
import br.com.cliente.requisicao.Requisicao;
import br.com.util.RedeUtil;
/**
 * Esta classe reqpresenta o cliente de uma rede
 * 
 * Classe<code>Cliente</code>
 * 
 * @author Tiago
 * @author Luis Carlos
 * @author Yasmin Farias
 * @version 1.0 (11/12/2015)
 * 
 */
public class Cliente <T> implements Runnable {
	
	/** Requisicao */
	private Requisicao requisicao;
	
	/** Cliente */
	private DatagramSocket socket;
	
	/** porta */
	private int PORTA_SERVIDOR = 12345;
	
	/**
	 * Instancia um novo cliente
	 */
	public Cliente(Requisicao _requisicao) {
		requisicao = _requisicao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		try {
			iniciarServidor();
			enviarParaServidor();
			receberRespostaServidor();
			enviarParaMelhorAlgoritmo();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Instancia o socket.
	 * 
	 * @throws SocketException
	 */
	private void iniciarServidor() throws SocketException {
		socket = new DatagramSocket(5000);
	}
	
	/**
	 * Envia para o servidor os dados da requisição
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void enviarParaServidor() throws IOException, ClassNotFoundException {
		
		byte[] data = RedeUtil.serializar(requisicao.getTipoRequisicao());
		enviarDados(new DatagramPacket(data, data.length, InetAddress.getLocalHost(), PORTA_SERVIDOR));
	}
	
	/**
	 * Recebe uma resposta do servidor e a partir
	 * dessa resposta recupera os dados, porta e IP
	 * do algortimo mais rápido.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void receberRespostaServidor() throws IOException, ClassNotFoundException {
		byte[] data = new byte[ 5000 ];
		
		DatagramPacket packetResposta = new DatagramPacket(data, data.length);
		socket.receive(packetResposta);
		
		DadosClient dadosMelhorAlgoritmo = (DadosClient) RedeUtil.deserialize(packetResposta.getData());
		System.out.println(dadosMelhorAlgoritmo.getPorta());
		requisicao.setDados(dadosMelhorAlgoritmo);
	}
	
	private void receberRespostaAlgoritmos() throws IOException, ClassNotFoundException {
		byte[] data = new byte[ 5000 ];
		
		//Resposta do Servidor.
		DatagramPacket packetResposta = new DatagramPacket(data, data.length);
		socket.receive(packetResposta);
		
		//Recupera os Dados, Porta e IP, do Algoritmo mais Rápido.
		Requisicao respostaAlgoritmo = (Requisicao) RedeUtil.deserialize(packetResposta.getData());
		
		switch(respostaAlgoritmo.getTipoRequisicao()){
		case BUSCA:
			System.out.println("OpaFion: " + respostaAlgoritmo.getPosicao().toString());
			break;
		case BUSCA_ARVORE:
			System.out.println("ÉOQ??: " + respostaAlgoritmo.getNo().toString());
		case ORDENACAO:
			System.out.println("ÉOQ??MALUCO: " + respostaAlgoritmo.getListaValores().toString());
			break;
		default:
			break;
		}
		
	}
	
	/**
	 * Envia os dados da requisição para o
	 * algoritmo mais rápido.
	 * 
	 * @throws IOException
	 */
	private void enviarParaMelhorAlgoritmo() throws IOException, ClassNotFoundException {
		
		byte[] data = RedeUtil.serializar(requisicao);
		enviarDados(new DatagramPacket(data, data.length, requisicao.getDados().getIp(), requisicao.getDados().getPorta()));
		
		receberRespostaAlgoritmos();
	}
	
	/**
	 * Envia os dados para um cliente através de um pacote
	 * 
	 * @param packet
	 * @throws IOException
	 */
	private void enviarDados(DatagramPacket packet) throws IOException {
		socket.send(packet);
	}

	/**
	 * Obtem requisição
	 * 
	 * @return requisição
	 */
	public Requisicao getRequisicao() {
		return requisicao;
	}

	/**
	 * Define requisição
	 * 
	 * @param requisicao
	 * 			requisição
	 */
	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
}
