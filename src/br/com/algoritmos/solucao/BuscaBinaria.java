package br.com.algoritmos.solucao;

import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.busca.IBuscavel;

public class BuscaBinaria<T extends Number> extends Solucao implements IBuscavel<T> {
	
	public BuscaBinaria() {
		super("Busca Binária");
	}

	@Override
	public int buscarElemento(ArrayList<T> lista, T valor) {
		Date dataInicial = new Date();		
		setOcupado(true);		
		int posicao = buscaBinaria(lista, valor);		
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);
		
		return posicao;
	}
	
	private int buscaBinaria(ArrayList<T> lista, T valor){
		int inicio = 0;
		int fim = lista.size()-1;
		
		while(inicio <= fim){
			int meio = (inicio+fim)/2;
			int valorMeio = lista.get(meio).intValue();
			
			if(valorMeio == valor.intValue()){
				return meio;
			}
			
			if(valor.intValue() > valorMeio){
				inicio = meio+1;
			} else {
				fim = meio-1;
			}
		}
		
		return -1;
	}
}
