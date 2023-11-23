package main.java;


import java.util.Arrays;



/**
 * @class MetVND
 * @brief Implementa la heurística de Descenso del Vecindario Variable (VND) para problemas de optimización en grafos.
 */
public class MetVND {
    /**
     * Representación del grafo donde se realizará la optimización.
     */
    private Grafo grafo;

    /**
     * Solución construida que será mejorada mediante VND.
     */
    private int[] solConstruida;

    /**
     * Constructor de la clase.
     * @param G El grafo sobre el cual se aplicará VND.
     * @param solucaoConstruida Solución inicial sobre la cual se mejorará.
     */
    public MetVND(Grafo builtGrafo, int[] solucaoConstruida) {
        this.grafo = builtGrafo;
        this.solConstruida = solucaoConstruida;
    }

    /**
     * Método principal que ejecuta el proceso de VND.
     * @return Retorna la mejor solución vecina encontrada.
     */
    public int[] run(){
        int[] melhorVizinho = this.solConstruida;
        int[] sTemp;
        int r = 2;
        int k = 1;

        while (k <= r){
            if (k == 1){
                sTemp = explorarSwap(melhorVizinho);
            }else{
                sTemp = explorarDoisOpt(melhorVizinho);
            }

            if (valorSol(sTemp) < valorSol(melhorVizinho)){
                melhorVizinho = sTemp;
                k = 1;
            }else{
                ++k;
            }
        }

        return melhorVizinho;
    }

    /**
     * Calcula el valor de la solución dada.
     * @param s Array que representa la solución cuyo valor será calculado.
     * @return Retorna el valor total de la solución.
     */
    public double valorSol(int[] s) {
        double solucao = 0;
        for (int c = 1; c < s.length; ++c) {
            solucao += grafo.getAresta(s[c-1], s[c]).getPeso();
        }
        solucao += grafo.getAresta(s[s.length-1], s[0]).getPeso();

        return solucao;
    }

    /**
     * Explora el vecindario mediante el intercambio de posiciones (swap) entre dos vértices.
     * @param s Solución actual.
     * @return Retorna la mejor solución vecina encontrada por swap.
     */
    private int[] explorarSwap(int[] s){
        int[] bestV = s;
        int[] sAux;
        double melhorSolucao = valorSol(s);

        for (int c = 0; c < s.length; ++c){
            for (int k = c+1; k < s.length; ++k){
                sAux = swap(c, k, s);

                if (valorSol(sAux) < melhorSolucao){
                    melhorSolucao = valorSol(sAux);
                    bestV = sAux;
                }
            }
        }

        return bestV;
    }

    /**
     * Explora el vecindario utilizando la heurística 2-opt.
     * @param s Solución actual.
     * @return Retorna la mejor solución vecina encontrada por 2-opt.
     */
    private int[] explorarDoisOpt(int[] s){
        int[] bestViz = s;
        int[] sAux;
        double bestSol = valorSol(s);

        for (int c = 0; c < s.length; ++c){
            for (int k = c+1; k < s.length; ++k){
                sAux = doisOpt(c, k, s);

                if (valorSol(sAux) < bestSol){
                    bestSol = valorSol(sAux);
                    bestViz = sAux;
                }
            }
        }

        return bestViz;
    }

    /**
     * Intercambia dos vértices en la solución dada.
     * @param i Índice del primer vértice a intercambiar.
     * @param j Índice del segundo vértice a intercambiar.
     * @param s Solución actual.
     * @return Retorna la solución con los dos vértices intercambiados.
     */
    private int[] swap(int i, int j, int[] s){
        int[] arrayAux = s.clone();
        int aux = arrayAux[i];
        arrayAux[i] = arrayAux[j];
        arrayAux[j] = aux;

        return arrayAux;
    }

    /**
     * Aplica la heurística 2-opt para invertir un segmento de la solución.
     * @param i Índice inicial del segmento a invertir.
     * @param j Índice final del segmento a invertir.
     * @param s Solución actual.
     * @return Retorna la solución con el segmento invertido.
     */
    private int[] doisOpt(int i, int j, int[] s){
        int[] subArray;
        int[] sAux = s.clone();
        int count = 0;

        subArray = Arrays.copyOfRange(sAux, i, j+1);
        inverterArray(subArray);

        for (int k = i; k <= j; ++k){
            sAux[k] = subArray[count];
            ++count;
        }
        return sAux;
    }

    /**
     * Invierte un array dado.
     * @param a El array que será invertido.
     */
    private void inverterArray(int[] a){
        int count = 0;
        int[] aux = a.clone();
        for (int i = a.length-1; i >= 0; --i){
            a[i] = aux[count];
            ++count;
        }
    }
}
