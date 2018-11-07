import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;

public class PointST<Value>
{
	private RedBlackBST<Point2D, Value> st;
	
	public PointST()                                // construct an empty symbol table of points 
	{
		st = new RedBlackBST<Point2D, Value>();
	}
	   
	public boolean isEmpty()                      // is the symbol table empty? 
	{
		return st.isEmpty();
	}
	   
	public int size()                         // number of points 
	{
		return st.size();
	}
	   
	public void put(Point2D p, Value val)      // associate the value val with point p
	{
		st.put(p, val);
	}
	   
	public Value get(Point2D p)                 // value associated with point p 
	{
		return st.get(p);
	}
	   
	public boolean contains(Point2D p)            // does the symbol table contain point p? 
	{
		return st.contains(p);
	}
	   
	public Iterable<Point2D> points()                       // all points in the symbol table 
	{
		return st.keys();
	}
	   
	public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
	{
		Queue<Point2D> q = new Queue<Point2D>();
		
		for (Point2D p : st.keys())
		{
			if (rect.contains(p))
			{
				q.enqueue(p);
			}
		}
		
		return q;
 	}
	   
	public Point2D nearest(Point2D p) 
	{
		Point2D nearest = null;
		Double n = null;
		
		for (Point2D s : st.keys())
		{
			if (n == null)
			{
				n = p.distanceSquaredTo(s);
				nearest = s;
			}
			
			if (n > p.distanceSquaredTo(s))
			{
				n = p.distanceSquaredTo(s);
				nearest = s;
			}
		}
		
		return nearest;
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
