package graph;

import java.util.LinkedList;
import java.util.List;

public class Exercice1 {

    // Question a
    public enum Color {
        GREEN, ORANGE, RED
    }

    // Question b
    private Color[] color;
    private int[] distance;
    private int[] parent;

    public Color getColor(int x){
        return this.color[x-1];
    }

    public int getDistance(int x){
        return this.distance[x-1];
    }
    
    public int getParent(int x){
        return this.parent[x-1];
    }

    public void setColor(int x, Color c){
        this.color[x-1] = c;
    }

    public void setDistance(int x, int dist){
        this.distance[x-1] = dist;
    }

    public void setParent(int x, int par){
        this.parent[x-1] = par;
    }


    //Question c
    public void initColor(){
        for(int i = 0; i < this.color.length; i++) {
            this.color[i] = Color.GREEN;
        }
    }


    public void changeValue(int s, int dist, Color c, int parent){
        this.setColor(s, c);
        this.setDistance(s, dist);
        this.setParent(s, parent);
    }

    public void parcoursLargeurBis(int r, GraphSimple src){
        int x;
        List<Integer> F = new LinkedList<>();

        F.add(r);
        changeValue(r, 0, Color.ORANGE, 0);

        while (!(F.isEmpty())){
            x = F.get(0);

            for(int y : src.getAdjencyList(x)){
                if(this.getColor(y).equals(Color.GREEN)){
                    changeValue(y, getDistance(x)+1, Color.ORANGE, x);
                    F.add(y);
                    System.out.println(F);
                }
            }
            F.remove(0);
            this.setColor(x, Color.RED);
        }
    }


    //Question d
    public void parcoursLargeur(int r, GraphSimple src){

        this.color = new Color[src.order()];
        this.distance = new int[src.order()];
        this.parent = new int[src.order()];

        this.initColor();

        this.parcoursLargeurBis(r, src);
    }

    //Question g
    public void parcoursLargeur(GraphSimple src){
        this.color = new Color[src.order()];
        this.distance = new int[src.order()];
        this.parent = new int[src.order()];

        this.initColor();

        for(int x=1; x <= src.order(); x++){
            if(this.getColor(x).equals(Color.GREEN)){
                this.parcoursLargeurBis(x, src);
            }
        }
    }

    //Question e et f
    public static void main(String[] args) {
        //TODO
        // penser à utliser GraphSimpleIO (GraphSimpleIO.getMatrix() ou dans exo1.main)
        GraphSimpleIO.InitializeSource("graph-003.alists");
        int n = GraphSimpleIO.scan.nextInt();

        GraphSimple g = new GraphSimple(n);
        
        int[][] ng = GraphSimpleIO.getGraph(g.matrix);
        g.matrix = ng;
        GraphSimpleIO.printGraph(g.matrix);

        Exercice1 graph = new Exercice1();
        graph.parcoursLargeur(g);
        for(int i =0; i < graph.color.length; i++){
            System.out.println(graph.color[i]);
            System.out.println(graph.distance[i]);
            System.out.println(graph.parent[i]);
        }
    }
}