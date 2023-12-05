package test.java;

import main.java.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReaderTest {

    @Test
    void testReaderConstructor() {
        // Arrange
        String nomeArquivo = "bayg29.txt";

        // Act
        Reader reader = new Reader(nomeArquivo);

        // Assert
        assertNotNull(reader.getArestas());
        assertNotNull(reader.getVertices());
        // As the method leArquivo is called in the constructor, its behavior is indirectly tested here
    }
}