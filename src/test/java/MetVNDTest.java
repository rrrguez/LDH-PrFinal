package test.java;

import main.java.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

public class MetVNDTest {

    @Test
    public void testRun() {
        // Crear un grafo de prueba con algunos vértices y aristas
        List<Vertice> vertices = new ArrayList<>();
        List<Aresta> arestas = new ArrayList<>();

        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);

        Aresta a1 = new Aresta(v1, v2, 2.0F);
        Aresta a2 = new Aresta(v2, v3, 3.0F);
        Aresta a3 = new Aresta(v3, v1, 4.0F);

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);

        arestas.add(a1);
        arestas.add(a2);
        arestas.add(a3);

        Grafo grafo = new Grafo(3, vertices, arestas);

        // Crear una solución de prueba
        int[] solucionInicial = {1, 2, 3};

        // Crear una instancia de MetVND con el grafo y la solución de prueba
        MetVND metVND = new MetVND(grafo, solucionInicial);

        // Ejecutar el método run
        int[] mejorSolucion = metVND.run();

        // Puedes agregar afirmaciones según la lógica de tu aplicación
        assertNotNull(mejorSolucion);
    }

    // Puedes agregar más pruebas según sea necesario
}
