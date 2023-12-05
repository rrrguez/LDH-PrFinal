package test.java;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import main.java.*;

import java.util.ArrayList;
import java.util.List;

public class GrafoTest {

    @Test
    public void testGetVertice() {
        // Crear un grafo de prueba con algunos vértices
        List<Vertice> vertices = new ArrayList<>();
        vertices.add(new Vertice(1));
        vertices.add(new Vertice(2));
        vertices.add(new Vertice(3));

        Grafo grafo = new Grafo(3, vertices, new ArrayList<>());

        // Probar el método getVertice
        Vertice v = grafo.getVertice(2);

        assertNotNull(v);
        assertEquals(2, v.getId());
    }

    @Test
    public void testGetAresta() {
        // Crear un grafo de prueba con algunas aristas
        List<Vertice> vertices = new ArrayList<>();
        vertices.add(new Vertice(1));
        vertices.add(new Vertice(2));
        vertices.add(new Vertice(3));

        List<Aresta> arestas = new ArrayList<>();
        arestas.add(new Aresta(vertices.get(0), vertices.get(1), 2.0F));
        arestas.add(new Aresta(vertices.get(1), vertices.get(2), 3.0F));

        Grafo grafo = new Grafo(3, vertices, arestas);

        // Probar el método getAresta
        Aresta a = grafo.getAresta(1, 2);

        assertNotNull(a);
        assertEquals(2.0, a.getPeso());
    }

    // Puedes agregar más pruebas según sea necesario
}
