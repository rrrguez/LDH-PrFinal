package main.java;

import java.util.List;

/**
 * Clase que representa un grafo, definido por un conjunto de vértices y aristas.
 * Permite representar la estructura de un grafo no dirigido con peso en las aristas.
 *
 * @author Tu Nombre
 * @version 1.0
 * @since 2023-11-07
 */
public class Grafo {
    /**
     * Número de vértices en el grafo.
     */
    private int nV;

    /**
     * Lista de vértices del grafo.
     */
    private List<Vertice> vertices;

    /**
     * Lista de aristas del grafo.
     */
    private List<Aresta> arestas;

    /**
     * Construye un grafo con un número específico de vértices, una lista de vértices y una lista de aristas.
     *
     * @param nV El número de vértices del grafo.
     * @param vertices La lista de vértices del grafo.
     * @param arestas La lista de aristas del grafo.
     */
    public Grafo(int nV, List<Vertice> vertices, List<Aresta> arestas) {
        this.nV = nV;
        this.vertices = vertices;
        this.arestas = arestas;
    }

    /**
     * Obtiene el número de vértices en el grafo.
     *
     * @return El número de vértices.
     */
    public int getnVertices() {
        return nV;
    }

    /**
     * Obtiene la lista de vértices del grafo.
     *
     * @return La lista de vértices.
     */
    public List<Vertice> getVertices() {
        return vertices;
    }

    /**
     * Obtiene la lista de aristas del grafo.
     *
     * @return La lista de aristas.
     */
    public List<Aresta> getArestas() {
        return arestas;
    }

    /**
     * Busca y retorna un vértice en el grafo por su identificador.
     *
     * @param identificador El identificador del vértice a buscar.
     * @return El vértice si es encontrado, de lo contrario retorna null.
     */
    public Vertice getVertice(int identificador){
        for (Vertice v: vertices){
            if (v.getId() == identificador){
                return v;
            }
        }
        return null;
    }

    /**
     * Busca y retorna una arista en el grafo que conecta dos vértices identificados por sus IDs.
     * La búsqueda no considera la dirección de la arista.
     *
     * @param v1 El identificador del primer vértice de la arista.
     * @param v2 El identificador del segundo vértice de la arista.
     * @return La arista si es encontrada, de lo contrario retorna null.
     */
    public Aresta getAresta(int v1, int v2){
        for (Aresta a: arestas){
            if ((a.getV1().getId() == v1 && a.getV2().getId() == v2)
                    || (a.getV2().getId() == v1 && a.getV1().getId() == v2)){
                return a;
            }
        }
        return null;
    }
}
