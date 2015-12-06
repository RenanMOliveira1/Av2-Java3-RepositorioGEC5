package br.com.algoritmos.solucao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;

public abstract class Solucao {
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
	
	protected void sendPacketToClient( DatagramPacket receivePacket ) throws IOException
	{
		DatagramPacket sendPacket = new DatagramPacket( 
		receivePacket.getData(), receivePacket.getLength(), 
		receivePacket.getAddress(), receivePacket.getPort() );
		socket.send( sendPacket );
	}

	protected <T> byte[] serializar(ArrayList<T> obj) throws IOException {
		  ByteArrayOutputStream b = new ByteArrayOutputStream();
		  ObjectOutputStream o = new ObjectOutputStream(b);
		  o.writeObject(obj);
		  o.close();
		  return b.toByteArray();
	}
	
	protected Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		  ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		  ObjectInputStream o = new ObjectInputStream(b);
		  o.close();
		  return o.readObject();
	}
}
