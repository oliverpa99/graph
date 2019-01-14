package com.example.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VertexPath {

    private List<Vertex> nodes = null;
    private List<Edge>  edges = null;
    private Set<Vertex> processedNodes;
    private Set<Vertex> unProcessedNodes;
    private Map<Vertex, Vertex> previous;
    private Map<Vertex, Integer> distance;
	
	public VertexPath(Graph graph) {
		nodes = graph.getVertices();
		edges = graph.getEdges();
	}
	
	/**
	 * Get the shortest path between start vertex and end vertex
	 * 
	 * @param start - vertex
	 * @param end   - vertex
	 * @return LinkedList<Vertex>
	 */
	public LinkedList<Vertex> getShortestPath(Vertex start, Vertex end) {
		
		if(! nodes.contains(start) || ! nodes.contains(end)) {
			return null;
		}
		
		execute(start);

		return getShortestPath(end);
	}
	
	/**
	 * Generate the paths for the start vertex
	 * 
	 * @param vertex - Vertex
	 */
    private void execute(Vertex vertex) {
        processedNodes   = new HashSet<Vertex>();
        unProcessedNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        previous = new HashMap<Vertex, Vertex>();
        distance.put(vertex, 0);
        unProcessedNodes.add(vertex);
        while (unProcessedNodes.size() > 0) {
            Vertex node = getClosestNode(unProcessedNodes);
            processedNodes.add(node);
            unProcessedNodes.remove(node);
            processAdjacentNodes(node);
        } 
     }
    
    /**
     * Get the closest node
     * 
     * @param  vertices Set<Vertex>
     * @return Vertex
     */
    private Vertex getClosestNode(Set<Vertex> vertices) {
        Vertex node = null;
        for (Vertex vertex : vertices) {
            if (node == null) {
            	node = vertex;
            } else {
                if (getSavedDistance(vertex) < getSavedDistance(node)) {
                	node = vertex;
                }
            }
        }
        return node;
    }
    
    /**
     * Get the distance between start vertex to end vertex 
     * 
     * @param  endVertex Vertex
     * @return int
     */
    private int getSavedDistance(Vertex endVertex) {
        Integer sd = distance.get(endVertex);
        if (sd == null) {
            return Integer.MAX_VALUE;
        } else {
            return sd;
        }
    }

    /**
     * Process adjacent nodes
     * 
     * @param node - Vertex
     */
    private void processAdjacentNodes(Vertex node) {
        List<Vertex> adjacentNodes = getAdjacentNodes(node);
        for (Vertex vertex : adjacentNodes) {
            if (getSavedDistance(vertex) > getSavedDistance(node) + getDistance(node, vertex)) {
                distance.put(vertex, getSavedDistance(node) + getDistance(node, vertex));
                previous.put(vertex, node);
                unProcessedNodes.add(vertex);
            }
        }
    }
    
    /**
     * Get the distance between  
     * 
     * @param  start - Vertex
     * @param  end   - Vertex
     * @return int
     */
    private int getDistance(Vertex start, Vertex end) {
        for (Edge edge : edges) {
            if (edge.getsVertex().equals(start)
                    && edge.geteVertex().equals(end)) {
                return edge.getWeight().intValue();
            }
        }
		return 0;
    }
    
    /**
     * Get all vertices connected to given vertex and not previously processed
     * 
     * @param  node - Vertex
     * @return List<Vertex>
     */
    private List<Vertex> getAdjacentNodes(Vertex vertex) {
        List<Vertex> adjacentNodes = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getsVertex().equals(vertex)
                    && !isProcessed(edge.geteVertex())) {
            	adjacentNodes.add(edge.geteVertex());
            }
        }
        return adjacentNodes;
    }
    
    /**
     * Check if the given vertex was already processed
     * 
     * @param  vertex - Vertex
     * @return boolean
     */
    private boolean isProcessed(Vertex vertex) {
        return processedNodes.contains(vertex);
    }
    
    /**
     * Get the shortest route from the path generated to the end vertex
     * 
     * @param vertex - Vertex
     * @return LinkedList<Vertex>
     */
    private LinkedList<Vertex> getShortestPath(Vertex vertex) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        
        Vertex node = previous.get(vertex);
        if (node == null) {
            return null;
        }
        path.add(vertex);
        while (node != null) {    
            path.add(node);
            node = previous.get(node);
        }
        
        Collections.reverse(path);
        
        return path;
    }
}
