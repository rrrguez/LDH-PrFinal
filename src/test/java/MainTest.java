package test.java;

import main.java.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {
    @Test
    public void testObtainFileName_args() {
        String[] args = new String[]{"bayg29.txt"};
        String expected = "bayg29.txt";
        assertEquals(expected, Main.obtainFileName(args));
    }
    @Test
    public void testObtainFileName_no_args() {
        String simulatedUserInput = "bayg29.txt";
        ByteArrayInputStream test = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(test);

        // Captura la salida esperada del método obtainFileName
        String expectedOutput = "bayg29.txt";

        // Captura la salida del método obtainFileName
        String actualOutput;
        try (Scanner scanner = new Scanner(System.in)) {
            actualOutput = scanner.nextLine();
        }

        // Verifica que el nombre del archivo ingresado coincida con la entrada simulada
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void testCreateGrafoFromReader() {
        Reader reader = new Reader("bayg29.txt");
        assertNotNull(Main.createGrafoFromReader(reader));
    }

    @Test
    public void testInitializeGrasp() {
        List<Vertice> vertices = new ArrayList<>();
        vertices.add(new Vertice(1));
        vertices.add(new Vertice(2));
        vertices.add(new Vertice(3));

        List<Aresta> arestas = new ArrayList<>();
        arestas.add(new Aresta(vertices.get(0), vertices.get(1), 2.0F));
        arestas.add(new Aresta(vertices.get(1), vertices.get(2), 3.0F));

        Grafo grafo = new Grafo(3, vertices, arestas);
        assertNotNull(Main.initializeGrasp(grafo));
    }

    @Test
    public void testExecuteGrasp() {
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
        assertDoesNotThrow(() -> Main.executeGrasp(metGrasp));
    }
}
