package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MetGrasp {
    // Variables para establecer el número máximo de iteraciones, el grafo sobre el cual operar y el parámetro alfa para la construcción de soluciones aleatorias.
    private int graspMax;
    private Grafo builtGrafo;
    private double alfa;
    private SecureRandom random = new SecureRandom();
    // Logger para mostrar por pantalla mensajes
    Logger logger = Logger.getLogger(getClass().getName());
    /**
     * Constructor para inicializar el algoritmo GRASP con un grafo, un número máximo de iteraciones y un factor alfa.
     *
     * @param g         El grafo sobre el que se aplicará GRASP.
     * @param graspMax  Número máximo de iteraciones para GRASP.
     * @param alfa      Parámetro alfa para la construcción de soluciones aleatorias.
     */
    public MetGrasp(Grafo g, int graspMax, double alfa) {
        this.graspMax = graspMax;
        this.builtGrafo = g;
        this.alfa = alfa;
    }

    /**
     * Método principal que ejecuta el algoritmo GRASP.
     * Realiza las iteraciones GRASP construyendo soluciones aleatorias y mejorándolas con VND.
     */
    public void run() {
        // Inicializar las soluciones y las variables de tiempo
        int[] solucao = new int[builtGrafo.getnVertices()];
        int[] solconstruida;
        int[] solAux;
        Instant start;
        Instant finish;
        long timeElapsed;

        // Inicializar el mejor valor a infinito para minimizar el costo.
        double bestVal = Double.MAX_VALUE;

        // Registrar el tiempo de inicio del algoritmo.
        start = Instant.now();
        // Ejecutar el ciclo GRASP para el número máximo de iteraciones.
        for (int i = 0; i < graspMax; ++i) {
            // Construir una solución aleatoria y mejorarla con VND.
            solconstruida = construirSolucaoAleatoria(1, alfa);
            solAux = new MetVND(builtGrafo, solconstruida).run();

            // Imprimir el valor de la solución construida.
            String solucion = "Valor da solucao construtiva=> " + String.valueOf(valorSolucao(solconstruida));
            logger.info(solucion);

            // Si la solución es mejor que la mejor actual, actualizar la mejor solución.
            if (valorSolucao(solAux) < bestVal) {
                bestVal = valorSolucao(solAux);
                solucao = solAux;
            }
        }
        // Registrar el tiempo de finalización y calcular la duración.
        finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toMillis();

        // Imprimir el tiempo de ejecución y el valor de la mejor solución encontrada.
        String exeTempo = " Tempo da solucao meta heuristica=> " + String.valueOf(timeElapsed);
        logger.info(exeTempo);

        escreverArray(solucao);

        String valorSolucao = " Valor da solucao => " + String.valueOf(valorSolucao(solucao));
        logger.info(valorSolucao);
    }

    /**
     * Construye una solución aleatoria para el grafo.
     *
     * @param id    El vértice de inicio para la construcción de la solución.
     * @param alfa  Parámetro alfa para controlar la aleatoriedad en la construcción de la solución.
     * @return      Un array que representa la solución construida.
     */
    public int[] construirSolucaoAleatoria(int id, double alfa) {
        int count = 0;
        Instant start = Instant.now();
        int[] solucao = new int[builtGrafo.getnVertices()];
        List<double[]> valSolucoespossiveis;

        // Inicializar los candidatos para el punto de partida.
        valSolucoespossiveis = inicializarCandidatos(id);

        try {
            // Mientras haya candidatos disponibles, seleccionar uno y construir la solución.
            while (!valSolucoespossiveis.isEmpty()) {
                int sig = valSolucoespossiveis.size() * (int) alfa + 1;
                int rand = this.random.nextInt(sig);
                double[] aux = valSolucoespossiveis.get(rand);
                solucao[count] = (int) aux[1];
                valSolucoespossiveis.remove(aux);
                ++count;
            }
        } catch (IndexOutOfBoundsException e) {
            // Si no hay más candidatos disponibles, seleccionar el último.
            double[] aux = valSolucoespossiveis.get(0);
            solucao[count] = (int) aux[1];
            valSolucoespossiveis.remove(aux);
            ++count;
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toNanos();

        // Imprimir el tiempo que tomó construir la solución aleatoria.
        String tiempo = String.valueOf(timeElapsed);
        String tempoSolucao = " Tempo da solucao construtiva (nanos)=> " + tiempo;
        logger.info(tempoSolucao);
        return solucao;
    }

    /**
     * Inicializa los candidatos con sus costos desde un vértice de inicio.
     *
     * @param id    Identificador del vértice de inicio.
     * @return      Lista de arrays con los costos y los identificadores de cada vértice candidato.
     */
    private List<double[]> inicializarCandidatos(int id) {
        List<double[]> custosCandidatos = new ArrayList<>();
        double[][] aux = new double[builtGrafo.getnVertices()][2];
        int count = 0;

        // Llenar la lista auxiliar con los pesos y los identificadores de los vértices.
        for (Vertice v : builtGrafo.getVertices()) {
            if (id == v.getId()) {
                aux[count][0] = 0;
                aux[count][1] = v.getId();
                ++count;
            } else {
                aux[count][0] = builtGrafo.getAresta(id, v.getId()).getPeso();
                aux[count][1] = v.getId();
                ++count;
            }
        }

        // Ordenar los candidatos por su costo utilizando una expresión lambda.
        java.util.Arrays.sort(aux, new java.util.Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        });

        for (double[] d: aux){
            Collections.addAll(custosCandidatos, d);
        }

        return custosCandidatos;
    }

    /**
     * Calcula el valor de una solución dada, sumando los pesos de las aristas que conectan los vértices.
     *
     * @param s La solución a evaluar.
     * @return  El costo total de la solución.
     */
    private double valorSolucao(int[] s) {
        double solucao = 0;

        // Sumar los pesos de las aristas entre vértices consecutivos en la solución.
        for (int c = 1; c < s.length; ++c) {
            solucao += builtGrafo.getAresta(s[c - 1], s[c]).getPeso();
        }
        // Añadir el peso de la arista que cierra el ciclo, conectando el último y el primer vértice.
        solucao += builtGrafo.getAresta(s[s.length - 1], s[0]).getPeso();

        return solucao;
    }

    /**
     * Imprime una representación en consola de una solución (arreglo de enteros).
     *
     * @param a La solución a imprimir.
     */
    private void escreverArray(int[] a) {
        String sol = "";
        for (int i = 0; i < a.length; ++i) {
            sol += String.valueOf(a[i]) + " ";
        }
        String message = "Rota encontrada => " + sol;
        logger.info(message);
        logger.log(Level.FINE, "\n"); // Añadir un salto de línea al final para separar la salida de otras impresiones.
    }
}

