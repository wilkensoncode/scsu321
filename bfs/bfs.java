package bfs;

/**
 * Southern Connecticut State University
 *CSC 321: Algorithm Design & Analysis
 *Section 01
 *Fall 2023
 *Tasks: Graph Presentation, Breadth First Search (BFS) 
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

class BreathFirstSearch {
    public static void main(String[] args) {
        HashMap<Character, ArrayList<Character>> graph;

        graph = buildGraph(new char[][] { { 's', 'a' }, { 's', 'b' },
                { 'a', 's' }, { 'a', 'b' }, { 'a', 'd' }, { 'b', 'a' },
                { 'b', 's' }, { 'b', 'c' }, { 'c', 'b' }, { 'c', 'd' }, { 'c', 'e' },
                { 'd', 'a' }, { 'd', 'c' }, { 'd', 'e' }, { 'e', 'c' }, { 'e', 'd' } });

        System.out.println("\nGraph: \n");

        for (char c : graph.keySet())
            System.out.println(c + " –> " + graph.get(c));
        System.out.println();

        bfs(graph, 's');

    }

    /**
     * this method builds a graph from a 2d array of characters
     * where the first character is the vertex and the second character is the
     * vertex it is connected to
     * 
     * @param ch 2d array of characters
     * @return a graph in the form of a hashmap
     */
    static HashMap<Character, ArrayList<Character>> buildGraph(char[][] ch) {
        HashMap<Character, ArrayList<Character>> map = new HashMap<>();
        for (char[] c : ch) {
            map.putIfAbsent(c[0], new ArrayList<>());
            map.get(c[0]).add(c[1]);
        }
        return map;
    }

    /**
     * this method performs a breadth first search on a graph
     * and prints the distance of each vertex from the source
     * 
     * @param graph  the graph to perform bfs on
     * @param source the source vertex
     */
    static void bfs(HashMap<Character, ArrayList<Character>> graph, char source) {
        HashMap<Character, Integer> distance = new HashMap<>();
        HashSet<Character> white = new HashSet<>(); // vertex whose adjacency list has not been processed
        // vertex whose adjacency list has not been completely processed
        // (some of its neighbors have not been visited)
        HashSet<Character> gray = new HashSet<>();
        // vertex whose adjacency list has been completely processed (all its neighbors
        // have been visited)
        HashSet<Character> black = new HashSet<>();
        // parent of each vertex in the bfs tree (parent of
        // source is null)
        HashMap<Character, Character> parent = new HashMap<>();

        for (char c : graph.keySet()) {
            white.add(c); // add all the vertices to white set
            distance.put(c, Integer.MAX_VALUE); // set distance to infinity
            parent.put(c, null); // set parent to null
        }
        gray.add(source); // add source to gray set
        distance.put(source, 0); // set distance to source to 0
        parent.put(source, null); // set parent to null

        Queue<Character> queue = new LinkedList<>(); // create a queue
        queue.add(source); // add source to queue

        while (!queue.isEmpty()) { // while queue is not empty
            char u = queue.poll(); // remove the first element from queue
            for (char v : graph.get(u)) { // for each vertex v in adjacency list of u
                if (white.contains(v)) { // if v is in white set
                    white.remove(v); // remove v from white set
                    gray.add(v); // add v to gray set
                    if (source != v)
                        distance.put(v, distance.get(u) + 1); // set distance of v to distance of u + 1
                    parent.put(v, u); // set parent of v to u
                    queue.add(v); // add v to queue
                }
            }
            black.add(u);
        }

        System.out.printf("Distance from source s : %s \n\n", distance);
        System.out.printf("the distacne form s to d is %s ", distance.get('d'));

    }

}