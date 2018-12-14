package utility.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tree
{
	private Tree parent;
	private List<Tree> children;
	private Object data;
	
	public Tree()
	{
		this ( null, null );
	}
	
	public Tree ( Tree parent )
	{
		this ( parent, null );
	}
	
	public Tree ( Tree parent, Object data )
	{
		this.parent = parent;
		
		if ( this.parent != null )
			this.parent.children.add ( this );
		
		children = new LinkedList<Tree>();
		this.data = data;
	}
	
	public Tree parent()
	{
		return parent;
	}
	
	public int depth()
	{
		if ( parent == null )
			return 0;
		
		return parent.depth() + 1;
	}
	
	public List<Tree> children()
	{
		return children;
	}
	
	public void addChild ( Tree child )
	{
		child.parent = this;
		children.add ( child );
	}
	
	public void removeChild ( Tree child )
	{
		Iterator<Tree> i = children.iterator();
		
		while ( i.hasNext() )
		{
			Tree node = i.next();
			if ( node == child )
			{
				i.remove();
				child.parent = null;
				break;
			}
		}
	}
	
	public void removeChildren()
	{
		children = new LinkedList<Tree>();
	}
	
	public void data ( Object data )
	{
		this.data = data;
	}
	
	public Object data()
	{
		return data;
	}
}
