// Assignment: kdTreeskdTrees
// Program:	   PointST
// Created:	   Oct 30, 2018
// Author:	   Ethan Chaiko
package kdTrees;

import edu.princeton.cs.algs4.BST;

public class PointST<Value> {
   
	private BST<Point2D, Value> st;
	
	public PointST() {// construct an empty symbol table of points
		st = new BST<Point2D, Value>();
   }
   public boolean isEmpty() {// is the symbol table empty?
	   return st.isEmpty();
   }
   public int size() {// number of points
	    return st.size();
   }
   public void put(Point2D p, Value val) {// associate the value val with point p
	   st.put(p, val);
   }
   public Value get(Point2D p) {// value associated with point p
	    return st.get(p);
   }
   public boolean contains(Point2D p) {// does the symbol table contain point p?
	    return st.contains(p);
   }
   public Iterable<Point2D> points(){// all points in the symbol table
	    return st.keys();
   }
   public Iterable<Point2D> range(RectHV rect) {// all points that are inside the rectangle
	    return st.keys(new Point2D(rect.xmin(), rect.ymin()), new Point2D(rect.xmax(), rect.ymax()));
   }
   public Point2D nearest(Point2D p) {// a nearest neighbor to point p; null if the symbol table is empty
	   if (isEmpty())
		   return null;
	   Point2D closest = null;
	   double dist = Double.MAX_VALUE;
	   
	   for(Point2D key : st.keys())
	   {
		   double key_distance = key.distanceSquaredTo(p);
		   if (key_distance < dist)
		   {
			   closest = key;
			   dist = key_distance;
		   }
	   }
	   
	   return closest;
   }
   public static void main(String[] args) {// unit testing of the methods (not graded)
	    
   }
}