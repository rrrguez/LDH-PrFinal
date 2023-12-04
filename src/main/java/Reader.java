package main.java;


import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Logger para gestionar las excepciones y level para gestionar su nivel
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * @class Reader
 * @brief Clase responsable de leer datos de un archivo para construir un grafo.
 *
 * Esta clase se encarga de leer un archivo que contiene la representación de un grafo
 * y almacenar esa información en estructuras de datos adecuadas.
 */
public class Reader {
    /**
     * Número de vértices en el grafo.
     */
    private int nVertices;

    /**
     * Lista de aristas del grafo.
     */
    private ArrayList<Aresta> arestas;

    /**
     * Lista de vértices del grafo.
     */
    private ArrayList<Vertice> vertices;

    /**
     * Nombre del archivo de donde se lee el grafo.
     */
    private String nomeArquivo;

    /**
     * Constructor de la clase Reader.
     * @param nomeArquivo Nombre del archivo a leer.
     */
    public Reader(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
        leArquivo();
    }

    /**
     * Método privado para leer el archivo y construir las listas de aristas y vértices.
     *
     * Este método inicializa el escáner para leer el archivo, procesa la información y
     * captura excepciones si el archivo no se encuentra.
     */
    private void leArquivo() {

        Scanner scanner;
        String temp;
        int i = 0;
        int j = i+1;
        FileReader r = null;
        try {
            r = new FileReader(nomeArquivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, "Erro na leitura do archivo.");
        }
        scanner = null;
        try {
            r = new FileReader(nomeArquivo);
            scanner = new Scanner(r);

            nVertices = Integer.valueOf(scanner.next());

            for (int c = 0; c < nVertices; ++c) {
                vertices.add(new Vertice(c + 1));
            }

            for (int c = 0; c < nVertices; ++c) {
                arestas.add(new Aresta(vertices.get(c), vertices.get(c), 0));
            }

            while (scanner.hasNext()) {
                temp = scanner.next();
                if (temp.equalsIgnoreCase("0")) {
                    ++i;
                    j = i + 1;
                } else {
                    arestas.add(new Aresta(vertices.get(i - 1), vertices.get(j - 1), Float.valueOf(temp)));
                    ++j;
                }

            }
        } catch (Exception e) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (scanner != null) {
                    scanner.close();
                }
            } catch (Exception e) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    /**
     * Obtiene el número de vértices del grafo.
     * @return El número de vértices.
     */
    public int getnVertices() {
        return nVertices;
    }

    /**
     * Obtiene la lista de aristas del grafo.
     * @return Una ArrayList de Aresta con las aristas del grafo.
     */
    public List<Aresta> getArestas() {
        return arestas;
    }

    /**
     * Obtiene la lista de vértices del grafo.
     * @return Una ArrayList de Vertice con los vértices del grafo.
     */
    public List<Vertice> getVertices(){
        return vertices;
    }
}

