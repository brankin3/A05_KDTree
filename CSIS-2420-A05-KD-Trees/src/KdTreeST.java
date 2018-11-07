import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeST<Value>
{
	private static final boolean X_AXIS = true;
	private static final boolean Y_AXIS = false;
	private Node start;
	int size = 0;
	
	public KdTreeST()
	{
	}
	
	public boolean isEmpty()                      // is the symbol table empty? 
	{
		return start == null;
	}
	   
	public int size()                         // number of points 
	{
		return size;
	}
	   
	public void put(Point2D p, Value val)      // associate the value val with point p
	{
		if (start == null)
		{
			start = new Node(p, val, X_AXIS, null);
			size++;
		}
		else
		{
			put(p, val, start);
		}
	}
	
	private void put(Point2D p, Value val, Node n)
	{
		if (n.p.x() == p.x() && n.p.y() == p.y())
		{
			n.val = val;
		}
		
		else if (n.axisComparsion == X_AXIS)
		{
			if (n.p.x() > p.x())
			{
				if (n.lesser != null)
					put(p, val, n.lesser);
				else
				{
					n.lesser = new Node(p, val, Y_AXIS, n);
					size++;
				}
			}
			else 
			{
				if (n.greater != null)
					put(p, val, n.greater);
				else
				{
					n.greater = new Node(p, val, Y_AXIS, n);
					size++;
				}
			}
		}
		
		else if (n.axisComparsion == Y_AXIS)
		{
			if (n.p.y() > p.y())
			{
				if (n.lesser != null)
					put(p, val, n.lesser);
				else
				{
					n.lesser = new Node(p, val, X_AXIS, n);
					size++;
				}
			}
			else 
			{
				if (n.greater != null)
					put(p, val, n.greater);
				else
				{
					n.greater = new Node(p, val, X_AXIS, n);
					size++;
				}
			}
		}
	}
	   
	public Value get(Point2D p)                 // value associated with point p 
	{
		return get(p, start);
	}
	
	public Value get(Point2D p, Node n)
	{
		if (n == null)
		{
			return null;
		}

		if (n.p.x() == p.x() && n.p.y() == p.y())
		{
			return n.val;
		}

		if (n.axisComparsion == X_AXIS)
		{
			if (n.p.x() > p.x())
			{
				return get(p, n.lesser);
			}
			else 
			{
				return get(p, n.greater);
			}
		}
		
		if (n.axisComparsion == Y_AXIS)
		{
			if (n.p.y() > p.y())
			{
				return get(p, n.lesser);
			}
			else 
			{
				return get(p, n.greater);
			}
		}
		
		return null;
	}
	
	   
	public boolean contains(Point2D p)            // does the symbol table contain point p? 
	{
		return contains(p, start);
	}
	
	private boolean contains(Point2D p, Node n)
	{
		if (n == null)
		{
			return false;
		}

		if (n.p.x() == p.x() && n.p.y() == p.y())
		{
			return true;
		}

		if (n.axisComparsion == X_AXIS)
		{
			if (n.p.x() > p.x())
			{
				return contains(p, n.lesser);
			}
			else 
			{
				return contains(p, n.greater);
			}
		}
		
		if (n.axisComparsion == Y_AXIS)
		{
			if (n.p.y() > p.y())
			{
				return contains(p, n.lesser);
			}
			else 
			{
				return contains(p, n.greater);
			}
		}
		
		return false;
	}
	   
	public Iterable<Point2D> points()                       // all points in the symbol table 
	{
		Queue<Point2D> q = new Queue<Point2D>();
		points(start, q);
		return q;
	}
	
	private void points(Node n, Queue<Point2D> q)
	{
		if (n != null)
		{
			points(n.lesser, q);
			points(n.greater, q);
			q.enqueue(n.p);
		}
	}
	   
	public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
	{
		return null;
 	}
	   
	public Point2D nearest(Point2D p) 
	{
		return null;
	}
	
	private class Node
	{
		public Point2D p;
		public Value val;
		public boolean axisComparsion;
		public Node parent;
		public Node greater;
		public Node lesser;
		
		public Node(Point2D p, Value val, boolean b, Node par)
		{
			this.p = p;
			this.val = val;
			this.axisComparsion = b;
			this.parent = par;
		}
	}
	
	public static void main(String[] args)
	{
		KdTreeST<Integer> st = new KdTreeST<Integer>();
		st.put(new Point2D(0.0,0.0), 5);
		st.put(new Point2D(1.1,1.1), 1);
		st.put(new Point2D(-2.2,-2.2), 2);
		st.put(new Point2D(3.3,3.3), 3);
		st.put(new Point2D(3.3,3.3), 333);
		st.put(new Point2D(4.4,4.4), 4);
		
		System.out.println(st.get(new Point2D(3.3,3.3)));
		System.out.println(st.contains(new Point2D(-2.2,-2.2)));
		System.out.println(st.points());

	}
}
