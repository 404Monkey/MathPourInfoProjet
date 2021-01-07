package graph;

import java.util.LinkedList;
import java.util.List;

public class Exercice1 {

    // Question a
    public enum Color {
        GREEN, ORANGE, RED
    }

    // Question b
    protected Color[] color;
    protected int[] distance;
    protected int[] parent;

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

    public static void displayColorArray(Color[] c){
        for(int i = 0; i < c.length; i++){
            if(c[i].equals(Color.GREEN)) {
                System.out.print(c[i]+" ");
            }
            if(c[i].equals(Color.RED)) {
                System.out.print(c[i]+"   ");
            }
        }
        System.out.println();
    }

    public static void displayIntArray(int[] t){
        for(int i = 0; i < t.length; i++){
            System.out.print(t[i]+"     ");
        }
        System.out.println();
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
        // TEST POUR GRAPH DE PETERSON

        // On initialise un scanner dans le fichier du graphe de peterson
        GraphSimpleIO.InitializeSource("graph-002.alists");
        
        // On récupère sa taille
        int n = GraphSimpleIO.scan.nextInt();

        // On crée un objet GraphSimple pour le graphe de peterson
        GraphSimple peterson = new GraphSimple(n);
        
        // on récupère grace à getGraph les listes d'adjacences sans les somments au début et les 0 à la fin
        int[][] agraph = GraphSimpleIO.getGraph(peterson.matrix);
        peterson.matrix = agraph;
        // On l'affiche pour vérifier
        GraphSimpleIO.printGraph(peterson.matrix);

        // On crée une classe Exercice1 puis on lui applique la méthode parcours largeur
        Exercice1 graph = new Exercice1();
        graph.parcoursLargeur(1, peterson);

        // On affiche les 3 tableaux
        displayColorArray(graph.color);
        displayIntArray(graph.distance);
        displayIntArray(graph.parent);

        // TEST POUR GRAPH 003

        // On initialise un scanner dans le fichier du graphe de graph_003
        GraphSimpleIO.InitializeSource("graph-003.alists");
        
        // On récupère sa taille
        int n2 = GraphSimpleIO.scan.nextInt();

        // On crée un objet GraphSimple pour le graphe de graph_003
        GraphSimple graph_003 = new GraphSimple(n2);
        
        // on récupère grace à getGraph les listes d'adjacences sans les somments au début et les 0 à la fin
        int[][] agraph_003 = GraphSimpleIO.getGraph(graph_003.matrix);
        graph_003.matrix = agraph_003;
        // On l'affiche pour vérifier
        GraphSimpleIO.printGraph(graph_003.matrix);

        // On crée une classe Exercice1 puis on lui applique la méthode parcours largeur
        Exercice1 graph2 = new Exercice1();
        graph2.parcoursLargeur(graph_003);

        // On affiche les 3 tableaux
        displayColorArray(graph2.color);
        displayIntArray(graph2.distance);
        displayIntArray(graph2.parent);
    }
}