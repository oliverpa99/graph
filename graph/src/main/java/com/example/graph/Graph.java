package com.example.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Graph implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4162285942028168790L;
	
	private List<Vertex> vertices = new ArrayList<Vertex>();
	private List<Edge> edges = new ArrayList<Edge>();
	
	public Graph() {}
	
	protected void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	protected void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	/**
	 * Returns a copy of the vertices list. 
	 * 
	 * @return List<Vertex>
	 */
	public List<Vertex> getVertices() {
		synchronized(this) {
			return new ArrayList<Vertex>(this.vertices);
		}
	}

	/**
	 * Returns a copy of the edges list
	 * 
	 * @return - List<Edge>
	 */
	public List<Edge> getEdges() {
		synchronized(this) {
			return new ArrayList<Edge>(this.edges);
		}
	}
	
	/** 
	 * Add a vertex
	 * 
	 * @param vertex - Vertex
	 */
	public void addVertex(Vertex vertex) {
		synchronized(this) {
			vertices.add(vertex);
		}
	}
	
	/**
	 * Remove a vertex also removes all edges using the vertex
	 * 
	 * @param vertex - Vertex
	 */
	public void deleteVertex(Vertex vertex) {
		synchronized(this) {
			
			int i = 0;
			while(i < edges.size()) {
				Edge edge = edges.get(i);
				if(edge.hasVertex(vertex) || edge.hasVertex(vertex)) {
					edges.remove(i);
				} else {
					i++;
				}
			}
			
			vertices.remove(vertex);		
		}
	}
	
	/**
	 * Add an edge to the graph, if the vertices is not present add it as well
	 * 
	 * @param edge - Edge
	 */
	public void addEdge(Edge edge) throws VertexNotFoundException {
		synchronized(this) {
			if(! vertices.contains(edge.getsVertex())) {
				throw new VertexNotFoundException(Util.toJson(edge.getsVertex()));
			}
			
			if(! vertices.contains(edge.geteVertex())) {
				throw new VertexNotFoundException(Util.toJson(edge.geteVertex()));
			}
			
			edges.add(edge);
		}
	}
	
	/**
	 * Delete all the same edge from the graph
	 * 
	 * @param edge - Edge
	 */
	public void deleteEdge(Edge edge) {
		synchronized(this) {
			edges.remove(edge);
		}
	}
	
	/**
	 * Get a copy of all the edges that intersect with the given vertex
	 * 
	 * @param vertex - Vertex
	 * @return List<Edge>
	 */
	public List<Edge>getEdgesWithVertex(Vertex vertex){
		synchronized (this) {
			List<Edge> list = new ArrayList<Edge>();
			
			for(Edge edge : edges) {
				if(edge.hasVertex(vertex)){
					list.add(edge);
				}
			}
			return list;
		}
	}
	
	/**
	 * Get a copy of all edges that uses the given start vertex and end vertex 
	 * 
	 * @param sVertex - Vertex
	 * @param eVertex - Vertex
	 * @return List<Edge>
	 */
	public List<Edge> getEdgesWithVertices(Vertex sVertex, Vertex eVertex){
		synchronized (this) {
			List<Edge> list = new ArrayList<Edge>();
		
			for(Edge edge : edges) {
				if(edge.hasVertices(sVertex, eVertex)){
					list.add(edge);
				}
			}
			return list;
		}
	}
	
	/**
	 * Get a copy of all vertices connected to a given vertex
	 * 
	 * @param  vertex - Vertex
	 * @return List<Vertex>
	 */
	public List<Vertex> getConnectedVectices(Vertex vertex){
		synchronized (this) {
			List<Vertex> list = new ArrayList<Vertex>();
		
			for(Edge edge : edges) {
				Vertex connected = edge.getConnectedVertex(vertex);
				if(connected != null){
					list.add(connected);
				}
			}
			return list;
		}
	}
}
