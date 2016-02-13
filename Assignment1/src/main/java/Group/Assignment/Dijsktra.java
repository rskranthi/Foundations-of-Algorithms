package Group.Assignment;

import java.util.*;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.EdgeReversedGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

public class Dijsktra {

	static HashMap<String, Double> ShortestPath(
			SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph,
			String Source) {
		// HashMap<String,Double> result=new HashMap<String,Double>();
		HashMap<String, Double> nodes = new HashMap<String, Double>();
		// FibonacciHeap<Double> fib = new FibonacciHeap<Double>();
		FibonacciHeap<String> fib1 = new FibonacciHeap<String>();
		// fib.
		// FibonacciHeapNode<String> source=new
		// FibonacciHeapNode<String>(Source);
		// fib1.insert(source, 0);
		HashMap<String, FibonacciHeapNode<String>> nodes1 = new HashMap<String, FibonacciHeapNode<String>>();
		HashMap<FibonacciHeapNode<String>, String> nodes2 = new HashMap<FibonacciHeapNode<String>, String>();
		for (String vertices : graph.vertexSet()) {
			nodes.put(vertices, Double.MAX_VALUE);
			FibonacciHeapNode<String> node = new FibonacciHeapNode<String>(
					vertices);
			fib1.insert(node, Double.MAX_VALUE);
			nodes1.put(vertices, node);
			nodes2.put(node, vertices);
		}
		fib1.decreaseKey(nodes1.get(Source), 0);
		nodes.put(Source, (double) 0);
		while (!fib1.isEmpty()) {
			FibonacciHeapNode<String> node = fib1.removeMin();
			System.out.print("hI " + nodes2.get(node));

			Set<DefaultWeightedEdge> edges = graph.outgoingEdgesOf(nodes2
					.get(node));
			for (DefaultWeightedEdge edge : edges) {
				String target = graph.getEdgeTarget(edge);
				FibonacciHeapNode<String> target1 = nodes1.get(target);
				double weight = graph.getEdgeWeight(edge);
				double d_of_u = nodes.get(nodes2.get(node));
				double d_of_v = d_of_u + weight;
				if (d_of_v < nodes.get(target)) {
					nodes.put(target, d_of_v);
					fib1.decreaseKey(target1, nodes.get(target));

				}
				// graph.
			}
		}
		//
		// for(String s:graph.vertexSet())
		// {
		// FibonacciHeapNode<String> node=new FibonacciHeapNode<String>(s);
		// nodes.put(s,fib1.insert(node,Double.POSITIVE_INFINITY));
		// }
		// System.out.println(nodes);
		return nodes;

	}

	static HashMap<String, Double> ShortestPath1(
			EdgeReversedGraph<String, DefaultWeightedEdge> graph, String Source) {
		// HashMap<String,Double> result=new HashMap<String,Double>();
		HashMap<String, Double> nodes = new HashMap<String, Double>();
		// FibonacciHeap<Double> fib = new FibonacciHeap<Double>();
		FibonacciHeap<String> fib1 = new FibonacciHeap<String>();
		// fib.
		// FibonacciHeapNode<String> source=new
		// FibonacciHeapNode<String>(Source);
		// fib1.insert(source, 0);
		HashMap<String, FibonacciHeapNode<String>> nodes1 = new HashMap<String, FibonacciHeapNode<String>>();
		HashMap<FibonacciHeapNode<String>, String> nodes2 = new HashMap<FibonacciHeapNode<String>, String>();
		for (String vertices : graph.vertexSet()) {
			nodes.put(vertices, Double.MAX_VALUE);
			FibonacciHeapNode<String> node = new FibonacciHeapNode<String>(
					vertices);
			fib1.insert(node, Double.MAX_VALUE);
			nodes1.put(vertices, node);
			nodes2.put(node, vertices);
		}
		fib1.decreaseKey(nodes1.get(Source), 0);
		nodes.put(Source, (double) 0);
		while (!fib1.isEmpty()) {
			FibonacciHeapNode<String> node = fib1.removeMin();
			System.out.print("hI " + nodes2.get(node));

			Set<DefaultWeightedEdge> edges = graph.outgoingEdgesOf(nodes2
					.get(node));
			for (DefaultWeightedEdge edge : edges) {
				String target = graph.getEdgeTarget(edge);
				FibonacciHeapNode<String> target1 = nodes1.get(target);
				double weight = graph.getEdgeWeight(edge);
				double d_of_u = nodes.get(nodes2.get(node));
				double d_of_v = d_of_u + weight;
				if (d_of_v < nodes.get(target)) {
					nodes.put(target, d_of_v);
					fib1.decreaseKey(target1, nodes.get(target));

				}
				// graph.
			}
		}
		//
		// for(String s:graph.vertexSet())
		// {
		// FibonacciHeapNode<String> node=new FibonacciHeapNode<String>(s);
		// nodes.put(s,fib1.insert(node,Double.POSITIVE_INFINITY));
		// }
		// System.out.println(nodes);
		return nodes;

	}
}
