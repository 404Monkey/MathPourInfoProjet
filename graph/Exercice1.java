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
        return this.color[x];
    }

    public int getDistance(int x){
        return this.distance[x];
    }
    
    public int getParent(int x){
        return this.parent[x];
    }

    public void setColor(int x, Color c){
        this.color[x] = c;
    }

    public void setDistance(int x, int dist){
        this.distance[x] = dist;
    }

    public void setParent(int x, int par){
        this.parent[x] = par;
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


    //Question d
    public void parcoursLargeur(int r, GraphSimple src){

        this.color = new Color[src.order()];
        this.distance = new int[src.order()];
        this.parent = new int[src.order()];
        System.out.println("Jai init les tableaux");

        int x;
        this.initColor();
        System.out.println("Jai init les couleurs");
        List<Integer> F = new LinkedList<>();

        F.add(r);
        changeValue(r, 0, Color.ORANGE, 0);
        System.out.println("r en orange");

        while (!(F.isEmpty())){
            x = F.get(0);

            for(int y : src.getAdjencyList(x)){
               System.out.println("entre dans le for : " + y);
               if(this.getColor(y-1).equals(Color.GREEN)){
                   System.out.println("entre dans le if");
                   changeValue(y, getDistance(x)+1, Color.ORANGE, x);
                   F.add(y);
                   System.out.println(F);
               }
            }
            this.setColor(x, Color.RED);
            System.out.println("rouge");
        }
    }

    //Question g
    public void parcoursLargeur(GraphSimple src){
        for(int x=1; x <= src.order(); x++){
            if(getColor(x) == Color.GREEN){
                parcoursLargeur(x, src);
            }
        }
    }

    //Question e et f
    public static void main(String[] args) {
        //TODO
        // penser à utliser GraphSimpleIO (GraphSimpleIO.getMatrix() ou dans exo1.main)
        GraphSimpleIO.InitializeSource("graph-002.alists");
        int n = GraphSimpleIO.scan.nextInt();

        GraphSimple g = new GraphSimple(n);
        
        int[][] ng = GraphSimpleIO.getGraph(g.matrix);
        g.matrix = ng;
        GraphSimpleIO.printGraph(g.matrix);

        Exercice1 graph = new Exercice1();
        graph.parcoursLargeur(1, g);
        System.out.println("color :" + graph.color);
        System.out.println("distance :" + graph.distance);
        System.out.println("parent :" + graph.parent);
    }
}