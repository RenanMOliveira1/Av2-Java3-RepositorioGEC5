package br.com.algoritmos.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Hashtable;

import br.com.algoritmos.cliente.requisicao.TipoRequisicao;
import br.com.algoritmos.executavel.Main;
import br.com.algritmos.util.RedeUtil;

/**
 * @author Renan
 * @author Tiago
 *
 */
public class ServidorCentral {
	private volatile static ServidorCentral servidor = null;

	private DatagramSocket socket;
	private Hashtable<Integer, Double> tabelaTempoBusca;
	private Hashtable<Integer, Double> tabelaTempoOrdena;
	private Hashtable<Integer, Double> tabelaTempoBuscaArvore;

	private ServidorCentral() {
		tabelaTempoOrdena = new Hashtable<Integer, Double>();
		tabelaTempoBusca = new Hashtable<Integer, Double>();

		try {
			socket = new DatagramSocket(Main.PORTA);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public static ServidorCentral getInstancia() {
		if (servidor == null) {
			synchronized (ServidorCentral.class) {
				if (servidor == null)
					return new ServidorCentral();
			}
		}

		return servidor;
	}

	public void waitForPackets() {
		
		while (true) {
			try {
				byte[] data = new byte[50000];
				DatagramPacket receivePacket = new DatagramPacket(data, data.length);
				socket.receive(receivePacket);

				Object object = RedeUtil.deserialize(receivePacket.getData());

				if (object instanceof TipoRequisicao) {
					responderCliente(receivePacket, (TipoRequisicao) object);
				} else if (object instanceof DadosAtualizacao) {
					atualizarTabela(receivePacket.getPort(), (DadosAtualizacao) object);
				}

			} catch (IOException ioException) {
				ioException.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
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
	
	//Atualiza na Tabela Correspondente ao Tipo de Solução.
	private void atualizarTabela(Hashtable<Integer, Double> tabelaTempo, Integer portaAlgoritmo, Double novaMediaTempo) {
	   
	   if (tabelaTempo.containsKey(portaAlgoritmo)) {
		   tabelaTempo.remove(portaAlgoritmo, novaMediaTempo);
	   } else {
		   tabelaTempo.put(portaAlgoritmo, novaMediaTempo);
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

	private void sendPacketToClient(DatagramPacket sendPacket) throws IOException {
		socket.send(sendPacket);
	}
}
