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
        GraphSimpleIO.InitializeSource("graph-003.alists");
        int n = GraphSimpleIO.scan.nextInt();

        GraphSimple g = new GraphSimple(n);

        int[][] ng = GraphSimpleIO.getGraph(g.matrix);
        g.matrix = ng;
        GraphSimpleIO.printGraph(g.matrix);

        Exercice3 graph = new Exercice3();

        graph.nbComposanteConnex(g);

        for(int i=0; i<g.order(); i++){
            System.out.print(graph.cc[i] + " ");
        }
    }
}