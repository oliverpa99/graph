package com.example.graph;

import java.io.Serializable;

public class Edge implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 395040665963315058L;
	
	private Integer id;
	private Vertex  sVertex;
	private Vertex  eVertex;
	private Integer weight;
	private String  label;

	protected Edge() {}
	
	public Edge(Integer id, Vertex sVertex, Vertex eVertex, Integer weight, String label) {
		this.id = id;
		this.sVertex = sVertex;
		this.eVertex = eVertex;
		this.weight = weight;
		this.label = label;
	}
	
	public Edge(Integer id, Vertex sVertex, Vertex eVertex, Integer weight) {
		this.id = id;
		this.sVertex = sVertex;
		this.eVertex = eVertex;
		this.weight = weight;
		this.label = "";
	}
	
	public Vertex getsVertex() {
		return sVertex;
	}
	
	public Vertex geteVertex() {
		return eVertex;
	}
	
	public Integer getWeight() {
		return weight;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean hasVertex(Vertex vertex) {
		if(vertex.equals(sVertex) || vertex.equals(eVertex))
			return true;
		
		return false;
	}
	
	public boolean isStartNode(Vertex vertex) {
		if(vertex.equals(sVertex))
			return true;
		
		return false;
	}
	
	public boolean isEndNode(Vertex vertex) {
		if(vertex.equals(eVertex))
			return true;
		
		return false;
	}
	
	public boolean hasVertices(Vertex sVertex, Vertex eVertex) {
		if(this.sVertex.equals(sVertex) && this.eVertex.equals(eVertex))
			return true;
		
		return false;
	}
	
	public Vertex getConnectedVertex(Vertex vertex) {
		if(vertex.equals(sVertex)) {
			return eVertex;
		} else if (vertex.equals(eVertex))
			return sVertex;
		
		return null;
	}

	public Integer getId() {
		return this.id;
	}
	
}
