package com.example.graph;

import java.io.Serializable;

public class Vertex implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -232748072967363278L;
	
	private Integer id;

	protected Vertex() {}
	
	public Vertex(Integer node) {
		this.id = node;
	}
		
	public Integer getId() {
		return id;
	}
}
