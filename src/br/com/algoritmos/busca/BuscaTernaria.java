package br.com.algoritmos.busca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.algoritmos.ordenacao.QuickSort;
import br.com.algoritmos.solucao.Solucao;
import br.com.cliente.requisicao.Requisicao;
import br.com.cliente.requisicao.TipoRequisicao;

/**
 *  Classe que implementa o Algoritmo de Busca Ternaria.
 *  Ele utiliza a mesma lógica da busca binária, só que
 *  dividindo o array em três partes.
 *  
 *	Classe <code>BuscaTernaria</code>
 *
 *  @author Yasmin Farias
 *  @author Tiago Henrique
 *  @version 1.0 (12/12/2015)
 */
public class BuscaTernaria<T extends Comparable<T>> extends Solucao implements IBuscavel<T> {
	
	/**
	 * Instancia uma nova Busca Ternaria
	 */
	public BuscaTernaria() {
		super("Busca Ternária", TipoRequisicao.BUSCA, 50002);
		setMediaGeral(4.0);
	}
	
	/**
	 * chama a função que faz a busca tarnaria
	 * 
	 * @param problema
	 * 			problema
	 * @param valor
	 * 			valor
	 * @return resultado
	 */
    public static <T> T buscaTernaria(ArrayList<T> problema, Comparable valor) {
        return buscaTernaria(problema, valor, 0, problema.size() - 1);
    }
    
    /**
     * Função que faz a busca ternaria
     * 
     * @param problema
     * 			problema
     * @param valor
     * 			valor
     * @param comeco
     * 			comeco
     * @param fim
     * 			fim
     * 
     * @return resultado
     */
	private static <T> T buscaTernaria(ArrayList<T> problema, Comparable valor, int comeco, int fim) {
        if (comeco > fim) 
            return (T) null;       
 
        /** Primeiro pedaço: adiciona 1/3 do tamanho para começar **/
        Integer metade1 =  comeco + (fim - comeco) / 3;        
        /** Segundo pedaço: adiciona 2/3 of do tamanho para começar **/
        Integer metade2 = comeco + (2 * (fim - comeco) / 3);
 
        if (valor.compareTo(metade1) == 0) 
            return (T) metade1;
 
        else if (valor.compareTo(metade2) == 0) 
            return (T) metade2;
        
        /** procura no primeiro pedaço **/
        else if (valor.compareTo(problema.get(metade1)) < 0) 
            return buscaTernaria (problema, valor, comeco, metade1 - 1);
        /** procura no terceiro pedaço **/
        else if (valor.compareTo(problema.get(metade2)) > 0) 
            return buscaTernaria (problema, valor, metade2 + 1, fim);
        /** procura no segundo pedaço **/
        else 
            return buscaTernaria (problema, valor, metade1, metade2);        
    }
    
    /*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		while(true) {
			Requisicao requisicao = receberRequisicao(); 
 			buscarElemento(requisicao.getListaValores(), (T) requisicao.getValor()); 
 			requisicao.setPosicao((Integer)buscarElemento(requisicao.getListaValores(), (T) requisicao.getValor())); 
 			enviarRequisicao(requisicao); 
			
		}
	}

    /*
     * (non-Javadoc)
     * @see br.com.algoritmos.busca.IBuscavel#buscarElemento(java.util.Collection, java.lang.Comparable)
     */
	@Override
	public <T> T buscarElemento(Collection<T> colecao, Comparable valor) {
		
		ArrayList<T> lista = (ArrayList<T>) colecao;
		
		QuickSort<T> ordenador = new QuickSort<T>();
		
		ordenador.ordernarLista(lista); 
		
		Date dataInicio = new Date();
		setOcupado(true);
		T resultado = buscaTernaria(lista, valor);
		setOcupado(false);
		Date dataFim = new Date();
		
		adicionarTempoDuracao(dataInicio, dataFim);
		
		return resultado;
	}
	
}
