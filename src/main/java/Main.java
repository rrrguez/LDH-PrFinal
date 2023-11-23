package main.java;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        // Logger para mostrar por pantalla mensajes
        Logger logger = Logger.getLogger("Logs");
        String filename;

        if (args.length > 0) {
            filename = args[0];
        } else {
            Scanner scanner = new Scanner(System.in);
            logger.info("Caipirinha gaucho, ingreissse el nombre do archivinho: ");
            filename = scanner.nextLine();
            scanner.close();
        }

        Reader leitor = new Reader(filename);
        Grafo builtSolution = new Grafo(leitor.getnVertices(), leitor.getVertices(), leitor.getArestas());

        int iteracoesGrasp = 2;
        double alfa = 0.1;

        MetGrasp grasp = new MetGrasp(builtSolution, iteracoesGrasp, alfa);

        // LACO PARA INICIALIZAR GRASP 10 VEZES COM N(iteracoesGrasp) ITERACOES)
        for (int i = 0; i < 10; ++i) grasp.run();
    }

}
