package main.java;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger("Logs");

    public static void main(String[] args) {
        String filename = obtainFileName(args);
        Reader leitor = new Reader(filename);
        Grafo builtSolution = createGrafoFromReader(leitor);
        MetGrasp grasp = initializeGrasp(builtSolution);
        executeGrasp(grasp);
    }

    public static String obtainFileName(String[] args) {
        if (args.length > 0) {
            return args[0];
        } else {
            logger.info("Ingrese el nombre del archivinho (Ramiro no se toca): ");
            Scanner scanner = new Scanner(System.in);
            String filename = scanner.nextLine();
            scanner.close();
            return filename;
        }
    }

    public static Grafo createGrafoFromReader(Reader leitor) {
        return new Grafo(leitor.getnVertices(), leitor.getVertices(), leitor.getArestas());
    }

    public static MetGrasp initializeGrasp(Grafo builtSolution) {
        int iteracoesGrasp = 2;
        double alfa = 0.1;
        return new MetGrasp(builtSolution, iteracoesGrasp, alfa);
    }

    public static void executeGrasp(MetGrasp grasp) {
        for (int i = 0; i < 10; ++i) {
            grasp.run();
        }
    }
}