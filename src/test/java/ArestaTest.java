package test.java;

import main.java.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ArestaTest {

    @Test
    public void testSetV1() {
        // Arrange
        List<Vertice> vertices = new ArrayList<>();
        vertices.add(new Vertice(1));
        vertices.add(new Vertice(2));
        vertices.add(new Vertice(3));

        Aresta aresta = new Aresta(vertices.get(0), vertices.get(1), 2.0F);
        Vertice vertice = new Vertice(4);

        // Act
        aresta.setV1(vertice);

        // Assert
        assertEquals(vertice, aresta.getV1());
    }
    @Test
    public void testSetV2() {
        // Arrange
        List<Vertice> vertices = new ArrayList<>();
        vertices.add(new Vertice(1));
        vertices.add(new Vertice(2));
        vertices.add(new Vertice(3));

        Aresta aresta = new Aresta(vertices.get(0), vertices.get(1), 2.0F);
        Vertice vertice = new Vertice(4);

        // Act
        aresta.setV2(vertice);

        // Assert
        assertEquals(vertice, aresta.getV2());
    }
}
