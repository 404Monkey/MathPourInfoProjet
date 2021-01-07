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
        //TODO
    }
}