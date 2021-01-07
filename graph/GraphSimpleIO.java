package graph;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class GraphSimpleIO {

    static Scanner scan;

    public static void Initialize(){
        scan = new Scanner(System.in);
    }

    public static void InitializeSource(String source){
        try
        {
        FileInputStream fichier = new FileInputStream(source); 
        scan = new Scanner(fichier);
        }
        catch (FileNotFoundException ex){}
    }

    public static int[][] getMatrix(int n) {
        int[][] matrix = new int[n][n];

        // valeurs de la matrice
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                matrix[i][j] = scan.nextInt();  
            }
        }

        return matrix;
    }

    public static int[][] getGraph(int[][] graph){
        for(int i = 0; i < graph.length; i++) {
            int n = scan.nextInt();
            int nb = -1;
            int nbElem = -1;
            int[] list = new int[graph.length];
            while(nb != 0) {
                nbElem++;
                nb = scan.nextInt();
                if(nb != 0) {
                    list[nbElem] = nb;
                }
            }
            int[] nlist = new int[nbElem];
            for(int j = 0; j < nbElem; j++){
                nlist[j] = list[j];
            }
            graph[n-1] = nlist;
        }
        return graph;
    }

    public static void printMatrix(int[][] graph){
        int n = graph.length;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printGraph(int[][] graph){
        for(int i=0; i<graph.length; i++){
            System.out.print(i+1 + " :  ");
            int n = graph[i].length;
            for(int j=0; j<n; j++)
            {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }   
    }    

    public static void rawPrintGraph(int[][] graph){
        //TODO
    }

    public static int getInt(){
        return scan.nextInt();
    }

    private GraphSimpleIO(){};

}

 