package main.java;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;


public class MetGrasp {
    // Variables para establecer el número máximo de iteraciones, el grafo sobre el cual operar y el parámetro alfa para la construcción de soluciones aleatorias.
    private int graspMax;
    private Grafo G;
    private double alfa;
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
        this.G = g;
        this.alfa = alfa;
    }

    /**
     * Método principal que ejecuta el algoritmo GRASP.
     * Realiza las iteraciones GRASP construyendo soluciones aleatorias y mejorándolas con VND.
     */
    public void run() {
        // Inicializar las soluciones y las variables de tiempo
        int[] solucao = new int[G.getnVertices()];
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
            solAux = new MetVND(G, solconstruida).run();

            // Imprimir el valor de la solución construida.
            logger.info(" Valor da solucao construtiva=> ");
            String builtSolution = String.valueOf(valorSolucao(solconstruida));
            logger.info(builtSolution);

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
        logger.info(" Tempo da solucao meta heuristica=> ");
        String tiempo = String.valueOf(timeElapsed);
        logger.info(tiempo);
        logger.info("Rota encontrada => ");
        escreverArray(solucao);
        logger.info(" Valor da solucao => ");
        String solucion = String.valueOf(valorSolucao(solucao));
        logger.info(solucion);
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
        int[] solucao = new int[G.getnVertices()];
        List<double[]> valSolucoespossiveis;

        // Inicializar los candidatos para el punto de partida.
        valSolucoespossiveis = inicializarCandidatos(id);

        try {
            // Mientras haya candidatos disponibles, seleccionar uno y construir la solución.
            while (!valSolucoespossiveis.isEmpty()) {
                double[] aux = valSolucoespossiveis.get((int) (Math.random() * ((int) valSolucoespossiveis.size() * alfa + 1)));
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
        logger.info(" Tempo da solucao construtiva (nanos)=> ");
        String tiempo = String.valueOf(timeElapsed);
        logger.info(tiempo);
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
        double[][] aux = new double[G.getnVertices()][2];
        int count = 0;

        // Llenar la lista auxiliar con los pesos y los identificadores de los vértices.
        for (Vertice v : G.getVertices()) {
            if (id == v.getId()) {
                aux[count][0] = 0;
                aux[count][1] = v.getId();
                ++count;
            } else {
                aux[count][0] = G.getAresta(id, v.getId()).getPeso();
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
            custosCandidatos.add(d);
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
            solucao += G.getAresta(s[c - 1], s[c]).getPeso();
        }
        // Añadir el peso de la arista que cierra el ciclo, conectando el último y el primer vértice.
        solucao += G.getAresta(s[s.length - 1], s[0]).getPeso();

        return solucao;
    }

    /**
     * Imprime una representación en consola de una solución (arreglo de enteros).
     *
     * @param a La solución a imprimir.
     */
    private void escreverArray(int[] a) {
        for (int i = 0; i < a.length; ++i) {
            String sol = String.valueOf(a[i]) + " ";
            logger.info(sol);
        }
        logger.info(""); // Añadir un salto de línea al final para separar la salida de otras impresiones.
    }
}

