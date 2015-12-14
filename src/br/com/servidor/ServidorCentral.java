package br.com.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Hashtable;

import br.com.cliente.requisicao.DadosClient;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
import br.com.util.RedeUtil;
/**
 * Esta classe representa um servidor central.
 * 
 * Classe <code>ServidorCentral</code>
 * 
 * @author Renan
 * @author Tiago
 * @author Luis Carlos
 * @author Yasmin Farias
 * @version 1.0 (12/12/2015)
 */
public class ServidorCentral implements Runnable {
	
	/** instancia servidorCentral */
	private volatile static ServidorCentral servidor = null;
	/** socket */
	private DatagramSocket socket;
	
	/** porta */
	private int porta;
	
	/** tabela tempo busca */
	private Hashtable<Integer, Double> tabelaTempoBusca;
	
	/** tabela tempo ordena */
	private Hashtable<Integer, Double> tabelaTempoOrdena;
	
	/** tabela tempo busca arvore */
	private Hashtable<Integer, Double> tabelaTempoBuscaArvore;

	/**
	 * Intancia um novo servidor central já instanciando as tabelas de tempo
	 * Seu construtor é privado para implementar o padrão singleton
	 */
	private ServidorCentral(int _porta) {
		tabelaTempoOrdena = new Hashtable<Integer, Double>();
		tabelaTempoBusca = new Hashtable<Integer, Double>();
		tabelaTempoBuscaArvore = new Hashtable<Integer, Double>();
		porta = _porta;
	}
	
	/**
	 * Pega a intancia da classe, já que o construtor é privado.
	 * Faz isso para implementar o padrão Singleton
	 * 
	 * @return ServidorCentral
	 */
	public static ServidorCentral getInstancia(int porta) {
		if (servidor == null) {
			synchronized (ServidorCentral.class) {
				if (servidor == null)
					return new ServidorCentral(porta);
			}
		}
		return servidor;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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

	/**
	 * Instancia um novo socket.
	 * 
	 * @throws SocketException
	 */
	private void inciarServidor() throws SocketException {
		socket = new DatagramSocket(porta);
	}
	/**
	 * Fica esperando o recebimento de pacotes para enviar ao cliente 
	 */
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

	/**
	 * Atravéz do pacote e do tipo de requisição, 
	 * envia o pacote para o cliente.
	 * 
	 * @param receivePacket
	 * 			pacote
	 * @param requisicao
	 * 			tipo requisicao
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void responderCliente(DatagramPacket receivePacket, TipoRequisicao requisicao)
			throws ClassNotFoundException, IOException {
		Integer porta = getPort(requisicao);
		DadosClient dadosClient = new DadosClient(porta, InetAddress.getLocalHost());
		
		byte[] data = RedeUtil.serializar(dadosClient);
		sendPacketToClient(new DatagramPacket(data, data.length,
				receivePacket.getAddress(), receivePacket.getPort()));
	}

	/**
	 * Autaliza a tabela de tempo dos algoritmos de acordo com
	 * a porta do algoritmo e os dados para a atualização.
	 * 
	 * @param portaAlgoritmo
	 * 			porta algoritmo
	 * @param dadosAtualizacao
	 * 			dados atualização
	 */
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

	/**
	 * Atualiza na Tabela Correspondente ao Tipo de Solução.
	 * 
	 * @param tabelaTempo
	 * 			tabela tempo
	 * @param portaAlgoritmo
	 * 			porta algoritmo
	 * @param novaMediaTempo
	 * 			nova media tempo
	 */
	private void atualizarTabela(Hashtable<Integer, Double> tabelaTempo, Integer portaAlgoritmo,
			Double novaMediaTempo) {

		if (tabelaTempo.containsKey(portaAlgoritmo)) {
			tabelaTempo.replace(portaAlgoritmo, novaMediaTempo);
		} else {
			tabelaTempo.put(portaAlgoritmo, novaMediaTempo);
		}
	}
	
	/**
	* Obtem a porta de acordo com o tipo de requisição
	* 
	* @param requisicao
	* 			tipo requisicao
	* @return porta
	*/
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
	
	/**
	* Verifica qual algoritmo da requisicao possui o melhor
	* tempo.
	* 
	* @param tabelaTempo
	* 				tabela tempo
	* @return melhor algoritmo
	*/
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
	
	/**
	* Envia dados para um cliente através de um pacote.
	* 
	* @param sendPacket
	* 			pacote
	* @throws IOException
	*/
	private void sendPacketToClient(DatagramPacket sendPacket) throws IOException {
		socket.send(sendPacket);
	}
	
	/**
	 * Dependendo do tipo de requisição, envia para o algoritmo seguinte 
	 * da tabela de tempo e envia para o cliente.
	 * 
	 * @param receivePacket
	 * 			pacote
	 * @param tipoRequisicao
	 * 			tipo requisicao
	 * 
	 * @throws IOException
	 */
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
	
	/**
	 * Envia o pacote para o próximo algoritmo da tabela.
	 * 
	 * @param receivePacket
	 * 			pacote
	 * @param tabelaTempo
	 * 			tabela tempo
	 * @param portaAlgoritmo
	 * 			porta do algoritmo
	 * 
	 * @throws IOException
	 */
	private void enviaProximoAlgoritmo(DatagramPacket receivePacket, Hashtable<Integer, Double> tabelaTempo, int portaAlgoritmo) throws IOException {
		
		byte[] data = RedeUtil.serializar(getProximoAlgoritmo(tabelaTempo, portaAlgoritmo));
		sendPacketToClient(new DatagramPacket(data, data.length,
				receivePacket.getAddress(), receivePacket.getPort()));		
	}
	
	/**
	 * Atraves da tabela do tempo do algoritmo e do último
	 * algoritmo, procura um próximo algoritmo.
	 * 
	 * @param tabelaTempo
	 * 			tabela tempo
	 * @param ultimoAlgoritmo
	 * 			ultimo algoritmo
	 * @return dados do algoritmo
	 * @throws UnknownHostException
	 */
	private DadosClient getProximoAlgoritmo(Hashtable<Integer, Double> tabelaTempo, int ultimoAlgoritmo) throws UnknownHostException {
		int port = tabelaTempo.entrySet().iterator().next().getKey();
		
		if (port == ultimoAlgoritmo) {
			port = tabelaTempo.entrySet().iterator().next().getKey();
		}
		
		DadosClient dadosClient = new DadosClient(port, InetAddress.getLocalHost());
		
		return dadosClient;		
	}
}
