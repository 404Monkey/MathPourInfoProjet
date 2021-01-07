package graph;

public class Exercice2 {

    private boolean isConnex;

    public Exercice2(){
        this.isConnex = true;
    }

    public void testConnex(GraphSimple src) {
        Exercice1 parcours = new Exercice1();

        parcours.parcoursLargeur(1, src);

        for(int x=1; x <= src.order(); x++){
            if(parcours.getColor(x).equals(Exercice1.Color.GREEN)){
                this.isConnex = false;
            }
        }
    }

    public static void main(String[] args) {
        GraphSimpleIO.InitializeSource("graph-002.alists");
        int n = GraphSimpleIO.scan.nextInt();

        GraphSimple g = new GraphSimple(n);

        int[][] ng = GraphSimpleIO.getGraph(g.matrix);
        g.matrix = ng;
        GraphSimpleIO.printGraph(g.matrix);

        Exercice2 graph = new Exercice2();
        graph.testConnex(g);

        if(graph.isConnex == true){
            System.out.println("Le graphe est connexe !");
        }
        else{
            System.out.println("Le graphe n'est pas connexe !");
        }
    }
}