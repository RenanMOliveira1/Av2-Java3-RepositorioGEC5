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
 * @author Luis Carlos
 * 
 */
public class Cliente <T> implements Runnable {
	private Requisicao requisicao;
	private DatagramSocket socket;
	private int PORTA_SERVIDOR = 12345;
	
	public Cliente(Requisicao _requisicao) {
		requisicao = _requisicao;
	}
	
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
	
	private void iniciarServidor() throws SocketException {
		socket = new DatagramSocket(5000);
	}
	
	private void enviarParaServidor() throws IOException, ClassNotFoundException {
		
		byte[] data = RedeUtil.serializar(requisicao.getTipoRequisicao());
		enviarDados(new DatagramPacket(data, data.length, InetAddress.getLocalHost(), PORTA_SERVIDOR));
	}
	
	private void receberRespostaServidor() throws IOException, ClassNotFoundException {
		byte[] data = new byte[ 5000 ];
		
		//Resposta do Servidor.
		DatagramPacket packetResposta = new DatagramPacket(data, data.length);
		socket.receive(packetResposta);
		
		//Recupera os Dados, Porta e IP, do Algoritmo mais Rápido.
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
	
	private void enviarParaMelhorAlgoritmo() throws IOException, ClassNotFoundException {
		
		byte[] data = RedeUtil.serializar(requisicao);
		enviarDados(new DatagramPacket(data, data.length, requisicao.getDados().getIp(), requisicao.getDados().getPorta()));
		
		receberRespostaAlgoritmos();
	}
	
	private void enviarDados(DatagramPacket packet) throws IOException {
		socket.send(packet);
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
}
