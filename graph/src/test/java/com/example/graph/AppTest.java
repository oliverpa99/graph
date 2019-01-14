package com.example.graph;


import java.util.List;

import com.example.graph.Edge;
import com.example.graph.Graph;
import com.example.graph.Util;
import com.example.graph.Vertex;
import com.example.graph.VertexNotFoundException;
import com.example.graph.VertexPath;

import junit.framework.TestCase;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
    public void testApp()
    {
        Graph graph = new Graph();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        
        Edge e1 = new Edge(1, v1, v2, 4);
        Edge e2 = new Edge(2, v2, v3, 4);
        Edge e3 = new Edge(3, v2, v4, 3);
        Edge e4 = new Edge(4, v1, v4, 5);
        Edge e5 = new Edge(5, v3, v4, 7);
        Edge e6 = new Edge(6, v3, v5, 8);
        Edge e7 = new Edge(7, v4, v5, 2);
        Edge e8 = new Edge(8, v5, v6, 2);
        try {
			graph.addEdge(e1);
			graph.addEdge(e2);
	        graph.addEdge(e3);
	        graph.addEdge(e4);
	        graph.addEdge(e5);
	        graph.addEdge(e6);
	        graph.addEdge(e7);
	        graph.addEdge(e8);
		} catch (VertexNotFoundException ex) {
			assertTrue(false);
			ex.printStackTrace();
		}
        
        System.out.println("The graph in JSON : " + Util.toJson(graph));
        
        List<Edge> edges = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8);

        // Validate that the graph has all the edges
        assertTrue(graph.getEdges().equals(edges));
        
        List<Vertex> vertices = Arrays.asList(v1, v2, v3, v4, v5, v6);
        
        // Validate that the graph has all the vertices
        assertTrue(graph.getVertices().equals(vertices));
 
        List<Edge> expectedEdges = Arrays.asList(e4);
        List<Edge> e = graph.getEdgesWithVertices(v1, v4);
        System.out.println("Edges connected to vertex 1 and vertex 4 : " + Util.toJson(e));
        assertTrue(e.equals(expectedEdges));
        
        expectedEdges = Arrays.asList(e1, e4);
        List<Edge> edgesWithVertex = graph.getEdgesWithVertex(v1);
        System.out.println("Edges connected to vertex 1 : " + Util.toJson(edgesWithVertex));
        assertTrue(edgesWithVertex.equals(expectedEdges));
        
        List<Vertex> expectedVertices = Arrays.asList(v2, v4); 
        List<Vertex> connected = graph.getConnectedVectices(v1);
        System.out.println("vertex 1 is Connected to : " + Util.toJson(connected));
        assertTrue(connected.equals(expectedVertices));
        
        Graph g = (Graph)Util.createObj(Util.toJson(graph), Graph.class);
        System.out.println("The new graph in JSON : " + Util.toJson(g));
    
        /*
         * Check the shortest path between 2 vertices
         */
        VertexPath path = new VertexPath(graph); 
        LinkedList<Vertex> spath = path.getShortestPath(v1, v5);
        expectedVertices = Arrays.asList(v1, v4, v5);
        assertTrue(expectedVertices.equals(spath));
        System.out.print("Shortest Path : ");
       	for (Vertex vertex : spath) {
       		System.out.println(Util.toJson(vertex));
       	}
               
        // delete edge 4, validate that edge 4 was remove from the graph
       	graph.deleteEdge(e4);
       	edges = Arrays.asList(e1, e2, e3, e5, e6, e7, e8);  	
       	assertTrue(graph.getEdges().equals(edges));       	
       	
        /*
         * Check the shortest path between 2 vertices after deleting edge 4
         */
       	path = new VertexPath(graph);
 
        spath = path.getShortestPath(v1, v5);
        expectedVertices = Arrays.asList(v1, v2, v4, v5);
        assertTrue(expectedVertices.equals(spath));
        System.out.print("Shortest Path : ");
       	for (Vertex vertex : spath) {
       		System.out.println(Util.toJson(vertex));
       	}
       	
       	// delete vertex 1
       	graph.deleteVertex(v1);
       	expectedVertices = Arrays.asList(v2, v3, v4, v5, v6); 
       	assertTrue(expectedVertices.equals(graph.getVertices()));
       	edges = Arrays.asList(e2, e3, e5, e6, e7, e8);
       	assertTrue(edges.equals(graph.getEdges()));
    }
}
