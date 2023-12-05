package test.java;

import main.java.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class MetGraspTest {
    @Test
    void testMetGraspConstructor() {
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

        int graspMax = 5;
        double alfa = 0.75;

        // Act
        MetGrasp metGrasp = new MetGrasp(grafo, graspMax, alfa);

        // Assert
        assertEquals(graspMax, metGrasp.getGraspMax());
        assertEquals(grafo, metGrasp.getBuiltGrafo());
        assertEquals(alfa, metGrasp.getAlfa());
    }
    @Test
    void testRun() {
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

        // Crear una instancia de MetGrasp con el grafo de prueba
        MetGrasp metGrasp = new MetGrasp(grafo, 10, 0.5);

        // Ejecutar el método run
        metGrasp.run();
    }
}
