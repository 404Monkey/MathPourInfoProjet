package graph;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class GraphSimple {

    int[][] matrix;
    boolean Alist;
    boolean Amatrix;

    public GraphSimple(int n){
        this.matrix = new int[n][];
        this.Alist = true;
        this.Amatrix = false;
    }

    public void setAdjencyList(int x, int[] list){
        if(this.Alist){
            this.matrix[x-1] = list;
        }
    }

    public int[] getAdjencyList(int x){
        return this.matrix[x-1];
    }

    public int order(){
        return this.matrix.length;
    }

    public int degree(int x){
        if(this.Alist){
            return this.matrix[x-1].length;
        }
        if(this.Amatrix){
            int nb = 0;
            for(int i = 0; i<this.matrix[x-1].length; i++){
                if(this.matrix[x-1][i] == 1){
                    nb++;
                }
            }
            return nb;
        }
        return -1;
    }

    public boolean isVertex(int x){
        return (1 <= x && x <= this.order());
    }

    public boolean isConnected(int x, int y){
        boolean b = false;
        int i = 0;
        while(!b) {
            if(this.matrix[x-1][i] == y) {
                b = true;
            }
            i++;
        }
        return b;
    }    

    public void toMatrix(){
        if(this.Alist) {
            int n = this.order();

            int[][] matrix = new int[n][n];
            for(int i = 0; i<n; i++){
                for(int j=0; j<n; j++){
                    matrix[i][j] = 0;
                }
            }

            for(int i=0; i<n; i++){
                for(int j=0; j<this.degree(i+1); j++){
                    matrix[i][this.matrix[i][j]-1] = 1;
                }
            }
            this.matrix = matrix;
            this.Alist = false;
            this.Amatrix = true;
        } else{
            System.out.println("Déja sous forme matrice d'adjacence");
            }
    }
   
    public static int[] Alist(int n, int[][] m) {
        int[] alist = new int[m.length];
        int nbElem = -1;
        
        for(int i = 0; i < m.length; i++) {
            if(m[n-1][i] == 1) {
                nbElem++;
                alist[nbElem] = i+1;
            }
        }

        int[] nalist = new int[nbElem+1];
        for(int i = 0; i <= nbElem; i++) {
            nalist[i] = alist[i];
        }

        return nalist;
    }

    public void fromMatrix() {
        if(this.Amatrix) {
            int n = this.order();

            for(int i=0; i<n; i++) {
                this.matrix[i] = Alist(i+1, this.matrix);
            }
            this.Alist = true;
            this.Amatrix = false;
        } else{
            System.out.println("Déja sous forme tableau de listes d'adjacences");
            }
    }

    public static void main(String[] args){
        GraphSimple gs = new GraphSimple(3);
        int[] tab1 = {1, 2};
        int[] tab2 = {2};
        int[] tab3 = {2, 3};
        gs.setAdjencyList(1, tab1);
        gs.setAdjencyList(2, tab2);
        gs.setAdjencyList(3, tab3);

        GraphSimpleIO.printGraph(gs.matrix);

        gs.toMatrix();
        GraphSimpleIO.printMatrix(gs.matrix);

        gs.fromMatrix();
        GraphSimpleIO.printGraph(gs.matrix);                
    }

}