package main.java;


public class Main {

    public static void main(String[] args) {
        
    	
    	Reader leitor = new Reader("bayg29.txt");
        
        
        Grafo G = new Grafo(leitor.getnVertices(), leitor.getVertices(), leitor.getArestas());

        int iteracoesGrasp = 2;
        double alfa = 0.1;
        
        MetGrasp grasp = new MetGrasp(G, iteracoesGrasp, alfa);
        
        
        //LACO PARA INICIALIZAR GRASP 10 VEZES COM N(iteracoesGrasp) ITERACOES)
        for (int i = 0; i < 10; ++i) grasp.run();
        
    }
    
}
