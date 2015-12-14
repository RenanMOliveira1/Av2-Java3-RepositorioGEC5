package br.com.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Hashtable;

import javax.sound.midi.Receiver;

import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
import br.com.executavel.MainServidor;
import br.com.util.RedeUtil;

/**
 * @author Renan
 * @author Tiago
 * @author Luis Carlos
 *
 */
public class ServidorCentral implements Runnable {
	private volatile static ServidorCentral servidor = null;
	
	private DatagramSocket socket;
	private int porta;
	
	private Hashtable<Integer, Double> tabelaTempoBusca;
	private Hashtable<Integer, Double> tabelaTempoOrdena;
	private Hashtable<Integer, Double> tabelaTempoBuscaArvore;

	private ServidorCentral(int _porta) {
		tabelaTempoOrdena = new Hashtable<Integer, Double>();
		tabelaTempoBusca = new Hashtable<Integer, Double>();
		porta = _porta;
	}
	
	public static ServidorCentral getInstancia(int porta) {
		if (servidor == null) {
			synchronized (ServidorCentral.class) {
				if (servidor == null)
					return new ServidorCentral(porta);
			}
		}

		return servidor;
	}

	@Override
	public void run() {

		try {
			inciarServidor();
			waitForPackets();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void inciarServidor() throws SocketException {
		socket = new DatagramSocket(porta);
	}

	private void waitForPackets() throws IOException, ClassNotFoundException {

		while (true) {
			byte[] data = new byte[50000];
			DatagramPacket receivePacket = new DatagramPacket(data, data.length);
			System.out.println("Esperando.");
			socket.receive(receivePacket);

			Object object = RedeUtil.deserialize(receivePacket.getData());

			if (object instanceof TipoRequisicao) {
				responderCliente(receivePacket, (TipoRequisicao) object);
			} else if (object instanceof DadosAtualizacao) {
				atualizarTabela(receivePacket.getPort(), (DadosAtualizacao) object);
			} else {				
				gerenciaProximoAlgoritmo(receivePacket, ((Requisicao) object).getTipoRequisicao());
			}
		}
	}

	private void responderCliente(DatagramPacket receivePacket, TipoRequisicao requisicao)
			throws ClassNotFoundException, IOException {

		sendPacketToClient(new DatagramPacket(receivePacket.getData(), receivePacket.getLength(),
				receivePacket.getAddress(), getPort(requisicao)));
	}

	private void atualizarTabela(Integer portaAlgoritmo, DadosAtualizacao dadosAtualizacao) {

		switch (dadosAtualizacao.getTipoSolucao()) {
		case BUSCA:
			atualizarTabela(tabelaTempoBusca, portaAlgoritmo, dadosAtualizacao.getNovaMedia());
			break;
		case BUSCA_ARVORE:
			atualizarTabela(tabelaTempoBuscaArvore, portaAlgoritmo, dadosAtualizacao.getNovaMedia());
			break;
		case ORDENACAO:
			atualizarTabela(tabelaTempoOrdena, portaAlgoritmo, dadosAtualizacao.getNovaMedia());
		}

	}

	// Atualiza na Tabela Correspondente ao Tipo de Solução.
	private void atualizarTabela(Hashtable<Integer, Double> tabelaTempo, Integer portaAlgoritmo,
			Double novaMediaTempo) {

		if (tabelaTempo.containsKey(portaAlgoritmo)) {
			tabelaTempo.replace(portaAlgoritmo, novaMediaTempo);
		} else {
			tabelaTempo.put(portaAlgoritmo, novaMediaTempo);
		}
	}
	
	private int getPort(TipoRequisicao requisicao) {

		switch (requisicao) {
		case BUSCA:
			return getMelhorAlgoritmo(tabelaTempoBusca);
		case ORDENACAO:
			return getMelhorAlgoritmo(tabelaTempoOrdena);
		case BUSCA_ARVORE:
			return getMelhorAlgoritmo(tabelaTempoBuscaArvore);
		default:
			throw new ArrayIndexOutOfBoundsException("Enum fora de Requisicao");
		}
	}
	
	private int getMelhorAlgoritmo(Hashtable<Integer, Double> tabelaTempo) {

		double menor = 0;
		int key = 0;

		for (int objeto : tabelaTempo.keySet()) {
			if (menor == 0 || menor > tabelaTempo.get(objeto)) {
				menor = tabelaTempo.get(objeto);
				key = objeto;
			}
		}
		return key;
	}
	
	private void sendPacketToClient(DatagramPacket sendPacket) throws IOException {
		socket.send(sendPacket);
	}
	
	private void gerenciaProximoAlgoritmo(DatagramPacket receivePacket, TipoRequisicao tipoRequisicao) throws IOException {
		int port = receivePacket.getPort();
		switch (tipoRequisicao) {
		case BUSCA:
			enviaProximoAlgoritmo(receivePacket, tabelaTempoBusca, port);
		case ORDENACAO:
			enviaProximoAlgoritmo(receivePacket, tabelaTempoOrdena, port);
		case BUSCA_ARVORE:
			enviaProximoAlgoritmo(receivePacket, tabelaTempoBuscaArvore, port);
		default:
			throw new ArrayIndexOutOfBoundsException("Enum fora de Requisicao");
		}
	}
	
	private void enviaProximoAlgoritmo(DatagramPacket receivePacket, Hashtable<Integer, Double> tabelaTempo, int portaAlgoritmo) throws IOException {
		sendPacketToClient(new DatagramPacket(receivePacket.getData(), receivePacket.getLength(),
				receivePacket.getAddress(), getProximoAlgoritmo(tabelaTempo, portaAlgoritmo)));		
	}
	
	private int getProximoAlgoritmo(Hashtable<Integer, Double> tabelaTempo, int ultimoAlgoritmo) {
		int port = tabelaTempo.entrySet().iterator().next().getKey();
		
		if (port == ultimoAlgoritmo) {
			return tabelaTempo.entrySet().iterator().next().getKey();
		}
		
		return port;		
	}
}
