/******************************************************************************
 *  Name:     Niles McNabb
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name: Ethan Chaiko, Benjamin Rankin      
 *  Partner NetID:      
 *  Partner Precept:    
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 5: Kd-Trees


/******************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 *****************************************************************************/
The node data type has a key of point2D a value of val(an assigned value on the xy axis)
a boolean of axisComparison(that determines which axis to compare) 
and references to the left and right subtrees.
/******************************************************************************
 *  Describe your method for range search in a kd-tree.
 *****************************************************************************/
We check if the rectangle intersects with the rectangle left, right, above or below the current node
and check the subtrees associated with each rectangle that intersects. If a node is within the rectangle 
its added to the queue.

/******************************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 *****************************************************************************/
We check if the point is less than the current node, and if it is we check its left subtree,
else we check the right subtree, and this method is called recursively until we reach the bottom
of the tree and return the closest point. We also prune subtrees from the search if it is impossible 
for the nearest point to be in the subtree's rectangle.

/******************************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Show the math how you used to determine
 *  the operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 *****************************************************************************/

                       calls to nearest() per second
                     brute force               2d-tree
                     ---------------------------------
input100K.txt           439                   1,226,993

input1M.txt             30                    567,794



/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
No known bugs or limitations.

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/
Didn't need any.

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/
None.

/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/
Niles : completed KdTreeST and test cases
Benjamin : Updated some methods for KdTreeST, worked on PointST
Ethan : Worked on PointST, readme.



/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on  how helpful the class meeting was and on how much you learned 
 * from doing the assignment, and whether you enjoyed doing it.
 *****************************************************************************/
