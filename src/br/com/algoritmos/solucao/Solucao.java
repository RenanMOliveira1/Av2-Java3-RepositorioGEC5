package br.com.algoritmos.solucao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.cliente.requisicao.Requisicao;
import br.com.algoritmos.executavel.Main;
import br.com.algritmos.util.RedeUtil;

/**
 * @author Tiago, Renan
 *
 */
public abstract class Solucao implements Runnable {
	protected String nomeSolucao;
	protected Double mediaGeral;
	protected boolean ocupado;
	protected ArrayList<Long> listaTempos;
	protected DatagramSocket socket;

	protected Solucao(String _nomeSolucao, int port) {
		mediaGeral = 0.0;
		ocupado = false;
		nomeSolucao = _nomeSolucao;
		listaTempos = new ArrayList<Long>();
		
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	protected Solucao(String _nomeSolucao) {
		mediaGeral = 0.0;
		ocupado = false;
		nomeSolucao = _nomeSolucao;
		listaTempos = new ArrayList<Long>();
	}

	public Double getMediaGeral() {
		long soma = 0;
		for (long tempo : listaTempos) {
			soma += tempo;
		}
		if (listaTempos.size() != 0)
			mediaGeral = (double) (soma / listaTempos.size());
		
		try {
			atualizarServidor(mediaGeral);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mediaGeral;
	}
	
	private void atualizarServidor(Double mediaGeral) throws IOException {
		byte[] data = RedeUtil.serializar(mediaGeral);
		DatagramPacket packetAtualizacao = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), Main.PORTA);
		
		socket.send(packetAtualizacao);
	}
	
	public void adicionarTempoDuracao(Date dataInicial, Date dataFinal) {
		// in milliseconds
		long tempoDuracao = dataFinal.getTime() - dataInicial.getTime();

		listaTempos.add(tempoDuracao);
	}

	public boolean isOcupado() {
		return ocupado;
	}

	protected void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public ArrayList<Long> getListaTempos() {
		return listaTempos;
	}

	public void setListaTempos(ArrayList<Long> listaTempos) {
		this.listaTempos = listaTempos;
	}

	public <T> Requisicao<T> receberRequisicao() {
		byte[] data = new byte[100];
		Requisicao<T> requisicao = null;
		DatagramPacket receivePacket = new DatagramPacket(data, data.length);

		try {
			socket.receive(receivePacket);
			requisicao = (Requisicao) RedeUtil.deserialize(receivePacket.getData());
		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return requisicao;
	}

	public <T> void enviarRequisicao(Requisicao<T> requisicao) {
		byte[] data = null;
		DatagramPacket sendPacket = null;

		try {
			data = RedeUtil.serializar(requisicao);
			sendPacket = new DatagramPacket(data, data.length, requisicao.getDados().getIp(), requisicao.getDados().getPorta());

			socket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
