package com.example.graph;

public class VertexNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8420434849739731583L;

	public VertexNotFoundException(String msg) {
		super("Vertex Not found : " + msg );		
	}
}
