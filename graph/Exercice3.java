package graph;

import java.util.LinkedList;
import java.util.List;

public class Exercice3 extends Exercice1{

    /// Attributes ///

    private int[] cc;

    @Override
    public void parcoursLargeurBis(int r, GraphSimple src){
        int x;
        List<Integer> F = new LinkedList<>();

        F.add(r);
        changeValue(r, 0, Color.ORANGE, 0);

        while (!(F.isEmpty())){
            x = F.get(0);
            cc[x-1] = r;

            for(int y : src.getAdjencyList(x)){
                if(this.getColor(y).equals(Color.GREEN)){
                    changeValue(y, getDistance(x)+1, Color.ORANGE, x);
                    F.add(y);
                }
            }
            F.remove(0);
            this.setColor(x, Color.RED);
        }
    }

    public void nbComposanteConnex(GraphSimple src) {
        cc = new int[src.order()];

        this.color = new Color[src.order()];
        this.distance = new int[src.order()];
        this.parent = new int[src.order()];

        this.initColor();

        for(int x=1; x <= src.order(); x++){
            if(this.getColor(x).equals(Exercice1.Color.GREEN)){
                this.parcoursLargeurBis(x, src);
            }
        }
    }

    public static void main(String[] args) {
        // On initialise un scanner dans le fichier du graphe de graph_003
        GraphSimpleIO.InitializeSource("graph-003.alists");

        // On récupère sa taille
        int n = GraphSimpleIO.scan.nextInt();

        // On crée un objet GraphSimple pour le graphe de graph_003
        GraphSimple graph_003 = new GraphSimple(n);

        // on récupère grace à getGraph les listes d'adjacences sans les somments au début et les 0 à la fin
        int[][] agraph_003 = GraphSimpleIO.getGraph(graph_003.matrix);
        graph_003.matrix = agraph_003;

        // On l'affiche pour vérifier
        GraphSimpleIO.printGraph(graph_003.matrix);

        // On crée une classe Exercice1 puis on lui applique la méthode nbComposanteConnex
        Exercice3 graph = new Exercice3();
        graph.nbComposanteConnex(graph_003);

        // On affiche
        System.out.print("    x :");
        for(int i=0; i<graph_003.order(); i++){
            System.out.printf(" %2d", i+1);
        }

        System.out.println();

        System.out.print("cc(x) :");
        for(int i=0; i<graph_003.order(); i++){
            System.out.printf( " %2d", graph.cc[i]);
        }
        System.out.println();
    }
}