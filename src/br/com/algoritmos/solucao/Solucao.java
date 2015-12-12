package br.com.algoritmos.solucao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.requisicao.Requisicao;
import br.com.algritmos.util.RedeUtil;

public abstract class Solucao implements Runnable{
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
			System.exit( 1 );
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
		for(long tempo : listaTempos){
			soma += tempo;
		}
		if(listaTempos.size() != 0)
			mediaGeral = (double) (soma / listaTempos.size());
		return mediaGeral;
	}
	
	public void adicionarTempoDuracao(Date dataInicial, Date dataFinal){
		//in milliseconds
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
	
	public DatagramPacket waitForPackets()
    {
		byte[] data = new byte[ 100 ];
		DatagramPacket receivePacket = new DatagramPacket( data, data.length );
		try
		{
			socket.receive( receivePacket );
		}
		catch ( IOException ioException )
		{
			ioException.printStackTrace();
        	System.exit(1);
     	}
		return receivePacket;
   }
	
	protected <T> void sendPacketToClient( Requisicao<T> obj ) throws IOException
	{
		byte[] data = RedeUtil.serializar(obj);
		DatagramPacket sendPacket = new DatagramPacket(data, data.length, obj.getDados().getIp(), obj.getDados().getPorta());
		socket.send( sendPacket );
	}
}
