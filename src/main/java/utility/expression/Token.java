package utility.expression;

/**
 * This class represents a parsing Token within an Expression.
 * 
 * @author Rob Duff
 */
public enum Token 
{
	/**
	 * An Empty Token.
	 */
	NONE ( "\\s+", false, false, -1, false ),
	
	/**
	 * A Numeric Literal.<br>
	 * Matches:
	 * <ul>
	 * <li>[0-9]+</li>
	 * <li>.[0-9]+</li>
	 * <li>[0-9]+.</li>
	 * <li>[0-9]+.[0-9]+</li>
	 * </ul>
	 */
	NUMBER ( "([0-9]+\\.[0-9]+|[0-9]+\\.|\\.[0-9]+|[0-9]+)", false, false, -1, false ),
	
	/**
	 * A Mathematically defined Constant.<br>
	 * Matches: 
	 * <ul>
	 * <li>"PI"</li>
	 * <li>"E"</li>
	 * </ul>
	 */
	CONSTANT ( "(PI|E)", false, false, -1, false ),
	
	/**
	 * A variable name.<br>
	 * Matches:
	 * <ul>
	 * <li>"X" or "x"</li>
	 * <li>"Y" or "y"</li>
	 * <li>"Z" or "z"</li>
	 * </ul> 
	 */
	VARIABLE ( "[xXyYzZ]", false, false, -1, false ),
	
	/**
	 * A Group Opening Symbol.<br>
	 * Matches:
	 * <ul>
	 * <li>Open Parenthesis "("</li>
	 * <li>Open Square Bracket "["</li>
	 * <li>Open Curly Brace "{"</li>
	 * <li>Open Angle Bracket "&lt;"</li>
	 * </ul>
	 */
	OPEN ( "[({\\[<]", false, false, -1, false ),
	
	/**
	 * A Group Closing Symbol.<br>
	 * Matches:
	 * <ul>
	 * <li>Close Parenthesis ")"</li>
	 * <li>Close Square Bracket "]"</li>
	 * <li>Close Curly Brace "}"</li>
	 * <li>Close Angle Bracket "&gt;"</li>
	 * </ul>
	 */
	CLOSE ( "[)}\\]>]", false, false, -1, false ),
	
	/**
	 * The Unary Negation operator "-"
	 */
	UNARY_NEGATION ( "[-]", true, false, 2, true ), 
	
	/**
	 * The Factorial Operator "!"
	 */
	FACTORIAL ( "[!]", true, false, 0, false ),
	
	/**
	 * The Square Root Function Name "sqrt"
	 */
	SQUARE_ROOT ( "sqrt", true, false, 0, true ), 
	
	/**
	 * The Trigonometric Sine Function "sin"
	 */
	SINE ( "sin", true, false, 0, true ),
	
	/**
	 * The Trigonometric Cosine Function "cos"
	 */
	COSINE ( "cos", true, false, 0, true ),
	
	/**
	 * The Base 10 Logarithm Function "log"
	 */
	LOGARITHM ( "log", true, false, 0, true ),
	
	/**
	 * The Random Integer Function "rand"
	 */
	RANDOM ( "rand", true, false, 0, true ),
	
	/**
	 * Tthe Binary Addition Operator "+"
	 */
	ADDITION ( "[+]", true, true, 2, true ), 
	
	/**
	 * The Binary Subtraction Operator "-"
	 */
	SUBTRACTION ( "[-]", true, true, 2, true ), 
	
	/**
	 * The Binary Multiplication Operator "*"
	 */
	MULTIPLICATION ( "[*]", true, true, 1, true ), 
	
	/**
	 * The Binary Division Operator "/"
	 */
	DIVISION ( "[/]", true, true, 1, true ), 
	
	/**
	 * The Modulo Operator "%"
	 */
	MODULO ( "[%]", true, true, 1, true ),
	
	/**
	 * The Exponentiation Operator "^"
	 */
	EXPONENTIATION ( "[\\^]", true, true, 0, false );
	
	/**
	 * The Token's Regular Expression.
	 */
	private String regex;
	
	/**
	 * Flag indicating if the Token represents an Operator
	 */
	private boolean operator;
	
	/**
	 * Flag indicating if the Token represents a Binary Operator
	 */
	private boolean binaryOperation;
	
	/**
	 * The Precedence of the Operator. Lower values are Higher Precedence.
	 */
	private int precedence;
	
	/**
	 * Flag indicating if the Binary Operator Token is Left Associative.
	 */
	private boolean leftAssociative;
	
	/**
	 * Private ENUM Constuctor
	 * 
	 * @param r A regular expression for matching this Token.
	 * @param op Flag indicating if this Token is an Operator.
	 * @param bin Flag indicating if this Token is a Binary Operator.
	 * @param p This Operator's Precedence (Lower Number is Higher Precedence).
	 * @param left Flag indicating if this Operator is Left Associative.
	 */
	private Token ( final String r, boolean op, boolean bin, int p, boolean left )
	{
		regex           = r;
		operator        = op;
		binaryOperation = bin;
		precedence      = p;
		leftAssociative = left;
	}
	
	/**
	 * @return Returns this Token's Regular Expression
	 */
	public final String regex()
	{
		return regex;
	}
	
	/**
	 * @return true if this Token is an Operator.
	 */
	public boolean isOperator()
	{
		return operator;
	}
	
	/**
	 * @return true if this Operator Token is a Binary Operator.
	 */
	public boolean isBinaryOperator()
	{
		return binaryOperation;
	}
	
	/**
	 * @return true if this Operator Token is Left Associative.
	 */
	public boolean isLeftAssociative()
	{
		return leftAssociative;
	}
	
	/**
	 * @return true if a Unary Negation Operator could follow this token.
	 */
	public boolean unaryNegationCondition()
	{
		return ( isOperator() || this == OPEN || this == NONE );
	}
	
	/**
	 * @param t Another Token
	 * @return true if both Tokens are Operators and This Token has a Higher Precedence than the Other Token.
	 */
	public boolean higherPrecedence ( Token t )
	{
		if ( !isOperator() )
			return false;
		
		if ( !t.isOperator() )
			return false;
		
		if ( isLeftAssociative() )
			return precedence <= t.precedence;
		
		return precedence < t.precedence;
	}
}
