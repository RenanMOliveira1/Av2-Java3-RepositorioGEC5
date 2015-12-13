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
 * @author Tiago
 *
 * 
 */
public class Cliente <T> {
	private Requisicao<T> requisicao;
	private DatagramSocket socket;
	
	public Cliente() {
		
		try {
			socket = new DatagramSocket(5000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarRequisicao(Requisicao<T> _requisicao) {
		
		requisicao = _requisicao;
		
		try {
			enviarParaServidor();
			receberRespostaServidor();
			enviarParaMelhorAlgoritmo();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void enviarParaServidor() throws IOException, ClassNotFoundException {
		
		byte[] data = RedeUtil.serializar(requisicao.getTipoRequisicao());
		enviarDados(new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5000));
	}
	
	private void receberRespostaServidor() throws IOException, ClassNotFoundException {
		byte[] data = new byte[ 5000 ];
		
		//Resposta do Servidor.
		DatagramPacket packetResposta = new DatagramPacket(data, data.length);
		socket.receive(packetResposta);
		
		//Recupera os Dados, Porta e IP, do Algoritmo mais Rápido.
		DadosClient dadosMelhorAlgoritmo = (DadosClient) RedeUtil.deserialize(packetResposta.getData());
		requisicao.setDados(dadosMelhorAlgoritmo);
	}
	
	private void enviarParaMelhorAlgoritmo() throws IOException {
		
		byte[] data = RedeUtil.serializar(requisicao);
		enviarDados(new DatagramPacket(data, data.length, requisicao.getDados().getIp(), requisicao.getDados().getPorta()));
	}
	
	private void enviarDados(DatagramPacket packet) throws IOException {
		socket.receive(packet);
	}

	public Requisicao<T> getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao<T> requisicao) {
		this.requisicao = requisicao;
	}
}
