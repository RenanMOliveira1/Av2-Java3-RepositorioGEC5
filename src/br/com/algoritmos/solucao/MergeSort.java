package br.com.algoritmos.solucao;

import java.util.ArrayList;
import java.util.Date;

import br.com.algoritmos.ordenacao.IOrdenavel;

public class MergeSort<T extends Comparable<T>> extends Solucao implements IOrdenavel<T>{
	
	ArrayList<T> listaDesordenada;
	
	protected MergeSort(String _nomeSolucao) {
		super(_nomeSolucao);	
	}

	public ArrayList<T> ordernarLista(ArrayList<T> lista) {
		Date dataInicial = new Date();
		setOcupado(true);
		listaDesordenada = lista;
		sort(listaDesordenada);
		setOcupado(false);
		Date dataFinal = new Date();
		adicionarTempoDuracao(dataInicial, dataFinal);

		return listaDesordenada;
	}
	
	public void sort(ArrayList<T> lista) {
        doMergeSort(0, lista.size() - 1);
    }
 
    private void doMergeSort(int lowerIndex, int higherIndex) {
         
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            
            doMergeSort(lowerIndex, middle);
            
            doMergeSort(middle + 1, higherIndex);
            
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
 
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
 
    	ArrayList<T> listaTemporaria = new ArrayList<T>();
    	listaTemporaria = listaDesordenada;
    	
        int menorIndice = lowerIndex;
        int indiceMeio = middle + 1;
        int menorIndice2 = lowerIndex;
        while (menorIndice <= middle && indiceMeio <= higherIndex) {
            if (listaTemporaria.get(menorIndice).compareTo(listaTemporaria.get(indiceMeio)) <= 0) {
            	listaDesordenada.remove(menorIndice2);
            	listaDesordenada.add(menorIndice2, listaTemporaria.get(menorIndice));
                menorIndice++;
            } else {
            	listaDesordenada.remove(menorIndice2);
            	listaDesordenada.add(menorIndice2, listaTemporaria.get(indiceMeio));
                indiceMeio++;
            }
            menorIndice2++;
        }
        while (menorIndice <= middle) {
        	listaDesordenada.remove(menorIndice2);
        	listaDesordenada.add(menorIndice2, listaTemporaria.get(menorIndice));
            menorIndice2++;
            menorIndice++;
        }
 
    }

}
