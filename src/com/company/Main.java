package com.company;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static final int V = 9;

    int minDistance(int dist[], Boolean sptSet[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    void printSolution(int dist[], int src, int des, int parent[])
    {
        System.out.println("Destination \t\t Distance from starting point");
        for (int i = 0; i < V; i++) {
            if (i == des)
                System.out.println(i + " \t\t\t\t\t " + dist[i] + "Km");
        }

        System.out.println("Path from source "+src+" to destination "+des);
        ArrayList<Integer> path = new ArrayList<Integer>();
        int temp = des;
        while(temp!=src){
            path.add(parent[temp]);
            temp = parent[temp];
        }
        Collections.reverse(path);
        for(Integer i: path){
            pointToLocation(i);
            System.out.print("->");
        }
        pointToLocation(des);

    }

    void dijkstra(int graph[][], int src, int des)
    {
        int dist[] = new int[V]; // The output array.
        int parent[] = new int[V];
        Boolean sptSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
            parent[i]=-1;
        }

        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {

            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int v = 0; v < V; v++){

                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }

            }
        }
        printSolution(dist,src,des,parent);
    }

    void printMap(int graph[][]){
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j]+"\t");
            }
            System.out.println();
        }
    }

    void nameOfPlaces(){
        System.out.println(
                "0. Hostel\n"+
                "1. AB1\n"+
                "2. Grand Stairs\n"+
                "3. AB2\n"+
                "4. Park\n"+
                "5. TNP\n"+
                "6. Dome Building\n"+
                "7. Library\n"+
                "8. Fountain"
        );
    }

    void pointToLocation(int x){
        switch (x){
            case 0:
                System.out.print("Hostel(0)");
                break;
            case 1:
                System.out.print("AB1(1)");
                break;
            case 2:
                System.out.print("Grand Stairs(2)");
                break;
            case 3:
                System.out.print("AB2(3)");
                break;
            case 4:
                System.out.print("Park(4)");
                break;
            case 5:
                System.out.print("TNP(5)");
                break;
            case 6:
                System.out.print("Dome Building(6)");
                break;
            case 7:
                System.out.print("library(7)");
                break;
            case 8:
                System.out.print("Fountain(8)");
                break;
        }
    }

    public static void main(String[] args) {
	// write your code here

        int graph[][]
                = new int[][]
                {{ 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

        Main t = new Main();

        System.out.println("Welcome to Google Maps");
        System.out.println("1. Map");
        System.out.println("2. Directions");

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        switch(x){
            case 1:
                System.out.println("Map:");
                t.printMap(graph);
                break;

            case 2:
                t.nameOfPlaces();
                System.out.println("Enter starting point: ");
                int s = sc.nextInt();
                System.out.println("Enter Destination: ");
                int d = sc.nextInt();
                t.dijkstra(graph, s, d);
                break;

        }
    }
}
