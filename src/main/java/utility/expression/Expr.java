package utility.expression;

import java.util.*;

/**
 * Class for Evaluating an Infix Arithmetic Expression.
 * @author Rob
 *
 */
public class Expr
{
	private Node root;

	public Expr ( final String expr )
	{
		Deque<Node> nodeStack = new ArrayDeque<Node>();
		nodeStack.push ( new Node ( Token.NONE ) );
		
		Deque<Node> evalStack  = new ArrayDeque<Node>();

		for ( Node node : Node.serialize ( expr ) )
		{
			if ( node.token() == Token.OPEN )
			{
				nodeStack.push ( node );
			}
			else if ( node.token() == Token.CLOSE )
			{
				while ( nodeStack.peek().token() != Token.OPEN )
					add ( evalStack, nodeStack.pop() );

				// Pop OPEN Token
				nodeStack.pop();

				while ( nodeStack.peek().token().isOperator() && !nodeStack.peek().token().isBinaryOperator() )
					add ( evalStack, nodeStack.pop() );
			}
			else if ( node.token().isOperator() )
			{
				if ( node.token().isBinaryOperator() )
				{
					while ( nodeStack.peek().token().higherPrecedence ( node.token() ) )
						add ( evalStack, nodeStack.pop() );

					nodeStack.push ( node );
				}
				// Else, Unary Operator
				else
				{
					if ( node.token().isLeftAssociative() )
					{
						nodeStack.push ( node );
					}
					// Else, Unary Right Associative
					else
					{
						add ( evalStack, node );
					}
				}
			}
			// Else, Operand
			else
			{
				add ( evalStack, node );
			}
		}

		while ( !nodeStack.isEmpty() )
			add ( evalStack, nodeStack.pop() );
		
		if ( evalStack.isEmpty() )
			throw new IllegalArgumentException ( "Invalid Expression: {" + expr + "}" );
		
		root = evalStack.pop();
	}
	
	private void add ( Deque<Node> evalStack, Node node )
	{
		if ( node.token() == Token.NONE )
			return;
		
		if ( node.token().isOperator() )
			if ( node.token().isBinaryOperator() )
			{
				Node r = evalStack.pop();
				Node l = evalStack.pop();
				
				node.setLeft  ( l );
				node.setRight ( r );
				
				evalStack.push ( node );
			}
			else
			{
				Node arg = evalStack.pop();
				
				node.setArg ( arg );
				
				evalStack.push ( node );
			}
		else
			evalStack.push ( node );
	}
	
	public void reduce()
	{
		root = root.reduce();
	}
	
	public double eval()
	{
		return root.eval();
	}
	
	public double eval ( double x )
	{
		Node.setVar ( Node.Var.X, x );
		return eval();
	}
	
	public double eval ( double x, double y )
	{
		Node.setVar ( Node.Var.Y, y );
		return eval ( x );
	}
	
	public double eval ( double x, double y, double z )
	{
		Node.setVar ( Node.Var.Z, z );
		return eval ( x, y );
	}
	
	public void dump()
	{
		root.dump ( 0 );
	}
}
