import java.util.Random;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

/**
 * @author Niles McNabb, Ethan Chaiko, Benjamin Rankin
 */
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
		if (p == null || val == null)
			throw new NullPointerException();
		
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
		if (p == null)
			throw new NullPointerException();
		
		return get(p, start); //Start recursion
	}
	
	private Value get(Point2D p, Node n)
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
	
	// does the symbol table contain point p?   
	public boolean contains(Point2D p)            
	{
		if (p == null)
			throw new NullPointerException();
		
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
		if (rect == null)
			throw new NullPointerException();
		
		Queue<Point2D> q = new Queue<Point2D>();  //Initialize queue
		range(rect, start, q); //Start recursion
		return q; //Return queue
 	}
	
	private void range(RectHV rect, Node n,  Queue<Point2D> q)             
	{
		if (n != null) //Base case: current node is null
		{ 
			//Else
			if (n.axisComparsion == X_AXIS) //Check if current node uses X axis 
			{
				if (n.p.x() > rect.xmin()) //Check if specified rectangle intersects with the rectangle left of the current node
				{
					range(rect, n.lesser , q); //Check lesser subtree
				}
				if (n.p.x() <= rect.xmax()) //Check if specified rectangle intersects with the rectangle right of the current node
				{
					range(rect, n.greater, q); //Check greater subtree
				}
				
			}
			if (n.axisComparsion == Y_AXIS) //Check if current node uses Y axis 
			{
				if (n.p.y() > rect.ymin()) //Check if specified rectangle intersects with the rectangle above the current node
				{
					range(rect, n.lesser , q); //Check lesser subtree
				}
				if (n.p.y() <= rect.ymax()) //Check if specified rectangle intersects with the rectangle below the current node
				{
					range(rect, n.greater, q); //Check greater subtree
				}
			}
			if (rect.contains(n.p)) //Check if current node is within the rectangle
			{
				q.enqueue(n.p); //Add the current node's point to the queue
			}
		}
 	}
	   
	public Point2D nearest(Point2D p) 
	{
		if (p == null)
			throw new NullPointerException();
		
		if (start != null) //Check if the table is not empty
		{ 
			return nearest(p, start.p, start); //Start recursion
		}
		else
			return null;
	}
	
	public Point2D nearest(Point2D p, Point2D near, Node n) 
	{
		if (n != null) //Base case: current node is null
		{
			if (n.axisComparsion == X_AXIS) //Check if current node uses X axis 
			{
				if (n.p.x() > p.x()) //If specified point is less than the current node
				{
					near = nearest(p, near, n.lesser); //Check lesser subtree for the nearest point
					                                                                //If the distance to rectangle right of the current node 
					if (Math.pow((n.p.x() - p.x()), 2) < near.distanceSquaredTo(p)) //less than distance to the current nearest point
						near = nearest(p, near, n.greater); //Check the greater subtree for the nearest point                     
				}                                                                                  
				else 
				{
					near = nearest(p, near, n.greater); //Check the greater subtree for the nearest point      
					                                                                //If the distance to rectangle left of the current node 
					if (Math.pow((n.p.x() - p.x()), 2) < near.distanceSquaredTo(p)) //less than distance to the current nearest point
						near = nearest(p, near, n.lesser); //Check the lesser subtree for the nearest point  
				}
			}
			else if (n.axisComparsion == Y_AXIS) //Check if current node uses Y axis 
			{
				if (n.p.y() > p.y()) //If specified point is less than the current node
				{
					near = nearest(p, near, n.lesser); //Check lesser subtree for the nearest point
					                                                                //If the distance to rectangle above the current node 
					if (Math.pow((n.p.y() - p.y()), 2) < near.distanceSquaredTo(p)) //less than distance to the current nearest point
						near = nearest(p, near, n.greater); //Check the greater subtree for the nearest point  
				}
				else 
				{
					near = nearest(p, near, n.greater); //Check the greater subtree for the nearest point  
					                                                                //If the distance to rectangle below the current node 
					if (Math.pow((n.p.y() - p.y()), 2) < near.distanceSquaredTo(p)) //less than distance to the current nearest point
						near = nearest(p, near, n.lesser); //Check lesser subtree for the nearest point
				}
			}
			
			if (p.distanceSquaredTo(n.p) < p.distanceSquaredTo(near)) //Check if the distance to the current node is less than the distance to the current nearest point
			{
				return n.p; //Return the current node's point
			}
		}
		
		return near; //Return current nearest point
	}
	
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
