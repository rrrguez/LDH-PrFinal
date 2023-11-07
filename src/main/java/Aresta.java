package main.java;

/**
 * Clase que representa una arista en un grafo.
 * La arista conecta dos vértices y tiene un peso asociado.
 *
 * @author Tu Nombre
 * @version 1.0
 * @since 2023-11-07
 */
public class Aresta {

    /**
     * Primer vértice de la arista.
     */
    private Vertice v1;

    /**
     * Segundo vértice de la arista.
     */
    private Vertice v2;

    /**
     * Peso de la arista.
     */
    private float peso;

    /**
     * Constructor para crear una nueva arista con los vértices y peso especificados.
     * @param v1 El primer vértice de la arista.
     * @param v2 El segundo vértice de la arista.
     * @param peso El peso de la arista.
     */
    public Aresta(Vertice v1, Vertice v2, float peso) {
        this.v1 = v1;
        this.v2 = v2;
        this.peso = peso;
    }

    /**
     * Obtiene el primer vértice de la arista.
     * @return El primer vértice de la arista.
     */
    public Vertice getV1() {
        return v1;
    }

    /**
     * Establece un nuevo primer vértice para la arista.
     * @param v1 El nuevo primer vértice de la arista.
     */
    public void setV1(Vertice v1) {
        this.v1 = v1;
    }

    /**
     * Obtiene el segundo vértice de la arista.
     * @return El segundo vértice de la arista.
     */
    public Vertice getV2() {
        return v2;
    }

    /**
     * Establece un nuevo segundo vértice para la arista.
     * @param v2 El nuevo segundo vértice de la arista.
     */
    public void setV2(Vertice v2) {
        this.v2 = v2;
    }

    /**
     * Obtiene el peso de la arista.
     * @return El peso de la arista.
     */
    public float getPeso() {
        return peso;
    }
}
