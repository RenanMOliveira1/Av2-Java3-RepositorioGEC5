package br.com.algoritmos.solucao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

import br.com.cliente.requisicao.DadosClient;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;
import br.com.execultavel.MainServidor;
import br.com.servidor.DadosAtualizacao;
import br.com.util.RedeUtil;

/**
 * Classe que representa um algoritmo de solução,
 * seja ordenação ou busca.
 * 
 * Classe<code>Solucao</code>
 * 
 * @author Tiago
 * @author Renan
 * @author Gabriel
 * @author Thaynara
 * @author Luis Carlos
 * @version 1.0 (12/12/2015)
 */
public abstract class Solucao implements Runnable {
	public static final int PORTA = 12345;
	
	/** nome solucao */
	protected String nomeSolucao;
	
	/** Tipo de Solição */
	protected TipoRequisicao tipoSolucao;
	
	/** media geral */
	protected Double mediaGeral;
	
	/** ocupado */
	protected boolean ocupado;
	
	/** list tempos */
	protected ArrayList<Long> listaTempos;
	
	/** socket */
	protected DatagramSocket socket;

	/**
	 * Instancia uma nova solução a partir de um nome e uma porta
	 * 
	 * @param _nomeSolucao
	 * 			nome solucao
	 * @param port
	 * 			porta
	 */
	protected Solucao(String _nomeSolucao, TipoRequisicao _tipoSolucao, int porta) {
		mediaGeral = 0.0;
		ocupado = false;
		nomeSolucao = _nomeSolucao;
		listaTempos = new ArrayList<Long>();
		tipoSolucao = _tipoSolucao;
		
		try {
			socket = new DatagramSocket(porta);
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Instancia uma nova solução a partir de um nome
	 * 
	 * @param _nomeSolucao
	 * 			nome solucao
	 */
	protected Solucao(String _nomeSolucao, TipoRequisicao _tipoSolucao) {
		mediaGeral = 0.0;
		ocupado = false;
		nomeSolucao = _nomeSolucao;
		listaTempos = new ArrayList<Long>();
		tipoSolucao = _tipoSolucao;
	}
	
	/**
	 * Envia por datagrama a atualização da media de tempo
	 * 
	 * @param mediaGeral
	 * 			media geral
	 * @throws IOException
	 */
	private void atualizarServidor(Double mediaGeral) throws IOException {

		byte[] data = RedeUtil.serializar(new DadosAtualizacao(getTipoSolucao(), mediaGeral));
		DatagramPacket packetAtualizacao = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), PORTA);
		
		socket.send(packetAtualizacao);
	}
	
	/**
	 * Adiciona um tempo decorrido a partir
	 * de um tempo inicial e um tempo final
	 * 
	 * @param dataInicial
	 * 				tempo inicial
	 * @param dataFinal
	 * 				tempo final
	 */
	public void adicionarTempoDuracao(Date dataInicial, Date dataFinal) {
		// em millisegundos
		long tempoDuracao = dataFinal.getTime() - dataInicial.getTime();

		listaTempos.add(tempoDuracao);
	}
	
	/**
	 * Obtem media geral
	 * 
	 * @return media geral
	 */
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

	/**
	 * Obtem ocupado
	 * 
	 * @return ocupado
	 */
	public synchronized boolean isOcupado() {
		return ocupado;
	}

	/**
	 * Define ocupado
	 * 
	 * @param ocupado
	 * 			ocupado
	 */
	protected synchronized void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	/**
	 * Obtem lista tempos
	 * 
	 * @return lista tempos
	 */
	public ArrayList<Long> getListaTempos() {
		return listaTempos;
	}

	/**
	 * Define lista tempos
	 * 
	 * @param listaTempos
	 * 			lista tempos
	 */
	public void setListaTempos(ArrayList<Long> listaTempos) {
		this.listaTempos = listaTempos;
	}

	/**
	 * Recebe uma requisição por datagrama
	 * 
	 * @return requisicao
	 */
	public <T> Requisicao<T> receberRequisicao() {		
		byte[] data = new byte[100];
		Requisicao<T> requisicao = null;
		DatagramPacket receivePacket = new DatagramPacket(data, data.length);

		try {
			System.out.println("Algoritmo " + getNomeSolucao() + " Esperando.");
			socket.receive(receivePacket);
			requisicao = (Requisicao) RedeUtil.deserialize(receivePacket.getData());
			
			if (!isOcupado()) {
				enviaInformacaoOcupado();
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return requisicao;		
	}

	/**
	 * Envia uma requisicao passada por parammetro por datagrama
	 * 
	 * @param requisicao
	 * 			requisicao
	 */
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
	
	/**
	 * Envia para o servidor que o algoritmo está ocupado
	 * 
	 */
	public void enviaInformacaoOcupado() {		
		try {
			Requisicao<Integer> requisicao = new Requisicao<Integer>(getTipoSolucao(), "O algoritmo está ocupado");
			requisicao.setDados(new DadosClient(PORTA, InetAddress.getLocalHost(), "Solução"));
			enviarRequisicao(requisicao);
		} catch (UnknownHostException e) {			
			e.printStackTrace();
		}		
	}
	
	public abstract void run();
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Solucao) {
			Solucao solucao = (Solucao) obj;
			return (getNomeSolucao().equals(solucao.getNomeSolucao()));
		}
		return false;
	}
	
	public String getNomeSolucao() {
		return nomeSolucao;
	}

	public void setNomeSolucao(String nomeSolucao) {
		this.nomeSolucao = nomeSolucao;
	}

	public TipoRequisicao getTipoSolucao() {
		return tipoSolucao;
	}

	public void setTipoSolucao(TipoRequisicao tipoSolucao) {
		this.tipoSolucao = tipoSolucao;
	}

	public void setMediaGeral(Double mediaGeral) {
		this.mediaGeral = mediaGeral;
	}
}
