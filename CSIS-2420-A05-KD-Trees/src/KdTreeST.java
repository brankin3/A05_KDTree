import java.util.Random;

import edu.princeton.cs.algs4.In;
//import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
//import edu.princeton.cs.algs4.RectHV;

public class KdTreeST<Value>
{
	private static final boolean X_AXIS = true;
	private static final boolean Y_AXIS = false;
	private Node start; //The beginning node of the Kd Tree
	int size = 0; //The current size of the Kd Tree
	
	public KdTreeST() // construct an empty Kd Tree
	{
	}
	
	 // is the symbol table empty? 
	public boolean isEmpty()                     
	{
		return start == null;
	}
	
	// number of points 
	public int size()                         
	{
		return size;
	}
	
	 // associate the value val with point p
	public void put(Point2D p, Value val)     
	{
		if (start == null) //Base case: tree is empty
		{
			start = new Node(p, val, X_AXIS); //Set start to a new node with the specified values
			size++; //Increment size
		}
		else
		{
			put(p, val, start); //Start recursion
		}
	}
	
	private void put(Point2D p, Value val, Node n)
	{
		if (n.p.x() == p.x() && n.p.y() == p.y()) //Base case: point is already in the table
		{
			n.val = val; //Update the value of the current point
		}
		
		else if (n.axisComparsion == X_AXIS) //Check if current node uses X axis 
		{
			if (n.p.x() > p.x()) //If specified point is less than the current node
			{
				if (n.lesser != null)      //If current node's lesser child is filled
					put(p, val, n.lesser); //Check lesser child subtree
				else
				{
					n.lesser = new Node(p, val, Y_AXIS); //Set lesser child to a new node with specified values
					size++; //Increment size
				}
			}
			else //Else specified point is greater than or equal to the current node
			{
				if (n.greater != null)      //If current node's greater child is filled
					put(p, val, n.greater); //Check greater child subtree
				else
				{
					n.greater = new Node(p, val, Y_AXIS); //Set greater child to a new node with specified values
					size++; //Increment size
				}
			}
		}
		
		else if (n.axisComparsion == Y_AXIS) //Check if current node uses Y axis 
		{
			if (n.p.y() > p.y()) //If specified point is less than the current node
			{
				if (n.lesser != null)      //If current node's lesser child is filled
					put(p, val, n.lesser); //Check lesser child subtree
				else
				{
					n.lesser = new Node(p, val, X_AXIS); //Set lesser child to a new node with specified values
					size++; //Increment size
				}
			}
			else //Else specified point is greater than or equal to the current node
			{
				if (n.greater != null)      //If current node's greater child is filled
					put(p, val, n.greater); //Check greater child subtree
				else
				{
					n.greater = new Node(p, val, X_AXIS); //Set greater child to a new node with specified values
					size++; //Increment size
				}
			}
		}
	}
	
	// value associated with point p 
	public Value get(Point2D p)                 
	{
		return get(p, start); //Start recursion
	}
	
	public Value get(Point2D p, Node n)
	{
		if (n == null) //Base case: no match found
		{
			return null;
		}

		if (n.p.x() == p.x() && n.p.y() == p.y()) //Base case: match found
		{
			return n.val; //return the value of the current node
		}

		if (n.axisComparsion == X_AXIS) //Check if current node uses X axis 
		{
			if (n.p.x() > p.x()) //If specified point is less than the current node
			{
				return get(p, n.lesser); //Check current node's lesser subtree 
			}
			else 
			{
				return get(p, n.greater); //Check current node's greater subtree 
			}
		}
		
		if (n.axisComparsion == Y_AXIS) //Check if current node uses Y axis 
		{
			if (n.p.y() > p.y()) //If specified point is less than the current node
			{
				return get(p, n.lesser); //Check current node's lesser subtree 
			}
			else 
			{
				return get(p, n.greater); //Check current node's greater subtree 
			}
		}
		
		return null;
	}

	private RectHV GetBox(Node target)
	{
		return GetBox(start, target, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	}
	
	private RectHV GetBox(Node n, Node target, double xmin, double ymin, double xmax, double ymax)
	{
		if (n == null || target == null)
			return null;
		
		if (n == target)
			return new RectHV(xmin, ymin, xmax, ymax);
		
		if (n.axisComparsion == X_AXIS)
		{
			if (target.p.x() < n.p.x())
				return GetBox(n.lesser, target, xmin, ymin, n.p.x(), ymax);
			else
				return GetBox(n.greater, target, n.p.x(), ymin, xmax, ymax);
		}
		
		else// if (n.axisComparsion == Y_AXIS)
		{
			if (target.p.y() < n.p.y())
				return GetBox(n.lesser, target, xmin, ymin, xmax, n.p.y());
			else
				return GetBox(n.greater, target, xmin, n.p.y(), xmax, ymax);
		}
	}
	
	// does the symbol table contain point p?   
	public boolean contains(Point2D p)            
	{
		return contains(p, start); //Start recursion
	}
	
	private boolean contains(Point2D p, Node n)
	{
		if (n == null) //Base case: no match found
		{
			return false;
		}

		if (n.p.x() == p.x() && n.p.y() == p.y()) //Base case: match found
		{
			return true;
		}

		if (n.axisComparsion == X_AXIS) //Check if current node uses X axis 
		{
			if (n.p.x() > p.x()) //If specified point is less than the current node
			{
				return contains(p, n.lesser); //Check current node's lesser subtree 
			}
			else 
			{
				return contains(p, n.greater); //Check current node's greater subtree 
			}
		}
		
		if (n.axisComparsion == Y_AXIS) //Check if current node uses Y axis 
		{
			if (n.p.y() > p.y()) //If specified point is less than the current node
			{
				return contains(p, n.lesser); //Check current node's lesser subtree 
			}
			else 
			{
				return contains(p, n.greater); //Check current node's greater subtree 
			}
		}
		
		return false;
	}
	
	// all points in the symbol table 
	public Iterable<Point2D> points()                       
	{
		Queue<Point2D> q = new Queue<Point2D>(); //Initialize queue
		points(start, q); //Start recursion
		return q; //Return queue
	}
	
	private void points(Node n, Queue<Point2D> q)
	{
		if (n != null) //Base case: current node is null
		{ 
			//Else
			points(n.lesser, q);  //Check current node's lesser subtree
			points(n.greater, q); //Check current node's greater subtree
			q.enqueue(n.p); //Add the current node's point to the queue
		}
	}
	   
	public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
	{
		Queue<Point2D> q = new Queue<Point2D>();  //Initialize queue
		range(rect, start, GetBox(start), q); //Start recursion
		return q; //Return queue
 	}
	
	private void range(RectHV rect, Node n, RectHV box, Queue<Point2D> q)             
	{
		if (n == null) //Base case: current node is null
			return;
		
		if (rect.contains(n.p))
			q.enqueue(n.p);
		
		RectHV lesser = GetBox(n, n.lesser, box.xmin(), box.ymin(), box.xmax(), box.ymax());
		if (lesser != null && lesser.intersects(rect))
			range(rect, n.lesser, lesser, q);
		
		RectHV greater = GetBox(n, n.greater, box.xmin(), box.ymin(), box.xmax(), box.ymax());
		if (greater != null && greater.intersects(rect))
			range(rect, n.greater, greater, q);
 	}

	public Point2D nearest(Point2D p)
	{
		if (start != null) //Check if the table is not empty
		{ 
			return nearest(p, start, GetBox(start), Double.NaN).p; //Start recursion
		}
		else
			return null;
	}
	
	/***
	 * Finds the Node in this branch of the tree that is closest to the specified point, checking only boxes that're within the specified squared distance.
	 * @param p The point we are trying to find the closest to.
	 * @param n The node we are currently checking
	 * @param rect The box of the node, this is for optimization
	 * @param dist The maximum distance we will use when considering checking a box. if NaN, then base it off of the specified node. This is for optimization
	 * @return The node closest to the point in this branch of the tree
	 */
	private Node nearest(Point2D p, Node n, RectHV rect, double dist)
	{
		//If we specified no distance, determine it from our node.
		if (Double.isNaN(dist))
			dist = n.p.distanceSquaredTo(p);
		
		//Our nearest node is the only one we've checked, which is N, we have not decided which we will check first.
		Node nearest = n, first = null, second = null;
		
		//Find the boxes of our two sub-points. This is normally a Log N operation, but we know we're only going down one step due to cache, so it's instead constant.
		RectHV first_rect = GetBox(n, n.lesser, rect.xmin(), rect.ymin(), rect.xmax(), rect.ymax());
		RectHV second_rect = GetBox(n, n.greater, rect.xmin(), rect.ymin(), rect.xmax(), rect.ymax());
		
		if (first_rect != null && second_rect != null)
		{
			if (first_rect.contains(p)) //If the lesser node contains the point, check the lesser one first.
			{
				first = n.lesser;
				second = n.greater;
			}
			else // Otherwise swap the order of the boxes, and check the greater one first.
			{
				RectHV tmp = first_rect;
				first_rect = second_rect;
				second_rect = tmp;
				
				first = n.greater;
				second = n.lesser;
			}
			
			//Determine which node is closer, and set that as our nearest
			nearest = check_nearest(nearest, first, first_rect, p, nearest.p.distanceSquaredTo(p));
			
			//Recalculate the distance we care about.
			dist = nearest.p.distanceSquaredTo(p);
			
			//If there's still still the possibility of a point being in the second rectangle, check it too
			if (second_rect.distanceSquaredTo(p) < dist)
			{
				nearest = check_nearest(nearest, second, second_rect, p, dist);
				dist = nearest.p.distanceSquaredTo(p);
			}
			
			//Do not let our code escape this if statement, return the result immediately.
			return nearest;
		}
		
		//Set up the nodes
		first = n.lesser;
		second = n.greater;
		
		//One or both of these nodes are null, if the first one is, move the second one up to first.
		if (first == null)
		{
			if (second != null)
			{
				first = second;
				first_rect = second_rect;
			}
		}
		
		//If we in fact have a node to check, determine which is closer and set that to the nearest.
		if (first != null && first_rect.distanceSquaredTo(p) < dist)
			nearest = check_nearest(nearest, first, first_rect, p, dist);
		
		return nearest;
	}
	
	/**
	 * Determines if the second node contains a node closer to the point than the first node.
	 * @param current The node to check against
	 * @param check The node to traverse
	 * @param rect The box of the current node, this is for optimization
	 * @param p The point to find the closer node to
	 * @param dist The maximum distance a region may be before we skip checking it, this is for optimization.
	 * @return The node which is closest to the point.
	 */
	private Node check_nearest(Node current, Node check, RectHV rect, Point2D p, double dist)
	{
		//Get the nearest node
		check = nearest(p, check, rect, dist);
		
		if (check.p.distanceSquaredTo(p) < dist) // If the node we found is closer, return it
			return check;
		
		//Or else return the one we've been told is closest.
		return current;
	}
	
//	public Point2D nearest(Point2D p) 
//	{
//		if (start != null) //Check if the table is not empty
//		{ 
//			return nearest(p, start.p, start); //Start recursion
//		}
//		else
//			return null;
//	}
//	
//	private Point2D nearest(Point2D p, Point2D near, Node n) 
//	{
//		if (n != null) //Base case: current node is null
//		{
//			if (n.axisComparsion == X_AXIS) //Check if current node uses X axis 
//			{
//				if (n.p.x() > p.x()) //If specified point is less than the current node
//				{
//					near = nearest(p, near, n.lesser); //Check lesser subtree for the nearest point
//					                                                                //If the distance to rectangle right of the current node 
//					if (Math.pow((n.p.x() - p.x()), 2) < near.distanceSquaredTo(p)) //less than distance to the current nearest point
//						near = nearest(p, near, n.greater); //Check the greater subtree for the nearest point                     
//				}                                                                                  
//				else 
//				{
//					near = nearest(p, near, n.greater); //Check the greater subtree for the nearest point      
//					                                                                //If the distance to rectangle left of the current node 
//					if (Math.pow((n.p.x() - p.x()), 2) < near.distanceSquaredTo(p)) //less than distance to the current nearest point
//						near = nearest(p, near, n.lesser); //Check the lesser subtree for the nearest point  
//				}
//			}
//			else if (n.axisComparsion == Y_AXIS) //Check if current node uses Y axis 
//			{
//				if (n.p.y() > p.y()) //If specified point is less than the current node
//				{
//					near = nearest(p, near, n.lesser); //Check lesser subtree for the nearest point
//					                                                                //If the distance to rectangle above the current node 
//					if (Math.pow((n.p.y() - p.y()), 2) < near.distanceSquaredTo(p)) //less than distance to the current nearest point
//						near = nearest(p, near, n.greater); //Check the greater subtree for the nearest point  
//				}
//				else 
//				{
//					near = nearest(p, near, n.greater); //Check the greater subtree for the nearest point  
//					                                                                //If the distance to rectangle below the current node 
//					if (Math.pow((n.p.y() - p.y()), 2) < near.distanceSquaredTo(p)) //less than distance to the current nearest point
//						near = nearest(p, near, n.lesser); //Check lesser subtree for the nearest point
//				}
//			}
//			
//			if (p.distanceSquaredTo(n.p) < p.distanceSquaredTo(near)) //Check if the distance to the current node is less than the distance to the current nearest point
//			{
//				return n.p; //Return the current node's point
//			}
//		}
//		
//		return near; //Return current nearest point
//	}
	
	//Node class to build the Kd Tree
	private class Node
	{
		public Point2D p; //The key 
		public Value val; //The assigned value
		public boolean axisComparsion; //Determines which axis to be compared
		public Node greater; //Reference to a node with a greater key value
		public Node lesser; //Reference to a node with a lesser key value
		
		public Node(Point2D p, Value val, boolean b) //Constructor
		{
			this.p = p;
			this.val = val;
			this.axisComparsion = b;
		}
	}
	
	public static void main(String[] args)
	{
		String filename = "C:\\TestDocs\\input1M.txt";
        In in = new In(filename);
        
        KdTreeST<Integer> st = new KdTreeST<Integer>();
        for (int i = 0; !in.isEmpty(); i++) 
        {
        	double x = in.readDouble();
        	double y = in.readDouble();
        	Point2D p = new Point2D(x, y);
        	st.put(p, i);
        }
        Random r = new Random();
        
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i++)
        {
        	Point2D p = new Point2D(r.nextDouble(), r.nextDouble());
        	st.nearest(p);
//        	System.out.println(st.nearest(p));
        }
        long fin = System.currentTimeMillis();
        double seconds = (fin - start)/ 1000.0;
        double calls = 5000000/seconds;
        
        System.out.println("Time: " + seconds);
        System.out.println("Calls per second: " + calls);
	}
}
