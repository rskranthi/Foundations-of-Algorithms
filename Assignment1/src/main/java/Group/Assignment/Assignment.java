/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
/* -----------------
 * HelloJGraphT.java
 * -----------------
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * Original Author:  Barak Naveh
 * Contributor(s):   -
 *
 * $Id$
 *
 * Changes
 * -------
 * 27-Jul-2003 : Initial revision (BN);
 *
 */
package Group.Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.EdgeReversedGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public final class Assignment {

	private Assignment() {
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc;
		TreeMap<Double, List> map = null;
		while (true) {

			System.out
					.println("SELECT YOUR CHOICE 1.CONSOLE 2.TEXTFILE 3.EXIT ");
			sc = new Scanner(System.in);
			try {
				switch (sc.nextInt()) {

				case 1:
					map = new TreeMap<Double, List>();
					break;

				case 2:
					File file = new File(args[0]);
					map = new TreeMap<Double, List>();
					sc = new Scanner(file);
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Please select valid input");

				}
			} catch (Exception e) {
				System.out.println("Exception message: " + e);
			}
			// sc.nextLine();
			SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(
					DefaultWeightedEdge.class);

			try {
				int vertices = sc.nextInt();
				int index = 0;

				while (index < vertices) {
					graph.addVertex(String.valueOf(index));
					index++;
				}

				int edges = sc.nextInt();
				sc.nextLine();
				String[] tokens = new String[edges];
				index = 0;
				while (index < edges) {

					tokens[index] = sc.nextLine().trim();
					String[] parts = tokens[index].split(":");
					DefaultWeightedEdge e = graph.addEdge(parts[0], parts[1]);
					graph.setEdgeWeight(e, Double.parseDouble(parts[2]));
					index++;
				}

				index = 0;

				int newEdges = sc.nextInt();
				sc.nextLine();
				String[] new_Edges = new String[newEdges];
				String[] newTokens = new String[newEdges];
				index = 0;
				while (index < newEdges) {

					new_Edges[index] = sc.nextLine().trim();
					index++;
				}

				String source = sc.nextLine();
				String destination = sc.nextLine();
				// System.out.println("Shortest path from "+source+" to "+destination);

				// Kranthi Code

				System.out.println("Shortest path from " + destination + " to "
						+ source);
				EdgeReversedGraph<String, DefaultWeightedEdge> reverseGraph = new EdgeReversedGraph<String, DefaultWeightedEdge>(
						graph);
				HashMap<String, Double> AllShortestPaths1 = Dijsktra
						.ShortestPath1(reverseGraph, destination);
				System.out.println("ALl paths" + AllShortestPaths1);

				System.out.println("Shortest path from " + source + " to "
						+ destination);
				HashMap<String, Double> AllShortestPaths = Dijsktra
						.ShortestPath(graph, source);
				System.out.println("ALL paths" + AllShortestPaths);

				// Kranthi Code

				index = 0;
				boolean flag = false;
				while (index < newEdges) {

					// boolean flag=false;
					String[] parts = new_Edges[index].split(":");
					String startEdge = parts[0];
					String endedge = parts[1];
					double newweight = Double.parseDouble(parts[2]);

					double shortestpath = AllShortestPaths.get(destination);
					double source2b = AllShortestPaths.get(startEdge);
					double source2E = AllShortestPaths.get(endedge);
					double dest2B = AllShortestPaths1.get(startEdge);
					double dest2E = AllShortestPaths1.get(endedge);

					if (source2E > source2b) {
						if (source2b + newweight < source2E) {
							if (dest2E + newweight + source2b < shortestpath) {
								flag = true;
								System.out
										.println(" New Shortest Path exists for the edge"
												+ parts[0] + ":" + parts[1]);
								System.out
										.println(" New Shortest Path exists length is "
												+ (dest2E + newweight + source2b));
								Double obj = new Double(dest2E + newweight
										+ source2b);
								if (!map.containsKey(obj)) {
									List list = new ArrayList();
									list.add(parts[0] + ":" + parts[1]);
									map.put(obj, list);
								} else {
									map.get(obj).add(parts[0] + ":" + parts[1]);
								}
								// map.put(dest2B+newweight+source2E,
								// parts[0]+":"+parts[1]);
							}
						}
					} else if (source2E > source2b) {
						if (source2E + newweight < source2b) {
							if (dest2B + newweight + source2E < shortestpath) {
								flag = true;
								System.out
										.println(" New Shortest Path exists for the edge"
												+ parts[0] + ":" + parts[1]);
								System.out
										.println(" New Shortest Path exists length is "
												+ (dest2B + newweight + source2E));
								Double obj = new Double(dest2B + newweight
										+ source2E);
								if (!map.containsKey(obj)) {
									List list = new ArrayList();
									list.add(parts[0] + ":" + parts[1]);
									map.put(obj, list);
								} else {
									map.get(obj).add(parts[0] + ":" + parts[1]);
								}
							}
						}
					}
					if (flag == false)
						System.out.println(" No Shortest Path exits");

					index++;
				}

				if (!map.isEmpty()) {
					System.out.println("Shortest of all Paths "
							+ map.firstEntry().getValue().toString());
				}
			} catch (Exception e) {
				System.out.println("Exception Message:" + e);
			}
		}
	}
}
