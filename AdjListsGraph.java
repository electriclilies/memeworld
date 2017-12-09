/* AdjListsGraph.java
 * AUTHORS: Lily Orth-Smith and Emma Shumadine (for lab 8)
 * MODIFIED BY: Lily Orth-Smith for Assignment 7 (mostly just added comments)
 * This class defines a graph by implementing vectors defining its vertices and arcs.
 * The user can add or remove vertices and arcs. The class can also print the graph
 * to a file using tgf format. 
 *
 */

import java.util.*;
import java.io.*;

public class AdjListsGraph<T> implements Graph<T> {
  private Vector<T> vertices;
  private Vector<LinkedList<Integer>> arcs;
   
   /** 
    * Constructor
    *
    */
   public AdjListsGraph() {
      vertices = new Vector<T>(10,10);
      arcs = new Vector<LinkedList<Integer>>(10, 10);
   }
   
   /**
    * Creates a TGF file representing the graph
    * @params fileName name of TGF file to be created
    *
    */
   
   public void saveToTGF(String fileName) {
   
      try {
      
         File f = new File(fileName);
         PrintWriter write = new PrintWriter(f);
         
         //add vertices
         for(int i=0; i<vertices.size(); i++) {
            write.println("" + (i+1) + " " + vertices.get(i));
         }
         
         write.println("#");
         
         //add edges
         for(int i=0; i<arcs.size(); i++) {
            for(int j=0; j<arcs.get(i).size(); j++) {
               write.println("" + (i+1) + " " + (j+1));
            }
         }
         
         write.close();
         
      } catch (IOException e) {
         System.out.println(e);
      }
   }
   
  /**
   * Tells you whether the graph is empty
   * @returns isEmpty true if is empty, else false
   *
   */
   
  public boolean isEmpty() {
    return vertices.size() == 0;
  }
  
  
   /**
   * Gets the number of vertices
   * @returns numVertices number of vertices in the graph
   */
   
  public int getNumVertices() {
    return vertices.size();
  }
  
   /**
   * Gets the number of arcs
   * @returns numArcs number of arcs in the graph
   */
   
  public int getNumArcs() {
    int count = 0;
    for (int i = 0; i < arcs.size(); i++) {
      /*for (int j = 0; j < arcs.get(i).size(); j++) {
        count++;
      }*/
      
      count += arcs.get(i).size();
    }
    return count;
  }
  
  /**
   * Adds a vertex to the graph
   * @param v value of the vertex you want to add
   */
  public void addVertex(T v) {
    vertices.add(v);
    arcs.add(new LinkedList<Integer>());
  }
  
   /**
   * Removes a vertex from the graph
   * @param v value of the vertex you want to remove
   */
  public void removeVertex(T v) {
    arcs.remove(vertices.indexOf(v));
    for(int i=0; i<arcs.size(); i++) {
      for(int j=0; j<arcs.get(i).size(); j++) {
        if (arcs.get(i).get(j) == vertices.indexOf(v)) {
          arcs.get(i).remove(j);
        }
      }
    }
    vertices.remove(v); //what if v is not in vertices?
  }
  
  
  /** 
   * Removes an arc from a graph
   * @param v1 starting vertex of arc to remove
   * @param v2 ending vertex of arc to remove
   */
  public void removeArc(T v1, T v2) {
    if (isArc(v1, v2)) {
      arcs.get(vertices.indexOf(v1)).remove((Integer)(vertices.indexOf(v2)));
    }
  }
  
  
  
   /** 
   * Adds an arc to graph
   * @param v1 starting vertex of arc to add
   * @param v2 ending vertex of arc to add
   */
   
  public void addArc(T v1, T v2) {
    if (vertices.contains(v1) && vertices.contains(v2)) {
      LinkedList<Integer> v1List = arcs.get(vertices.indexOf(v1));

      if(v1List.indexOf(vertices.indexOf(v2)) == -1) { //if v2 is not in v1's arc list
         arcs.get(vertices.indexOf(v1)).add(vertices.indexOf(v2));
      }

    /*
      LinkedList<Integer> v1List = arcs.get(vertices.indexOf(v1));
      System.out.println(arcs.get(vertices.indexOf(v1)));
      System.out.println(v1List);
      //DOESNT WORK
      System.out.println(v1List.indexOf(vertices.indexOf(v2)) + 1); //index of element
      System.out.println(v2);
      if (v1List.indexOf(v2) == -1) { //if arcs doesn't contain v2
          
         arcs.get(vertices.indexOf(v1)).add(vertices.indexOf(v2));

      }
      */
    }
  }

  /**
   * Gets a new vertex
   * @returns vertex at index
   */
  public T getVertex(int index) {
     return vertices.get(index);
  }
  
  
  /** 
   * Determines whether there is an edge between two vertices (edge is two way arc)
   * @param v1 starting vertex of edge to check
   * @param v2 ending vertex of dedge to check
   */
  public boolean isEdge(T v1, T v2) {
    return isArc(v1, v2) && isArc(v2, v1);
  }
   /** 
   * Determines whether there is an arc between two vertices
   * @param v1 starting vertex of arc to check
   * @param v2 ending vertex of arc to check
   */
  public boolean isArc(T v1, T v2) {
    return arcs.get(vertices.indexOf(v1)).contains(vertices.indexOf(v2));
  }
  
  
   /** 
   * Determines whether the graph is undirected
   *@returns isUndirected true if undirected, else false
   */
  public boolean isUndirected() {
    for (int i = 0; i < vertices.size(); i++) {
      for (int j = i + 1; j < vertices.size(); j++) {
        if (isArc(vertices.get(i), vertices.get(j)) && !(isEdge(vertices.get(i), vertices.get(j)))) {
          return false;
        }
      }
    }
    return true;
  }
   /** 
   * Adds an edge to the graph
   * @param v1 starting vertex of edge to add
   * @param v2 ending vertex of edge to add
   */
  public void addEdge(T v1, T v2) {
    addArc(v1, v2);
    addArc(v2, v1);
  }
  
   /** 
   * Removes an edge from the graph
   * @param v1 starting vertex of edge to remove
   * @param v2 ending vertex of edge to remove
   */
   
  public void removeEdge(T v1, T v2) {
    if (isEdge(v1, v2)) {
      removeArc(v1, v2);
      removeArc(v2, v1);
    }
  }
  
   /** 
   * Gets successors of a vertex 
   * @param v vertex whose successors you are getting
   * @returns successors a linked list containing all the predecessors of vertex v
   */
  public LinkedList<T> getSuccessors(T v) {
    LinkedList<T> successors = new LinkedList<T>();
    LinkedList<Integer> arcList = arcs.get(vertices.indexOf(v));
    for (int i = 0; i < arcList.size(); i++) {
      successors.add(vertices.get(arcList.get(i)));
    }
    return successors;
  }
  
   /** 
   * Gets predecessors of a vertex 
   * @param v vertex whose successors you are getting
   * @returns successors a linked list containing all the predecessors of vertex v
   */
  public LinkedList<T> getPredecessors(T v) {
    int vIndex = vertices.indexOf(v);
    LinkedList<T> predecessors = new LinkedList<T>();
    for(int i=0; i<arcs.size(); i++) {
      if (arcs.get(i).contains(vIndex)) {
        predecessors.add(vertices.get(i));
      }
    }
    return predecessors;
  }
   /** 
   * Returns string representation of this graph 
   * @return toString string representation of the graph
   */
  public String toString() {
    String s = "Vertices:\n" + vertices + "\n\nArcs:";
    for (int i = 0; i<arcs.size(); i++) {
      s += "\nfrom " + vertices.get(i) + ": ";
      Vector<T> storage = new Vector<T>(arcs.get(i).size());
      for (int j=0; j<arcs.get(i).size(); j++) {
        storage.add(vertices.get(arcs.get(i).get(j)));
      }
      s += storage.toString();
    }
    return s;
  }
  
   /*
   * Main
   * tests the AdjListsGraph class
   */
  public static void main(String[] args) {
  
    /* The testing code below was for Assignment 7 -- it doesn't work here because the person class isn't included in the game.
     * However, AdjListsGraph has been thouroghly tested, and does work. 
     */
     
    /*
    AdjListsGraph<Person> hi = new AdjListsGraph<Person>();
    
    Person p1 = new Person("bob", "1");
    Person p2 = new Person("sally", "2");
    Person p3 = new Person("dorothea", "3");
    //System.out.println(hi);
    //System.out.println(hi.isEmpty());
    hi.addVertex(p1);
    hi.addVertex(p2);
    hi.addVertex(p3);
    
    System.out.println("Adding arcs and edges: "); 
    System.out.println("Empty graph: \n" + hi);
    System.out.println();
    
    hi.addEdge(p1, p2);
    hi.addEdge(p2, p3);
    
    System.out.println("After adding 2 edges: \n" + hi);
    System.out.println("Is hi undirected? (should be true): " + hi.isUndirected());
    System.out.println();
    
    hi.addArc(p1, p3);
    System.out.println("After adding an arc: \n" + hi);
    System.out.println("Is hi undirected? (should be false): " + hi.isUndirected());
    System.out.println();
    
    hi.addArc(p1, p3);
    System.out.println("After adding an arc already in the graph: \n" + hi);
    
    System.out.println("Testing successors and predecessors: \n");
    System.out.println("Predecessors of p1: " + hi.getPredecessors(p1));
    System.out.println("Successors of p1: " + hi.getSuccessors(p1));
    System.out.println();
    
    hi.saveToTGF("hiToTGF.tgf");
    System.out.println("Hi was saved to tgf file at this point");
    
    System.out.println("Removing edges and arcs: \n");
    hi.removeEdge(p1, p2);
    
    System.out.println("After removing an edge: \n" + hi);
    System.out.println();
    
    hi.removeArc(p1, p3);
    System.out.println("After removing an arc: \n" + hi);
    System.out.println();
    
    System.out.println("Num vertices: " + hi.getNumVertices());
    System.out.println("Num arcs: " + hi.getNumArcs());
    System.out.println("Is arc? p1->p2 (false): " + hi.isArc(p1, p2));
    System.out.println("Is arc? p2->p3 (true): " + hi.isArc(p2, p3));
    System.out.println("Is edge? p2->p2 (true): " + hi.isArc(p2, p3));
    */
    
  }
}