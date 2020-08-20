package com.mastercard.challenge.service;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Used to check if nodes are connected 
 * 
 * That apply to cities or any graph
 * @author karim
 *
 */
public class NodesCheck {

	private String origin;
	private String destination;
	private boolean isConnected;
	private boolean result = false;

	private Map<String, LinkedHashSet<String>> map = new HashMap<String, LinkedHashSet<String>>();

	public void addEdge(String node1, String node2) {

		LinkedHashSet<String> adjacent = map.get(node1);

		if (adjacent == null) {
			adjacent = new LinkedHashSet<String>();
			map.put(node1, adjacent);
		}

		adjacent.add(node2);
	}

	public boolean isConnected(String node1, String node2) {

		Set adjacent = map.get(node1);
		if (adjacent == null) {
			return false;
		}
		return adjacent.contains(node2);

	}

	public LinkedList<String> adjacentNodes(String last)

	{

		LinkedHashSet<String> adjacent = map.get(last);

		if (adjacent == null){
			return new LinkedList<String>();
		}

		return new LinkedList<String>(adjacent);

	}

	public void checkNodeLink(NodesCheck graph, LinkedList<String> visited) {

		LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());

		for (String node : nodes) {

			if (visited.contains(node)){
				continue;
			}

			if (node.equals(destination)) {

				visited.add(node);
				// Check if there is a path for the node
				result = !visited.isEmpty();
				isConnected = true;
				visited.removeLast();
				break;

			}

		}

		for (String node : nodes) {

			if (visited.contains(node) || node.equals(destination)) {
				continue;
			}

			visited.addLast(node);
			checkNodeLink(graph, visited);
			visited.removeLast();
		}

		if (isConnected == false) {
			// No Path exist setting up isConnected flag to true for the recursive method
			isConnected = true;
		}

	}

	public Map<String, LinkedHashSet<String>> getNodes() {
		return this.map;
	}

	public boolean getResult() {
		return result;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

}