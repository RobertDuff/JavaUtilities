package utility.expression;

import java.util.*;
import java.util.regex.*;

/**
 * Represents a Node in an Expression Tree.
 * @author Rob
 */
public class Node
{	
	/**
	 * Represents the possible Variables.<p>
	 * Values:
	 * <ul>
	 * <li>X</li>
	 * <li>Y</li>
	 * <li>Z</li>
	 * </ul>
	 */
	public enum Var  { X, Y, Z };

	/**
	 * Value of the Variable X.
	 */
	private static Double x = new Double(0);
	
	/**
	 * Value of the Variable Y.
	 */
	private static Double y = new Double(0);
	
	/**
	 * Value of the Variable Z.
	 */
	private static Double z = new Double(0);
	
	/**
	 * Random Number Generator.
	 */
	private static Random random;

	static { random = new Random(); }

	/**
	 * Parses an Input String into a Series of Nodes.
	 * 
	 * @param input The String to Parse.
	 * @return An array of Nodes.
	 */
	public static Node[] serialize ( final String input )
	{
		String s = new String ( input );
		Token last = Token.NONE;

		Node[] seq = new Node[0];

		Matcher m;

		while ( !s.isEmpty() )
			for ( Token t : Token.values() )
			{
				m = Pattern.compile ( t.regex() ).matcher ( s );

				if ( m.lookingAt() )
				{
					if ( t == Token.UNARY_NEGATION && !last.unaryNegationCondition() )
						continue;

					Node[] nseq = new Node[ seq.length+1 ];
					System.arraycopy ( seq, 0, nseq, 0, seq.length );

					Node newNode;

					switch ( t )
					{
					case NUMBER:
						newNode = new Node.Number ( m.group() );
						break;

					case VARIABLE:
						newNode = new Node.Variable ( m.group() );
						break;

					case CONSTANT:

						if ( m.group().equalsIgnoreCase ( "PI" ) )
							newNode = new Node.Number ( Math.PI );
						else if ( m.group().equalsIgnoreCase ( "E" ) )
							newNode = new Node.Number ( Math.E );
						else
							newNode = new Node.Number ( 0 );

						break;

					default:
						newNode = new Node ( t );
						break;
					}

					nseq[ seq.length ] = newNode;
					seq = nseq;

					s = s.substring ( m.group().length() );
					last = t;
					break;
				}
			}

		return seq;
	}

	/**
	 * Assign a Variable value.
	 * 
	 * @param var The Variable to Assign.
	 * @param value The Value to Assign.
	 */
	public static void setVar ( Var var, double value )
	{
		switch ( var )
		{
		case X: x = value; break;
		case Y: y = value; break;
		case Z: z = value; break;
		}
	}

	/**
	 * The Left Child of the Node.
	 */
	protected Node   left;
	
	/**
	 * The Right Child of the Node.
	 */
	protected Node   right;
	
	/**
	 * The Token Represented by this Node.
	 */
	protected Token  token;

	/**
	 * Constructor.
	 * 
	 * @param t A Token.
	 */
	public Node ( Token t )
	{
		left  = null;
		right = null;
		token = t;
	}

	/**
	 * @return This Node's Token.
	 */
	public Token token()
	{
		return token;
	}

	/**
	 * @return The Calculated Value of this Node Tree.
	 */
	public double eval()
	{
		switch ( token )
		{
		case UNARY_NEGATION: return -( left.eval() );
		case FACTORIAL:      return factorial  ( left.eval() );
		case SQUARE_ROOT:    return Math.sqrt  ( left.eval() );
		case SINE:           return Math.sin   ( left.eval() );
		case COSINE:         return Math.cos   ( left.eval() );
		case LOGARITHM:      return Math.log10 ( left.eval() );
		case RANDOM:         return (double) random.nextInt ( (int) left.eval() );

		case ADDITION:       return left.eval() + right.eval();
		case SUBTRACTION:    return left.eval() - right.eval();
		case MULTIPLICATION: return left.eval() * right.eval();
		case DIVISION:       return left.eval() / right.eval();
		case MODULO:         return left.eval() % right.eval();
		case EXPONENTIATION: return Math.pow ( left.eval(), right.eval() );

		case CLOSE:
		case CONSTANT:
		case NONE:
		case NUMBER:
		case OPEN:
		case VARIABLE:
		default:
			break;
		}

		return Double.NaN;
	}

	/**
	 * Calculates Factorial.
	 */
	private double factorial ( double n )
	{
		return (double) factorial ( (int) n );
	}

	/**
	 * Calculates Factorial.
	 */
	private int factorial ( int n )
	{
		int f = 1;

		while ( n > 1 )
			f *= n--;

		return f;
	}

	/**
	 * Sets the Argument for a Function.
	 * @param a The argument {@code Node}
	 */
	public void setArg ( Node a )
	{
		setLeft ( a );
	}

	/**
	 * Set The Left Operand for a Binary Operator
	 * @param l The left operand {@code Node}
	 */
	public void setLeft ( Node l )
	{
		left = l;
	}

	/**
	 * Set The Right Operand for a Binary Operator
	 * @param r The right operand {@code Node}
	 */
	public void setRight ( Node r )
	{
		right = r;
	}

	/**
	 * Determines is a Node is Reducable.
	 * @return TRUE if the node is reducable.
	 */
	public boolean reducable()
	{
		switch ( token )
		{
		case UNARY_NEGATION:
		case FACTORIAL:
		case SQUARE_ROOT:
		case SINE:
		case COSINE:
		case LOGARITHM:
			return left.reducable();

		case RANDOM:
			break;

		case ADDITION:
		case SUBTRACTION:
		case MULTIPLICATION:
		case DIVISION:
		case MODULO:
		case EXPONENTIATION:
			return left.reducable() && right.reducable();
		
		case CLOSE:
		case CONSTANT:
		case NONE:
		case NUMBER:
		case OPEN:
		case VARIABLE:
		default:
			break;
		}

		return false;
	}

	public Node reduce()
	{
		switch ( token )
		{
		case UNARY_NEGATION:
		case FACTORIAL:
		case SQUARE_ROOT:
		case SINE:
		case COSINE:
		case LOGARITHM:
			setLeft ( left.reduce() );

			if ( left.reducable() )
				return new Number ( eval() );
			break;

		case RANDOM:
			setLeft ( left.reduce() );
			break;

		case ADDITION:
		case SUBTRACTION:
		case MULTIPLICATION:
		case DIVISION:
		case MODULO:
		case EXPONENTIATION:
			setLeft ( left.reduce() );
			setRight ( right.reduce() );

			if ( left.reducable() && right.reducable() )
				return new Number ( eval() );
			break;
		
		case CLOSE:
		case CONSTANT:
		case NONE:
		case NUMBER:
		case OPEN:
		case VARIABLE:
		default:
			break;
		}

		return this;
	}

	public void dump ( int level )
	{
		for ( int n = 0; n < level; n++ )
			System.out.print ( "  " );

		System.out.printf ( "%s\n", toString() );

		if ( token.isOperator() )
			if ( token.isBinaryOperator() )
			{
				left.dump  ( level+1 );
				right.dump ( level+1 ); 
			}
			else
				left.dump ( level+1 );
	}

	public String toString()
	{
		return String.format ( "<%s>", token.name() );
	}

	public static class Number extends Node
	{
		private double value;

		public Number ( String s )
		{
			this ( Double.valueOf ( s ) );
		}

		public Number ( double v )
		{
			super ( Token.NUMBER );
			value = v;
		}

		public boolean reducable()
		{
			return true;
		}

		public Node reduce()
		{
			return this;
		}

		public double eval()
		{
			return value;
		}

		public String toString()
		{
			return String.format ( "<%s,%f>", token.name(), value );
		}
	}

	public static class Variable extends Node
	{
		private Var var;

		public Variable ( String s )
		{
			super ( Token.VARIABLE );

			if ( s.matches ( "^[xX]$" ) )
				var = Var.X;
			else if ( s.matches ( "^[yY]$" ) )
				var = Var.Y;
			else
				var = Var.Z;
		}

		public Variable ( Var v )
		{
			super ( Token.VARIABLE );
			var = v;
		}

		public boolean reducable()
		{
			return false;
		}

		public Node reduce()
		{
			return this;
		}

		public double eval()
		{
			switch ( var )
			{
			case X: return x;
			case Y: return y;
			case Z: return z;
			}

			return Double.NaN;
		}

		public String toString()
		{
			return String.format ( "<%s,%s>", token.name(), var.name() );
		}
	}
}