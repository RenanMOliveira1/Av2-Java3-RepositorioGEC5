package br.com.algoritmos.solucao;

import java.util.ArrayList;
import java.util.Date;

public class Solucao {
	protected String nomeSolucao;
	protected Double mediaGeral;
	protected boolean ocupado;
	protected ArrayList<Long> listaTempos;
	
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
	
	
}
