package br.com.algoritmos.busca;

import java.util.ArrayList;

/**
 ** Programa que Implementa o Algoritmo de Busca Ternaria
 *	Interface <code>BuscaTernaria</code>
 *
 *  @author Yasmin Farias
 *  @author Tiago Henrique
 *  @version 1.0 (12/12/2015)
 **/
public class BuscaTernaria<T extends Comparable<T>> {
	
    /** chama a função **/
    public static <T> T busca(ArrayList<T> problema, Comparable valor) {
        return busca(problema, valor, 0, problema.size() - 1);
    }
    
    /** função de busca ternaria **/
    public static <T> T busca(ArrayList<T> problema, Comparable valor, int comeco, int fim) {
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
            return busca (problema, valor, comeco, metade1 - 1);
        /** procura no terceiro pedaço **/
        else if (valor.compareTo(problema.get(metade2)) > 0) 
            return busca (problema, valor, metade2 + 1, fim);
        /** procura no segundo pedaço **/
        else 
            return busca (problema, valor, metade1, metade2);        
    }
}