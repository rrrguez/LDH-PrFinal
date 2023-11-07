package main.java;


/**
 * @class Vertice
 * @brief Clase que representa un vértice en un grafo.
 */
public class Vertice {
    /**
     * Identificador único para el vértice.
     */
    private int id;

    /**
     * Constructor de la clase Vertice.
     * @param id El identificador único para el nuevo vértice.
     */
    public Vertice(int id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del vértice.
     * @return El identificador del vértice.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece o actualiza el identificador del vértice.
     * @param id El nuevo identificador para este vértice.
     */
    public void setId(int id) {
        this.id = id;
    }
}

