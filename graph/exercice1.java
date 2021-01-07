package graph;

public class Exercice1 {

    // Question a
    public enum Color {
        GREEN, ORANGE, RED
    }

    /// Attributes ///
    private GraphSimple graph;

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


    // Question c
    public void initColor(){
        for(int i = 0; i < this.color.length; i++) {
            this.color[i] = Color.GREEN;
        }
    }


    //Constructeur
    public Exercice1(GraphSimple src){
        this.graph = src;
        this.color = new Color[src.order];
        this.distance = new int[src.order];
        this.parent = new int[src.order];

        this.initColor();
    }


    //Question d
    public void parcoursLargeur(int x){
        //TODO
    }

    //Question g
    public void parcoursLargeur(){
        //TODO
    }

    //Question e et f
    public static void main(String[] args) {
        //TODO
        // penser à utliser GraphSimpleIO (GraphSimpleIO.getMatrix() ou dans exo1.main)
    }
}